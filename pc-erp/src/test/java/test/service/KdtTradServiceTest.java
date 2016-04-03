/**
 * 
 */
package test.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import test.BaseServiceTestCase;
import net.caiban.pc.erp.domain.sys.SysApp;
import net.caiban.pc.erp.domain.trade.Trade;
import net.caiban.pc.erp.domain.trade.TradeCond;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.persist.sys.SysAppMapper;
import net.caiban.pc.erp.persist.trade.TradeMapper;
import net.caiban.pc.erp.service.trade.KdtTradeService;
import net.sf.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author parox
 *
 */
public class KdtTradServiceTest extends BaseServiceTestCase {

	@InjectMocks
	@Resource
	private KdtTradeService kdtTradeService;
	
	@Mock
	private TradeMapper tradeMapper;
	@Mock
	private SysAppMapper sysAppMapper;
	
	@Before
	public void before(){
		MockitoAnnotations.initMocks(this);
	}

//	@Test
//	public void test_queryBeMarkedTrade(){
//		Mockito.when(tradeMapper.pageByCond(Mockito.any(TradeCond.class))).thenReturn(new ArrayList<Trade>());
//
//		SysApp sysApp = new SysApp();
//		sysApp.setAppKey("f92d3321b5af77d7e2");
//		sysApp.setAppSecret("f74f1a49faaae58c24388af9c9697153");
//		Mockito.when(sysAppMapper.queryByDomain(Mockito.anyLong(), Mockito.anyString())).thenReturn(sysApp);
//
//		try {
//			List<JSONObject> result = kdtTradeService.queryBeMarkedTrade(1l, 47l, "13958918221");
//			Assert.assertTrue(result.size()>0);
//		} catch (ServiceException e) {
//			Assert.fail();
//		}
//	}
}
