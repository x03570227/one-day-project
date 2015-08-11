/**
 * 
 */
package net.caiban.pc.erp.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import net.caiban.pc.erp.event.EventUtil;
import net.caiban.pc.erp.utils.ESClient;
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
		//暂时将验证码保存在session里
//		JedisUtil.initPool(null);
		HttpRequestUtil.monitor();
		EventUtil.initAsync();
	}
	
	@PreDestroy
	public void destory(){
		HttpRequestUtil.shutdown();
		ESClient.shutdown();
	}
}
