package com.iocframework.bean;

import annotations.IOCAutowired;
import annotations.IOCComponent;

@IOCComponent
public class AnotherBean {

	@IOCAutowired
	private QuickyLastBean lastBean;

	public QuickyLastBean getLastBean() {
		return lastBean;
	}

	public void setLastBean(QuickyLastBean lastBean) {
		this.lastBean = lastBean;
	}
}
