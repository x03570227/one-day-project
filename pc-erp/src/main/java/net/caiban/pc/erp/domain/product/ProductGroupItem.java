/**
 * 
 */
package net.caiban.pc.erp.domain.product;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import net.caiban.pc.erp.domain.BaseDomain;

/**
 * @author caiban
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class ProductGroupItem extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer productGroupId;
	private Integer productId;
	private String isPrimary;
	
	public Integer getProductGroupId() {
		return productGroupId;
	}
	public void setProductGroupId(Integer productGroupId) {
		this.productGroupId = productGroupId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}
}
