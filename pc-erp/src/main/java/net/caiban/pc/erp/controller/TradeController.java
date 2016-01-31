/**
 * 
 */
package net.caiban.pc.erp.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.sys.SysAppCond;
import net.caiban.pc.erp.domain.trade.TradeCond;
import net.caiban.pc.erp.domain.trade.TradeFull;
import net.caiban.pc.erp.service.sys.SysAppService;
import net.caiban.pc.erp.service.trade.TradeService;
import net.caiban.utils.DateUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;

/**
 * @author mays
 * 
 */
@Controller
public class TradeController extends BaseController {
	
	@Resource
	private TradeService tradeService;
	@Resource
	private SysAppService sysAppService;

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
	
	@RequestMapping
	public ModelAndView summary(HttpServletRequest request, ModelMap model,
			TradeCond cond) throws ParseException{
		
		SessionUser user = getSessionUser(request);
		cond.setCid(user.getCid());
		
		cond.setSourceDomain(Strings.isNullOrEmpty(cond.getSourceDomain())?SysAppCond.DEFAULT_DOMAIN: cond.getSourceDomain());
		cond.setSourceType(Strings.isNullOrEmpty(cond.getSourceType())?SysAppCond.DEFAULT_TYPE:cond.getSourceType());
		model.put("cond", cond);
		
		model.put("domains", sysAppService.getDomains());
		
		Date today = DateUtil.getDate(new Date(), AppConst.DATE_FORMAT_DATE);
		model.put("today", today);
		
		model.put("tomorrow", DateUtil.getDateAfterDays(today, 1));
		
		model.put("yestoday", DateUtil.getDateAfterDays(today, -1));
		
		model.put("last3days", DateUtil.getDateAfterDays(today, -3));
		
		model.put("last7days", DateUtil.getDateAfterDays(today, -7));
		
		model.put("last30days", DateUtil.getDateAfterDays(today, -30));
		
		model.put("summaryList", tradeService.summary(cond));
		
		return null;
	}
	
	@RequestMapping
	@ResponseBody
	public Map<String, Object> doDelete(HttpServletRequest request, Long id){
		
		SessionUser user = getSessionUser(request);
		
		tradeService.doDelete(id, user.getCid());
		
		return ajaxResult(true, null);
	}
	
}
