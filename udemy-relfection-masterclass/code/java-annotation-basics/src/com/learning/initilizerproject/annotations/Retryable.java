package com.learning.initilizerproject.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Retryable {

	//no default attempts, mandaotry to provide while using annotaiton
	int attempts();
	
	//by default handles runtime excetpion only
	Class<?> exceptions() default RuntimeException.class;
}
