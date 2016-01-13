package net.caiban.pc.erp.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.caiban.pc.erp.domain.Everyday;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.EverydayService;
import weixin.popular.bean.EventMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;

@Component("everydayService")
public class EverydayServiceImpl implements EverydayService{

	@Override
	public XMLTextMessage save(EventMessage message) throws ServiceException {
		
		//解析 tags
		//XXX 解析 url
		
		Everyday everyday = new Everyday();
		everyday.setContent(message.getContent());
		everyday.setWxOpenid(message.getFromUserName());
		everyday.setWxMsgid(message.getMsgId());
		
		List<String> tags = parseTags(everyday.getContent());
		everyday.setTags(Joiner.on(",").join(tags));
		
//		everyday.setWxOpenid();
		
		
		return null;
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
	
	public static void main(String[] args) {
		Pattern p=Pattern.compile("#[A-Za-z\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5]*"); 
		Matcher m=p.matcher("我的QQ是:456456 #我的电话是:05#32214 我的#邮箱#是:a#aa123@aaa.com");
		
		while(m.find()) { 
		     System.out.println(m.group()); 
		} 
	}

}
