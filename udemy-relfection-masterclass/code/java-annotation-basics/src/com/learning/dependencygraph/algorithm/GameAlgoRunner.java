package com.learning.dependencygraph.algorithm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.learning.dependencygraph.annotations.AlgorithmRunnerClass;
import com.learning.dependencygraph.annotations.DependsOn;
import com.learning.dependencygraph.annotations.FinalResult;
import com.learning.util.ClassSacnningUtil;

public class GameAlgoRunner {
	
	private static Map<String, Object> methodInvocationReusltMap = new HashMap<String, Object>();
	public static void main(String[] args) {
		//oldWay();
		
		usingReflection();
	}

	private static void usingReflection() {
		try {
			runAlgorithmLogically(new GameAlgoithm());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void oldWay() {
		GameAlgoithm algo = new GameAlgoithm();
		List<String> findAllNames = algo.findAllNames();
		List<String> createValidNames = algo.createValidNames(findAllNames);
		
		Set<String> uniqueNames = algo.uniqueNames(createValidNames);
		
		algo.compareItems(findAllNames, uniqueNames);
	}
	
	public static void runAlgorithmLogically(Object instance) throws Exception{
		Class<?> algoClass = instance.getClass();
		if(!algoClass.isAnnotationPresent(AlgorithmRunnerClass.class)) {
			throw new RuntimeException("Class is not annotated with @AlgorithmRunnerClass annotation");
		}
		
		Method resultListMethod = findfinalResultMethod(algoClass);
		
		executeMethodsRecursively(resultListMethod, instance, algoClass);
	}
	
	public static Object executeMethodsRecursively(Method method, Object instance, Class<?> algoClass) throws Exception{
		
		if(method.getParameterCount() == 0) {
			if(methodInvocationReusltMap.containsKey(method.getName())) {
				return methodInvocationReusltMap.get(method.getName());
			}else {
				Object result=  method.invoke(instance);
				methodInvocationReusltMap.put(method.getName(), result);
				return result;
			}
			
		}
		
		Parameter[] parameters = method.getParameters();
		Object[] arguments = new Object[parameters.length];
		int i=0;
		for (Parameter parameter : parameters) {
			//in algo runner logic if method have parameter it has to have a dependency on return type of another method
			if(parameter.isAnnotationPresent(FieldParam.class)) {
				FieldParam paramAnnotion = parameter.getAnnotation(FieldParam.class);
				String fieldProperty = paramAnnotion.property();
				Field findFieldByPAramType = findFieldByPAramType(fieldProperty, algoClass);
				findFieldByPAramType.setAccessible(true);
				arguments[i]=findFieldByPAramType.get(instance);
			}else{
				DependsOn dependsOnObject = parameter.getAnnotation(DependsOn.class);
				String operationName = dependsOnObject.operationName();
				Method operationMethod = ClassSacnningUtil.findMethodByOperationName(operationName, algoClass);
				arguments[i]= executeMethodsRecursively(operationMethod, instance, algoClass);
			}
			i++;
		}
		return method.invoke(instance, arguments);
	}
	
	
	
	public static Method findfinalResultMethod(Class<?> algoClass)
	{	
		//assuming only pulbic methods are used as part of final result method
		return Arrays.stream(algoClass.getMethods())
			.filter(method -> method.isAnnotationPresent(FinalResult.class))
			.findFirst()
			.orElseThrow(() -> new RuntimeException(String.format("Algorithm class %s do not have valei result list method", algoClass.getSimpleName())))
					;
	}
	
	public static Field findFieldByPAramType(String fieldProperty,Class<?> algoClass) {
		return Arrays.stream(algoClass.getDeclaredFields())
			.map(field -> {field.setAccessible(true); return field;})
			.filter(field -> field.isAnnotationPresent(FieldValue.class))
			.filter(field -> field.getAnnotation(FieldValue.class).name().equals(fieldProperty))
			.findFirst()
			.orElseThrow(() -> new RuntimeException(String.format("Class %s do not contain expected field %s", algoClass.getSimpleName(), fieldProperty)));
	}

}
