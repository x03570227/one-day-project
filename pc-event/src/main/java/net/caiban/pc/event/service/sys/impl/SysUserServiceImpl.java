/**
 * 
 */
package net.caiban.pc.event.service.sys.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.caiban.MD5;
import net.caiban.pc.event.config.LogHelper;
import net.caiban.pc.event.domain.SessionUser;
import net.caiban.pc.event.domain.sys.SysUser;
import net.caiban.pc.event.exception.ServiceException;
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
	public SessionUser login(SysUser user) throws ServiceException {

		if (user == null) {
			throw new ServiceException("e.login");
		}

		if (StringUtils.isEmpty(user.getAccount())
				|| StringUtils.isEmpty(user.getPassword())) {
			throw new ServiceException("e.login");
		}

		String classify = classifyOfAccount(user.getAccount());
		String salt = sysUserMapper.querySalt(rebuildAccount(classify,
				user.getAccount()));
		if (StringUtils.isEmpty(salt)) {
			throw new ServiceException("e.login");
		}

		Integer uid = sysUserMapper.queryUidByLogin(
				rebuildAccount(classify, user.getAccount()),
				encodePassword(user.getPassword(), salt));
		if (uid == null || uid.intValue() <= 0) {
			throw new ServiceException("e.login.failure");
		}

		return new SessionUser(uid, user.getAccount());
	}

	@Override
	public SessionUser regist(SysUser user, String passwordRepeat, Integer accept) throws ServiceException {
		// TODO 需要事务控制
		
		if(accept == null || accept.intValue()!=SysUserService.ACCEPT_TRUE){
			throw new ServiceException("e.regist");
		}
		
		if(user==null){
			throw new ServiceException("e.regist");
		}
		
		if(StringUtils.isEmpty(passwordRepeat) || !passwordRepeat.equals(user.getPassword())){
			throw new ServiceException("e.regist");
		}
		SysUser registUser = new SysUser();
		registUser.setSalt(randomSalt());
		registUser.setClassify(classifyOfAccount(user.getAccount()));
		registUser.setAccount(rebuildAccount(registUser.getClassify(), user.getAccount()));
		registUser.setPassword(encodePassword(user.getPassword(), registUser.getSalt()));
		registUser.setUid(DEFAULT_UID);
		
		try {
			sysUserMapper.insert(registUser);
		} catch (Exception e) {
			throw new ServiceException("e.regist");
		}
		
		if(registUser.getId()==null || registUser.getId().intValue()<=0){
			throw new ServiceException("e.regist");
		}
		sysUserMapper.updateUid(registUser.getId(), registUser.getId());
		
		return new SessionUser(registUser.getId(), user.getAccount());
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
	
	/**
	 * 账户规则为：classify#account
	 * 可避免多种类型账户重复
	 * @param classify
	 * @param account
	 * @return
	 */
	private String rebuildAccount(String classify, String account){
		if(StringUtils.isEmpty(classify) || StringUtils.isEmpty(account)){
			return null;
		}
		return classify+"#"+account;
	}

	@Override
	public Integer registNewAccount(SysUser user, String passwordRepeat)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rememberMe(HttpServletResponse response, SessionUser user,
			Integer rememberFlag) {
		// TODO Auto-generated method stub
		
	}
}
