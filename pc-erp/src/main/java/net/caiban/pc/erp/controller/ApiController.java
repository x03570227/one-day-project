/**
 * 
 */
package net.caiban.pc.erp.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.sys.SysLoginRememberService;

/**
 * @author mays
 *
 */
@Controller
public class ApiController extends BaseController {

	@Resource
	private SysLoginRememberService sysLoginRememberService;
	
	final static Logger LOG = LoggerFactory.getLogger(ApiController.class);
	
	@RequestMapping
	@ResponseBody
	public Map<String, Object> validateToken(HttpServletRequest request, String token, String sign){
		
		try {
			SessionUser user = sysLoginRememberService.validateToken(token);
			return ajaxResult(true, user);
		} catch (ServiceException e) {
			return ajaxResult(false, e.getMessage());
		}
		
	}
	
	@RequestMapping
	public ModelAndView validWeixin(HttpServletRequest request, ModelMap model,
			String signature, String timestamp, String nonce, String echostr
			){
		//TODO 接入微信
		
		List<String> params = Lists.newArrayList();
		params.add(timestamp);
		params.add(nonce);
		params.add(AppConst.getConfig("weixin.token", ""));
		
		LOG.info("REQUEST FROM WEIXIN: "+new Gson().toJson(params)+" sign is:"+signature);
		
		Collections.sort(params);
		
		LOG.info("RESORT PARAMS: "+new Gson().toJson(params));
		
		String paramsStr = Joiner.on("").join(params);
		
		String genSign = DigestUtils.shaHex(paramsStr);
		
		LOG.info("GENERATE SIGN: "+genSign);
		
		if(genSign.equals(signature)){
			model.put("echost", echostr);
		}
		
		return null;
	}
	
}
