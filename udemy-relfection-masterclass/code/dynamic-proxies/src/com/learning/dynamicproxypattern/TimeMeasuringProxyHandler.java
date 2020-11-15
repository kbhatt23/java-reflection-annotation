package com.learning.dynamicproxypattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.learning.dynamicproxypattern.annotations.EnableCache;

public class TimeMeasuringProxyHandler implements InvocationHandler {

	private Object realObjectInstance;

	private Map<String, Object> cachedResponseMap = new HashMap<String, Object>();

	public TimeMeasuringProxyHandler(Object realObjectInstance) {
		this.realObjectInstance = realObjectInstance;
	}

	// this method will get called for each emthod of realobjectinstance class
	// meaning this can act as proxy for all the methods
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		System.out.println("Interceptor called for method " + methodName);
		long startTime = System.nanoTime();
		String methodWithArgString =null;
		Class<?>[] argumentClassArray = null;
		if(args != null && args.length > 0) {
			methodWithArgString= methodName+":"+Arrays.stream(args).map(Object::toString).collect(Collectors.joining(","));
			List<Class<?>> argumentClassList = Arrays.stream(args).map(Object::getClass).collect(Collectors.toList());
			argumentClassArray = new Class<?>[argumentClassList.size()];
			argumentClassList.toArray(argumentClassArray);
		}else {
			methodWithArgString = methodName;
		}
		boolean isCacheEnabled = false;
		if(argumentClassArray == null || argumentClassArray.length == 0) {
			//no args
			isCacheEnabled = realObjectInstance.getClass().getDeclaredMethod(methodName).isAnnotationPresent(EnableCache.class);
		}else {
			isCacheEnabled = realObjectInstance.getClass().getDeclaredMethod(methodName,argumentClassArray).isAnnotationPresent(EnableCache.class);
		}
		
		Object result;
		 if(!isCacheEnabled) {
			 //cache is diabled
			 result = method.invoke(realObjectInstance, args);
		 }
		 else if (!cachedResponseMap.containsKey(methodWithArgString)) {
			result = method.invoke(realObjectInstance, args);
			cachedResponseMap.put(methodWithArgString, result);
		} else {
			result = cachedResponseMap.get(methodWithArgString);
		}
		long endtime = System.nanoTime();
		System.out.println("Total time taken to read data " + (endtime - startTime));
		return result;
	}

}
