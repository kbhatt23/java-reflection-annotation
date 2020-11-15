package com.learning.dynamicproxypattern;

import java.util.HashMap;
import java.util.Map;

public class HttpClientProxy implements HttpClient{
	private HttpClient client;
	
	public HttpClientProxy(HttpClient client) {
		this.client=client;
	}
	private String result ;
	
	private Map<String, String> executeURLResultMap = new HashMap<String, String>();
	@Override
	public String execute() {
		System.out.println("Static Proxy called for method execute");
		long startTime = System.nanoTime();
		
		if(result == null) {
			result = client.execute();
		}
		
		long endtime = System.nanoTime();
		
		System.out.println("Total time taken to execute "+ (endtime-startTime ));
		return result;
	
	}
	@Override
	public String executeURL(String url) {
		System.out.println("Static Proxy called for method executeURL");
		String suceessResult = null;
		long startTime = System.nanoTime();
		
		if(!executeURLResultMap.containsKey(url)) {
			suceessResult = client.executeURL(url);
			executeURLResultMap.put(url, suceessResult);
		}else {
			suceessResult= executeURLResultMap.get(url);
		}
		
		long endtime = System.nanoTime();
		
		System.out.println("Total time taken to execute "+ (endtime-startTime ));
		return suceessResult;
	}

}
