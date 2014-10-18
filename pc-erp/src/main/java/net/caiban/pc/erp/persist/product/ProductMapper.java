/**
 * 
 */
package net.caiban.pc.erp.persist.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.product.Product;
import net.caiban.pc.erp.domain.product.ProductCond;

/**
 * @author mays
 *
 */
public interface ProductMapper {

	public Integer save(Product product);
	
	public List<Product> pagerDefault(@Param("pager") Pager<Product> pager, @Param("cond") ProductCond cond);
	
	public Integer pagerDefaultCount(ProductCond cond);
	
	public Integer delete(Integer id);
	
	public Integer update(Product product);
	
	public Product queryOne(Integer id);
	
}
