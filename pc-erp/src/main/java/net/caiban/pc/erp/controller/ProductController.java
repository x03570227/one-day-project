/**
 * 
 */
package net.caiban.pc.erp.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.product.Product;
import net.caiban.pc.erp.domain.product.ProductCond;
import net.caiban.pc.erp.domain.product.ProductFull;
import net.caiban.pc.erp.service.product.ProductService;
import net.caiban.utils.AssertHelper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author mays
 * 
 */
@Controller
public class ProductController extends BaseController {

	@Resource
	private ProductService productService;

	/**
	 * 普通搜索列表页面 暂时不做按属性搜索功能，按属性搜索功能通过solr搜索实现
	 * 
	 * @param request
	 * @param out
	 * @param pager
	 * @return
	 */
	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			Map<String, Object> out, Pager<Product> pager, ProductCond cond) {
		// TODO 产品首页，显示所有产品信息，带分页

		pager = productService.pager(pager, cond);

		out.put("pager", pager);

		out.put("cond", cond);
		return null;
	}

	@RequestMapping
	public ModelAndView create(HttpServletRequest request,
			Map<String, Object> out) {

		return null;
	}

	@RequestMapping
	@ResponseBody
	public ProductFull doCreate(HttpServletRequest request, ModelMap out,
			ProductFull productFull) {
		return productService.saveFull(productFull);
	}
	
	@RequestMapping
	@ResponseBody
	public Map<String, Object> doDelete(HttpServletRequest request, Integer id){
		Integer impact = productService.remove(id);
		return ajaxResult(AssertHelper.positiveInt(impact), null);
	}
	
	@RequestMapping
	public ModelAndView view(HttpServletRequest request, ModelMap out, 
			Integer id){
		//TODO 验证可否访问
		
		return null;
	}
	
}
