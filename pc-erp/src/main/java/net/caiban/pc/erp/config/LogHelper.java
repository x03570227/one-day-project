/**
 * 
 */
package net.caiban.pc.erp.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于处理日志信息
 * @author mays
 *
 */
public class LogHelper {

	final static Logger logger = LoggerFactory.getLogger(LogHelper.class);
	
	public static Logger getLogger(){
		return logger;
	}

	public static <T> void debug(Class<T> clazz, String message){
		logger.debug("["+clazz.getName()+"]:"+message);
	}
	
	public static <T> void debug(Class<T> clazz, String message, Throwable t){
		logger.debug("["+clazz.getName()+"]:"+message, t);
	}
	
	public static <T> void info(Class<T> clazz, String message){
		logger.info("["+clazz.getName()+"]:"+message);
	}
	
	public static <T> void info(Class<T> clazz, String message, Throwable t){
		logger.info("["+clazz.getName()+"]:"+message, t);
	}
	
	public static <T> void warn(Class<T> clazz, String message){
		logger.warn("["+clazz.getName()+"]:"+message);
	}
	
	public static <T> void warn(Class<T> clazz, String message, Throwable t){
		logger.warn("["+clazz.getName()+"]:"+message, t);
	}
	
	public static <T> void error(Class<T> clazz, String message){
		logger.error("["+clazz.getName()+"]:"+message);
	}
	
	public static <T> void error(Class<T> clazz, String message, Throwable t) {
        logger.error("[" + clazz.getName() + "]:" + message, t);
    }
	
}
