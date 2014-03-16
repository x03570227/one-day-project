/**
 * 
 */
package net.caiban.pc.event.domain;

import java.io.Serializable;

/**
 * @author mays
 *
 */
public class SessionUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer uid;
	private String account;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
}
