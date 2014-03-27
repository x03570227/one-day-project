/**
 * 
 */
package net.caiban.pc.event.persist.sys;

import net.caiban.pc.event.domain.sys.SysUser;

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
	public String querySalt(@Param("account") String account);
	
	/**
	 * 密码验证，返回 UID
	 * @param account
	 * @param password
	 * @return
	 */
	public Integer queryUidByLogin(@Param("account") String account, 
			@Param("password") String password);
	
	/**
	 * 写入用户信息，第一次写
	 * @param user
	 * @return
	 */
	public Integer insert(SysUser user);
	
	/**
	 * 更新注册信息的UID，默认为0
	 * @param uid
	 * @param id
	 * @return
	 */
	public Integer updateUid(@Param("uid")Integer uid, @Param("id")Integer id);
}
