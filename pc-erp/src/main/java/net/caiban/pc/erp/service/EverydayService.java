package net.caiban.pc.erp.service;

import java.util.List;

import net.caiban.pc.erp.domain.Everyday;
import net.caiban.pc.erp.domain.EverydayCond;
import net.caiban.pc.erp.domain.EverydayModel;
import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.exception.ServiceException;
import weixin.popular.bean.EventMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;

public interface EverydayService {
	
	public XMLTextMessage save(EventMessage message) throws ServiceException;
	
	public XMLTextMessage queryRecent(EventMessage message);
	
	public XMLTextMessage queryMy(EventMessage message) ;
	
	public Pager<EverydayModel> pagerRecent(EverydayCond cond, Pager<EverydayModel> pager) throws ServiceException;
	
	public EverydayModel queryById(Long id) throws ServiceException;
	
	public List<EverydayModel> queryTheDayByEveryday(Everyday everyday);
}
