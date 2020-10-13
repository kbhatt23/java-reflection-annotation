package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import annotations.IOCAutowired;
import annotations.IOCComponent;

public class ContextLoader {

	public static <T> T findBean(Class<T> classObj) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		// no arg constructor
		if (!classObj.isAnnotationPresent(IOCComponent.class)) {
			throw new RuntimeException("Class is not annotated with annotation " + IOCComponent.class.getSimpleName());
		}

		// lets also load the dependencies for each fields
		T instance = classObj.getDeclaredConstructor().newInstance();
		// find all public and private fields present only in class -> ignore super
		// class

		Field[] declaredFields = classObj.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.isAnnotationPresent(IOCAutowired.class)) {
				field.setAccessible(true);
				// no arg privat econstrucot also fetched
				Class<?> type = field.getType();
				if (type.isAnnotationPresent(IOCComponent.class)) {
					Constructor<?> declaredConstructor = type.getDeclaredConstructor();
					field.set(instance, declaredConstructor.newInstance());
				}
			}
		}
		return instance;
	}

	public static <T> void findBeanAndLoadInCache(Class<T> classObj, Map<Class<?>, Object> classCache)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
// no arg constructor

// lets also load the dependencies for each fields
		if(classCache.containsKey(classObj)) {
			return;
		}
		T instance = classObj.getDeclaredConstructor().newInstance();
		classCache.putIfAbsent(classObj, instance);
// find all public and private fields present only in class -> ignore super
// class

		Field[] declaredFields = classObj.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.isAnnotationPresent(IOCAutowired.class)) {
				field.setAccessible(true);
				// no arg privat econstrucot also fetched
				Class<?> type = field.getType();
				if (type.isAnnotationPresent(IOCComponent.class)) {
					Constructor<?> declaredConstructor = type.getDeclaredConstructor();
					Object newInstance = null;
					if(classCache.containsKey(type)) {
						newInstance = classCache.get(type);
					}else {
					 newInstance = declaredConstructor.newInstance();
					}
					field.set(instance, newInstance);
					classCache.putIfAbsent(type, newInstance);
				}
			}
		}
	}
	

}
