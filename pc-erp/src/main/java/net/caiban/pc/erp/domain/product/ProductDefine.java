/**
 * 
 */
package net.caiban.pc.erp.domain.product;

import net.caiban.pc.erp.domain.BaseDomain;

/**
 * @author mays
 *
 */
public class ProductDefine extends BaseDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer productId;
	private String details;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}

}
