/**
 * 
 */
package net.caiban.pc.erp.persist.sys;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import net.caiban.pc.erp.domain.sys.SysLoginRemember;

/**
 * @author mays
 *
 */
public interface SysLoginRememberMapper {

	public Integer insert(SysLoginRemember loginRemember);
	
	public SysLoginRemember queryOneByToken(String token);
	
	public Integer updateGmtRefresh(@Param("token") String token,
			@Param("gmtExpired") Date gmtExpired,
			@Param("gmtRefresh") Date gmtRefresh);
	
	public Integer deleteByToken(String token);
}
	
