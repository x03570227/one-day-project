/**
 * 
 */
package net.caiban.pc.erp.service.trade;

import java.util.List;

import net.caiban.pc.erp.domain.trade.TradeCond;
import net.caiban.pc.erp.domain.trade.TradeDefine;
import net.caiban.pc.erp.domain.trade.TradeSummary;
import net.caiban.pc.erp.exception.ServiceException;
import net.sf.json.JSONObject;

/**
 * @author mays
 *
 */
public interface KdtTradeService {

	public JSONObject checkTicket(Integer cid, String tradeNum) throws ServiceException;
	
	public void marksign(Integer cid, String tradeNum) throws ServiceException;
	
	public TradeDefine queryDefineBytradeNum(Integer cid, String tradeNum);
	
	public JSONObject getDetails(TradeDefine define);
	
	public List<TradeSummary> summary(TradeCond cond);
	
}
