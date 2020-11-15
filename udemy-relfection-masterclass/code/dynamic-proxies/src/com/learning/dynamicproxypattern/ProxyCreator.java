package com.learning.dynamicproxypattern;

import java.lang.reflect.Proxy;

public class ProxyCreator {

	public static <T> T createProxy(Class<T> realObjectClass) throws Exception{
		T realObjectInstance = realObjectClass.newInstance();
		return (T)Proxy.newProxyInstance(realObjectClass.getClassLoader(), realObjectClass.getInterfaces(), new TimeMeasuringProxyHandler(realObjectInstance));
	}
}
