/**
 * 
 */
package net.caiban.pc.erp.mock;

import javax.annotation.Resource;

import net.caiban.pc.erp.BaseServiceTestCase;
import net.caiban.pc.erp.persist.sys.SysUserMapper;

/**
 * @author parox
 *
 */
public class Mock extends BaseServiceTestCase{

	@Resource
	private SysUserMapper sysUserMapper;
	
	public void mockUser(){
		System.out.println(sysUserMapper.countByAccount("八月下沙"));
	}
}
