/**
 * 
 */
package net.caiban.pc.event.service.sys;

import javax.servlet.http.HttpServletRequest;

import net.caiban.pc.event.domain.SessionUser;
import net.caiban.pc.event.domain.sys.SysUser;

/**
 * @author mays
 *
 */
public interface SysUserService {

	public SessionUser login(SysUser user);
	
	public SessionUser register(SysUser user);
	
	public void initSession(SessionUser user, HttpServletRequest request);
}
