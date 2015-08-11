/**
 * 
 */
package net.caiban.pc.erp.event;

import net.caiban.pc.erp.service.product.ProductService;
import net.caiban.pc.erp.utils.ESClient;
import net.sf.json.JSONObject;

import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;

/**
 * @author parox
 *
 */
public class UpdateProductIndexEvent extends ProductIndexEvent{
	public UpdateProductIndexEvent(Integer id, ProductService productService){
		super(id, productService);
	}
	
	public void excuteCMD() throws EventException{
		Client client = ESClient.getClient();
		UpdateResponse resp = client.prepareUpdate(INDECI, TYPES, String.valueOf(product.getId())).setDoc(
				JSONObject.fromObject(product).toString())
				.execute()
				.actionGet();
		
		System.out.println(JSONObject.fromObject(resp));
		
	}
}
