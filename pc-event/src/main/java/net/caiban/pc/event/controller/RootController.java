/**
 * 
 */
package net.caiban.pc.event.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author mays
 *
 */
@Controller
public class RootController extends BaseController{

	@RequestMapping
	public ModelAndView index(HttpServletRequest request, Map<String, Object> out
			){
		
		//判断浏览器类型，手机浏览器则直接跳转到 indexMobel
		//cookies可设置强制使用电脑版 index
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView indexMobel(HttpServletRequest request, Map<String, Object> out){
		
		return null;
	}
}
