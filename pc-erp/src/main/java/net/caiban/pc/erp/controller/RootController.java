/**
 * 
 */
package net.caiban.pc.erp.controller;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.sys.SysCompany;
import net.caiban.pc.erp.service.product.ProductService;
import net.caiban.pc.erp.service.sys.SysCompanyService;
import net.caiban.pc.erp.service.sys.SysUserService;
import net.caiban.pc.erp.service.trade.TradeService;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author mays
 *
 */
@Controller
public class RootController extends BaseController{

	
	@Resource
	MessageSource messageSource;
	@Resource
	private SysCompanyService sysCompanyService;
	@Resource
	private ProductService productService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private TradeService tradeService;
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, Map<String, Object> out,
			Locale locale){
		
		SessionUser user = getSessionUser(request);
		if(user==null){
			return new ModelAndView("redirect:p/puser/login.do");
		}
		
		out.put("sessionUser", user);
		
		request.setAttribute("locale", locale);
		
		SysCompany company = sysCompanyService.queryOne(user.getCid());
		out.put("company", company);
		
		out.put("productCount", productService.countProduct(user.getCid()));
		
		out.put("accountCount", sysUserService.doCountUserOfCompany(user.getCid(), false));
		
		out.put("tradeTodayCount", tradeService.doCountToday(user.getCid(), null));
		return null;
	}
	
	@RequestMapping
	public ModelAndView indexMobel(HttpServletRequest request, Map<String, Object> out){
	
		return null;
	}
	
	@RequestMapping
	public ModelAndView test(HttpServletRequest request, Map<String, Object> out){
	
		return null;
	}
	
}
