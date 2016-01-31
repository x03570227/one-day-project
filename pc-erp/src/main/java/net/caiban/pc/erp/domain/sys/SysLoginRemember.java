/**
 * 
 */
package net.caiban.pc.erp.domain.sys;

import java.util.Date;

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
public class SysLoginRemember extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long uid;
	private Date gmtExpired;
	private String rememberToken;
	private Date gmtRefresh;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Date getGmtExpired() {
		return gmtExpired;
	}
	public void setGmtExpired(Date gmtExpired) {
		this.gmtExpired = gmtExpired;
	}
	public String getRememberToken() {
		return rememberToken;
	}
	public void setRememberToken(String rememberToken) {
		this.rememberToken = rememberToken;
	}
	public Date getGmtRefresh() {
		return gmtRefresh;
	}
	public void setGmtRefresh(Date gmtRefresh) {
		this.gmtRefresh = gmtRefresh;
	}

}
