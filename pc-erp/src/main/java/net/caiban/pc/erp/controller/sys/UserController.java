/**
 * 
 */
package net.caiban.pc.erp.controller.sys;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.erp.controller.BaseController;
import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.sys.SysUser;
import net.caiban.pc.erp.domain.sys.SysUserCond;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.sys.SysUserService;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author parox-design
 *
 */
@Controller
public class UserController extends BaseController {
	
	@Resource
	private SysUserService sysUserService;
	@Resource
	private MessageSource messageSource;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request, ModelMap model){
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView reset(HttpServletRequest request, ModelMap model){
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView doReset(HttpServletRequest request, ModelMap model,
			String origin, String password, String confirm, Locale locale){
		
		Integer uid=getSessionUser(request).getUid();
		
		String error=null;
		try {
			sysUserService.resetPassword(uid, origin, password, confirm);
			error="e.sys.user.reset.password.success";
		} catch (ServiceException e) {
			error = e.getMessage();
		}
		
		model.put("error", error);
		return new ModelAndView("/sys/user/reset");
	}
	
	@RequestMapping
	@ResponseBody
	public Pager<SysUser> list(HttpServletRequest request,
			SysUserCond cond,
			Pager<SysUser> pager){
		SessionUser user = getSessionUser(request);
		
		cond.setCid(user.getCid());
		cond.setUidNot(user.getUid());
		
		pager = sysUserService.pager(cond, pager);
		return pager;
	}
}
