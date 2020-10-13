package util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iocframework.bean.ApplicationConfigContext;

import annotations.IOCAutowired;
import annotations.IOCBean;
import annotations.IOCComponent;
import annotations.IOComponentScan;
import annotations.IOConfiguration;

public class ApplicationContextLoader {
	// for each class we will load its object
	// we know Class.forName will always return smae class reference
	private static final Map<Class<?>, Object> classCache;

	static {
		Class<?> applicationConfigContextClass = ApplicationConfigContext.class;
		if (!applicationConfigContextClass.isAnnotationPresent(IOComponentScan.class)) {
			throw new RuntimeException("The base class" + applicationConfigContextClass.getSimpleName()
					+ " is not annotated with @IOCComponentScan");
		}
		// instanitate only if proper appcontext class is present
		classCache = new HashMap<Class<?>, Object>();
		String packageToScan = applicationConfigContextClass.getAnnotation(IOComponentScan.class).basePackage();
		List<Class<?>> findQualifiedNameUnderPackage = FileScanningUtil.findQualifiedNameUnderPackage(packageToScan);

		for (Class<?> classFound : findQualifiedNameUnderPackage) {
			try {
				if(classFound.isAnnotationPresent(IOConfiguration.class)) {
					//config class can have beans
					Object newInstanceNoArgConsturcot = classFound.getDeclaredConstructor().newInstance();
					Method[] methods = classFound.getMethods();
					for(Method method : methods) {
						//create bean onlt for the specific @IOCBean methods
						if(method.isAnnotationPresent(IOCBean.class))
						{
							Object invokeResult = method.invoke(newInstanceNoArgConsturcot);
							classCache.putIfAbsent(invokeResult.getClass(), invokeResult);
						}
					}
					
				}
				else if (classFound.isAnnotationPresent(IOCComponent.class)) {
					ContextLoader.findBeanAndLoadInCache(classFound, classCache);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public  <T> T findBeanFromCache(Class<T> classObj)  {
		return (T)classCache.get(classObj);
	
}
}
