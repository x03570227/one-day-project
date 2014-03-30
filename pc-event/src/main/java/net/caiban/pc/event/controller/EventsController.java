/**
 * 
 */
package net.caiban.pc.event.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.event.domain.events.Events;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

/**
 * @author mays
 *
 */
@Controller
public class EventsController extends BaseController {
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, Map<String, Object> out){
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView create(HttpServletRequest request, Map<String, Object> out){
		
		RequestContext requestContext = new RequestContext(request);
		Locale locale = requestContext.getLocale();
		out.put("locale", locale.getLanguage());
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView doCreate(HttpServletRequest request, Map<String, Object> out,
			Events events){
		
		//
		
		return new ModelAndView("/events/create");
	}
}
