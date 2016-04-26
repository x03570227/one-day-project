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
import weixin.popular.bean.EventMessage;

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

    /**
     * 分页查询用户信息
     * @param cond
     * @param pager
     * @return
     */
    public Pager<SysUser> pager(SysUserCond cond, Pager<SysUser> pager);

    /**
     * 排除主账号
     * @param userList
     * @return
     */
    public List<SysUser> excludeMainAccount(List<SysUser> userList);

    /**
     * 企业账户注册
     *
     * @param mainAccount
     * @param cid
     * @param account
     * @param password
     * @param confirm
     * @return
     * @throws ServiceException
     */
    public SysUser doRegistByCompany(String mainAccount, Long cid, String account,
			String password, String confirm) throws ServiceException;

    /**
     * 计算企业员工数
     *
     * @param cid
     * @param withMainUser
     * @return
     */
    public Integer doCountUserOfCompany(Long cid, Boolean withMainUser);

    /**
     * 管理员重置密码
     *
     * @param adminUid
     * @param uid
     * @param password
     * @param confirm
     * @throws ServiceException
     */
    public void doResetPasswordByAdmin(Long adminUid, Long uid, String password,
			String confirm) throws ServiceException;

    /**
     * 第三登录自动注册系统账号
     *
     * @param auth
     * @param profile
     * @return
     * @throws ServiceException
     */
	SessionUser doRegistByOauth(SysUserAuthModel auth, SysUserProfileModel profile) throws ServiceException;

    /**
     * 非商家用户注册,默认不关联Company, 不开通 ERP
     *
     * @param user
     * @return
     * @throws ServiceException
     */
    SessionUser doRegistByEveryday(SysUserModel user) throws ServiceException;

    /**
     * 普通用户登录（每1天用户）
     *
     * @param user
     * @return
     * @throws ServiceException
     */
    SessionUser doLoginByEveryday(SysUserModel user) throws ServiceException;

    /**
     * 绑定系统用户与公众号 follower
     *
     * @param uid
     * @param wxOpenid
     * @throws ServiceException
     */
    void doBindWeixinFollower(Long uid, String wxOpenid) throws ServiceException;

    /**
     * 通过关注添加第三方账号授权
     *
     * @param eventMessage
     */
    void doAuthByFollow(EventMessage eventMessage);

    /**
     * 用户取消关注
     *
     * @param eventMessage
     */
    void doUnauthByUunfollow(EventMessage eventMessage);
}
