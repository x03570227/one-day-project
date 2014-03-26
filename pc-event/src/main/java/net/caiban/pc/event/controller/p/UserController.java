/**
 * 
 */
package net.caiban.pc.event.controller.p;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.event.controller.BaseController;
import net.caiban.pc.event.domain.sys.SysUser;
import net.caiban.pc.event.service.sys.SysUserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 与用户注册，登录，验证有关的页面
 * @author mays
 *
 */
@Controller
public class UserController extends BaseController {
	
	@Resource
	private SysUserService sysUserService;

	@RequestMapping
	public ModelAndView login(HttpServletRequest request, Map<String, Object> out, 
			String refurl, String refparam){
		
		out.put("refurl", refparam);
		out.put("refparam", refparam);
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView doLogin(HttpServletRequest request, Map<String, Object> out,
			String refurl, String refparam, SysUser user){
		//成功登录后跳转到refurl或index
		return null;
	}
	
	@RequestMapping
	public ModelAndView doLoginByAjax(HttpServletRequest request, Map<String, Object> out){
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView register(HttpServletRequest request, Map<String, Object> out,
			String refurl, String refparam){
		return null;
	}
	
	@RequestMapping
	public ModelAndView doRegister(HttpServletRequest request, Map<String, Object> out,
			String refurl, String refparam){
		return null;
	}
	
	/**决定跳转地址
	 * @param refurl
	 * @param refparam
	 * @return
	 */
	private ModelAndView redirect(String refurl, String refparam){
		
		return null;
	}
}
