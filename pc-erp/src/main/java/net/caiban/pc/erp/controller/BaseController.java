/**
 * 
 */
package net.caiban.pc.erp.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;

import com.google.common.collect.Maps;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.domain.SessionUser;


/**
 * @author mays
 *
 */
public class BaseController {
	
	@Resource
	protected MessageSource messageSource;

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
	
	public void serverError(HttpServletRequest request, HttpServletResponse response, String errorCode){
		try {
			response.addHeader("CB_ERROR", URLEncoder.encode(errorCode, "utf-8"));
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "SERVER ERROR OCCURRED.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
