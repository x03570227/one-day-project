package net.caiban.pc.erp.service;

import java.util.Date;
import java.util.List;

import net.caiban.pc.erp.domain.*;
import net.caiban.pc.erp.exception.ServiceException;
import weixin.popular.bean.EventMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;

public interface EverydayService {

    /**
     * 通过微信公众号提供的信息保存 Everyday
     *
     * @param message
     * @return
     * @throws ServiceException
     */
    public String save(EventMessage message) throws ServiceException;

    /**
     * 查询最新每1天信息,返回给微信公众号
     *
     * @param message
     * @return
     */
    public String queryRecent(EventMessage message);
	
	public String queryMy(EventMessage message) ;
	
	public Pager<EverydayModel> pagerRecent(EverydayCond cond, Pager<EverydayModel> pager) throws ServiceException;
	
	public EverydayModel queryById(Long id) throws ServiceException;
	
	public List<EverydayModel> queryTheDayByEveryday(Everyday everyday);

	public List<EverydayModel> queryTheDayByDay(Date day, String wxOpenid, Long excludeId);
	
	public String saveImage(EventMessage message) throws ServiceException;

	/**
	 * 重新构建 everyday 对象，加入最大连续天数和百分比
	 * @param everyday
	 * @return
	 */
	public EverydayModel rebuildEveryday(EverydayModel everyday);

    /**
     * 按照 subject id 查找 subject
     *
     * @param id
     * @return
     */
    public EverydaySubject querySubject(Long id);

    /**
     * 获取某主题某一天的所有 everyday 信息
     *
     * @param id
     * @param day
     * @return
     */
    public List<EverydayModel> queryBySubject(Long id, Date day);

}
