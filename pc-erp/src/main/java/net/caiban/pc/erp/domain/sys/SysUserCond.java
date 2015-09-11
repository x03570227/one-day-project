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
