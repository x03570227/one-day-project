/**
 * 
 */
package com.hlmm.http;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;



/**
 * @author E430
 */
public class HttpRemoteControl {

	private HttpClient httpClient;
	private static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(10000).setConnectTimeout(10000).build();// 设置请求

	public void exe(String remoteUrl,String nextUrl,String proxyServer,int proxyPort)
			throws Exception {
		if (httpClient == null) {
			httpClient = new DefaultHttpClient(
					new ThreadSafeClientConnManager());
			if(proxyServer!=null&&!proxyServer.equals("")){
			 HttpHost proxy = new HttpHost(proxyServer, proxyPort);
			 httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
			 proxy);
			}
			// 设置代理结束

		}
		try {

			exeControl(httpClient, remoteUrl,nextUrl);
		} catch (Exception e) {

			System.out.println("HttpRemoteControl exeControl:" + e);
			throw e;
		}

		finally {
			httpClient.getConnectionManager().shutdown();
			
		}
		
	}

	// 登录入口
	private void exeControl(HttpClient httpClient, String remoteUrl,String nextUrl) throws Exception {
		HttpGet httpGet =null;
		try{
			httpGet=this.builHttpGet(remoteUrl);
			httpGet.addHeader("Referer", "http://hunjia.hunlimama.com/");
		String result = this.requestRomote(httpClient, httpGet);
		//可以动态解析返回内容获取下一个点击链接
		//nextUrl=参数
		System.out.println(result);
		if(nextUrl!=null&&!nextUrl.equals(""))
		{
			Thread.sleep(5*1000);//停留5秒
			httpGet = this.builHttpGet(nextUrl);
			result = this.requestRomote(httpClient, httpGet);
			System.out.println(result);
		}
		}finally{
			if(httpGet!=null){
				httpGet.abort();
			}
		}
	}


	private String requestRomote(HttpClient httpClient,
			HttpUriRequest httpUriRequest) throws URISyntaxException,
			IOException {
		HttpResponse response = httpClient.execute(httpUriRequest);
		//如果只是发送请求，下面的逻辑可以删除
		HttpEntity entity = response.getEntity();
		StatusLine statusLine = response.getStatusLine();
		if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
			String body = EntityUtils.toString(entity,"UTF-8");
			if (entity != null) {
				EntityUtils.consume(entity);
			}
		return body;
		} 
		return null;
	}

	private HttpPost builHttpPost(String url) throws MalformedURLException,
			UnsupportedEncodingException, URISyntaxException {

		HttpPost httpPost = new HttpPost(this.getHsURI(url));
		httpPost.setConfig(requestConfig);
		return httpPost;
	}
	
	private HttpGet builHttpGet(String url) throws MalformedURLException,
		UnsupportedEncodingException, URISyntaxException {
		HttpGet httpGet = new HttpGet(this.getHsURI(url));
		httpGet.setConfig(requestConfig);
		return httpGet;
	}

	private URI getHsURI(String url) throws MalformedURLException,
			URISyntaxException, UnsupportedEncodingException {
		URI uri = null;
		try {
			uri = new java.net.URI(url);
		} catch (java.net.URISyntaxException e) {
			URL remoteUrl = new URL(url);
			uri = new java.net.URI(remoteUrl.getProtocol(),
					remoteUrl.getUserInfo(), remoteUrl.getHost(),
					remoteUrl.getPort(), remoteUrl.getPath(),
					remoteUrl.getQuery(), null);
		}
		return uri;

	}


}
