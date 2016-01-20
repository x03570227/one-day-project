/**
 * 
 */
package net.caiban.pc.erp.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import main.java.com.UpYun;
import net.caiban.pc.erp.config.AppConst;
import net.caiban.utils.DateUtil;

/**
 * @author mar
 *
 */
public class UpyunUtils {
	
	private static Lock lock = new ReentrantLock();
	
	private static Map<String, UpYun> upyuns = new HashMap<String, UpYun>();
	
	public static UpYun getClient(String namespace){
		lock.lock();
		try {
			
			if(upyuns.get(namespace)!=null){
				return upyuns.get(namespace);
			}
			
			UpYun client = new UpYun(namespace, AppConst.getConfig("upyun.op"), AppConst.getConfig("upyun.op.passwd"));
			
			client.setDebug("true".equalsIgnoreCase(AppConst.getConfig("upyun.debug"))?true:false);
			client.setApiDomain(UpYun.ED_AUTO);
			client.setTimeout(Integer.valueOf(AppConst.getConfig("upyun.timeout")));
			
			upyuns.put(namespace, client);
			return client;
		} finally {
			lock.unlock();
		}
	}
	
	public static String getMonthPath(String filename){
		Date now = new Date();
		
		String year=DateUtil.toString(now, "yyyy");
		String month=DateUtil.toString(now, "MM");
		
		String path="/"+year+"/"+month+"/"+filename;
		
		return path;
	}

}
