package net.caiban.pc.erp.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.domain.EverydayCond;
import net.caiban.pc.erp.domain.EverydayModel;
import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.persist.EverydayMapper;
import net.caiban.pc.erp.service.EverydayService;
import weixin.popular.bean.EventMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;

@Component("everydayService")
public class EverydayServiceImpl implements EverydayService{

	@Resource
	private EverydayMapper everydayMapper;
	
	@Override
	public XMLTextMessage save(EventMessage message) throws ServiceException{
		
		EverydayModel everyday = new EverydayModel();
		everyday.setContent(message.getContent());
		everyday.setWxOpenid(message.getFromUserName());
		everyday.setWxMsgid(message.getMsgId());
		everyday.setWxDescription(message.getDescription());
		everyday.setWxLabel(message.getLabel());
		everyday.setWxLx(message.getLocation_X()==null?null:Double.valueOf(message.getLocation_X()));
		everyday.setWxLy(message.getLocation_Y()==null?null:Double.valueOf(message.getLocation_Y()));
		everyday.setWxMediaid(message.getMediaId());
		everyday.setWxMsgtype(message.getMsgType());
		everyday.setWxPicurl(message.getPicUrl());
		everyday.setWxScale(message.getScale());
		everyday.setWxThumbMediaId(message.getThumbMediaId());
		everyday.setWxTitle(message.getTitle());
		
		List<String> tags = parseTags(everyday.getContent());
		everyday.setTags(Joiner.on(",").join(tags));
		
		everydayMapper.insertSelective(everyday);
		if(everyday.getId()==null || everyday.getId()<=0){
			throw new ServiceException("FAILURE_SAVE_EVERYDAY");
		}
		
		StringBuffer sb =new StringBuffer();
		sb.append("每1天 \n<a href='")
		.append(AppConst.getConfig("app.host"))
		.append("/f/feveryday/detail.do?id=")
		.append(everyday.getId())
		.append("' >")
		.append(everyday.getContent())
		.append("</a> \n");
		
		sb.append("<a href='").append(AppConst.getConfig("app.host")).append("/f/feveryday/index.do?wxOpenid=").append(message.getFromUserName()).append("'>查看更多</a>");
		
		return new XMLTextMessage(message.getFromUserName(), message.getToUserName(), sb.toString());
	}
	
	private List<String> parseTags(String content){
		
		if(Strings.isNullOrEmpty(content)){
			return Lists.newArrayList();
		}
		
		Pattern p=Pattern.compile("#[A-Za-z\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5]*");
		
		Matcher m=p.matcher(content);
		
		List<String> tags = Lists.newArrayList();
		while(m.find()){
			tags.add(m.group().replace("#", ""));
		}
		
		return tags;
	}
	
//	private List<String> parseUrl(String content){
//		
//		String regEx = "(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
//				+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
//				+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
//				+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
//				+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
//				+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
//				+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
//				+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*";
//		
//		Pattern p=Pattern.compile(regEx);
//		
//		Matcher m=p.matcher(content);
//		
//		List<String> urls = Lists.newArrayList();
//		while(m.find()){
//			urls.add(m.group());
//		}
//		
//		return urls;
//	}

	@Override
	public XMLTextMessage queryRecent(EventMessage message) {
		
		EverydayCond cond = new EverydayCond();
		cond.setLimit(5);
		
		StringBuffer sb = buildRespByCond(cond);
		
		sb.append("<a href='").append(AppConst.getConfig("app.host")).append("/f/feveryday/index.do'>查看更多</a>");
		
		return new XMLTextMessage(message.getFromUserName(), message.getToUserName(), sb.toString());
	}

	@Override
	public XMLTextMessage queryMy(EventMessage message) {
		
		EverydayCond cond = new EverydayCond();
		cond.setWxOpenid(message.getFromUserName());
		cond.setLimit(5);
		
		StringBuffer sb = buildRespByCond(cond);
		
		sb.append("<a href='").append(AppConst.getConfig("app.host")).append("/f/feveryday/index.do?wxOpenid=").append(message.getFromUserName()).append("'>查看更多</a>");
		
		return new XMLTextMessage(message.getFromUserName(), message.getToUserName(), sb.toString());
	}
	
	private StringBuffer buildRespByCond(EverydayCond cond){
		
		List<EverydayModel> list = everydayMapper.queryByCond(cond);
		
		StringBuffer sb = new StringBuffer();
		
		int idx = 1;
		for(EverydayModel everyday: list){
			sb.append(idx).append(". <a href='")
				.append(AppConst.getConfig("app.host"))
				.append("/f/feveryday/detail.do?id=")
				.append(everyday.getId())
				.append("' >");
			if(everyday.getContent().length()<=14){
				sb.append(everyday.getContent());
			}else{
				sb.append(everyday.getContent().substring(0, 14));
			}
				sb.append("</a> \n");
			idx++;
		}
		
		return sb;
	}
	
	public static void main(String[] args) {
		Pattern p=Pattern.compile("#[A-Za-z\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5]*"); 
		Matcher m=p.matcher("我的QQ是:456456 #我的电话是:05#32214 我的#邮箱#是:a#aa123@aaa.com");
		
		while(m.find()) { 
			System.out.println(m.group()); 
		} 
	}

	@Override
	public Pager<EverydayModel> pagerRecent(EverydayCond cond, Pager<EverydayModel> pager) throws ServiceException {
		return null;
	}

	@Override
	public EverydayModel queryById(Long id) throws ServiceException {
		return null;
	}
}
