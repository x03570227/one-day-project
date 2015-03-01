/**
 * 
 */
package net.caiban.pc.erp.config;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;

import net.caiban.utils.file.PropertiesUtil;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

/**
 * @author mays
 * 
 */
@Component("appConst")
public class AppConst {

	public final static String SESSION_KEY = "sessionuserkey";
	
	/**
	 * 默认日期格式：yyyy-MM-dd HH:mm:ss
	 */
	public final static String DATE_FORMAT_DEFAULT="yyyy-MM-dd HH:mm:ss";
	/**
	 * 仅日期格式：yyyy-MM-dd
	 */
	public final static String DATE_FORMAT_DATE="yyyy-MM-dd";
	/**
	 * 仅时间格式：HH:mm:ss
	 */
	public final static String DATE_FORMAT_TIME="HH:mm:ss";
	
	public final static String LOGIN_REMEMBER_TOKEN = "_CLT";
	
	public static Map<String, String> CONFIG_PROPERTIES = null;
	
	@PostConstruct
	public void init(){
		try {
			CONFIG_PROPERTIES = PropertiesUtil.classpathRead("config.properties", PropertiesUtil.CHARSET_UTF8);
		} catch (IOException e) {
			CONFIG_PROPERTIES=Maps.newHashMap();
		}
	}
}
