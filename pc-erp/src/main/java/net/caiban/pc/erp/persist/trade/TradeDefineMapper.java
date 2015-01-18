/**
 * 
 */
package net.caiban.pc.erp.persist.trade;

import net.caiban.pc.erp.domain.trade.TradeDefine;

/**
 * @author mays
 *
 */
public interface TradeDefineMapper {

	public Integer updateByTradeId(TradeDefine define);
	
	public Integer insert(TradeDefine define);
	
	public TradeDefine queryByTradeId(Integer tradeId);
	
}
