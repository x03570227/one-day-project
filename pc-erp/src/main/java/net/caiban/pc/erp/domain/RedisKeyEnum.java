/**
 * 
 */
package net.caiban.pc.erp.domain;

/**
 * @author mar
 *
 */
public enum RedisKeyEnum {
	WX_MSGID
	;
	public String getKey(String key){
		return this.toString()+":"+key;
	}
}
