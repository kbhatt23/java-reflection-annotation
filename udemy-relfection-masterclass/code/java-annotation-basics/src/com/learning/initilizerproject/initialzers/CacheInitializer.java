package com.learning.initilizerproject.initialzers;

import com.learning.initilizerproject.annotations.InitializerClass;
import com.learning.initilizerproject.annotations.InitializerMethod;

@InitializerClass
public class CacheInitializer {

	@InitializerMethod
	public void startMessageCache() {
		System.out.println("Started Message Cache");
	}
	@InitializerMethod(ignore = true)
	public void ignoreThis() {
		System.out.println("Cache internal method, ignore this");
	}
}
