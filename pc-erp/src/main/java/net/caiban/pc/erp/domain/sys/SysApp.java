/**
 * 
 */
package net.caiban.pc.erp.domain.sys;

import net.caiban.pc.erp.domain.BaseDomain;

/**
 * @author mays
 *
 */
public class SysApp extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum DOMAIN{
		
		KDT("koudaitong.com"), A1688("1688.com");
		
		private String domain;
		
		private DOMAIN(String domain){
			this.domain = domain;
		}
		
		@Override
		public String toString(){
			return this.domain; 
		}
	}

	private Integer cid;
	private String domain;
	private String appKey;
	private String appSecret;
	private String refreshToken;
	private String accessToken;
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	
}
