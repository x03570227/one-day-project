/**
 * 
 */
package net.caiban.pc.erp.controller.f;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.caiban.pc.erp.controller.BaseController;
import net.caiban.pc.erp.domain.EverydayCond;
import net.caiban.pc.erp.domain.EverydayModel;
import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.EverydayService;

/**
 * @author mar
 *
 */
@Controller
public class FeverydayController extends BaseController {
	
	@Resource
	private EverydayService everydayService;
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,
			ModelMap model, EverydayCond cond, Pager<EverydayModel> pager){
		try {
			model.put("everydayList", everydayService.pagerRecent(cond, pager));
			model.put("pager", pager);
		} catch (ServiceException e) {
			serverError(request, response, e.getMessage());
		}
		return new ModelAndView();
	}
	
	@RequestMapping
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response,
			ModelMap model, Long id){
		//TODO
		return new ModelAndView();
	}

}
