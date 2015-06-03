package net.caiban.pc.erp.controller;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.caiban.pc.erp.service.sys.SysVerifyCodeService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

/**
 * 验证码的controller
 * @author Administrator
 *
 */
@Controller
public class CheckCodeController extends BaseController {
	
	@Resource
	private SysVerifyCodeService sysVerifyCodeService;
	
	@RequestMapping
	@ResponseBody
	public Map<String, Object> mobileCode(HttpServletRequest request, Map<String, Object> out,
			HttpServletResponse response,String mobileNum){
		int mobileCode=sysVerifyCodeService.mobileVerifyCode(mobileNum);
		Map<String, Object> message = Maps.newHashMap();
		message.put("mobileCode", mobileCode);
		return ajaxResult(true, message);
	}
	
	@RequestMapping
	@ResponseBody
	public Map<String, Object> checkMobileCode(HttpServletRequest request, Map<String, Object> out,
			HttpServletResponse response,Locale locale,String mobileNum,String verifyCode){
		int statusInt=sysVerifyCodeService.checkMobileVerifyCode(mobileNum,verifyCode);
		if(statusInt==0){
			return ajaxResult(true, null);
		}else if(statusInt==1){
			serverError(request, response,messageSource.getMessage("tip.phone.verify.timeOut", null, locale));
		}else{
			serverError(request, response,messageSource.getMessage("tip.phone.verify.error", null, locale)); 
		}
		return null;
	}
}
