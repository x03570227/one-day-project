/**
 * 
 */
package net.caiban.pc.erp.domain.calendar;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import net.caiban.pc.erp.domain.BaseDomain;

/**
 * @author parox
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class Calendar extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer cid;
	private Integer uidCreated;
	private Integer uidOwner;
	private String name;
	private String remark;
	private String accessLevel;
	private String color;
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getUidCreated() {
		return uidCreated;
	}
	public void setUidCreated(Integer uidCreated) {
		this.uidCreated = uidCreated;
	}
	public Integer getUidOwner() {
		return uidOwner;
	}
	public void setUidOwner(Integer uidOwner) {
		this.uidOwner = uidOwner;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	
}
