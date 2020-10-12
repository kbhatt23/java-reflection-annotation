package basics;

import java.util.Arrays;

public class ClassBasicMetaData {
public static void main(String[] args) throws ClassNotFoundException {
	Class<?> privateClassInstance = Class.forName("common.PrivateConstructorClass");
	
	Class<?> superclass = privateClassInstance.getSuperclass();
	System.out.println("class name "+privateClassInstance.getName());
	System.out.println("superclass "+superclass.getName());
	
	System.out.println("isarray "+privateClassInstance.isArray());
	
	Class<?>[] interfaces = privateClassInstance.getInterfaces();
	Arrays.stream(interfaces)
		.forEach(i -> System.out.println("interface found "+i.getName()));
}
}
