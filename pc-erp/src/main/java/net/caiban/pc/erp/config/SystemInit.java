/**
 * 
 */
package net.caiban.pc.erp.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import net.caiban.utils.cache.JedisUtil;
import net.caiban.utils.http.HttpRequestUtil;

/**
 * @author mays
 *
 */
@Component("systemInit")
public class SystemInit {
	
	@PostConstruct
	public void init(){
		//暂时将验证码保存在session里
//		JedisUtil.initPool(null);
		HttpRequestUtil.monitor();
	}
	
	@PreDestroy
	public void destory(){
		HttpRequestUtil.shutdown();
	}
}
