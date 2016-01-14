package net.caiban.pc.erp.service.impl;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.domain.RedisKeyEnum;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.EverydayService;
import net.caiban.pc.erp.service.WeixinService;
import net.caiban.utils.cache.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import weixin.popular.bean.EventMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;
import weixin.popular.util.XMLConverUtil;

@Component("weixinService")
public class WeixinServiceImpl implements WeixinService {
	
	Logger LOG = LoggerFactory.getLogger(WeixinServiceImpl.class);
	
	@Resource
	private EverydayService everydayService;
	
	static enum MESSAGE_CMD{
		//帮助命令
		HELP("帮助,help,h"),
//		回复命令（回复某 ID 的MSG）
		REPLAY("评论,回复,replay,r"),
//		查看命令（查看别人的需要，查看自己的帮助）
		SHOW("查看,show,s,list,ls,l,1"),
//		我发布的信息
		MY("我的,my,m,11")
		;
		
		private String cmds;
		
		private MESSAGE_CMD(String cmds){
			this.cmds = cmds;
		}
		
		public boolean isCmd(String cmd){
			
			if(Strings.isNullOrEmpty(cmd)){
				return false;
			}
			
			List<String> cmdList = Splitter.on(",").splitToList(cmds);
			for(String c:cmdList){
				if(c.equalsIgnoreCase(cmd)){
					return true;
				}
			}
			
			return false;
		}
	}
	
	static enum MESSAGE_TYPE{
		event,
		text,
		image,
		voice,
		video,
		shortvideo,
		location,
		link;
	}
	
	static enum EVENT_TYPE{
		subscribe, unsubscribe,
		LOCATION,
		CLICK,
		VIEW;
	}

	@Override
	public boolean validSign(String signature, String timestamp, String nonce) {
		
		Preconditions.checkNotNull(signature);
		Preconditions.checkNotNull(timestamp);
		Preconditions.checkNotNull(nonce);
		
		List<String> params = Lists.newArrayList();
		params.add(timestamp);
		params.add(nonce);
		params.add(AppConst.getConfig("weixin.token", ""));
		
		LOG.info("REQUEST FROM WEIXIN: "+new Gson().toJson(params)+" sign is:"+signature);
		
		Collections.sort(params);
		
		LOG.info("RESORT PARAMS: "+new Gson().toJson(params));
		
		String paramsStr = Joiner.on("").join(params);
		
		String genSign = DigestUtils.shaHex(paramsStr);
		
		LOG.info("GENERATE SIGN: "+genSign);
		
		return genSign.equals(signature);
	}

	@Override
	public String autoResp(InputStream is) {
		
		Preconditions.checkNotNull(is);
		
		EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, is);
		
		//TODO 重复通知处理
		LOG.info("REQUEST MESSAGE:"+new Gson().toJson(eventMessage));
		
		if(duplicateMsgid(eventMessage.getMsgId())){
			LOG.debug("DUPLICATE MESSAGE ID:"+eventMessage.getMsgId());
			return null;
		}
		
		if(MESSAGE_TYPE.event.name().equalsIgnoreCase(eventMessage.getMsgType())){
			if(EVENT_TYPE.subscribe.name().equalsIgnoreCase(eventMessage.getEvent())){
				//订阅后的回复
				return buildXmlTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), "发送 1 试试看");
			}
		}
		
		if(MESSAGE_TYPE.text.name().equalsIgnoreCase(eventMessage.getMsgType())){
			
			if(MESSAGE_CMD.HELP.isCmd(eventMessage.getContent())){
				//TODO 返回命令说明
				LOG.info("RESPONSE HELP CMD.");
				return "";
			}
			
			if(MESSAGE_CMD.REPLAY.isCmd(eventMessage.getContent())){
				LOG.info("RESPONSE REPLAY CMD.");
				//TODO 回复命令，需要获取回复对象 ID 等信息
				return "";
			}
			
			if(MESSAGE_CMD.SHOW.isCmd(eventMessage.getContent())){
				LOG.info("RESPONSE SHOW CMD.");
				return everydayService.queryRecent(eventMessage).toXML();
			}
			
			if(MESSAGE_CMD.MY.isCmd(eventMessage.getContent())){
				LOG.info("RESPONSE MY CMD.");
				//拉取自己发布信息命令
				return everydayService.queryMy(eventMessage).toXML();
			}
			
			//保存信息
			try {
				XMLTextMessage resultmsg = everydayService.save(eventMessage);
				return resultmsg.toXML();
			} catch (ServiceException e) {
				LOG.error("FAILURE SAVE TEXT MESSAGE:"+e.getMessage());
			}
			
			return null;
		}
		
		//TODO 为用户保存信息，需要分享标签等信息
		
//		return buildXmlTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), "暂无法处理这类信息");
		return null;
	}
	
	private boolean duplicateMsgid(String msgid){
		
		if(Strings.isNullOrEmpty(msgid)){
			return true;
		}
		
		Jedis jedis = null;
		
		try {
			jedis = JedisUtil.getPool().getResource();
			
			jedis.watch(RedisKeyEnum.WX_MSGID.getKey(msgid));
			
			Transaction tx = jedis.multi();
			tx.exists(RedisKeyEnum.WX_MSGID.getKey(msgid));
			tx.setex(RedisKeyEnum.WX_MSGID.getKey(msgid), 60, msgid);
			List<Object> result = tx.exec();
			
			if(result==null){
				return true;
			}
			
			Boolean existed = (Boolean) result.get(0);
			return existed;
		} finally {
			if(jedis!=null){
				jedis.close();
			}
		}
		
	}
	
	private String buildXmlTextMessage(String fromUserName, String toUserName, String content){
		XMLTextMessage xmlTextMessage = new XMLTextMessage(fromUserName, toUserName, content);
		return xmlTextMessage.toXML();
	}
	
//	public static void main(String[] args) {
//		JedisUtil.initPool(null);
//		System.out.println(duplicateMsgid("k2"));
//	}

}
