/**
 * 
 */
package net.caiban.pc.erp.service.sys.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.config.LogHelper;
import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.sys.SysCompany;
import net.caiban.pc.erp.domain.sys.SysLoginRemember;
import net.caiban.pc.erp.domain.sys.SysUser;
import net.caiban.pc.erp.domain.sys.SysUserCond;
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
		
		//XXX 变更为通过查找UID对应的密码登录
		String classify = classifyOfAccount(user.getAccount());
		String salt = sysUserMapper.querySalt(rebuildAccount(classify,
				user.getAccount()));
		if (StringUtils.isEmpty(salt)) {
			throw new ServiceException("e.login");
		}

		SysUser confirmedUser = sysUserMapper.queryUidByLogin(
				rebuildAccount(classify, user.getAccount()),
				encodePassword(user.getPassword(), salt));
		if (confirmedUser == null) {
			throw new ServiceException("e.login.failure");
		}

		return new SessionUser(confirmedUser.getUid(), user.getAccount(), confirmedUser.getCid());
	}

	@Override
	public SessionUser doRegist(SysUser user, SysCompany company,
			String passwordRepeat, Integer accept) throws ServiceException {
		
		if(accept == null || accept.intValue()!=SysUserService.ACCEPT_TRUE){
			throw new ServiceException("e.regist");
		}
		
		if(user==null){
			throw new ServiceException("e.regist");
		}
		
		if(StringUtils.isEmpty(passwordRepeat) || !passwordRepeat.equals(user.getPassword())){
			throw new ServiceException("e.regist");
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
	
	private SysUser saveAccount(SysUser user, Integer cid, String rebuildedAccount) throws ServiceException{
		SysUser registUser = new SysUser();
		registUser.setSalt(randomSalt());
		registUser.setClassify(classifyOfAccount(user.getAccount()));
		registUser.setAccount(rebuildedAccount);
		registUser.setPassword(encodePassword(user.getPassword(), registUser.getSalt()));
		registUser.setUid(DEFAULT_UID);
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
			return SysUserService.CLASSIFY_E;
		}
		
		if(ValidateUtil.isMobile(account)){
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
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println(MD5.encode("135246e25iirlljqq73hqp7023d2i701n503mq", MD5.LENGTH_32));
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
	public Integer queryIdByAccount(String account) {
		
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
	public void resetPassword(Integer uid, String origin, String password, String confirm)
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
	public SysUser doRegistByCompany(String mainAccount, Integer cid, String account,
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
	public Integer doCountUserOfCompany(Integer cid, Boolean withMainUser) {
		
		SysUserCond cond = new SysUserCond();
		cond.setCid(cid);
		Integer count = sysUserMapper.pageDefaultCount(cond);
		
		withMainUser = withMainUser==null?false:withMainUser;
		return withMainUser?count:count-1;
	}
}
