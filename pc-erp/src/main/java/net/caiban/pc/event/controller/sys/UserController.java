/**
 * 
 */
package net.caiban.pc.event.controller.sys;

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
public class UserController extends BaseController {

	@RequestMapping
	public ModelAndView index(HttpServletRequest request, Map<String, Object> out){
		
		return null;
	}
	
}
