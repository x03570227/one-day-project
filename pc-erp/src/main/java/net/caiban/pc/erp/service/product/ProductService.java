/**
 * 
 */
package net.caiban.pc.erp.service.product;

import java.util.List;

import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.product.Product;
import net.caiban.pc.erp.domain.product.ProductCond;
import net.caiban.pc.erp.domain.product.ProductDefine;
import net.caiban.pc.erp.domain.product.ProductFull;
import net.caiban.pc.erp.domain.product.ProductGroupModel;
import net.caiban.pc.erp.domain.product.ProductModel;
import net.caiban.pc.erp.domain.product.ProductPriceModel;
import net.caiban.pc.erp.exception.ServiceException;

/**
 * @author mays
 *
 */
public interface ProductService {

	public Pager<Product> pager(Pager<Product> pager, ProductCond cond);
	
	public ProductFull saveFull(ProductFull productFull);
	
	public Product update(Product product);
	
	public Integer remove(SessionUser user, Long id);
	
	public Product queryOne(Long id, SessionUser user);
	
	public ProductFull queryOneFull(Long id, Boolean readDefine, SessionUser user);
	
	public ProductFull updateFull(ProductFull productFull);
	
	public ProductFull initFull(SessionUser user, Product product, ProductDefine define);
	
	public Integer countProduct(Long cid);
	
	public Product queryOneByCode(Long cid, String code);
	
	public List<ProductPriceModel> queryPriceModels(ProductCond cond);
	
	/**
	 * Add new price of product.
	 * @param price
	 * @return
	 * @throws ServiceException
	 */
	public ProductPriceModel doAddPrice(ProductPriceModel price) throws ServiceException;
	
	/**
	 * Remove one price of product.
	 * @param id
	 * @param productId
	 * @throws ServiceException
	 */
	public void removePrice(Long id, Long productId) throws ServiceException;
	
	/**
	 * Add product to a group, if the group does not exist, create it.
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	public ProductGroupModel doGroup(SessionUser user, ProductGroupModel model)
			throws ServiceException;
	
	/**
	 * Query groups of product. Default limited to 200.
	 * @param user
	 * @param cond
	 * @return
	 */
	public List<ProductGroupModel> queryGroups(SessionUser user,
			ProductCond cond);
	
	/**
	 * Remove group and all related product's id.
	 * @param user
	 * @throws ServiceException
	 */
	public void removeGroup(SessionUser user, Long groupId)
			throws ServiceException;
	
	/**
	 * Remove product from group. If it's last one, also remove group.
	 * @param user
	 * @param productId
	 * @throws ServiceException
	 */
	public void removeProductFromGroup(SessionUser user, Long groupId,
			Long productId) throws ServiceException;
	
	/**
	 * Query all products of group.
	 * @param cond
	 * @return
	 */
	public List<ProductModel> queryProductOfGroup(ProductCond cond);
}
