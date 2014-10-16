/**
 * 
 */
package net.caiban.pc.erp.domain.sys;

import net.caiban.pc.erp.domain.BaseDomain;

/**
 * @author mays
 *
 */
public class SysUser extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer uid;
	private String classify;
	private String account;
	private String password;
	private String salt;
	private Integer cid;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	
}
