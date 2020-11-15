package com.learning.dynamicproxypattern;

import com.learning.dynamicproxypattern.annotations.EnableCache;

public class HttpClientImpl implements HttpClient{

	@EnableCache
	@Override
	public String execute() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Succesfully executed http client");
		return "jai shree ram";
	}

	@EnableCache
	@Override
	public String executeURL(String url) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Succesfully executed http client with url "+url);
		return "jai shree ram"+" : "+url;
	}

}
