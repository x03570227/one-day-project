/**
 * 
 */
package net.caiban.pc.erp.domain.sys;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import net.caiban.pc.erp.domain.BaseCond;

/**
 * @author mays
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class SysUserCond extends BaseCond{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long cid;
	private Long uidNot;

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getUidNot() {
		return uidNot;
	}

	public void setUidNot(Long uidNot) {
		this.uidNot = uidNot;
	}
}
