/**
 * 
 */
package net.caiban.pc.erp.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import net.caiban.utils.cache.JedisUtil;
import net.caiban.utils.http.HttpRequestUtil;

import org.springframework.stereotype.Component;

/**
 * @author mays
 *
 */
@Component("systemInit")
public class SystemInit {

	@PostConstruct
	public void init(){
		JedisUtil.initPool(null);
		HttpRequestUtil.monitor();
	}
	
	@PreDestroy
	public void destory(){
		HttpRequestUtil.shutdown();
	}
}
