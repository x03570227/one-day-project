/**
 * 
 */
package net.caiban.pc.erp.controller.p;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.erp.controller.BaseController;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.product.ProductService;
import net.caiban.pc.erp.service.trade.KdtTradeService;
import net.sf.json.JSONObject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author parox
 *
 */
@Controller
public class PKdtController extends BaseController {
	
	@Resource
	private ProductService productService;
	@Resource
	private KdtTradeService kdtTradeService;
	@Resource
	private MessageSource messageSource;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request, ModelMap model,
			Integer pid, Integer cid, String error){
		
		model.put("pid", pid);
		model.put("cid", cid);
		
		model.put("error", error);
		return null;
	}
	
	@RequestMapping
	public ModelAndView mytrade(HttpServletRequest request, ModelMap model, 
			String mobile, Long pid, Long cid, Locale locale){
		
		model.put("cid", cid);
		model.put("pid", pid);
		model.put("mobile", mobile);
		
		List<JSONObject> tradeList;
		try {
			tradeList = kdtTradeService.queryBeMarkedTrade(cid, pid, mobile);
			model.put("tradeList", tradeList);
			return null;
		} catch (ServiceException e) {
			model.put("error", e.getMessage());
		}

		return new ModelAndView("/p/pkdt/index");
	}
	
	@RequestMapping
	public ModelAndView doMark(HttpServletRequest request, ModelMap model,
			Long cid, Long pid, String tradeNum, Locale locale){
		
		model.put("cid", cid);
		model.put("pid", pid);
		
		try {
			kdtTradeService.checkTicket(cid, tradeNum);
			kdtTradeService.marksign(cid, tradeNum);
			
			model.put("success", "e.trade.marksign.success");
			
		} catch (ServiceException e) {
			model.put("failure", e.getMessage());
		}
		
		return null;
	}
}
