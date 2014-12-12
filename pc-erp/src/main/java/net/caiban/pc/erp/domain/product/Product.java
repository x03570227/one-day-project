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

	private static final long serialVersionUID = 1L;
	
	public final static String LIFE_DRAFT="DRAFT";
	public final static String LIFE_SALING="SALING";
	public final static String LIFE_SHELVES="SHELVES";
	
	public final static String SORT_COLUMN_ID="product.id";
	
	private String code;
	private String name;
	private String categoryCode;
	private String remark;
	private Integer cid;
	private Integer uidCreated;
	private Integer uidModified;
	private String statusLife;
	
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
	public String getStatusLife() {
		return statusLife;
	}
	public void setStatusLife(String statusLife) {
		this.statusLife = statusLife;
	}
	

}
