/**
 * 
 */
package net.caiban.pc.erp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.domain.SessionUser;


/**
 * @author mays
 *
 */
public class BaseController {

//	public ModelAndView printJson(Object obj, Map<String, Object> out) {
//		String jsonString = "";
//		if (obj instanceof List) {
//			jsonString = (JSONArray.fromObject(obj).toString());
//		} else {
//			jsonString = (JSONObject.fromObject(obj).toString());
//		}
//		out.put("json", jsonString);
//		return new ModelAndView("json");
//	}
	
//	public ModelAndView ajaxResult(Boolean success, Object arg, Map<String, Object> out){
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("success", success==null?false:success);
//		map.put("result", arg);
//		return printJson(map, out);
//	}
	
	public Map<String, Object> ajaxResult(Boolean success, Object arg){
		Map<String, Object> map = Maps.newHashMap();
		map.put("result", success);
		map.put("data", arg);
		return map;
	}
	
	public SessionUser getSessionUser(HttpServletRequest request) {
		return (SessionUser) request.getSession().getAttribute(AppConst.SESSION_KEY);
	}
	
	public void setSessionUser(HttpServletRequest request, SessionUser sessionUser){
		request.getSession().setAttribute(AppConst.SESSION_KEY, sessionUser);
	}
	
	public void removeSession(HttpServletRequest request, String sessionKey) {
		request.getSession().removeAttribute(sessionKey);
	}
}
