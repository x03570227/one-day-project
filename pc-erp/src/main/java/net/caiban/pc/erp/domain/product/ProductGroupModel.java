/**
 * 
 */
package net.caiban.pc.erp.domain.product;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author caiban
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class ProductGroupModel extends ProductGroup{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer productId;
	private Integer itemCount;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	
}
