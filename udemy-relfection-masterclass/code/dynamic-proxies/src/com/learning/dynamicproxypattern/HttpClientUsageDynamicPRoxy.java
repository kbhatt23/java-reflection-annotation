package com.learning.dynamicproxypattern;

public class HttpClientUsageDynamicPRoxy {
	public static void main(String[] args) {
		HttpClient httpClient = null;
		try {
			httpClient = ProxyCreator.createProxy(HttpClientImpl.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (httpClient != null) {
			HttpClientUsage.callexecute(httpClient);
			System.out.println("====================");
			HttpClientUsage.callexecuteURL(httpClient, "radhe shyam");
			System.out.println("====================");
			HttpClientUsage.callexecute(httpClient);
			System.out.println("====================");
			HttpClientUsage.callexecuteURL(httpClient, "uma shankar");
			System.out.println("====================");
			HttpClientUsage.callexecute(httpClient);
			System.out.println("====================");
			HttpClientUsage.callexecuteURL(httpClient, "radhe shyam");
			System.out.println("====================");
		}
	}
}
