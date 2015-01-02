/**
 * 
 */
package net.caiban.pc.erp.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.trade.TradeCond;
import net.caiban.pc.erp.domain.trade.TradeFull;
import net.caiban.pc.erp.service.trade.TradeService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author mays
 * 
 */
@Controller
public class TradeController extends BaseController {
	
	@Resource
	private TradeService tradeService;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			Map<String, Object> out) {

		return null;
	}
	
	@RequestMapping
	@ResponseBody
	public Pager<TradeFull> list(HttpServletRequest request,
			Pager<TradeFull> pager, TradeCond cond) {
		
		SessionUser user = getSessionUser(request);
		cond.setCid(user.getCid());
		pager = tradeService.pager(cond, pager);
		
		return pager;
	}

}
