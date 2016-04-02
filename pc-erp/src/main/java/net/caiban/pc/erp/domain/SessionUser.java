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
	
	private Long uid;
	private String account;
	private Long cid;

    private static final long DEFAULT_CID = 0l;

	public SessionUser(Long uid, String account, Long cid) {
		super();
		this.uid = uid;
		this.account = account;
		this.cid=cid;
	}

    public SessionUser(Long uid, String account) {
        this.uid = uid;
        this.account = account;
        this.cid = DEFAULT_CID;
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

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
}
