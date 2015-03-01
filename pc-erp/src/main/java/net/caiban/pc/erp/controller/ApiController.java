/**
 * 
 */
package net.caiban.pc.erp.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.sys.SysLoginRememberService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mays
 *
 */
@Controller
public class ApiController extends BaseController {

	@Resource
	private SysLoginRememberService sysLoginRememberService;
	
	@RequestMapping
	@ResponseBody
	public Map<String, Object> validateToken(HttpServletRequest request, String token, String sign){
		
		try {
			SessionUser user = sysLoginRememberService.validateToken(token);
			return ajaxResult(true, user);
		} catch (ServiceException e) {
			return ajaxResult(false, e.getMessage());
		}
		
	}
	
}
