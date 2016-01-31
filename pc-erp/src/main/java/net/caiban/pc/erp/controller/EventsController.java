/**
 * 
 */
package net.caiban.pc.erp.controller;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.erp.domain.events.Events;
import net.caiban.pc.erp.service.events.EventsService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

/**
 * @author mays
 *
 */
@Deprecated
@Controller
public class EventsController extends BaseController {
	
	@Resource
	private EventsService eventsService;
	
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
	
//	@RequestMapping
//	public ModelAndView doCreate(HttpServletRequest request, Map<String, Object> out,
//			Events event, String gmtStartStr, String gmtEndStr, String invitedAccountId){
//
//		eventsService.initGmt(event, gmtStartStr, gmtEndStr);
//		event.setUid(getSessionUser(request).getUid());
//
//		Integer id = eventsService.saveEvent(event);
//
//		if(id!=null && id.intValue()>0){
////			eventsService.appendJoiner(id, invitedAccountId);
//			return new ModelAndView("redirect:index.do");
//		}
//
//		return new ModelAndView("/events/create");
//	}
	
//	@Deprecated
//	@RequestMapping
//	@ResponseBody
//	public Map<String, Integer> ajaxAppendJoin(HttpServletRequest request, Map<String, Object> out,
//			String origin, String originId, String append){
//
//		return eventsService.filterAppendJoiner(origin, originId, append);
//	}
	
	@RequestMapping
	public ModelAndView ajaxAttend(HttpServletRequest request, ModelMap out, String account){
		
		return null;
	}
}
