/**
 * 
 */
package net.caiban.pc.erp.service.trade;

import net.caiban.pc.erp.exception.ServiceException;
import net.sf.json.JSONObject;

/**
 * @author mays
 *
 */
public interface KdtTradeService {

	public JSONObject checkTicket(Integer cid, String tradeNum) throws ServiceException;
	
	public void marksign(Integer cid, String tradeNum) throws ServiceException;
}
