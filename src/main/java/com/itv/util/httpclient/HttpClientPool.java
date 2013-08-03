package com.itv.util.httpclient;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientPool {
	/*private static PoolingClientConnectionManager cm=null;
	static{
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		 schemeRegistry.register(
		          new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
		 schemeRegistry.register(
		          new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

		 cm = new PoolingClientConnectionManager(schemeRegistry);
		 cm.setMaxTotal(200);
		 cm.setDefaultMaxPerRoute(20);
		 HttpHost localhost = new HttpHost("locahost", 80);
		 cm.setMaxPerRoute(new HttpRoute(localhost), 50);
	}*/
	/**
	 * 获取httpclient实例
	 * @return
	 */
	public static HttpClient getHttpClient(){
		HttpClient httpClient = new DefaultHttpClient();
		return httpClient;
	}
}
