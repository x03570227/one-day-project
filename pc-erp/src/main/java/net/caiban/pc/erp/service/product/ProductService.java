/**
 * 
 */
package net.caiban.pc.erp.service.product;

import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.product.Product;
import net.caiban.pc.erp.domain.product.ProductCond;
import net.caiban.pc.erp.domain.product.ProductDefine;
import net.caiban.pc.erp.domain.product.ProductFull;

/**
 * @author mays
 *
 */
public interface ProductService {

	public Pager<Product> pager(Pager<Product> pager, ProductCond cond);
	
	public ProductFull saveFull(ProductFull productFull);
	
	public Product update(Product product);
	
	public Integer remove(Integer id);
	
	public Product queryOne(Integer id, SessionUser user);
	
	public ProductFull queryOneFull(Integer id, Boolean readDefine, SessionUser user);
	
	public ProductFull updateFull(ProductFull productFull);
	
	public ProductFull initFull(SessionUser user, Product product, ProductDefine define);
}
