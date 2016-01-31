/**
 * 
 */
package net.caiban.pc.erp.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import net.caiban.pc.erp.domain.sys.SysApp;
import net.caiban.pc.erp.domain.sys.SysAppCond;
import net.caiban.pc.erp.persist.sys.SysAppMapper;
import net.caiban.pc.erp.service.sys.SysAppService;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * @author mays
 *
 */
@Component("sysAppService")
public class SysAppServiceImpl implements SysAppService {

	@Resource
	private SysAppMapper sysAppMapper;
	
	@Override
	public SysApp queryByDomain(Long cid, String domain) {
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

	@Override
	public List<String> getDomains() {
		SysApp.DOMAIN[] domains = SysApp.DOMAIN.values();
		List<String> resultList = Lists.newArrayList(); 
		for(SysApp.DOMAIN domain: domains){
			resultList.add(domain.toString());
		}
		return resultList;
	}

	public static void main(String[] args) {
		SysApp.DOMAIN[] domains = SysApp.DOMAIN.values();
		List<String> resultList = Lists.newArrayList(); 
		for(SysApp.DOMAIN domain: domains){
			resultList.add(domain.toString());
		}
		System.out.println(resultList.size());
	}
}
