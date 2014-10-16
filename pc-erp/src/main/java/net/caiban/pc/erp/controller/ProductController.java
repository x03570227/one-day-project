/**
 * 
 */
package net.caiban.pc.erp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author mays
 *
 */
@Controller
public class ProductController extends BaseController {
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, Map<String, Object> out){
		// TODO 产品首页，显示所有产品信息，带分页
		return null;
	}
	
	@RequestMapping
	public ModelAndView create(HttpServletRequest request, Map<String, Object> out){
		//TODO 创建产品信息页面
		return null;
	}
	
	@RequestMapping
	public ModelAndView doCreate(HttpServletRequest request, ModelMap out){
		//TODO 执行产品信息创建功能
		return new ModelAndView("/product/create");
	}
	
	
}
