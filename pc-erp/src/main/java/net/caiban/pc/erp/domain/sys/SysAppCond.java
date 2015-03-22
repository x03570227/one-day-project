/**
 * 
 */
package net.caiban.pc.erp.domain.sys;

import net.caiban.pc.erp.domain.BaseCond;

/**
 * @author mays
 *
 */
public class SysAppCond extends BaseCond{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static String DEFAULT_DOMAIN="koudaitong.com";
	public final static String DEFAULT_TYPE="API";
	
	private String domain;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
}
