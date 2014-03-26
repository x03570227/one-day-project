/**
 * 
 */
package net.caiban.pc.event.service.sys.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import net.caiban.pc.event.domain.SessionUser;
import net.caiban.pc.event.domain.sys.SysUser;
import net.caiban.pc.event.persist.sys.SysUserMapper;
import net.caiban.pc.event.service.sys.SysUserService;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionUser register(SysUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initSession(SessionUser user, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}
	
}
