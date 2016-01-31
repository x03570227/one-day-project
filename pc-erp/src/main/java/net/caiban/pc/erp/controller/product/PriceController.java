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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.caiban.pc.erp.controller.BaseController;
import net.caiban.pc.erp.domain.product.ProductCond;
import net.caiban.pc.erp.domain.product.ProductPriceModel;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.product.ProductService;

/**
 * @author parox
 *
 */
@Controller
public class PriceController extends BaseController{

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
	public List<ProductPriceModel> list(HttpServletRequest request, ProductCond cond){
		
		return productService.queryPriceModels(cond);
	}
	
	@RequestMapping
	@ResponseBody
	public ProductPriceModel doCreate(HttpServletRequest request, HttpServletResponse response,
			ProductPriceModel price, Locale locale){
		try {
			return productService.doAddPrice(price);
		} catch (ServiceException e) {
			serverError(request, response, messageSource.getMessage(e.getMessage(), null, locale));
		}
		return null;
	}
	
	@RequestMapping
	@ResponseBody
	public Map<String, Object> doDelete(HttpServletRequest request, HttpServletResponse response,
			Long productId, Long id, Locale locale){
		try {
			productService.removePrice(id, productId);
			return ajaxResult(true, null);
		} catch (ServiceException e) {
			serverError(request, response, messageSource.getMessage(e.getMessage(), null, locale));
		}
		return null;
	}
	
}
