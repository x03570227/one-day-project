/**
 * 
 */
package net.caiban.pc.erp.service.sys.impl;

import java.util.Date;

import javax.annotation.Resource;

import net.caiban.pc.erp.config.AppConst;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.sys.SysLoginRemember;
import net.caiban.pc.erp.domain.sys.SysUser;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.persist.sys.SysLoginRememberMapper;
import net.caiban.pc.erp.persist.sys.SysUserMapper;
import net.caiban.pc.erp.service.sys.SysLoginRememberService;
import net.caiban.utils.DateUtil;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

/**
 * @author mays
 *
 */
@Component("sysLoginRemember")
public class SysLoginRememberServiceImpl implements SysLoginRememberService {

	@Resource
	private SysLoginRememberMapper sysLoginRememberMapper;
	@Resource
	private SysUserMapper sysUserMapper;
	
	@Override
	public SessionUser validateToken(String token) throws ServiceException{
		
		SysLoginRemember remember = sysLoginRememberMapper.queryOneByToken(token);
		
		if(remember==null){
			throw new ServiceException("TOKEN_NOT_EXIST");
		}
		
		SysUser user = sysUserMapper.queryById(remember.getUid());
		if(user == null){
			throw new ServiceException("USER_NOT_EXIST");
		}
		
		Integer rememberDay = Integer.valueOf(AppConst.CONFIG_PROPERTIES.get("remember.day"));
		Date now  = new Date();
		sysLoginRememberMapper.updateGmtRefresh(token, DateUtil.getDateAfterDays(now, rememberDay), now);
		
		return new SessionUser(user.getUid(), accountOnly(user.getAccount()), user.getCid());
	}
	
	//TODO 账号处理类需要提取
	private String accountOnly(String account){
		if(Strings.isNullOrEmpty(account) || !account.contains("#")){
			return account;
		}
		return account.split("#")[1];
	}

	@Override
	public void removeToken(String token) {
		if(Strings.isNullOrEmpty(token)){
			return ;
		}
		sysLoginRememberMapper.deleteByToken(token);
	}
	
}
