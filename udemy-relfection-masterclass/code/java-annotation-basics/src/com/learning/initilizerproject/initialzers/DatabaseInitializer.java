package com.learning.initilizerproject.initialzers;

import com.learning.initilizerproject.annotations.InitializerClass;
import com.learning.initilizerproject.annotations.InitializerMethod;
import com.learning.initilizerproject.annotations.Retryable;

@InitializerClass
public class DatabaseInitializer {
	
	@Retryable(attempts = 3)
	@InitializerMethod
	public void initializeDBResource() {
		System.out.println("Started initializeDBResource");
		if(true) {
			throw new RuntimeException("could not complete");
		}
	}

	@InitializerMethod
	public void populateDBConnectoinPools() {
		System.out.println("Started populateDBConnectoinPools");
	}
}
