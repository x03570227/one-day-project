/**
 * 
 */
package net.caiban.pc.erp.service.sys.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.config.LogHelper;
import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.sys.*;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.persist.sys.SysCompanyMapper;
import net.caiban.pc.erp.persist.sys.SysLoginRememberMapper;
import net.caiban.pc.erp.persist.sys.SysUserMapper;
import net.caiban.pc.erp.service.sys.SysUserService;
import net.caiban.pc.erp.utils.ValidateUtil;
import net.caiban.utils.DateUtil;
import net.caiban.utils.MD5;
import net.caiban.utils.http.CookiesUtil;
import net.caiban.utils.lang.StringUtils;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

/**
 * @author mays
 *
 */
@Component("sysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private SysCompanyMapper sysCompanyMapper;
	@Resource
	private SysLoginRememberMapper sysLoginRememberMapper;

	@Override
	public SessionUser doLogin(SysUser user) throws ServiceException {

		if (user == null) {
			throw new ServiceException("e.login");
		}

		if (StringUtils.isEmpty(user.getAccount())
				|| StringUtils.isEmpty(user.getPassword())) {
			throw new ServiceException("e.login");
		}
		
		user.setAccount(user.getAccount().replace("：", ":"));
		
		String classify = classifyOfAccount(user.getAccount());
		
		Long uid = sysUserMapper.queryUidByAccount(rebuildAccount(classify, user.getAccount()));
		if(uid==null || uid.intValue()<=0){
			throw new ServiceException("e.login.account.not.exist");
		}
		
		String salt = sysUserMapper.querySaltByUid(uid);
		
//		String salt = sysUserMapper.querySalt(rebuildAccount(classify,
//				user.getAccount()));
		if (StringUtils.isEmpty(salt)) {
			throw new ServiceException("e.login");
		}

		SysUser confirmedUser = sysUserMapper.queryUserByPassword(uid,
				encodePassword(user.getPassword(), salt));
		
//		SysUser confirmedUser = sysUserMapper.queryUidByLogin(
//				rebuildAccount(classify, user.getAccount()),
//				encodePassword(user.getPassword(), salt));
		if (confirmedUser == null) {
			throw new ServiceException("e.login.password.not.confirmed");
		}

		return new SessionUser(confirmedUser.getUid(), user.getAccount(), confirmedUser.getCid());
	}

	@Override
	public SessionUser doRegist(SysUser user, SysCompany company,
			String passwordRepeat, Integer accept) throws ServiceException {
		
		if(accept == null || accept.intValue()!=SysUser.ACCEPT_TRUE){
			throw new ServiceException("e.regist");
		}
		
		if(user==null){
			throw new ServiceException("e.regist");
		}
		
		if(StringUtils.isEmpty(passwordRepeat) || !passwordRepeat.equals(user.getPassword())){
			throw new ServiceException("e.regist");
		}
		
		if(reservedAccount(user.getAccount())){
			throw new ServiceException("e.regist.exist.account");
		}
		
		String rebuildedAccount = rebuildAccount(classifyOfAccount(user.getAccount()), user.getAccount());
		if(existAccount(rebuildedAccount)){
			throw new ServiceException("e.regist.exist.account");
		}
		
		sysCompanyMapper.insert(company);
		if(company.getId()==null || company.getId().intValue()==0){
			throw new ServiceException("e.regist");
		}
		
		SysUser registUser = saveAccount(user, company.getId(), rebuildedAccount);
		
		return new SessionUser(registUser.getId(), user.getAccount(), company.getId());
	}
	
	final static Set<String> RESERVED_AC=new HashSet<String>();
	static{
		RESERVED_AC.add("mays");
		RESERVED_AC.add("mar");
		RESERVED_AC.add("account");
		RESERVED_AC.add("admin");
		RESERVED_AC.add("administrator");
		RESERVED_AC.add("caiban");
		RESERVED_AC.add("caiban.net");
		RESERVED_AC.add("google");
		RESERVED_AC.add("example");
		RESERVED_AC.add("example.com");
		RESERVED_AC.add("管理员");
	}
	
	private boolean reservedAccount(String account){
		account = account.toLowerCase();
		return RESERVED_AC.contains(account);
	}
	
	private SysUser saveAccount(SysUser user, Long cid, String rebuildedAccount) throws ServiceException{
		SysUser registUser = new SysUser();
		registUser.setSalt(randomSalt());
		registUser.setClassify(classifyOfAccount(user.getAccount()));
		registUser.setAccount(rebuildedAccount);
		registUser.setPassword(encodePassword(user.getPassword(), registUser.getSalt()));
		registUser.setUid(SysUser.DEFAULT_UID);
		registUser.setCid(cid);
		
		try {
			sysUserMapper.insert(registUser);
		} catch (Exception e) {
			throw new ServiceException("e.regist");
		}
		
		if(registUser.getId()==null || registUser.getId().intValue()<=0){
			throw new ServiceException("e.regist");
		}
		sysUserMapper.updateUid(registUser.getId(), registUser.getId());
		
		return registUser;
	}

	@Override
	public String classifyOfAccount(String account){
		
		if(StringUtils.isEmail(account)){
			return SysUser.CLASSIFY.E.toString();
		}
		
		if(ValidateUtil.isMobile(account)){
			return SysUser.CLASSIFY.M.toString();
		}
		
		return SysUser.CLASSIFY.A.toString();
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
	@Override
	public String rebuildAccount(String classify, String account){
		if(StringUtils.isEmpty(classify) || StringUtils.isEmpty(account)){
			return null;
		}
		return classify+"#"+account;
	}

	@Override
	public Integer registNewAccount(SysUser user, String passwordRepeat)
			throws ServiceException {
		return null;
	}

//	final static int DEFAULT_REMEMBER_DAY=7; 
	
	@Override
	public void rememberMe(HttpServletResponse response, SessionUser user,
			Integer rememberFlag) {
		
		if(rememberFlag==null || rememberFlag.intValue()!=1){
			return ;
		}
		
		String token = UUID.randomUUID().toString();
		Date now = new Date();
		Integer rememberDay = Integer.valueOf(AppConst.CONFIG_PROPERTIES.get("remember.day"));
		
		SysLoginRemember remember = new SysLoginRemember();
		remember.setUid(user.getUid());
		remember.setRememberToken(token);
		remember.setGmtExpired(DateUtil.getDateAfterDays(now, rememberDay));
		remember.setGmtRefresh(now);
		
		sysLoginRememberMapper.insert(remember);
		
		CookiesUtil.setCookie(response, AppConst.LOGIN_REMEMBER_TOKEN, token, null, rememberDay*86400);
	}

	@Override
	public Long queryIdByAccount(String account) {
		
		if(StringUtils.isEmpty(account)){
			return null;
		}
		
		String ac = rebuildAccount(classifyOfAccount(account), account);
		
		return sysUserMapper.queryUidByAccount(ac);
	}
	
	private boolean existAccount(String account){
		Integer c = sysUserMapper.countByAccount(account);
		if(c!=null && c.intValue()>0){
			return true;
		}
		return false;
	}

	@Override
	public void doResetPassword(Long uid, String origin, String password, String confirm)
			throws ServiceException {
		if(Strings.isNullOrEmpty(password)){
			throw new ServiceException("e.sys.user.reset.password.invalid");
		}
		
		if(!password.equals(confirm)){
			throw new ServiceException("e.sys.user.reset.password.confirm.invalid");
		}
		
		String salt=sysUserMapper.querySaltByUid(uid);
		Integer c = sysUserMapper.countByPassword(uid, encodePassword(origin, salt));
		if(c==null || c.intValue()<=0){
			throw new ServiceException("e.sys.user.reset.password.origin.invalid");
		}
		
		Integer impact = sysUserMapper.updatePassword(uid, encodePassword(password, salt));
		if(impact==null || impact.intValue()<=0){
			throw new ServiceException("e.sys.user.reset.password.failure");
		}
	}

	@Override
	public Pager<SysUser> pager(SysUserCond cond, Pager<SysUser> pager) {
		
		pager.setTotals(sysUserMapper.pageDefaultCount(cond));
		pager.setRecords(sysUserMapper.pageDefault(cond, pager));
		//TODO 需要处理账号前缀
		return pager;
	}

	@Override
	public SysUser doRegistByCompany(String mainAccount, Long cid, String account,
			String password, String confirm) throws ServiceException {
		if(mainAccount.contains(":")){
			throw new ServiceException("e.sys.user.accoun.save.noauth");
		}
		if(!account.startsWith(mainAccount+":")){
			throw new ServiceException("e.sys.user.accoun.format.invalid");
		}
		
		if(Strings.isNullOrEmpty(confirm) || !confirm.equals(password)){
			throw new ServiceException("e.sys.user.reset.password.confirm.invalid");
		}
		
		String rebuildedAccount = rebuildAccount(classifyOfAccount(account), account);
		if(existAccount(rebuildedAccount)){
			throw new ServiceException("e.regist.exist.account");
		}
		
		SysUser user = new SysUser();
		user.setAccount(account);
		user.setPassword(password);
		
		return saveAccount(user, cid, rebuildedAccount);
	}

	@Override
	public Integer doCountUserOfCompany(Long cid, Boolean withMainUser) {
		
		SysUserCond cond = new SysUserCond();
		cond.setCid(cid);
		Integer count = sysUserMapper.pageDefaultCount(cond);
		
		withMainUser = withMainUser==null?false:withMainUser;
		return withMainUser?count:count-1;
	}

	@Override
	public List<SysUser> excludeMainAccount(List<SysUser> userList) {
		
		if(userList==null){
			return null;
		}
		
		List<SysUser> excludedList = new ArrayList<SysUser>();  
		for(SysUser user: userList){
			if(user.getAccount().contains(":")){
				excludedList.add(user);
			}
		}
		
		return excludedList;
	}

	@Override
	public void doResetPasswordByAdmin(Long adminUid, Long uid, String password,
			String confirm) throws ServiceException {
		
		SysUser adminUser = sysUserMapper.queryById(adminUid);
		if(adminUser.getAccount().contains(":")){
			throw new ServiceException("e.sys.user.admin.forbid");
		}
		
		if(Strings.isNullOrEmpty(password)){
			throw new ServiceException("e.sys.user.reset.password.invalid");
		}
		
		if(!password.equals(confirm)){
			throw new ServiceException("e.sys.user.reset.password.confirm.invalid");
		}
		
		String salt=sysUserMapper.querySaltByUid(uid);
		if(Strings.isNullOrEmpty(salt)){
			LogHelper.debug(SysUserServiceImpl.class, "Error get salt from uid:"+uid);
			throw new ServiceException("e.sys.user.reset.password.failure");
		}
		
		Integer impact = sysUserMapper.updatePassword(uid, encodePassword(password, salt));
		if(impact==null || impact.intValue()<=0){
			throw new ServiceException("e.sys.user.reset.password.failure");
		}		
	}

	@Override
	public SessionUser doRegistByOauth(SysUserAuthModel auth, SysUserProfileModel profile) throws ServiceException {
		//TODO 模拟注册
		return null;
	}
}
