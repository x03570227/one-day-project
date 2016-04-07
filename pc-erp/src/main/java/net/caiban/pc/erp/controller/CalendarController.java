/**
 * 
 */
package net.caiban.pc.erp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author mays@caiban.net
 *
 */
@Controller
public class CalendarController extends BaseController {

	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		return new ModelAndView();
	}
	
	
}
