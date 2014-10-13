/**
 * 
 */
package net.caiban.pc.event.controller.p;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.caiban.pc.event.controller.BaseController;

/**
 * @author parox-design
 *
 */
@Controller
public class AboutController extends BaseController {

	/**
	 * 服务条款
	 * @param request
	 * @param out
	 * @return
	 */
	@RequestMapping
	public ModelAndView termOfService(HttpServletRequest request, Map<String, Object> out){
		
		return null;
	}
}
