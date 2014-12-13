/**
 * 
 */
package net.caiban.pc.erp.persist.sys;

import java.util.List;

import net.caiban.pc.erp.domain.sys.SysCompany;

/**
 * @author mays
 *
 */
public interface SysCompanyMapper {

	/**
	 * 获取公司信息
	 * @param account
	 * @param classify
	 * @return
	 */
	public List<SysCompany> query();
	
	public Integer insert(SysCompany company);
}
