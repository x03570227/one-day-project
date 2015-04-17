/**
 * 
 */
package net.caiban.pc.erp.controller.p.trade;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.caiban.pc.erp.controller.BaseController;
import net.caiban.pc.erp.domain.trade.TradeFull;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.product.ProductService;
import net.caiban.pc.erp.service.trade.KdtTradeService;

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
			Integer pid, Integer cid){
		
		model.put("pid", pid);
		model.put("cid", cid);
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView mytrade(HttpServletRequest request, ModelMap model, 
			String mobile, Integer pid, Integer cid){
		
		List<TradeFull> tradeList = kdtTradeService.queryBeMarkedTrade(cid, pid, mobile);
		
		model.put("tradeList", tradeList);
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView doMark(HttpServletRequest request, ModelMap model,
			String mobile, Integer cid, String tradeNum, Locale locale){
		
		//TODO 处理订单，使状态为 DONE
		
		try {
			kdtTradeService.checkTicket(cid, tradeNum);
			kdtTradeService.marksign(cid, tradeNum);
			
			model.put("success", messageSource.getMessage("e.trade.marksign.success", null, locale));
			
		} catch (ServiceException e) {
			model.put("failure", messageSource.getMessage(e.getMessage(), null, locale));
		}
		
		return null;
	}
}
