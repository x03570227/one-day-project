/**
 * 
 */
package net.caiban.pc.event.service.sys;

<<<<<<< HEAD
import javax.servlet.http.HttpServletRequest;

=======
>>>>>>> update event v1.0.4-SNAPSHOT
import net.caiban.pc.event.domain.SessionUser;
import net.caiban.pc.event.domain.sys.SysUser;

/**
 * @author mays
 *
 */
public interface SysUserService {
<<<<<<< HEAD
=======
	
	public final static String CLASSIFY_A="A";
	public final static String CLASSIFY_M="M";
	public final static String CLASSIFY_E="E";
>>>>>>> update event v1.0.4-SNAPSHOT

	public SessionUser login(SysUser user);
	
	public SessionUser register(SysUser user);
	
<<<<<<< HEAD
	public void initSession(SessionUser user, HttpServletRequest request);
=======
>>>>>>> update event v1.0.4-SNAPSHOT
}
