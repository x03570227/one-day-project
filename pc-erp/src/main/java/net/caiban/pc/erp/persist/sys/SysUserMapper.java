/**
 * 
 */
package net.caiban.pc.erp.persist.sys;

import java.util.List;

import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.sys.SysUser;
import net.caiban.pc.erp.domain.sys.SysUserCond;

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
	 * 根据账户ID获取盐值
	 * @param uid
	 * @return
	 */
	public String querySaltByUid(@Param("uid") Long uid);
	/**
	 * 密码验证，返回 UID
	 * @param account
	 * @param password
	 * @return
	 */
	@Deprecated
	public SysUser queryUidByLogin(@Param("account") String account, 
			@Param("password") String password);
	
	public SysUser queryUserByPassword(@Param("uid") Long uid,
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
	public Integer updateUid(@Param("uid")Long uid, @Param("id")Long id);
	
	/**
	 * 根据用户账号查询用户UID
	 * @param account
	 * @return
	 */
	public Long queryUidByAccount(String account);
	
	public Integer countByAccount(String account);
	
	public Integer countByPassword(@Param("uid")Long uid, @Param("password")String password);
	
	public Integer updatePassword(@Param("uid")Long uid, @Param("password")String password);
	
	public List<SysUser> pageDefault(@Param("cond") SysUserCond cond, @Param("pager") Pager<SysUser> pager);
	
	public Integer pageDefaultCount(@Param("cond") SysUserCond cond);
	
	public SysUser queryById(Long id);
	
}
