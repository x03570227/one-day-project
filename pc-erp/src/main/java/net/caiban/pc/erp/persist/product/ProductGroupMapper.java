/**
 * 
 */
package net.caiban.pc.erp.persist.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.caiban.pc.erp.domain.product.ProductCond;
import net.caiban.pc.erp.domain.product.ProductGroupItem;
import net.caiban.pc.erp.domain.product.ProductGroupModel;

/**
 * @author caiban
 *
 */
public interface ProductGroupMapper {

	public Long queryIdByName(String name);
	
	public Integer insert(ProductGroupModel group);
	
	public Integer insertItem(ProductGroupItem item);
	
	public List<ProductGroupModel> queryGroups(@Param("cond") ProductCond cond);
	
	public Integer delete(Long id);
	
	public Integer deleteItem(@Param("groupId") Long groupId,
			@Param("productId") Long productId);

	public Integer countItem(@Param("groupId") Long groupId,
			@Param("productId") Long productId);

}
