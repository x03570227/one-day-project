/**
 * 
 */
package net.caiban.pc.erp.event;

import net.caiban.pc.erp.domain.product.ProductFull;
import net.caiban.pc.erp.domain.product.ProductSearchModel;
import net.caiban.pc.erp.service.product.ProductService;
import net.sf.json.JSONObject;

import com.google.common.base.Preconditions;

/**
 * https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index-doc.html
 * @author parox
 *
 */
public class ProductIndexEvent {

	protected ProductSearchModel product;
	private Integer id;
	
	final static String INDECI="caiban";
	final static String TYPES="products";
	
	private ProductService productService;
	
	public enum ACTION{
		
	}
	
	public ProductIndexEvent(Integer id, ProductService productService){
		this.id = id;
		this.productService = productService;
	}
	
	public void excute() throws EventException{
		//准备数据
		Preconditions.checkNotNull(id);
		
		ProductFull full = productService.queryOneFull(id, true, null);
		
		product = new ProductSearchModel();
		
		product.setId(full.getProduct().getId());
		product.setName(full.getProduct().getName());
		product.setRemark(full.getProduct().getRemark());
		product.setCategoryCode(full.getProduct().getCategoryCode());
		product.setCode(full.getProduct().getCode());
		product.setUidCreated(full.getProduct().getUidCreated());
		product.setUidModified(full.getProduct().getUidModified());
		product.setStatusLife(full.getProduct().getStatusLife());
		product.setCreatedTime(full.getProduct().getGmtCreated().getTime());
		product.setModifiedTime(full.getProduct().getGmtModified().getTime());
		product.setDetails(JSONObject.fromObject(full.getDefine().getDetails()));
		
		excuteCMD();
	}
	
	public void excuteCMD() throws EventException{
//		Client client = ESClient.getClient();
//		IndexResponse resp = client.prepareIndex(INDECI, TYPES, String.valueOf(product.getId())).setSource(
//				JSONObject.fromObject(product).toString())
//				.execute()
//				.actionGet();
//		System.out.println(JSONObject.fromObject(resp));
		
	}
	
	
}
