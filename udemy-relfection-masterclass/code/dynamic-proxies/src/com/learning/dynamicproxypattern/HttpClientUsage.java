package com.learning.dynamicproxypattern;

public class HttpClientUsage {
public static void main(String[] args) {
	HttpClient httpClient = new HttpClientProxy(new HttpClientImpl());
	callexecute(httpClient);
	System.out.println("====================");
	callexecuteURL(httpClient, "radhe shyam");
	System.out.println("====================");
	callexecute(httpClient);
	System.out.println("====================");
	callexecuteURL(httpClient, "uma shankar");
	System.out.println("====================");
	callexecute(httpClient);
	System.out.println("====================");
	callexecuteURL(httpClient, "radhe shyam");
	System.out.println("====================");
	
}

public static void callexecute(HttpClient httpClient) {
	System.out.println("Result aya client me "+httpClient.execute());
	
	System.out.println("Result aya client me "+httpClient.execute());
	
	System.out.println("Result aya client me "+httpClient.execute());
}

public static void callexecuteURL(HttpClient httpClient, String url) {
	System.out.println("Result aya client me "+httpClient.executeURL(url));
	
	System.out.println("Result aya client me "+httpClient.executeURL(url));
	
	System.out.println("Result aya client me "+httpClient.executeURL(url));
}
}
