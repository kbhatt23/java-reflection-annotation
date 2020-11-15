package com.learning.initilizerproject;

import com.learning.initilizerproject.annotations.InitializerClass;
import com.learning.initilizerproject.annotations.InitializerMethod;
import com.learning.initilizerproject.annotations.Retryable;

@InitializerClass
public class DummyInitializer {

	@InitializerMethod
	//@Retryable(attempts = 2)
	public void dummyinitialze() {
		
		System.out.println("calling dummy initizlier");
		if(true) {
			throw new RuntimeException("could not complete");
		}
	}
}
