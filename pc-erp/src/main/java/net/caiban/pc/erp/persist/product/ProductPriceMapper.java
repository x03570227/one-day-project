/**
 * 
 */
package net.caiban.pc.erp.persist.product;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.caiban.pc.erp.domain.product.ProductPrice;

/**
 * @author mays
 *
 */
public interface ProductPriceMapper {

	public Integer insert(ProductPrice price);
	
	public List<ProductPrice> queryByPid(@Param("pid") Integer pid,
			@Param("minExpired") Date minExpired,
			@Param("maxExpired") Date maxExpired);
	
	public Integer delete(Integer id);
	
	public Integer deleteByPid(Integer pid);
	
	public Integer update(ProductPrice price);
	
	public Integer updateArchive(@Param("id") Integer id, @Param("archive") String archive);
	
}
