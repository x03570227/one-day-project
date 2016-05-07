/**
 * 
 */
package net.caiban.pc.erp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.service.sys.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;

import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.service.WeixinService;
import net.caiban.pc.erp.service.sys.SysLoginRememberService;

/**
 * @author mays
 *
 */
@Controller
public class ApiController extends BaseController {

	@Resource
	private SysLoginRememberService sysLoginRememberService;
	@Resource
	private WeixinService weixinService;
	@Resource
	private SysUserService sysUserService;
	
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
	@ResponseBody
	public Map<String, Object> genWxActoken(HttpServletRequest request, HttpServletResponse response){
		try {
			String token = weixinService.genAccessToken();
			return ajaxResult(true, token);
		} catch (ServiceException e) {
//			serverError(request, response, e.getMessage());
			return ajaxResult(false, e.getMessage());
		}
	}
//	
//	@RequestMapping(method=RequestMethod.GET)
//	public ModelAndView validWeixin(HttpServletRequest request, ModelMap model,
//			String signature, String timestamp, String nonce, String echostr,
//			String encrypt_type, String msg_signature, String get
//			){
//		
//		LOG.info("signature:"+signature+" timestamp:"+timestamp+" noce:"+nonce+" echostr:"+echostr+" encrypt_type:"+encrypt_type+" msg_signature:"+msg_signature);
//		
//		if(!weixinService.validSign(signature, timestamp, nonce)){
//			LOG.warn("WEIXIN VALID FAILURE.");
//			return null;
//		}
//		
//		// VALID 
//		if(!Strings.isNullOrEmpty(echostr)){
//			model.put("echost", echostr);
//			return null;
//		}
//		
//		return null;
//	}
	
	@RequestMapping
	public ModelAndView validWeixin(HttpServletRequest request, ModelMap model,
			String signature, String timestamp, String nonce, String echostr,
			String encrypt_type, String msg_signature
			) throws IOException{
		request.setCharacterEncoding("utf-8");
        LOG.info("接收参数： signature:{0}, timestamp:{1}, noce:{2}, echostr:{3}, encrypt_type:{4}, msg_signature:{5}",
                signature, timestamp, nonce, echostr, encrypt_type, msg_signature);
		
		LOG.info("接收参数： signature:"+signature+" timestamp:"+timestamp+" noce:"+nonce+" echostr:"+echostr+" encrypt_type:"+encrypt_type+" msg_signature:"+msg_signature);
		
		if(!weixinService.validSign(signature, timestamp, nonce, null)){
			LOG.warn("WEIXIN VALID FAILURE.");
			return null;
		}
		
		if(request.getMethod().equalsIgnoreCase("get")){
			if(!Strings.isNullOrEmpty(echostr)){
				model.put("echost", echostr);
				return null;
			}
		}
		
		InputStream is=request.getInputStream();
		
		if(is!=null){
			model.put("echost", weixinService.autoResp(is));
			LOG.info("返回echost:"+model.get("echost"));
			return null;
		}
		
		return null;
	}

    /**
     * 跳转到微信 oauth 服务
     *
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping
	public ModelAndView gowxauth(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {

		//https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect

		StringBuffer url = new StringBuffer();
		url.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=")
				.append(AppConst.getConfig("weixin.web.app.id"))
				.append("&redirect_uri=")
				.append(URLEncoder.encode(AppConst.getConfig("weixin.web.oauth"), "utf-8"))
				.append("&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");

		return new ModelAndView("redirect:" + url.toString());
	}

    /**
     * 微信 oauth 服务回调
     *
     * @param request
     * @param response
     * @param code
     * @param state
     * @return
     */
    @RequestMapping
	public ModelAndView wxauth(HttpServletRequest request, HttpServletResponse response,
		String code, String state){

		if (Strings.isNullOrEmpty(code)){
			return new ModelAndView("redirect:/"); //跳转到错误页面
		}

		try {
			SessionUser sessionUser = weixinService.oauth(code);

			setSessionUser(request, sessionUser);
			sysUserService.rememberMe(response, sessionUser, 1);

			return new ModelAndView(""); //跳转到业务页面
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:"); //跳转到错误页面
	}
	

}
