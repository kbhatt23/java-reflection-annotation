package com.iocframework.bean;

import annotations.IOCAutowired;
import annotations.IOCComponent;

@IOCComponent
public class SimpleBean {

	@IOCAutowired
	private AnotherBean anotherBean;

	public AnotherBean getAnotherBean() {
		return anotherBean;
	}

	public void setAnotherBean(AnotherBean anotherBean) {
		this.anotherBean = anotherBean;
	}
	
}
