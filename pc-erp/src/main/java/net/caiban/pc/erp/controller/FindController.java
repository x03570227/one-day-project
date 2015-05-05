/**
 * 
 */
package net.caiban.pc.erp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import net.caiban.utils.http.HttpRequestUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author parox
 *
 */
@Controller
public class FindController {

	@RequestMapping
	public ModelAndView index(HttpServletRequest request, ModelMap model,
			String q) throws UnsupportedEncodingException{
		
		q = URLDecoder.decode(q, "utf-8");
		
		model.put("q", q);
		
		return null;
	}
	
	
}
