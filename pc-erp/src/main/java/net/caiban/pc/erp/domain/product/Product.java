/**
 * 
 */
package net.caiban.pc.erp.domain.product;

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
public class Product extends BaseDomain {

	private static final long serialVersionUID = 1L;
	
//	public final static String LIFE_DRAFT="DRAFT";
//	public final static String LIFE_SALING="SALING";
//	public final static String LIFE_SHELVES="SHELVES";
	
	public enum STATUS_LIFE{
		DRAFT, SAILING, SHELVES
		;
	}
	
	public final static String SORT_COLUMN_ID="product.id";
	
	private String code;
	private String name;
	private String categoryCode;
	private String remark;
	private Long cid;
	private Long uidCreated;
	private Long uidModified;
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

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getUidCreated() {
		return uidCreated;
	}

	public void setUidCreated(Long uidCreated) {
		this.uidCreated = uidCreated;
	}

	public Long getUidModified() {
		return uidModified;
	}

	public void setUidModified(Long uidModified) {
		this.uidModified = uidModified;
	}
}
