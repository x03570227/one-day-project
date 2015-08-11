/**
 * 
 */
package net.caiban.pc.erp.event;

import net.caiban.pc.erp.service.product.ProductService;
import net.caiban.pc.erp.utils.ESClient;
import net.sf.json.JSONObject;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

/**
 * https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index-doc.html
 * @author parox
 *
 */
public class CreateProductIndexEvent extends ProductIndexEvent{

	
	public CreateProductIndexEvent(Integer id, ProductService productService){
		super(id, productService);
	}
	
	public void excuteCMD() throws EventException{
		
		Client client = ESClient.getClient();
		IndexResponse resp = client.prepareIndex(INDECI, TYPES, String.valueOf(product.getId())).setSource(
				JSONObject.fromObject(product).toString())
				.execute()
				.actionGet();
		
		System.out.println(JSONObject.fromObject(resp));
		
	}
	
	
}
