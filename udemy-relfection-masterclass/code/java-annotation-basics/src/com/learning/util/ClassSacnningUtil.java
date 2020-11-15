package com.learning.util;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.learning.dependencygraph.annotations.Operation;
import com.learning.initilizerproject.annotations.InitializerMethod;

public class ClassSacnningUtil {
	private static final String DIRECTORY_SEPERATOR = "/";

	//same canbe used in spring feature like component scan
	public static List<Class<?>> findAllClassesInAPackage(String... packageNames) throws Exception{
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for(String packageName:packageNames) {
		String directoryPath = extractFilePAth(packageName);
		File directory  = new File(directoryPath);
		if(directory.exists()) {
		for(File file : directory.listFiles()) {
			if(file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName+"."+file.getName().split("\\.")[0]));
			}
		}
		}
		}
		return classes;
	}

	private static String extractFilePAth(String packageName) {
		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("user.dir").replaceAll("\\\\", DIRECTORY_SEPERATOR))
		  .append(DIRECTORY_SEPERATOR)
		  .append("bin")
		  .append(DIRECTORY_SEPERATOR)
		  .append(packageName.replaceAll("\\.", DIRECTORY_SEPERATOR));
		
		return sb.toString();
	}
	
	  public Set<Method> getAllAnnotatedMethods(Object input) {
	        Set<Method> annotatedMethods = new HashSet<>();
	        Method[] declaredMethods = input.getClass().getDeclaredMethods();
	        annotatedMethods = Stream.of(declaredMethods)
	        	  .filter(clazz -> clazz.isAnnotationPresent(InitializerMethod.class))
	        	  .collect(Collectors.toSet());
	        return annotatedMethods; 
	    }
	  
	  public static Method findMethodByName(String methodName, Class<?> clazz) {
		  return Arrays.stream(clazz.getMethods())
		  			.filter(method -> method.getName().equals(methodName))
		  			.findFirst()
		  			.orElseThrow(() -> new RuntimeException(String.format("Class %s do not have any method with name %s", clazz.getSimpleName(),methodName)));
	  }
	  
	  public static Method findMethodByOperationName(String operationName, Class<?> clazz) {
		  return Arrays.stream(clazz.getMethods())
		  			.filter(method -> method.isAnnotationPresent(Operation.class))
		  			.filter(method -> method.getAnnotation(Operation.class).name().equals(operationName))
		  			.findFirst()
		  			.orElseThrow(() -> new RuntimeException(String.format("Class %s do not have any method with operationname %s", clazz.getSimpleName(),operationName)));
	  }
	
}

