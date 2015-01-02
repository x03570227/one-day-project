/**
 * 
 */
package net.caiban.pc.erp.service.sys.impl;

import javax.annotation.Resource;

import net.caiban.pc.erp.domain.sys.SysApp;
import net.caiban.pc.erp.domain.sys.SysAppCond;
import net.caiban.pc.erp.persist.sys.SysAppMapper;
import net.caiban.pc.erp.service.sys.SysAppService;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

/**
 * @author mays
 *
 */
@Component("sysAppService")
public class SysAppServiceImpl implements SysAppService {

	@Resource
	private SysAppMapper sysAppMapper;
	
	@Override
	public SysApp queryByDomain(Integer cid, String domain) {
		if(cid==null){
			return null;
		}
		if(Strings.isNullOrEmpty(domain)){
			domain = SysAppCond.DEFAULT_DOMAIN;
		}
		
		return sysAppMapper.queryByDomain(cid, domain);
	}

	@Override
	public SysApp saveOrUpdate(SysApp app) {
		if(app==null){
			return null;
		}
		
		if(app.getId()!=null){
			sysAppMapper.update(app);
		}else{
			sysAppMapper.insert(app);
		}
		
		return app;
	}

}
