/**
 * 
 */
package net.caiban.pc.erp.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import net.caiban.pc.erp.domain.sys.SysCompany;
import net.caiban.pc.erp.persist.sys.SysCompanyMapper;
import net.caiban.pc.erp.service.sys.SysCompanyService;

import org.springframework.stereotype.Component;

/**
 * @author mays
 *
 */
@Component("sysCompany")
public class SysCompanyServiceImpl implements SysCompanyService{

	@Resource
	private SysCompanyMapper sysCompanyMapper;

	@Override
	public List<SysCompany> query() {
		return sysCompanyMapper.query();
	}

	@Override
	public SysCompany queryOne(Long cid) {
		if(cid==null || cid.intValue()<=0){
			return null;
		}
		return sysCompanyMapper.queryOne(cid);
	}
	
	
}
