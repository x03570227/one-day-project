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
	
	public final static String CLASSIFY_A="A";
	public final static String CLASSIFY_M="M";
	public final static String CLASSIFY_E="E";

	public SessionUser login(SysUser user);
	
	public SessionUser register(SysUser user);
}
