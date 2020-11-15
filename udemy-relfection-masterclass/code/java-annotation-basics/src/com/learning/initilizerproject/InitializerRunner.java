package com.learning.initilizerproject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.learning.initilizerproject.annotations.InitializerClass;
import com.learning.initilizerproject.annotations.InitializerMethod;
import com.learning.initilizerproject.annotations.Retryable;
import com.learning.initilizerproject.annotations.ScanPackages;
import com.learning.util.ClassSacnningUtil;
@ScanPackages(packagesToScan = {"com.learning.initilizerproject.initialzers","com.learning.initilizerproject"})
public class InitializerRunner {
public static void main(String[] args) {
	try {
		//assuming this class as main class
		Class<?> mainRunnerClass = InitializerRunner.class;
		if(!mainRunnerClass.isAnnotationPresent(ScanPackages.class)) {
			throw new RuntimeException(String.format("Class %s do not have scan package annotion present",mainRunnerClass.getName())); 
		}
		List<Class<?>> classesToInitialze = ClassSacnningUtil.findAllClassesInAPackage(mainRunnerClass.getAnnotation(ScanPackages.class).packagesToScan());
		
		for (Class<?> clazz : classesToInitialze) {
			if(clazz.isAnnotationPresent(InitializerClass.class)) {
				//assuming only public methods
				Method[] methods = clazz.getMethods();
				Object instance = null;
				int attempts = 0;
				for(int i=0; i< methods.length;) {
					Method method = methods[i];
					if(method.isAnnotationPresent(InitializerMethod.class)) {
						InitializerMethod annotation = method.getAnnotation(InitializerMethod.class);
						Retryable retryableAnnotaiton = method.getAnnotation(Retryable.class);
						if(!annotation.ignore()) {
						if(instance == null) {
							instance = clazz.newInstance();
						}
						if(retryableAnnotaiton != null && attempts > retryableAnnotaiton.attempts()) {
							i++;
							continue;
						}
						try {
							method.invoke(instance);
						}catch (InvocationTargetException e) {
							if(retryableAnnotaiton != null ) {
								i--;
								attempts++;
							}
						}
						}
					}
					i++;
				}
			}
			
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
