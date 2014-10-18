/**
 * 
 */
package net.caiban.pc.erp.persist.product;

import java.util.List;

import net.caiban.pc.erp.domain.product.ProductPrice;

/**
 * @author mays
 *
 */
public interface ProductPriceMapper {

	public Integer save(ProductPrice price);
	
	public List<ProductPrice> query();
	
	public Integer delete(Integer id);
	
	public Integer update(ProductPrice price);
	
}
