/**
 * 
 */
package net.caiban.pc.erp.domain.sys;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import net.caiban.pc.erp.domain.BaseDomain;

/**
 * @author mays
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class SysUser extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	public final static String CLASSIFY_A="A";
//	public final static String CLASSIFY_M="M";
//	public final static String CLASSIFY_E="E";

	public enum CLASSIFY{
		A, //普通账户（默认情况）
		M, //手机号账户
		E, //邮件账户
		W  //微信 oauth2 账户
		;
	}

	public final static int ACCEPT_TRUE=1;
	public final static long DEFAULT_UID=0;
	
	private Long uid;
	private String classify;
	private String account;
	private String password;
	private String salt;
	private Long cid;

	private String oauthProfile;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
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

	public String getOauthProfile() {
		return oauthProfile;
	}

	public void setOauthProfile(String oauthProfile) {
		this.oauthProfile = oauthProfile;
	}
}
