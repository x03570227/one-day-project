/**
 * Copyright 2011 ASTO.
 * All right reserved.
 * Created on 2011-5-5
 */
package net.caiban.pc.erp.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.utils.http.CookiesUtil;
import net.caiban.utils.http.HttpRequestUtil;
import net.caiban.utils.lang.StringUtils;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.google.common.base.Strings;


/**
 * @author mays (mays@zz91.com)
 *
 * created on 2011-5-5
 */
public class AuthorizeFilter implements Filter {
	
//	private String deniedURL = "";
	private String loginURL = "";
	
	private Set<String> noLoginPage;
//	private Set<String> noAuthPage;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest rq, ServletResponse rp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request= (HttpServletRequest) rq;
		HttpServletResponse response = (HttpServletResponse) rp;

		String uri = request.getRequestURI();
		String path = request.getContextPath();
		path= path==null?"":path;

		StringBuffer refURL=request.getRequestURL();
		
		String url=loginURL+"?refurl="+URLEncoder.encode(refURL.toString(), "utf-8")+"&refparam="+URLEncoder.encode(JSONObject.fromObject(request.getParameterMap()).toString(), "utf-8");
		
		do {


			if(filterByConfig(noLoginPage, path, uri)){
				chain.doFilter(request, response);
				return ;
			}
			
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(AppConst.SESSION_KEY);
			
			if(sessionUser==null){
				
				sessionUser = validateToken(request);
				
				if(sessionUser == null){
					break;
				}
				request.getSession().setAttribute(AppConst.SESSION_KEY, sessionUser);
			}
			
			request.setAttribute("sessionUser", sessionUser);
			request.setAttribute("juser", JSONObject.fromObject(sessionUser));
			
			chain.doFilter(request, response);
			return ;
		} while (false);
//		
//		//AJAX请求权限过滤
		if(request.getHeader("x-requested-with")!=null
				&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
			response.setHeader("sessionstatus","timeout");
			response.setHeader("redirectUrl",path+url);
			return ;
		}

		response.sendRedirect(path+url);
		return ;
	}
//
	@Override
	public void init(FilterConfig config) throws ServletException {
//		deniedURL = config.getInitParameter("deniedURL");
		loginURL = config.getInitParameter("loginURL");
		
		String tmp[]= null;
		noLoginPage = new HashSet<String>();
		String e1=config.getInitParameter("noLoginPage");
		if(StringUtils.isNotEmpty(e1)){
			tmp=e1.split("\\|");
			for(String ex:tmp){
				noLoginPage.add(ex.trim().replace("\\n", ""));
			}
		}

//		noAuthPage = new HashSet<String>();
//		String e2=config.getInitParameter("noAuthPage");
//		if(StringUtils.isNotEmpty(e2)){
//			tmp=e2.split("\\|");
//			for(String ex:tmp){
//				noAuthPage.add(ex.trim().replace("\\n", ""));
//			}
//		}
	}

	public boolean filterByConfig(Set<String> exclude, String path,String uri){
		for(String url:exclude){
			url=path+url;
			if(url.startsWith("*")){
				if(uri.endsWith(url.substring(1))){
					return true;
				}
			}else if(url.endsWith("*")){
				if(uri.startsWith(url.substring(0, url.length()-1))){
					return true;
				}
			}else{
				if(url.equals(uri)){
					return true;
				}
			}
		}
		return false;
	}

	private SessionUser validateToken(HttpServletRequest request){
		String token = CookiesUtil.getCookie(request, AppConst.LOGIN_REMEMBER_TOKEN, null);
		
		if(Strings.isNullOrEmpty(token)){
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(AppConst.CONFIG_PROPERTIES.get("api.server"))
			.append("/validateToken.do?token=").append(token);
		
		String resp = HttpRequestUtil.httpGet(sb.toString());
		if(Strings.isNullOrEmpty(resp)||!JSONUtils.mayBeJSON(resp)){ 
			return null;
		}
		
		JSONObject jobj = JSONObject.fromObject(resp);
		
		boolean result = jobj.optBoolean("result", false);
		
		if(!result){
			return null;
		}
		
		SessionUser user = (SessionUser) JSONObject.toBean(jobj.getJSONObject("data"), SessionUser.class);

		return user;
	}

}
