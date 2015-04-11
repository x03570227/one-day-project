package net.caiban.pc.erp;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.annotation.Resource;

import net.caiban.pc.erp.domain.trade.Trade;
import net.caiban.pc.erp.domain.trade.TradeCond;
import net.caiban.pc.erp.persist.trade.TradeMapper;
import net.caiban.pc.erp.service.trade.TradeService;

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
public class TradeServiceTest extends BaseServiceTestCase {

	@Mock
	private TradeMapper tradeMapper;
	
	@InjectMocks
	@Resource
	private TradeService tradeService;
	
	@Before
	public void before(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveTest(){
//		TradeMapper tradeMapper = mock(TradeMapper.class);
		
//		when(cond).thenReturn(new TradeCond());
		when(tradeMapper.pageDefaultCount(Mockito.any(TradeCond.class))).thenReturn(8);
		
		Integer a = tradeService.doCountToday(1,Trade.STATUS.DONE.getKey());
		
		Assert.assertEquals(8, a.intValue());
	
	}
	
	
}
