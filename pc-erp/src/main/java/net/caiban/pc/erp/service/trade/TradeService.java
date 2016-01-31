/**
 * 
 */
package net.caiban.pc.erp.service.trade;

import java.util.List;

import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.trade.TradeCond;
import net.caiban.pc.erp.domain.trade.TradeFull;
import net.caiban.pc.erp.domain.trade.TradeSummary;


/**
 * @author mays
 *
 */
public interface TradeService {

	public Pager<TradeFull> pager(TradeCond cond, Pager<TradeFull> page);
	
	public Integer doCountToday(Long cid, Integer status);
	
	public List<TradeSummary> summary(TradeCond cond);
	
	public void doDelete(Long id, Long cid);
}
