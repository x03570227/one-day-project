package net.caiban.pc.erp.filter;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.domain.SessionUser;
//import org.apache.log4j.MDC;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author mays (mays@caiban.net)
 *
 * created on 2016-5-8
 */
public class Log4jMDCFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest rq, ServletResponse rp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request= (HttpServletRequest) rq;
		HttpServletResponse response = (HttpServletResponse) rp;

        try {
            putMDC(request, response);
            chain.doFilter(request, response);
        }finally {
            clearMDC(request);
        }
        return ;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

    public void putMDC(HttpServletRequest request, HttpServletResponse response){
        MDC.put("remoteAddr", request.getRemoteAddr());
        MDC.put("remoteHost", request.getRemoteHost());
        SessionUser user = (SessionUser) request.getSession().getAttribute(AppConst.SESSION_KEY);
        if(user!=null){
            MDC.put("uid", String.valueOf(user.getUid()));
            MDC.put("cid", String.valueOf(user.getCid()));
            MDC.put("account", user.getAccount());
        }
    }

    public void clearMDC(HttpServletRequest request){
        MDC.clear();
    }
}
