/**
 * 
 */
package net.caiban.pc.erp.service.trade.impl;

import hirondelle.date4j.DateTime;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Nullable;
import javax.annotation.Resource;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.sys.SysApp;
import net.caiban.pc.erp.domain.sys.SysAppCond;
import net.caiban.pc.erp.domain.trade.Trade;
import net.caiban.pc.erp.domain.trade.TradeCond;
import net.caiban.pc.erp.domain.trade.TradeFull;
import net.caiban.pc.erp.domain.trade.TradeSummary;
import net.caiban.pc.erp.persist.trade.TradeMapper;
import net.caiban.pc.erp.service.trade.KdtTradeService;
import net.caiban.pc.erp.service.trade.TradeService;
import net.caiban.utils.DateUtil;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * @author mays
 *
 */
@Component("tradeService")
public class TradeServiceImpl implements TradeService {
	
	final static Logger LOG = Logger.getLogger(TradeServiceImpl.class);

	@Resource
	private TradeMapper tradeMapper;
	@Resource
	private KdtTradeService kdtTradeService;
	
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

	@Override
	public List<TradeSummary> summary(TradeCond cond) {
		
		Date today=null;
		try {
			today = DateUtil.getDate(new Date(), AppConst.DATE_FORMAT_DATE);
		} catch (ParseException e) {
			LOG.debug("Error parse gmt_created.", e);
		}
		cond.setGmtCreatedMin(cond.getGmtCreatedMin()==null?today:cond.getGmtCreatedMin());
		Date tomorrow = DateUtil.getDateAfterDays(today, 1);
		cond.setGmtCreatedMax(cond.getGmtCreatedMax()==null?tomorrow: cond.getGmtCreatedMax());
		
		cond.setStatus(Trade.STATUS.DONE.getKey());
		
		List<TradeSummary> resultList = null;
		if(SysApp.DOMAIN.KDT.toString().equals(cond.getSourceDomain())){
			resultList = kdtTradeService.summary(cond);
		}
		
		if(resultList==null){
			return null;
		}
		
		Ordering<TradeSummary> ordering = getOrdering();
		
		resultList = ordering.sortedCopy(resultList);
		
		return resultList;
	}
	
	@SuppressWarnings("rawtypes")
	private Ordering<TradeSummary> getOrdering(){
		
		return Ordering.natural().onResultOf(new Function<TradeSummary, Comparable>() {

			@Override
			@Nullable
			public	Comparable apply(TradeSummary input) {
				
				return input.getGmtCreated().getTime();
			}
			
		});
	}
	
}
