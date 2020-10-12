package methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import common.Entity;

public class FindAllMethods {
public static void main(String[] args) {
	Entity e = new Entity(108, "jai shree ram");
	
	Class<? extends Entity> class1 = e.getClass();
	//returns methods based on acces modififer and super class ase well
	Method[] visibleMethods = class1.getMethods();
	//super class public methods also inlcuded, but private are completley ignored
	for(Method m : visibleMethods) {
		System.out.println(m.getName());
	}
	
	System.out.println("=========");
	//methods in super class are ignored, and even private methods of currnet class is returned
	Method[] allMethods = class1.getDeclaredMethods();
	for(Method m : allMethods) {
		System.out.println(m.getName());
	}
	System.out.println("=========");
	//calling no arg public method
	try {
		Method publicMethod = class1.getMethod("publicMethod");
		publicMethod.invoke(e);
	} catch (NoSuchMethodException | SecurityException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IllegalAccessException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IllegalArgumentException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (InvocationTargetException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	System.out.println("=========");
	//calling no arg public method
	try {
		//get method will return only visible methods of class ans superclass
		//no suc method exception
		//Method publicMethod = class1.getMethod("privateMethod");
		Method privateMethod = class1.getDeclaredMethod("privateMethod");
		if(!privateMethod.isAccessible())
			privateMethod.setAccessible(true);
		privateMethod.invoke(e);
	} catch (NoSuchMethodException | SecurityException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IllegalAccessException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IllegalArgumentException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (InvocationTargetException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	//calling arg method with same name
	//calling no arg public method
		try {
			//get method will return only visible methods of class ans superclass
			//no suc method exception
			//Method publicMethod = class1.getMethod("privateMethod");
			//second argument describes the argumen type of method
			Method privateMethod = class1.getMethod("publicMethod", String.class);
			privateMethod.invoke(e,"jai shree ram");
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
}
}
