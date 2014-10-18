/**
 * 
 */
package net.caiban.pc.erp.persist.product;

import net.caiban.pc.erp.domain.product.ProductDefine;

/**
 * @author mays
 *
 */
public interface ProductDefineMapper {

	public Integer save(ProductDefine define);
	
	public ProductDefine queryOne(Integer id);
	
	public Integer delete(Integer id);
	
	public Integer update(ProductDefine define);
	
}
