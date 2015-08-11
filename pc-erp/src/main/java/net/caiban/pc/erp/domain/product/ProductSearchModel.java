/**
 * 
 */
package net.caiban.pc.erp.domain.product;

import net.sf.json.JSONObject;

/**
 * @author parox
 *
 */
public class ProductSearchModel extends Product {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long createdTime;
	private Long modifiedTime;
	private JSONObject details;
	
	public Long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}
	public Long getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public JSONObject getDetails() {
		return details;
	}
	public void setDetails(JSONObject details) {
		this.details = details;
	}
	
	
}
