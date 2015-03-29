/**
 * 
 */
package net.caiban.pc.erp.controller.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.erp.controller.BaseController;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.sys.SysApp;
import net.caiban.pc.erp.domain.sys.SysAppCond;
import net.caiban.pc.erp.service.sys.SysAppService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;

/**
 * @author mays
 *
 */
@Controller
public class AppController extends BaseController{
	

	@Resource
	private SysAppService sysAppService;
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, ModelMap model, 
			String domain, String success){
		
		SessionUser user  = getSessionUser(request);
		
		model.put("app", sysAppService.queryByDomain(user.getCid(), domain));
		
		model.put("domain", Strings.isNullOrEmpty(domain)?SysAppCond.DEFAULT_DOMAIN: domain);
		
		model.put("success", success);
		
		model.put("domains", sysAppService.getDomains());
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView saveOrUpdate(HttpServletRequest request, SysApp app,
			ModelMap model){
		
		SessionUser user = getSessionUser(request);
		app.setCid(user.getCid());
		sysAppService.saveOrUpdate(app);
		
		model.put("domain", app.getDomain());
		
		model.put("success", "msg.form.save.success");
		
		return new ModelAndView("redirect:/sys/app/index.do");
	}
}
