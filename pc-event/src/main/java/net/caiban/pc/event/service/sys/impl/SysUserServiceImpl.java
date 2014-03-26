/**
 * 
 */
package net.caiban.pc.event.service.sys.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import net.caiban.MD5;
import net.caiban.pc.event.config.LogHelper;
import net.caiban.pc.event.domain.SessionUser;
import net.caiban.pc.event.domain.sys.SysUser;
import net.caiban.pc.event.persist.sys.SysUserMapper;
import net.caiban.pc.event.service.sys.SysUserService;
import net.caiban.utils.lang.StringUtils;

import org.springframework.stereotype.Component;

/**
 * @author mays
 *
 */
@Component("sysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserMapper sysUserMapper;

	@Override
	public SessionUser login(SysUser user) {
		//判断 classify
		//获取盐值
		//MD5(密码+盐)
		//查询是否密码正确
		
		if(user == null){
			return null; // TODO 异常处理
		}
		
		if(StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword())){
			return null; //使用异常处理
		}
		
		String salt = sysUserMapper.querySalt(user.getAccount(), classifyOfAccount(user.getAccount()));
		if(StringUtils.isEmpty(salt)){
			return null;  //使用异常处理
		}
		
		Integer uid = sysUserMapper.queryUidByLogin(user.getAccount(), encodePassword(user.getPassword(), salt));
		if(uid==null || uid.intValue()<=0){
			return null; //使用异常处理
		}
		
		return new SessionUser(uid, user.getAccount());
	}

	@Override
	public SessionUser register(SysUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	private String classifyOfAccount(String account){
		
		if(StringUtils.isEmail(account)){
			return SysUserService.CLASSIFY_E;
		}
		
		if(StringUtils.isMobile(account)){
			return SysUserService.CLASSIFY_M;
		}
		
		return SysUserService.CLASSIFY_A;
	}
	
	/**
	 * 加密密码
	 * @param password
	 * @param salt 为 null 或 空，则不进行加密
	 * @return
	 */
	private String encodePassword(String password, String salt) {
		if(StringUtils.isEmpty(salt)){
			return null;
		}
		try {
			return MD5.encode(password+salt, MD5.LENGTH_32);
		} catch (NoSuchAlgorithmException e) {
			LogHelper.debug(SysUserServiceImpl.class, e.getMessage());
		} catch (UnsupportedEncodingException e) {
			LogHelper.debug(SysUserServiceImpl.class, e.getMessage());
		}
		return null;
	}
	
	/**
	 * 生成随机盐值，用于新保存密码时
	 * @return
	 */
	private String randomSalt(){
		return StringUtils.randomString(MD5.LENGTH_32);
	}
	
}
