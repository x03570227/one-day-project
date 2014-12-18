/**
 * 
 */
package net.caiban.pc.erp.domain.sys;

import net.caiban.pc.erp.domain.BaseCond;

/**
 * @author mays
 *
 */
public class SysUserCond extends BaseCond{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer cid;
	private Integer uidNot;
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getUidNot() {
		return uidNot;
	}
	public void setUidNot(Integer uidNot) {
		this.uidNot = uidNot;
	}
	
	
}
