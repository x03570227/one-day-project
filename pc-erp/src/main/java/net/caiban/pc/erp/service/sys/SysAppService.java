/**
 * 
 */
package net.caiban.pc.erp.service.sys;

import java.util.List;

import net.caiban.pc.erp.domain.sys.SysApp;

/**
 * @author mays
 *
 */
public interface SysAppService {

	public SysApp queryByDomain(Integer cid, String domain);
	
	public SysApp saveOrUpdate(SysApp app);
	
	public List<String> getDomains();
	
}
