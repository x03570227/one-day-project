/**
 * 
 */
package net.caiban.pc.erp.service.trade;

import net.caiban.pc.erp.exception.ServiceException;

/**
 * @author mays
 *
 */
public interface KdtTradeService {

	public void checkTicket(Integer cid, String tradeNum) throws ServiceException;
	
	public void marksign(Integer cid, String tradeNum) throws ServiceException;
}
