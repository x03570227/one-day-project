/**
 * 
 */
package net.caiban.pc.erp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

/**
 * @author mays
 *
 */
public class ValidateUtil {

	public static boolean isMobile(String mobile){
		
		if(Strings.isNullOrEmpty(mobile)){
			return false;
		}
		
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
		Matcher m = p.matcher(mobile);
		
		return m.matches();
	}
}
