/**
 * 
 */
package net.caiban.pc.erp.persist.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.product.Product;
import net.caiban.pc.erp.domain.product.ProductCond;
import net.caiban.pc.erp.domain.product.ProductModel;

/**
 * @author mays
 *
 */
public interface ProductMapper {

	public Integer insert(Product product);
	
	public List<Product> pagerDefault(@Param("pager") Pager<Product> pager, @Param("cond") ProductCond cond);
	
	public Integer pagerDefaultCount(@Param("cond") ProductCond cond);
	
	public Integer delete(Long id);
	
	public Integer update(Product product);
	
	public Product queryOne(Long id);
	
	public Product queryOneByCode(@Param("cid")Long cid, @Param("code") String code);
	
	public String queryCode(Long id);
	
	public Integer countByCid(@Param("id") Long id, @Param("cid") Long cid);
	
	public List<ProductModel> queryProductOfGroup(@Param("cond") ProductCond cond);
	
}
