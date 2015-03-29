/**
 * 
 */
package net.caiban.pc.erp.service.trade.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.domain.product.Product;
import net.caiban.pc.erp.domain.sys.SysApp;
import net.caiban.pc.erp.domain.trade.Trade;
import net.caiban.pc.erp.domain.trade.TradeCond;
import net.caiban.pc.erp.domain.trade.TradeDefine;
import net.caiban.pc.erp.domain.trade.TradeSummary;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.persist.product.ProductMapper;
import net.caiban.pc.erp.persist.sys.SysAppMapper;
import net.caiban.pc.erp.persist.trade.TradeDefineMapper;
import net.caiban.pc.erp.persist.trade.TradeMapper;
import net.caiban.pc.erp.service.trade.KdtTradeService;
import net.caiban.utils.DateUtil;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kdt.api.KdtApiClient;

/**
 * @author mays
 *
 */
@Component("kdtTradeService")
public class KdtTradeServiceImpl implements KdtTradeService {

	@Resource
	private SysAppMapper sysAppMapper;
	@Resource
	private TradeMapper tradeMapper;
	@Resource
	private TradeDefineMapper tradeDefineMapper;
	@Resource
	private ProductMapper productMapper;
	
//	final static int DEFAULT_STATUS=0;
	
	final static String SOURCE_DOMAIN="koudaitong.com";
	final static String SOURCE_TYPE="API";
	
	final static String READY_STATUS = "WAIT_BUYER_CONFIRM_GOODS";
	
	@Override
	public JSONObject checkTicket(Integer cid, String tradeNum)
			throws ServiceException {
		//获取appkey throw e.sys.app.unfound
		//从appclient获取订单 throw e.trade.unfound, e.sys.app.client.exception
		//检查trade是否已存在
		//如果存在，仅更新define
		//否则 1.通过订单first商品ID，获取first_pid
		//     2.写入订单，及define
		
		SysApp app = sysAppMapper.queryByDomain(cid, SOURCE_DOMAIN);
		
		if (app == null || Strings.isNullOrEmpty(app.getAppKey())
				|| Strings.isNullOrEmpty(app.getAppSecret())) {
			throw new ServiceException("e.sys.app.unfound");
		}
		
		KdtApiClient client = null;
		try {
			client =  new KdtApiClient(app.getAppKey(), app.getAppSecret());
		} catch (Exception e) {
			throw new ServiceException("e.sys.app.client.exception");
		}
		
		JSONObject tradeJson = getResponseTrade(client, app, tradeNum);
		
		if(!READY_STATUS.equalsIgnoreCase(tradeJson.getString("status"))){
			throw new ServiceException("e.trade.status.not.ready");
		}
		
		Integer tid = tradeMapper.queryIdByTradeNum(cid, tradeNum);
		
		if(tid!=null && tid.intValue()>0){
			TradeDefine define = new TradeDefine();
			define.setTradeId(tid);
			define.setDetails(tradeJson.toString());
			tradeDefineMapper.updateByTradeId(define);
		}else{
			Product product = productMapper.queryOneByCode(cid, tradeJson.getString("num_iid"));
			if(product ==null){
				throw new ServiceException("e.product.code.unfound");
			}
			
			Trade trade = new Trade();
			trade.setCid(cid);
			trade.setPidFirst(product.getId());
			trade.setSourceDomain(SOURCE_DOMAIN);
			trade.setSourceType(SOURCE_TYPE);
			trade.setTradeNum(tradeNum);
			trade.setStatus(Trade.STATUS.DEFAULT.getKey());
			tradeMapper.insert(trade);
			
			TradeDefine define = new TradeDefine();
			define.setTradeId(trade.getId());
			define.setDetails(tradeJson.toString());
			tradeDefineMapper.insert(define);
		}
		return tradeJson;
	}
	
	private JSONObject getResponseTrade(KdtApiClient client, SysApp app, String tradeNum) throws ServiceException {
		
		HashMap<String, String> params = Maps.newHashMap();
		params.put("tid", tradeNum);
		
		try {
			HttpResponse response = client.get("kdt.trade.get", params);
			String respString = EntityUtils.toString(response.getEntity());
			JSONObject jobj = JSONObject.fromObject(respString);
			
			return jobj.getJSONObject("response").getJSONObject("trade");
		} catch (Exception e) {
			throw new ServiceException("e.sys.app.client.exception");
		}
		
	}
	
	@Override
	public void marksign(Integer cid, String tradeNum) throws ServiceException {
		SysApp app = sysAppMapper.queryByDomain(cid, SOURCE_DOMAIN);
		
		if (app == null || Strings.isNullOrEmpty(app.getAppKey())
				|| Strings.isNullOrEmpty(app.getAppSecret())) {
			throw new ServiceException("e.sys.app.unfound");
		}
		
		KdtApiClient client = null;
		try {
			client =  new KdtApiClient(app.getAppKey(), app.getAppSecret());
		} catch (Exception e) {
			throw new ServiceException("e.sys.app.client.exception");
		}
		
		HashMap<String, String> params = Maps.newHashMap();
		params.put("tid", tradeNum);
		
		try {
			HttpResponse response = client.get("kdt.logistics.online.marksign", params);
			String respString = EntityUtils.toString(response.getEntity());
			JSONObject jobj = JSONObject.fromObject(respString);
			
			Boolean result = jobj.getJSONObject("response").getBoolean("is_success");
			if(!result){
				throw new ServiceException("e.trade.marksign.failure");
			}else{
				tradeMapper.updateStatus(cid, tradeNum, Trade.STATUS.DONE.getKey());
			}
		} catch (Exception e) {
			throw new ServiceException("e.sys.app.client.exception");
		}
	}

	@Override
	public TradeDefine queryDefineBytradeNum(Integer cid, String tradeNum) {
		
		if(Strings.isNullOrEmpty(tradeNum)){
			return null;
		}
		
		Integer tradeId = tradeMapper.queryIdByTradeNum(cid, tradeNum);
		
		return tradeDefineMapper.queryByTradeId(tradeId);
	}

	@Override
	public JSONObject getDetails(TradeDefine define) {
		if(define==null || Strings.isNullOrEmpty(define.getDetails())){
			return null;
		}
		if(!define.getDetails().startsWith("{")){
			return null;
		}
		return JSONObject.fromObject(define.getDetails());
	}
	
//	public static void main(String[] args) throws Exception {
//		KdtApiClient client = new KdtApiClient("f92d3321b5af77d7e2", "f74f1a49faaae58c24388af9c9697153");
//		HashMap<String, String> params = new HashMap<String, String>(); 
//		params.put("tid", "E20150314150055756820");
//		HttpResponse response = client.get("kdt.trade.get", params);
//		String respStr = EntityUtils.toString(response.getEntity());
//		JSONObject jobj = JSONObject.fromObject(respStr);
//		JSONObject trade= jobj.getJSONObject("response").getJSONObject("trade");
//		
//		System.out.println("num_iid: "+trade.getString("num_iid")+" title:"+ trade.getString("title"));
//		System.out.println(trade.getString("status"));
//	}

	@Override
	public List<TradeSummary> summary(TradeCond cond) {
		
		cond.setIdMax(0);
		
		Map<Long, TradeSummary> summaryMap = new HashMap<Long, TradeSummary>();
		
		List<TradeDefine> defineList = null;
		TradeSummary summaryAll = new TradeSummary();
		summaryAll.setNum(0);
		summaryAll.setTotalFee(new BigDecimal("0"));
		do {
			
			defineList = tradeMapper.queryDefine(cond);
			for(TradeDefine define: defineList){
				
				cond.setIdMax(define.getTradeId());
				
				Date day=null;
				try {
					day = DateUtil.getDate(define.getGmtCreated(), AppConst.DATE_FORMAT_DATE);
				} catch (ParseException e) {
				}
				if(day==null){
					continue;
				}
				
				TradeSummary summary = summaryMap.get(day.getTime());
				if(summary==null){
					summary = new TradeSummary();
					summary.setGmtCreated(day);
					summary.setGmtScan(day);
					summary.setNum(0);
					summary.setTotalFee(new BigDecimal("0"));
					summaryMap.put(day.getTime(), summary);
				}
				if(!JSONUtils.mayBeJSON(define.getDetails())){
					continue ;
				}
				JSONObject kdtObj = JSONObject.fromObject(define.getDetails());
				
				summary.setNum(summary.getNum()+kdtObj.optInt("num", 0));
				summary.setTotalFee(summary.getTotalFee().add(new BigDecimal(kdtObj.optString("total_fee", "0"))));
				
				summaryAll.setNum(summaryAll.getNum()+kdtObj.optInt("num", 0));
				summaryAll.setTotalFee(summaryAll.getTotalFee().add(new BigDecimal(kdtObj.optString("total_fee", "0"))));
				
				
			}
			
		} while (defineList!=null && defineList.size()>0);
		
		List<TradeSummary> resultList = Lists.newArrayList();
		for(Long key: summaryMap.keySet()){
			resultList.add(summaryMap.get(key));
		}
		
		resultList.add(summaryAll);
		
		return resultList;
	}
}
