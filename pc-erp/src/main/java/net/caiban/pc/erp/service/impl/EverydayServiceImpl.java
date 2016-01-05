package net.caiban.pc.erp.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.EverydayService;
import weixin.popular.bean.EventMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;

@Component("everydayService")
public class EverydayServiceImpl implements EverydayService{

	@Override
	public XMLTextMessage save(EventMessage message) throws ServiceException {
		
		return null;
	}
	
	private List<String> parseTags(String content){
		return null;
	}
	
	private String parseUrl(String content){
		
		return null;
	}

}
