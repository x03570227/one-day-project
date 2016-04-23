/**
 * 
 */
package net.caiban.pc.erp.controller;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Strings;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.sys.SysCompany;
import net.caiban.pc.erp.service.product.ProductService;
import net.caiban.pc.erp.service.sys.SysCompanyService;
import net.caiban.pc.erp.service.sys.SysUserService;
import net.caiban.pc.erp.service.trade.TradeService;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    /**
     * 后台 ERP 首页
     *
     * @param request
     * @param out
     * @param locale
     * @return
     */
    @RequestMapping
	public ModelAndView index(HttpServletRequest request, Map<String, Object> out,
			Locale locale){
		
		SessionUser user = getSessionUser(request);
		
		if(user==null){
			return new ModelAndView("redirect:/index.do");
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

    /**
     * 失败类消息提示页面（微信）
     *
     * @param request
     * @param model
     * @param errorCode
     * @param locale
     * @return
     */
    @RequestMapping
    public ModelAndView error_wx(HttpServletRequest request,ModelMap model,
                                     String errorCode, Locale locale){

        model.put("errorCode", errorCode);
        model.put("errorMessage", messageSource.getMessage(errorCode, null, locale));
        return null;
    }

    /**
     * 成功类消息提示页面（微信）
     *
     * @param request
     * @param model
     * @param code
     * @param locale
     * @param okurl
     * @param cancelurl
     * @return
     */
    @RequestMapping
    public ModelAndView message_wx(HttpServletRequest request, ModelMap model,
                                   String code, Locale locale, String okurl, String cancelurl) {
        model.put("code", code);
        model.put("message", messageSource.getMessage(code, null, locale));
        model.put("okurl", okurl);
        model.put("cancelurl", cancelurl);
        return null;
    }
}
