/**
 * 
 */
package net.caiban.pc.event.service.sys;

import javax.servlet.http.HttpServletResponse;

import net.caiban.pc.event.domain.SessionUser;
import net.caiban.pc.event.domain.sys.SysUser;
import net.caiban.pc.event.exception.ServiceException;

/**
 * @author mays
 *
 */
public interface SysUserService {
	
	public final static String CLASSIFY_A="A";
	public final static String CLASSIFY_M="M";
	public final static String CLASSIFY_E="E";
	public final static int ACCEPT_TRUE=1;
	public final static int DEFAULT_UID=0;
	

	/**
	 * 登录认证
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	public SessionUser login(SysUser user) throws ServiceException;
	
	/**
	 * 注册新用户（第一次注册）
	 * @param user
	 * @param passwordRepeat
	 * @param accept
	 * @return
	 * @throws ServiceException
	 */
	public SessionUser regist(SysUser user, String passwordRepeat, Integer accept) throws ServiceException;
	
	/**
	 * 老用户增加更多登录账户
	 * @param user
	 * @param passwordRepeat
	 * @return
	 * @throws ServiceException
	 */
	public Integer registNewAccount(SysUser user, String passwordRepeat) throws ServiceException;
	
	/**
	 * 登录时记住密码
	 * @param response
	 * @param user
	 * @param rememberFlag
	 */
	public void rememberMe(HttpServletResponse response, SessionUser user,
			Integer rememberFlag);
	
}
