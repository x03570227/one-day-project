/**
 * 
 */
package net.caiban.pc.erp.domain.product;

import net.caiban.pc.erp.domain.BaseDomain;

/**
 * @author mays
 *
 */
public class Product extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	private String categoryCode;
	private String remark;
	private Integer cid;
	private Integer uidCreated;
	private Integer uidModified;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
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
	public Integer getUidModified() {
		return uidModified;
	}
	public void setUidModified(Integer uidModified) {
		this.uidModified = uidModified;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
