package net.caiban.pc.erp.service;

import net.caiban.pc.erp.exception.ServiceException;
import weixin.popular.bean.EventMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;

public interface EverydayService {
	
	public XMLTextMessage save(EventMessage message) throws ServiceException;
	
}
