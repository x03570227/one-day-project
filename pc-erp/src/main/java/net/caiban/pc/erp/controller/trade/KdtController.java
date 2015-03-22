/**
 * 
 */
package net.caiban.pc.erp.controller.trade;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.erp.controller.BaseController;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.trade.TradeDefine;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.trade.KdtTradeService;
import net.sf.json.JSONObject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;

/**
 * @author mays
 *
 */
@Controller
public class KdtController extends BaseController {
	
	@Resource
	private KdtTradeService kdtTradeService;
	@Resource
	private MessageSource messageSource;

	@RequestMapping
	public ModelAndView ticket(HttpServletRequest request, ModelMap model,
			String error, String success) {
		
		model.put("error", error);
		
		model.put("success", success);
//		model.put("tradeNum", tradeNum);
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView ticket2(HttpServletRequest request, ModelMap model,
			String error, String success) {
		
		model.put("error", error);
		
		model.put("success", success);
//		model.put("tradeNum", tradeNum);
		
		return null;
	}
	
	@RequestMapping
	@ResponseBody
	public Map<String, Object> doMarkAndPrint(HttpServletRequest request,
			String tradeNum, Locale locale){
		
		SessionUser user = getSessionUser(request);
		String error = null;
		
		try {
			kdtTradeService.checkTicket(user.getCid(), tradeNum);
			
			kdtTradeService.marksign(user.getCid(), tradeNum);
			
			TradeDefine define = kdtTradeService.queryDefineBytradeNum(user.getCid(), tradeNum);
			
			Map<String, Object> result = Maps.newHashMap();
			result.put("message", messageSource.getMessage("e.trade.marksign.success", null, locale));
			result.put("details", define.getDetails());
			
			return ajaxResult(true, result);
			
		} catch (ServiceException e) {
			error = messageSource.getMessage(e.getMessage(), null, locale);
		}
		
		return ajaxResult(false, error);
	}
	
	@Deprecated
	@RequestMapping
	public ModelAndView doCheckTicket(HttpServletRequest request, ModelMap model,
			String tradeNum, Locale locale){
		
		SessionUser user = getSessionUser(request);
		String error = null; 
		
		try {
			JSONObject tradeJson = kdtTradeService.checkTicket(user.getCid(), tradeNum);
			model.put("trade", tradeJson);
			model.put("tradeNum", tradeNum);

			return null;
		} catch (ServiceException e) {
			error = e.getMessage();
		}
		
		model.put("error", error);
		return new ModelAndView("redirect:/trade/kdt/ticket.do");
	}
	
	@Deprecated
	@RequestMapping
	@ResponseBody
	public Map<String, Object> doMarksign(HttpServletRequest request, 
			String tradeNum, Locale locale){
		
		SessionUser user = getSessionUser(request);
		String error = null;
		try {
			kdtTradeService.marksign(user.getCid(), tradeNum);
			
			TradeDefine define = kdtTradeService.queryDefineBytradeNum(user.getCid(), tradeNum);
			
			Map<String, Object> result = Maps.newHashMap();
			result.put("message", messageSource.getMessage("e.trade.marksign.success", null, locale));
			result.put("details", define.getDetails());
			
			return ajaxResult(true, result);
			
		} catch (ServiceException e) {
			error = e.getMessage();
		}
		
		return ajaxResult(false, error);
	}
	
	@Deprecated
	@RequestMapping
	@ResponseBody
	public Map<String, Object> checkAndMarksign(HttpServletRequest request, String tradeNum, Locale locale){
		
		SessionUser user = getSessionUser(request);
		
		String error = null;
		try {
			
			kdtTradeService.checkTicket(user.getCid(), tradeNum);
			
			kdtTradeService.marksign(user.getCid(), tradeNum);
			
			TradeDefine define = kdtTradeService.queryDefineBytradeNum(user.getCid(), tradeNum);
			
			return ajaxResult(true, define);
		} catch (ServiceException e) {
			error = e.getMessage();
		}
		
		return ajaxResult(false, error);
	}
	
	@RequestMapping
	@ResponseBody
	public TradeDefine doPrint(HttpServletRequest request, ModelMap model,
			String tradeNum){
		
		SessionUser user = getSessionUser(request);
		TradeDefine define = kdtTradeService.queryDefineBytradeNum(user.getCid(), tradeNum);
		return define;
	}
}
