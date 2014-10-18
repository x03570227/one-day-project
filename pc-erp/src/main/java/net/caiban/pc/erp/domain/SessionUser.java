/**
 * 
 */
package net.caiban.pc.erp.domain;

import java.io.Serializable;

/**
 * @author mays
 *
 */
public class SessionUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer uid;
	private String account;
	private Integer cid;
	
	public SessionUser(Integer uid, String account, Integer cid) {
		super();
		this.uid = uid;
		this.account = account;
		this.cid=cid;
	}
	
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
	public SessionUser() {
		super();
	}

	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	
}
