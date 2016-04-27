/**
 * 
 */
package net.caiban.pc.erp.controller.p;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;
import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.controller.BaseController;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.sys.SysCompany;
import net.caiban.pc.erp.domain.sys.SysUser;
import net.caiban.pc.erp.domain.sys.SysUserModel;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.sys.SysCompanyService;
import net.caiban.pc.erp.service.sys.SysLoginRememberService;
import net.caiban.pc.erp.service.sys.SysUserService;
import net.caiban.utils.http.CookiesUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@ResponseBody
	public Map<String, Object> doLogin(HttpServletRequest request, Map<String, Object> out,
			HttpServletResponse response,
			String refurl, String refparam, Integer rememberMe, SysUser user, Locale locale){
		
		try {
			SessionUser sessionUser = sysUserService.doLogin(user);
			if(sessionUser!=null){
				setSessionUser(request, sessionUser);
				sysUserService.rememberMe(response, sessionUser, rememberMe);
				return ajaxResult(true, null);
			}
			serverError(request, response, messageSource.getMessage("e.login", null, locale));
		} catch (ServiceException e) {
			serverError(request, response, messageSource.getMessage(e.getMessage(), null, locale));
		}
		
//		out.put("refurl", refurl);
//		out.put("refparam", refparam);
//		out.put("user", user);
		return null;//Login page
	}
	
//	@RequestMapping
//	@ResponseBody
//	public Map<String, Object> doLoginByAjax(HttpServletRequest request, Map<String, Object> out,
//			HttpServletResponse response, Locale locale,
//			String refurl, String refparam, Integer rememberMe, SysUser user){
//		
//		try {
//			SessionUser sessionUser = sysUserService.doLogin(user);
//			if(sessionUser!=null){
//				setSessionUser(request, sessionUser);
//				sysUserService.rememberMe(response, sessionUser, rememberMe);
//				
//				Map<String, Object> message = Maps.newHashMap();
//				message.put("refurl", refurl);
//				message.put("refparam", refparam);
//				
//				return ajaxResult(true, message);
//			}
//			
//			serverError(request, response, messageSource.getMessage("e.login", null, locale));
//		} catch (ServiceException e) {
//			serverError(request, response, messageSource.getMessage(e.getMessage(), null, locale));
//		}
//		return null;
//	}
	
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
	@ResponseBody
	public Map<String, Object> doRegist(HttpServletRequest request, HttpServletResponse response,
			String refurl, String refparam, SysUser user, SysCompany company,
			String passwordRepeat, Integer accept, Locale locale){
		
		try {
			SessionUser sessionUser = sysUserService.doRegist(user, company, passwordRepeat, accept);
			if(sessionUser!=null){
				setSessionUser(request, sessionUser);
				return ajaxResult(true, null);
			}
			serverError(request, response, messageSource.getMessage("e.regist", null, locale));
		} catch (ServiceException e) {
			serverError(request, response, messageSource.getMessage(e.getMessage(), null, locale));
		}
		
//		out.put("company", company);
//		out.put("user", user);
//		out.put("refurl", refurl);
//		out.put("refparam", refparam);
		return null;
//		return new ModelAndView("/p/puser/regist");
	}
	
	/**决定跳转地址
	 * @param refurl
	 * @param refparam
	 * @return
	 */
//	private ModelAndView redirect(String refurl, String refparam){
//		if(StringUtils.isNotEmpty(refurl)){
//			//TODO 参数后面处理
//			return new ModelAndView("redirect:"+refurl);
//		}
//		return new ModelAndView("redirect:/");
//	}

    /**
     * 用户登出操作
     * */
	@RequestMapping
	public ModelAndView doLogout(HttpServletRequest request, Map<String, Object> out){
		removeSession(request, AppConst.SESSION_KEY);
		
		String token = CookiesUtil.getCookie(request, AppConst.LOGIN_REMEMBER_TOKEN, null);
		
		sysLoginRememberService.removeToken(token);
		
		return new ModelAndView("redirect:/");
	}

    @RequestMapping
    public ModelAndView everydayLogin(HttpServletRequest request, ModelMap model){
        return null;
    }

    @RequestMapping
    public ModelAndView everydayRegist(HttpServletRequest request, ModelMap model){
        return null;
    }

    /**
     * 微信公众号用户登录操作
     * */
    @Deprecated
    @RequestMapping
    @ResponseBody
    public Map<String, Object> doEverydayLogin(HttpServletRequest request, HttpServletResponse response,
                                         SysUserModel user, Locale locale) {
        try {
            SessionUser sessionUser = sysUserService.doLoginByEveryday(user);
            setSessionUser(request, sessionUser);
            sysUserService.rememberMe(response, sessionUser, user.getRememberMe());
            return ajaxResult(true, null);
        } catch (ServiceException e) {
            serverError(request, response, messageSource.getMessage(e.getMessage(), null, locale));
        }
        return null;
    }

    /**
     * 微信公众号用户注册操作
     * */
    @Deprecated
    @RequestMapping
    @ResponseBody
    public Map<String, Object> doEverydayRegist(HttpServletRequest request, HttpServletResponse response,
                                          SysUserModel user, Locale locale){
        try {
            SessionUser sessionUser = sysUserService.doRegistByEveryday(user);
            setSessionUser(request, sessionUser);
            return ajaxResult(true, null);
        } catch (ServiceException e) {
            serverError(request, response, messageSource.getMessage(e.getMessage(), null, locale));
        }

        return null;
    }

    /**
     * 微信粉丝绑定
     *
     * @param request
     * @param model
     * @param wxOpenid
     * @param locale
     * @return
     */
    @RequestMapping
    public ModelAndView wxgate(HttpServletRequest request, ModelMap model,
                                           String wxOpenid, Locale locale){

        SessionUser sessionUser = getSessionUser(request);
        if(sessionUser!=null && !Strings.isNullOrEmpty(wxOpenid)){
            try {
                sysUserService.doBindWeixinFollower(sessionUser.getUid(), wxOpenid);
            }catch (ServiceException e){
                model.put("errorCode", e.getMessage());
                return new ModelAndView("redirect:/error_wx.do");
            }
        }

        model.put("wxOpenid", wxOpenid);
        return null;
    }

    /**
     * 微信公众号关注者登录
     *
     * @param request
     * @param response
     * @param user
     * @param wxOpenid
     * @param locale
     * @return
     */
    @RequestMapping
    @ResponseBody
    public Map<String, Object> doWxLogin(HttpServletRequest request, HttpServletResponse response,
                                           SysUserModel user, String wxOpenid, Locale locale) {
        //TODO 登录->成功后绑定

        try {
            SessionUser sessionUser = sysUserService.doLoginByEveryday(user);
            setSessionUser(request, sessionUser);
            sysUserService.rememberMe(response, sessionUser, user.getRememberMe());
            if(!Strings.isNullOrEmpty(wxOpenid)){
                sysUserService.doBindWeixinFollower(sessionUser.getUid(), wxOpenid);
            }
            return ajaxResult(true, null);
        } catch (ServiceException e) {

            return ajaxResult(false, messageSource.getMessage(e.getMessage(), null, locale));
        }

    }

    @RequestMapping
    @ResponseBody
    public Map<String, Object> doWxRegist(HttpServletRequest request, HttpServletResponse response,
                                               SysUserModel user, String wxOpenid, Locale locale) {
        try {
            SessionUser sessionUser = sysUserService.doRegistByEveryday(user);
            setSessionUser(request, sessionUser);
            sysUserService.rememberMe(response, sessionUser, user.getRememberMe());
            if(!Strings.isNullOrEmpty(wxOpenid)){
                sysUserService.doBindWeixinFollower(sessionUser.getUid(), wxOpenid);
            }
            return ajaxResult(true, null);
        } catch (ServiceException e) {

            return ajaxResult(false, messageSource.getMessage(e.getMessage(), null, locale));
        }

    }
}
