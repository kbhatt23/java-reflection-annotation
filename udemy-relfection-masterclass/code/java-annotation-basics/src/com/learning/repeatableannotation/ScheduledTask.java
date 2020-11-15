package com.learning.repeatableannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//this will get rmoved at runtime and hence no need to define @retention
@Retention(RetentionPolicy.CLASS) //-> this is default and will do the job as it gets removed at runtime
@Target(ElementType.METHOD)
@Repeatable(ScheduledTasks.class)
public @interface ScheduledTask {

	int initialDelay() default 0;
	
	int periodicDelay();
}

