/**
 * 
 */
package net.caiban.pc.erp.controller;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.service.sys.SysCompanyService;

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
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, Map<String, Object> out,
			Locale locale){
		
		SessionUser user = getSessionUser(request);
		if(user==null){
			return new ModelAndView("redirect:p/puser/login.do");
		}
		
		out.put("sessionUser", user);
		
		request.setAttribute("locale", locale);
		
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
