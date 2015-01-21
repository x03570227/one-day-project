/**
 * 
 */
package net.caiban.pc.erp.controller.trade;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.erp.controller.BaseController;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.trade.TradeDefine;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.trade.KdtTradeService;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author mays
 *
 */
@Controller
public class KdtController extends BaseController {
	
	@Resource
	private KdtTradeService kdtTradeService;

	@RequestMapping
	public ModelAndView ticket(HttpServletRequest request, ModelMap model,
			String error, String success) {
		
		model.put("error", error);
		
		model.put("success", success);
//		model.put("tradeNum", tradeNum);
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView doCheckTicket(HttpServletRequest request, ModelMap model,
			String tradeNum, Locale locale){
		//TODO 检查订单情况
		//1. 从口袋通获取订单
		//2. 写入或更新本地订单信息（特别注意状态）
		//3. 根据状态返回信息
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
	
	@RequestMapping
	public ModelAndView doMarksign(HttpServletRequest request, ModelMap model, 
			String tradeNum){
		SessionUser user = getSessionUser(request);
		try {
			kdtTradeService.marksign(user.getCid(), tradeNum);
			//TODO 跳转到打印页面
			model.put("tradeNum", tradeNum);
			model.put("success", "e.trade.marksign.success");
		} catch (ServiceException e) {
			model.put("error", e.getMessage());
		}
		
		return new ModelAndView("redirect:/trade/kdt/ticket.do");
	}
	
	@RequestMapping
	public ModelAndView print(HttpServletRequest request, ModelMap model,
			String tradeNum, Integer preview){
		
		
		model.put("preview", preview);
		
		SessionUser user = getSessionUser(request);
		TradeDefine define = kdtTradeService.queryDefineBytradeNum(user.getCid(), tradeNum);
		model.put("define", define);
		model.put("details", kdtTradeService.getDetails(define));
		return null;
	}
}
