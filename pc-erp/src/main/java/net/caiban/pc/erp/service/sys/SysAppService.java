/**
 * 
 */
package net.caiban.pc.erp.service.sys;

import net.caiban.pc.erp.domain.sys.SysApp;

/**
 * @author mays
 *
 */
public interface SysAppService {

	public SysApp queryByDomain(Integer cid, String domain);
	
	public SysApp saveOrUpdate(SysApp app);
	
}
