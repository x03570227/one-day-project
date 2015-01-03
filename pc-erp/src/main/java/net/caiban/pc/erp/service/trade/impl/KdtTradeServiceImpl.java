/**
 * 
 */
package net.caiban.pc.erp.service.trade.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import net.caiban.pc.erp.domain.product.Product;
import net.caiban.pc.erp.domain.sys.SysApp;
import net.caiban.pc.erp.domain.trade.Trade;
import net.caiban.pc.erp.domain.trade.TradeDefine;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.persist.product.ProductMapper;
import net.caiban.pc.erp.persist.sys.SysAppMapper;
import net.caiban.pc.erp.persist.trade.TradeDefineMapper;
import net.caiban.pc.erp.persist.trade.TradeMapper;
import net.caiban.pc.erp.service.trade.KdtTradeService;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.kdt.api.KdtApiClient;

/**
 * @author mays
 *
 */
public class KdtTradeServiceImpl implements KdtTradeService {

	@Resource
	private SysAppMapper sysAppMapper;
	@Resource
	private TradeMapper tradeMapper;
	@Resource
	private TradeDefineMapper tradeDefineMapper;
	@Resource
	private ProductMapper productMapper;
	
	
	final static String SOURCE_DOMAIN="koudaitong.com";
	final static String SOURCE_TYPE="API";
	
	final static String READY_STATUS = "WAIT_BUYER_CONFIRM_GOODS";
	
	@Override
	public void checkTicket(Integer cid, String tradeNum)
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
			tradeMapper.insert(trade);
			
			TradeDefine define = new TradeDefine();
			define.setTradeId(trade.getId());
			define.setDetails(tradeJson.toString());
			tradeDefineMapper.insert(define);
		}
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
			}
		} catch (Exception e) {
			throw new ServiceException("e.sys.app.client.exception");
		}
	}

//	public static void main(String[] args) throws Exception {
//		KdtApiClient client = new KdtApiClient("f92d3321b5af77d7e2", "f74f1a49faaae58c24388af9c9697153");
//		HashMap<String, String> params = new HashMap<String, String>(); 
//		params.put("tid", "E20150103152946547091");
//		HttpResponse response = client.get("kdt.trade.get", params);
//		String respStr = EntityUtils.toString(response.getEntity());
//		JSONObject jobj = JSONObject.fromObject(respStr);
//		JSONObject trade= jobj.getJSONObject("response").getJSONObject("trade");
//		
//		System.out.println("num_iid: "+trade.getString("num_iid")+" title:"+ trade.getString("title"));
//		System.out.println(trade.getString("status"));
//	}
}
