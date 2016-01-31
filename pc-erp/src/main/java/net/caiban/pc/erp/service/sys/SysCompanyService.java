/**
 * 
 */
package net.caiban.pc.erp.service.sys;

import java.util.List;

import net.caiban.pc.erp.domain.sys.SysCompany;

/**
 * @author mays
 *
 */
public interface SysCompanyService {

	public List<SysCompany> query();
	
	public SysCompany queryOne(Long cid);
}
