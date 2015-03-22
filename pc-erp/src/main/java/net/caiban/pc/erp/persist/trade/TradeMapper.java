/**
 * 
 */
package net.caiban.pc.erp.persist.trade;

import java.util.List;

import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.trade.Trade;
import net.caiban.pc.erp.domain.trade.TradeCond;
import net.caiban.pc.erp.domain.trade.TradeDefine;
import net.caiban.pc.erp.domain.trade.TradeFull;

import org.apache.ibatis.annotations.Param;

/**
 * @author mays
 *
 */
public interface TradeMapper {

	public List<Trade> pageDefault(@Param("cond") TradeCond cond, @Param("pager") Pager<TradeFull> pager);
	
	public Integer pageDefaultCount(@Param("cond") TradeCond cond);
	
	public Integer insert(Trade trade);
	
	public Integer queryIdByTradeNum(@Param("cid") Integer cid, @Param("tradeNum") String tradeNum);
	
	public Integer updateStatus(@Param("cid") Integer cid,
			@Param("tradeNum") String tradeNum, @Param("status") Integer status);
	
	public List<TradeDefine> queryDefine(@Param("cond") TradeCond cond);
}
