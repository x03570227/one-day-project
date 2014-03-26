/**
 * 
 */
package net.caiban.pc.event.persist.sys;

import org.apache.ibatis.annotations.Param;

/**
 * @author mays
 *
 */
public interface SysUserMapper {

	/**
	 * 获取盐值
	 * @param account
	 * @param classify
	 * @return
	 */
	public String querySalt(@Param("account") String account, 
			@Param("classify") String classify);
	
	public Integer queryUidByLogin(@Param("account") String account, 
			@Param("password") String password);
}
