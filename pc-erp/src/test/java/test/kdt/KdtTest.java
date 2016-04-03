/**
 * 
 */
package test.kdt;

import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.kdt.api.KdtApiClient;

/**
 * @author parox
 *
 */
public class KdtTest {
	
	public static void main(String[] args) throws Exception {
		test1();
		test2();
	}
	
	public static void test1() throws Exception{
		
		KdtApiClient client = new KdtApiClient("f92d3321b5af77d7e2", "f74f1a49faaae58c24388af9c9697153");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("tid", "E20150314150055756820");
		HttpResponse response = client.get("kdt.trade.get", params);
		String respStr = EntityUtils.toString(response.getEntity());
		JSONObject jobj = JSONObject.fromObject(respStr);
		JSONObject trade = jobj.getJSONObject("response")	.getJSONObject("trade");

		System.out.println("num_iid: " + trade.getString("num_iid") + " title:" + trade.getString("title"));
		System.out.println(trade.getString("status"));
	}
	
	public static void test2() throws Exception{
		KdtApiClient client = new KdtApiClient("f92d3321b5af77d7e2", "f74f1a49faaae58c24388af9c9697153");
		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("weixin_user_id", "13819789000");
//		params.put("buyer_nick", "董希斌");
		params.put("status", "WAIT_BUYER_CONFIRM_GOODS");
//		params.put("page_size", "10");
		params.put("use_has_next", "true");
		params.put("page_no", "2");
//		params.put("start_created", "2015-4-10 00:00:00");
//		params.put("end_created", "2015-4-16 00:00:00");
		
		HttpResponse response = client.get("kdt.trades.sold.get", params);
		
		String respStr = EntityUtils.toString(response.getEntity());
		
		JSONObject jobj = JSONObject.fromObject(respStr);
		JSONArray jarry = jobj.getJSONObject("response").getJSONArray("trades");
		
		System.out.println(jobj.toString());
		System.out.println(jarry.size());
	}

}
