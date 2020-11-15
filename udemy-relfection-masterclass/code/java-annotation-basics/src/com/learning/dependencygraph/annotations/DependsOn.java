package com.learning.dependencygraph.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)

//can be used in paramter of any ocnstructir/method
@Target(ElementType.PARAMETER)
public @interface DependsOn {

	String operationName();
	
}
