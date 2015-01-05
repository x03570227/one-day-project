/**
 * 
 */
package net.caiban.pc.erp.service.trade.impl;

import hirondelle.date4j.DateTime;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

	@Override
	public Integer doCountToday(Integer cid, Integer status) {
		
		TradeCond cond = new TradeCond();
		cond.setCid(cid);
		cond.setStatus(status);
		DateTime dt = DateTime.today(TimeZone.getDefault());
		cond.setGmtCreatedMin(new Date(dt.getMilliseconds(TimeZone.getDefault())));
		cond.setGmtCreatedMax(new Date(dt.plusDays(1).getMilliseconds(TimeZone.getDefault())));
		
		return tradeMapper.pageDefaultCount(cond);
	}
	
}
