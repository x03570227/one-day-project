/**
 * 
 */
package net.caiban.pc.erp.domain.product;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author mays
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
@Deprecated
public class ProductFull implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Product product;
	private ProductDefine define;
	private List<ProductPriceModel> price;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductDefine getDefine() {
		return define;
	}
	public void setDefine(ProductDefine define) {
		this.define = define;
	}
	public List<ProductPriceModel> getPrice() {
		return price;
	}
	public void setPrice(List<ProductPriceModel> price) {
		this.price = price;
	}
	
}
