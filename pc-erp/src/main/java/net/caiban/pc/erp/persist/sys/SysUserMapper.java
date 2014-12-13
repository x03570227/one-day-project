/**
 * 
 */
package net.caiban.pc.erp.persist.sys;

import net.caiban.pc.erp.domain.sys.SysUser;

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
	public SysUser queryUidByLogin(@Param("account") String account, 
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
	
	/**
	 * 根据用户账号查询用户UID
	 * @param account
	 * @return
	 */
	public Integer queryUidByAccount(String account);
	
	public Integer countByAccount(String account);
}
