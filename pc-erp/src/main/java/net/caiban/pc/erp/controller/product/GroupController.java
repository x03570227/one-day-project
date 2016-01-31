/**
 * 
 */
package net.caiban.pc.erp.controller.product;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.caiban.pc.erp.controller.BaseController;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.product.ProductCond;
import net.caiban.pc.erp.domain.product.ProductGroupModel;
import net.caiban.pc.erp.domain.product.ProductModel;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.product.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author caiban
 *
 */
@Controller
public class GroupController extends BaseController {

	@Resource
	private ProductService productService;
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, ModelMap model,
			Integer id){
		if(id==null||id.intValue()<=0){
			return new ModelAndView("redirect:/product/index.do");
		}
		model.put("id", id);
		return null;
	}
	
	@RequestMapping
	@ResponseBody
	public List<ProductGroupModel> queryByPid(HttpServletRequest request, 
			ProductCond cond){
		SessionUser user = getSessionUser(request);
		return productService.queryGroups(user, cond);
	}
	
	@RequestMapping
	@ResponseBody
	public List<ProductModel> queryItems(HttpServletRequest request, 
			ProductCond cond){
		//获取当前关联的所有产品基本信息
		return productService.queryProductOfGroup(cond);
	}
	
	@RequestMapping
	@ResponseBody
	public Map<String, Object> doLeave(HttpServletRequest request,
			HttpServletResponse response, Locale locale,
			Long productId, Long groupId){
		
		SessionUser user = getSessionUser(request);
		
		try {
			productService.removeProductFromGroup(user, groupId, productId);
			return ajaxResult(true, null);
		} catch (ServiceException e) {
			serverError(request, response, 
					messageSource.getMessage(e.getMessage(), null, locale));
		}
		
		return null;
	}
	
	@RequestMapping
	@ResponseBody
	public Map<String, Object> doJoin(HttpServletRequest request,
			HttpServletResponse response, Locale locale,
			ProductGroupModel groupModel){
		
		SessionUser user = getSessionUser(request);
		
		try {
			productService.doGroup(user, groupModel);
			return ajaxResult(true, null);
		} catch (ServiceException e) {
			serverError(request, response, 
					messageSource.getMessage(e.getMessage(), null, locale));
		}
		
		return null;
	}
}
