/**
 * 
 */
package net.caiban.pc.erp.service.sys;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.sys.*;
import net.caiban.pc.erp.exception.ServiceException;

/**
 * @author mays
 *
 */
public interface SysUserService {
	
	/**
	 * 登录认证(ERP,未开通用户无法登录)
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	public SessionUser doLogin(SysUser user) throws ServiceException;

	/**
     * 商家注册（ERP 用户）
	 * 注册新用户（第一次注册）
	 * @param user
	 * @param passwordRepeat
	 * @param accept 协议是否接受
	 * @return
	 * @throws ServiceException
	 */
	public SessionUser doRegist(SysUser user, SysCompany company, String passwordRepeat, Integer accept) throws ServiceException;

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

	/**
	 * 识别账户类型
	 * @param account
	 * @return
	 */
	public String classifyOfAccount(String account);

	/**
	 * 账户规则为：classify#account
	 * 可避免多种类型账户重复
	 * @param classify
	 * @param account
	 * @return
	 */
	public String rebuildAccount(String classify, String account);

	/**
	 * 根据未识别的用户账号查找用户ID
	 * @param account
	 * @return
	 */
	public Long queryIdByAccount(String account);

	/**
	 * 密码重置
	 * @param origin
	 * @param password
	 * @param confirm
	 * @throws ServiceException
	 */
	public void doResetPassword(Long uid, String origin, String password,
			String confirm) throws ServiceException;

	public Pager<SysUser> pager(SysUserCond cond, Pager<SysUser> pager);

	public List<SysUser> excludeMainAccount(List<SysUser> userList);

	public SysUser doRegistByCompany(String mainAccount, Long cid, String account,
			String password, String confirm) throws ServiceException;

	public Integer doCountUserOfCompany(Long cid, Boolean withMainUser);

	public void doResetPasswordByAdmin(Long adminUid, Long uid, String password,
			String confirm) throws ServiceException;

    /**
     * 第三登录自动注册系统账号
     * */
	SessionUser doRegistByOauth(SysUserAuthModel auth, SysUserProfileModel profile) throws ServiceException;

    /**
     * 非商家用户注册,默认不关联Company, 不开通 ERP
     * */
    SessionUser doRegist(SysUserModel user) throws ServiceException;
}
