/**
 * 
 */
package net.caiban.pc.erp.domain.product;

import java.io.Serializable;
import java.util.List;

/**
 * @author mays
 *
 */
public class ProductFull implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Product product;
	private ProductDefine define;
	private List<ProductPrice> price;
	
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
	public List<ProductPrice> getPrice() {
		return price;
	}
	public void setPrice(List<ProductPrice> price) {
		this.price = price;
	}
	
}
