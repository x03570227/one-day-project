/**
 * 
 */
package net.caiban.pc.erp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.product.Product;
import net.caiban.pc.erp.domain.product.ProductCond;
import net.caiban.pc.erp.domain.product.ProductDefine;
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
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			ModelMap model) throws UnsupportedEncodingException {
		
		
		model.put("qrcodeUrl", URLEncoder.encode("http://www.caiban.net"+request.getContextPath()+"/p/pkdt/index.do?", "utf-8"));

		return null;
	}
	
	@RequestMapping
	@ResponseBody
	public Pager<Product> list(HttpServletRequest request,
			Pager<Product> pager, ProductCond cond) {
		
		SessionUser user = getSessionUser(request);
		cond.setCid(user.getCid());
		pager = productService.pager(pager, cond);
		
//		pager.setTotals(12);
		return pager;
	}

	@RequestMapping
	public ModelAndView edit(HttpServletRequest request,
			Map<String, Object> out, Integer id) {
		
		out.put("id", id);
		
		return null;
	}

	@RequestMapping
	@ResponseBody
	public ProductFull doUpdate(HttpServletRequest request,
			Product product, ProductDefine define) {
		
		SessionUser user = getSessionUser(request);
		ProductFull productFull = productService.initFull(user, product, define);
		
		if(product.getId()!=null && product.getId().intValue()>0){
			return productService.updateFull(productFull);
		}
		
		productFull = productService.saveFull(productFull);
		return productFull;
	}
	
	@RequestMapping
	@ResponseBody
	public ProductFull queryFull(HttpServletRequest request, Integer id){
		SessionUser user = getSessionUser(request);
		return productService.queryOneFull(id, true, user);
	}
	
	@RequestMapping
	@ResponseBody
	public Map<String, Object> doDelete(HttpServletRequest request, Integer id){
		Integer impact = productService.remove(id);
		
		boolean result = AssertHelper.positiveInt(impact);
		return ajaxResult(result, null);
	}
	
//	@RequestMapping
//	public ModelAndView view(HttpServletRequest request, ModelMap out, 
//			Integer id){
//		//TODO 验证可否访问
//		
//		return null;
//	}
//	
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(URLEncoder.encode("http://www.caiban.net/erp/p/pkdt/index.do?cid=1&pid=2", "utf-8"));
	}
}
