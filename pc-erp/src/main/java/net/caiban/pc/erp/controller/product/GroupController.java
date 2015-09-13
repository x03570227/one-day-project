/**
 * 
 */
package net.caiban.pc.erp.controller.product;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.caiban.pc.erp.controller.BaseController;

/**
 * @author caiban
 *
 */
@Controller
public class GroupController extends BaseController {

	@RequestMapping
	public ModelAndView index(HttpServletRequest request, ModelMap model,
			Integer id){
		if(id==null||id.intValue()<=0){
			return new ModelAndView("redirect:/product/index.do");
		}
		model.put("id", id);
		return null;
	}
	
	
}
