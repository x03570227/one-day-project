/**
 * 
 */
package net.caiban.pc.erp.persist.product;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.caiban.pc.erp.domain.product.ProductPrice;
import net.caiban.pc.erp.domain.product.ProductPriceModel;

/**
 * @author mays
 *
 */
public interface ProductPriceMapper {

	public Integer insert(ProductPrice price);
	
	public List<ProductPriceModel> queryByPid(@Param("pid") Long pid,
			@Param("minExpired") Date minExpired,
			@Param("maxExpired") Date maxExpired);
	
	public Integer delete(Long id);
	
	public Integer deleteByPid(Long pid);
	
	public Integer update(ProductPrice price);
	
	public Integer updateArchive(@Param("id") Long id, @Param("archive") String archive);
	
}
