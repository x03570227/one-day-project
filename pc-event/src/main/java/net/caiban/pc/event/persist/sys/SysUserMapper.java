/**
 * 
 */
package net.caiban.pc.event.persist.sys;

<<<<<<< HEAD
=======
import org.apache.ibatis.annotations.Param;

>>>>>>> update event v1.0.4-SNAPSHOT
/**
 * @author mays
 *
 */
public interface SysUserMapper {

<<<<<<< HEAD
=======
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
>>>>>>> update event v1.0.4-SNAPSHOT
}
