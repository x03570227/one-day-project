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

	public Integer insert(ProductDefine define);
	
	public ProductDefine queryOne(Integer id);
	
	public ProductDefine queryOneByPid(Integer pid);
	
	public Integer delete(Integer id);
	
	public Integer deleteByPid(Integer pid);
	
	public Integer update(ProductDefine define);
	
}
