/**
 * 
 */
package net.caiban.pc.erp.enums;

/**
 * @author mar
 *
 */
public enum RedisKeyEnum {
	WX_MSGID,ACCESSTOKEN
	;
	public String getKey(String key){
		return this.toString()+":"+key;
	}
}
