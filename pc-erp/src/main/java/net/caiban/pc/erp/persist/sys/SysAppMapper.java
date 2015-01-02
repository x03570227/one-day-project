/**
 * 
 */
package net.caiban.pc.erp.persist.sys;

import org.apache.ibatis.annotations.Param;

import net.caiban.pc.erp.domain.sys.SysApp;

/**
 * @author mays
 *
 */
public interface SysAppMapper {

	public Integer insert(SysApp sysApp);
	
	public Integer update(SysApp sysApp);
	
	public SysApp queryByDomain(@Param("cid") Integer cid,
			@Param("domain") String domain);
	
}
