/**
 * 
 */
package net.caiban.pc.erp.utils;

import net.sf.json.JSONObject;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

/**
 * @author parox
 *
 */
public class ESClient {

	private static Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "parox").put("client.transport.sniff", true).build();
	private static Client client=new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
	
	public static Client getClient(){
		return client;
	}
	
	public static void shutdown(){
		if(client!=null){
			client.close();
		}
	}
	
	public static void main(String[] args) {
//		IndicesStatsResponse resp = ESClient.getClient().admin().indices().prepareStats("caiban").execute().actionGet();
//		
//		System.out.println(resp.toString());
//		System.out.println(resp.getSuccessfulShards());
		
		SearchResponse resp = ESClient.getClient().prepareSearch("caiban")
				.setTypes("products")
//				.setQuery(QueryBuilders.multiMatchQuery("红包", "name","details"))
				.setQuery(QueryBuilders.matchQuery("statusLife", "SAILING"))
//				.setPostFilter(FilterBuilders.queryFilter(QueryBuilders.matchQuery("statusLife", "SAILING")))
//				.setPostFilter(FilterBuilders.termFilter("createdTime", "1439284473000"))
				.execute()
				.actionGet();
		
		for(SearchHit hit: resp.getHits()){
			System.out.println(JSONObject.fromObject(hit.getSource()).toString());
		}
	}
	
}
