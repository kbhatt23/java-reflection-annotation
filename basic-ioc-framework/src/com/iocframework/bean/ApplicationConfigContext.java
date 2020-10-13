package com.iocframework.bean;

import annotations.IOCBean;
import annotations.IOComponentScan;
import annotations.IOConfiguration;
import otherbeans.OtherBean;

//this class is the root package to scan for
//we will scan all the @componet and load in class cache
@IOComponentScan(basePackage = "com.iocframework.bean")
//we can define @beans in this class and that also will load the classes in context
@IOConfiguration
public class ApplicationConfigContext {

	
	@IOCBean
	public OtherBean otherBean() {
		return new OtherBean();
	}
}
