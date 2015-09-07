/**
 * 
 */
package net.caiban.pc.erp.utils;

import net.caiban.pc.erp.config.AppConst;
import net.sf.json.JSONObject;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

/**
 * @author parox
 *
 */
public class ESClient {

	private static Settings settings;
	private static Client client;
	private static TransportClient tclient;
	
	public static Client getClient(){
		if(client==null){
			startup();
		}
		return client;
	}
	
	synchronized public static void  startup(){
		
		if(client==null){
			settings = ImmutableSettings
					.settingsBuilder()
					.put("cluster.name", AppConst.getConfig("search.cluster.name", "parox"))
					.put("client.transport.sniff", true)
					.build();
			tclient = new TransportClient(settings);
			client = tclient.addTransportAddress(new InetSocketTransportAddress(
					AppConst.getConfig("search.host", "127.0.0.1"), Integer
					.valueOf(AppConst.getConfig("search.port", "9300"))));
		}
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
//				.setQuery(QueryBuilders.multiMatchQuery("请帖",  "product.name"))
//				.setQuery(QueryBuilders.termQuery("id","1"))
				.setPostFilter(FilterBuilders.queryFilter(QueryBuilders.matchQuery("statusLife", "SALING")))
				.setPostFilter(FilterBuilders.termFilter("createdTime", "1439278819000"))
				.execute()
				.actionGet();
		
		for(SearchHit hit: resp.getHits()){
			System.out.println(JSONObject.fromObject(hit.getSource()).toString());
		}
	}
	
}
