package basics;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import common.PrivateConstructorClass;

public class ReflectionPRivateConstructor {
	public static void main(String[] args) throws ClassNotFoundException ,Exception{
		//wont even show in suggestions ofcntrl+space
		//as reflection api shows the constructor not accesible
		//PrivateConstructorClass obj = new PrivateConstructorClass();
	
		//however in frameworks like sporing /jackson we need to still call these constructors
		
		Class<?> privateRelfectoinClass = Class.forName("common.PrivateConstructorClass");
		
		Constructor<?>[] declaredConstructors = privateRelfectoinClass.getDeclaredConstructors();
		
		Arrays.stream(declaredConstructors).forEach(a -> System.out.println(a.getName()+" : "+a.isAccessible()));
		
		Arrays.stream(declaredConstructors)
				.map(a -> {
					//a.setAccessible(true);
					return a;
				})
				//no arg construnctor and hence not passing any argument in newInstance method
				.forEach(a -> {
					try {
						Object newInstance = a.newInstance();
						if(newInstance instanceof PrivateConstructorClass) {
							System.out.println("object got created "+newInstance);
						}
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				;
				
	}
}
