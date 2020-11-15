package com.learning.initilizerproject.initialzers;

import com.learning.initilizerproject.annotations.InitializerClass;
import com.learning.initilizerproject.annotations.InitializerMethod;
@InitializerClass
public class FrameworkInitializer {

	@InitializerMethod
	public void initilzieFramework() {
		System.out.println("started framework internal initializer");
	}
}
