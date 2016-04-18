package net.caiban.pc.erp.service.impl;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.domain.RedisKeyEnum;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.sys.SysUser;
import net.caiban.pc.erp.domain.sys.SysUserAuthModel;
import net.caiban.pc.erp.domain.sys.SysUserProfileModel;
import net.caiban.pc.erp.enums.UserClassifyEnum;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.EverydayService;
import net.caiban.pc.erp.service.WeixinService;
import net.caiban.pc.erp.service.sys.SysUserService;
import net.caiban.utils.cache.JedisUtil;
import net.caiban.utils.http.HttpRequestUtil;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.EventMessage;
import weixin.popular.bean.Token;
import weixin.popular.bean.xmlmessage.XMLTextMessage;
import weixin.popular.util.XMLConverUtil;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component("weixinService")
public class WeixinServiceImpl implements WeixinService {
	
	Logger LOG = LoggerFactory.getLogger(WeixinServiceImpl.class);
	
	@Resource
	private EverydayService everydayService;
	@Resource
	private SysUserService sysUserService;

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

            cmd=cmd.trim();
            cmd=cmd.replace("\n", "");
			
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
				return buildXmlTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), "每1天，1件事，记1笔 \n发送 1 试试看");
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
			
			//保存文本信息
			try {
				XMLTextMessage resultmsg = everydayService.save(eventMessage);
				return resultmsg.toXML();
			} catch (ServiceException e) {
				LOG.error("FAILURE SAVE TEXT MESSAGE:"+e.getMessage());
			}
			
			return null;
		}
		
		if(MESSAGE_TYPE.image.name().equalsIgnoreCase(eventMessage.getMsgType())){
			try {
				//保存图片
				String imgPath = everydayService.saveImage(eventMessage);
				eventMessage.setPicUrl(Strings.isNullOrEmpty(imgPath)?eventMessage.getPicUrl():imgPath);
				XMLTextMessage resultmsg = everydayService.save(eventMessage);
				return resultmsg.toXML();
			} catch (ServiceException e) {
				LOG.error("FAILURE SAVE TEXT MESSAGE:"+e.getMessage());
			}
		}
		
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

	@Override
	public String genAccessToken() throws ServiceException {
		
		//XXX 并发特别大的情况下可能会出现actoken 失效与获取之间时间差导致多次获取 access token 问题
		Jedis jedis = null;
		
		String actoken = null;
		try {
			jedis = JedisUtil.getPool().getResource();
			actoken=jedis.get(RedisKeyEnum.ACCESSTOKEN.getKey("WEIXIN"));
			
			if(Strings.isNullOrEmpty(actoken)){
				Token token = TokenAPI.token(AppConst.getConfig("weixin.gzh.app.id"), AppConst.getConfig("weixin.gzh.app.secret"));
				
				if(token==null){
					LOG.error("FAILURED GET ACCESS TOKEN: token is null");
					throw new ServiceException("FAILURED GET ACCESS TOKEN:"+new Gson().toJson(token));
				}
				
				actoken = token.getAccess_token();
				
				if(Strings.isNullOrEmpty(actoken)){
					LOG.error("FAILURED GET ACCESS TOKEN:"+new Gson().toJson(token));
					throw new ServiceException("FAILURED GET ACCESS TOKEN:"+new Gson().toJson(token));
				}
				
				jedis.setex(RedisKeyEnum.ACCESSTOKEN.getKey("WEIXIN"), token.getExpires_in()-60, actoken);
			}
		} finally {
			if(jedis!=null){
				jedis.close();
			}
		}
		
		return actoken;
	}

	@Override
	public String remoteAccessToken() throws ServiceException {
		String remoteResult = HttpRequestUtil.httpGet(AppConst.getConfig("app.host.inter")+"/api/genWxActoken.do");
		if(!JSONUtils.mayBeJSON(remoteResult)){
			throw new ServiceException("REMOTE_ACCESS_FAILURE");
		}
		JSONObject jobj = JSONObject.fromObject(remoteResult);
		Boolean result = jobj.optBoolean("result");
		if(!result){
			throw new ServiceException(jobj.optString("data"));
		}
		return jobj.optString("data");
	}

	@Override
	public SessionUser oauth(String code) throws ServiceException {

		SysUserAuthModel userAuth = remoteOauthAccessToken(code);

		SysUserProfileModel profile = remoteOauthProfile(userAuth.getAccessToken(), userAuth.getOpenid());

		return sysUserService.doRegistByOauth(userAuth, profile);

	}

	private SysUserProfileModel remoteOauthProfile(String accessToken, String openid) throws ServiceException{

		//ACCESS_TOKEN&openid=OPENID&lang=zh_CN
		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/sns/userinfo?access_token=")
			.append(accessToken)
			.append("&openid=").append(openid)
			.append("&lang=zh_CN");

		String resp = HttpRequestUtil.httpGet(url.toString());

		if (!JSONUtils.mayBeJSON(resp)) {
			LOG.error("Invalid response: " + resp);
			throw new ServiceException("INVALID_RESPONSE");
		}

		JSONObject jobj = JSONObject.fromObject(resp);
		if (jobj.has("errcode")) {
			LOG.error("Error access oauth token, response is: " + resp);
			throw new ServiceException("FAILURE_GET_ACCESS_TOKEN");
		}

		SysUserProfileModel profile = new SysUserProfileModel();
		profile.setOpenid(openid);
		profile.setNickname(jobj.optString("nickname"));
		profile.setSex(jobj.optString("sex"));
		profile.setProvince(jobj.optString("province"));
		profile.setCity(jobj.optString("city"));
		profile.setCountry(jobj.optString("country"));
		profile.setHeadimgurl(jobj.optString("headimgurl"));
		profile.setUnionid(jobj.optString("unionid"));
		profile.setPrivilege(jobj.optString("privilege"));

		return profile;

	}

	private SysUserAuthModel remoteOauthAccessToken(String code) throws ServiceException{

		if(Strings.isNullOrEmpty(code)){
			throw new ServiceException("AUTH_DENIED");
		}

//		https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code

		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
				.append(AppConst.getConfig("weixin.web.app.id"))
				.append("&secret=").append(AppConst.getConfig("weixin.web.app.secret"))
				.append("&code=").append(code)
				.append("&grant_type=authorization_code");

		LOG.info("Get oauth access token from: "+url.toString());

		String resp = HttpRequestUtil.httpGet(url.toString());

		return buildAuthFromResp(resp);

	}

	private SysUserAuthModel buildAuthFromResp(String resp) throws ServiceException {
		if (!JSONUtils.mayBeJSON(resp)) {
			LOG.error("Invalid oauth response: " + resp);
			throw new ServiceException("INVALID_RESPONSE");
		}

		JSONObject jobj = JSONObject.fromObject(resp);
		if (jobj.has("errcode")) {
			LOG.error("Error access oauth token, response is: " + resp);
			throw new ServiceException("FAILURE_GET_ACCESS_TOKEN");
		}

		SysUserAuthModel userAuth = new SysUserAuthModel();
		userAuth.setClassify(UserClassifyEnum.WEIXIN_OAUTH.getCode());
		userAuth.setOpenid(jobj.optString("openid"));
		userAuth.setAccessToken(jobj.optString("access_token"));
		userAuth.setRefreshToken(jobj.optString("refresh_token"));
		userAuth.setExpiresIn(jobj.optInt("expires_in"));
		userAuth.setScope(jobj.optString("scope"));
		userAuth.setUnionid(jobj.optString("unionid"));

		userAuth.setGmtAuth(new Date());
		Date gmtExpires= new Date(userAuth.getGmtAuth().getTime()+(userAuth.getExpiresIn()*1000l));
		userAuth.setGmtExpires(gmtExpires);

		return userAuth;
	}

}
