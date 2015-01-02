/**
 * 
 */
package net.caiban.pc.erp.service.trade.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.trade.Trade;
import net.caiban.pc.erp.domain.trade.TradeCond;
import net.caiban.pc.erp.domain.trade.TradeFull;
import net.caiban.pc.erp.persist.trade.TradeMapper;
import net.caiban.pc.erp.service.trade.TradeService;

/**
 * @author mays
 *
 */
@Component("tradeService")
public class TradeServiceImpl implements TradeService {

	@Resource
	private TradeMapper tradeMapper;
	
	@Override
	public Pager<TradeFull> pager(TradeCond cond, Pager<TradeFull> page) {
		
		page.setTotals(tradeMapper.pageDefaultCount(cond));
		
		List<Trade> list = tradeMapper.pageDefault(cond, page);
		
		List<TradeFull> result = Lists.newArrayList();
		for(Trade trade: list){
			TradeFull full = new TradeFull();
			full.setTrade(trade);
			result.add(full);
		}
		
		page.setRecords(result);
		return page;
	}

}
