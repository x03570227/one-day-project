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

	public Integer insert(Product product);
	
	public List<Product> pagerDefault(@Param("pager") Pager<Product> pager, @Param("cond") ProductCond cond);
	
	public Integer pagerDefaultCount(@Param("cond") ProductCond cond);
	
	public Integer delete(Integer id);
	
	public Integer update(Product product);
	
	public Product queryOne(Integer id);
	
	public Product queryOneByCode(@Param("cid")Integer cid, @Param("code") String code);
	
	public String queryCode(Integer id);
	
	public Integer countByCid(Integer id, Integer cid);
}
