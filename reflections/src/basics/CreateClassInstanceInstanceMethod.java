package basics;

import java.lang.reflect.Constructor;

import common.PrivateConstructorClass;

public class CreateClassInstanceInstanceMethod {
	public static void main(String[] args) {
		try {
			//can use class level keyword
			//Class<?> class1 = Class.forName("common.PrivateConstructorClass");
			Class<?> class1 = PrivateConstructorClass.class;
			
			//if constructor was public
			//Class<?> class1 = new PrivateConstructorClass().getClass();
			Class<?> class2 = Class.forName("common.PrivateConstructorClass");
			System.out.println(class1 == class2);
			
			//both instance of class will be same as it is static -> to save heap memory
			Constructor<?> declaredConstructor = class1.getDeclaredConstructor();
			declaredConstructor.setAccessible(true);
			Object newInstance1 = declaredConstructor.newInstance();
			Constructor<?> declaredConstructor2 = class2.getDeclaredConstructor();
			declaredConstructor2.setAccessible(true);
			Object newInstance2 = declaredConstructor2.newInstance();
			
			//both instance wil be differnet
			System.out.println(newInstance1 == newInstance2);
			
			CreateClassInstanceInstanceMethod a = new CreateClassInstanceInstanceMethod();
			a.checkClassNameFactory(class1);
			a.checkClassNameFactory(class2);
		} catch (Exception e) {
			System.out.println("exception occured " + e);
		}
		
	}
	
	private void checkClassNameFactory(Class<?> classOld) {
		System.out.println("chekcing ");
		try {
			//create class for name anywhere once called it will return same object
			Class<?> classNew = Class.forName("common.PrivateConstructorClass");
			//still will be same as object is staic/singleton
			System.out.println(classNew == classOld);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
