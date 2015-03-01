/**
 * 
 */
package net.caiban.pc.erp.service.sys;

import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.exception.ServiceException;


/**
 * @author mays
 *
 */
public interface SysLoginRememberService {

	public SessionUser validateToken(String token) throws ServiceException;
	
	public void removeToken(String token);
}
