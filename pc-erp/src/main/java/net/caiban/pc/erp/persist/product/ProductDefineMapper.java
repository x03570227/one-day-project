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
	
	public ProductDefine queryOne(Long id);
	
	public ProductDefine queryOneByPid(Long pid);
	
	public Integer delete(Long id);
	
	public Integer deleteByPid(Long pid);
	
	public Integer update(ProductDefine define);
	
	public Integer updateByPid(ProductDefine define);
	
}
