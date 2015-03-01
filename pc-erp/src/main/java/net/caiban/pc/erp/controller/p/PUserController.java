/**
 * 
 */
package net.caiban.pc.erp.controller.p;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.config.ExceptionHelper;
import net.caiban.pc.erp.controller.BaseController;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.sys.SysCompany;
import net.caiban.pc.erp.domain.sys.SysUser;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.sys.SysCompanyService;
import net.caiban.pc.erp.service.sys.SysLoginRememberService;
import net.caiban.pc.erp.service.sys.SysUserService;
import net.caiban.utils.http.CookiesUtil;
import net.caiban.utils.lang.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 与用户注册，登录，验证有关的页面
 * @author mays
 *
 */
@Controller
public class PUserController extends BaseController {
	
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysCompanyService sysCompanyService;
	@Resource
	private SysLoginRememberService sysLoginRememberService;

	@RequestMapping
	public ModelAndView login(HttpServletRequest request, Map<String, Object> out, 
			String refurl, String refparam, String error){
		
		out.put("refurl", refurl);
		out.put("refparam", refparam);
		out.put("error", error);
		
		//TODO 判断是否已经登录，如果已经登录则直接跳转到首页
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView doLogin(HttpServletRequest request, Map<String, Object> out,
			HttpServletResponse response,
			String refurl, String refparam, Integer rememberMe, SysUser user){
		
		try {
			SessionUser sessionUser = sysUserService.doLogin(user);
			if(sessionUser!=null){
				setSessionUser(request, sessionUser);
				sysUserService.rememberMe(response, sessionUser, rememberMe);
				return redirect(refurl, refparam); //login success
			}else {
				out.put(ExceptionHelper.PAGE_ERROR_FLAG, "e.login");
			}
		} catch (ServiceException e) {
			out.put(ExceptionHelper.PAGE_ERROR_FLAG, e.getMessage());
		}
		
		out.put("refurl", refurl);
		out.put("refparam", refparam);
		out.put("user", user);
		
		return new ModelAndView("/p/puser/login"); //Login page
	}
	
	@Deprecated
	@RequestMapping
	public ModelAndView doLoginByAjax(HttpServletRequest request, Map<String, Object> out){
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView regist(HttpServletRequest request, Map<String, Object> out,
			String refurl, String refparam, String error){
		
		out.put("refurl", refurl);
		out.put("refparam", refparam);
		out.put("error", error);
		
//		out.put("companyList",sysCompanyService.query());
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView doRegist(HttpServletRequest request, Map<String, Object> out,
			String refurl, String refparam, SysUser user, SysCompany company,
			String passwordRepeat, Integer accept){
		
		try {
			SessionUser sessionUser = sysUserService.doRegist(user, company, passwordRepeat, accept);
			if(sessionUser!=null){
				setSessionUser(request, sessionUser);
				return redirect(refurl, refparam);
			}
			out.put(ExceptionHelper.PAGE_ERROR_FLAG, "e.regist");
		} catch (ServiceException e) {
			out.put(ExceptionHelper.PAGE_ERROR_FLAG, e.getMessage());
		}
		
		out.put("company", company);
		out.put("user", user);
		out.put("refurl", refurl);
		out.put("refparam", refparam);
		
		return new ModelAndView("/p/puser/regist");
	}
	
	/**决定跳转地址
	 * @param refurl
	 * @param refparam
	 * @return
	 */
	private ModelAndView redirect(String refurl, String refparam){
		if(StringUtils.isNotEmpty(refurl)){
			//TODO 参数后面处理
			return new ModelAndView("redirect:"+refurl);
		}
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping
	public ModelAndView doLogout(HttpServletRequest request, Map<String, Object> out){
		removeSession(request, AppConst.SESSION_KEY);
		
		String token = CookiesUtil.getCookie(request, AppConst.LOGIN_REMEMBER_TOKEN, null);
		
		sysLoginRememberService.removeToken(token);
		
		return new ModelAndView("redirect:/");
	}
}
