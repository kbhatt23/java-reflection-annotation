package com.learning.repeatableannotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

//any lcass need to have this anntoation for scheuled method
@SchedulerService
public class ScheduledTaskRunner {
public static void main(String[] args) {
	try {
		executeTasks(ScheduledTaskRunner.class);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

//demnosartion only one scheiulable method
//better for readability , instead of keeping eveyrhint ins ingle anotion in array form

//the first thread whihc runs start at 0 initial delat and periodc repetion is less freqeunt
@ScheduledTask(initialDelay = 0,periodicDelay = 5)
//this mena that after some time have spent in server , cache refrehs period shud be more freequent
@ScheduledTask(initialDelay = 10 , periodicDelay = 1)
public void method1() {
	System.out.println("jai shree ram task started by method1");
	helper1();
	helper2();
	System.out.println("jai shree ram task completed by method1");
}

private void helper1() {
	System.out.println("jai shree ram task completed by helper1");
}
private void helper2() {
	System.out.println("jai shree ram task completed by helper2");
}


public static void executeTasks(Class<?> clazz) throws Exception {
	if(!clazz.isAnnotationPresent(SchedulerService.class)) {
		throw new RuntimeException(String.format("Class %s do not have valid @SchedulerService expected for scheuled task classes ", clazz.getSimpleName()));
	}
	
	List<Method> scheduledTaskMethods=
	Arrays.stream(clazz.getDeclaredMethods())
		.filter(method -> method.getAnnotationsByType(ScheduledTask.class).length > 0)
		.collect(Collectors.toList());
	;
	
	
	Object instance  = clazz.newInstance();
	for (Method scheduledTaskMethod : scheduledTaskMethods) {
		//scheuled executor service running in siongle thread
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		ScheduledTask[] tasks = scheduledTaskMethod.getAnnotationsByType(ScheduledTask.class);
		
		for (ScheduledTask task : tasks) {
			service.scheduleAtFixedRate(() -> runSingleTask(scheduledTaskMethod, instance),
					task.initialDelay(), task.periodicDelay(), TimeUnit.SECONDS);
		}
	}
}

public static void runSingleTask(Method method, Object instance) {
	method.setAccessible(true);
	try {
		System.out.println("Task started at "+LocalDateTime.now());
		method.invoke(instance);
		System.out.println("Task completed at "+LocalDateTime.now());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



}
