package classreflectionusage.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectionUtil {
//analyze class of any type and any number
	public static void analyzeClasses(Class<?>... classes ) {
		for(Class<?> clazz: classes) {
			System.out.println("Class with name "+clazz.getSimpleName());
			if(clazz.getPackage() != null)
				System.out.println("Class with package "+clazz.getPackage().getName());
			
			Class<?>[] interfaces = clazz.getInterfaces();
			for(Class<?> interfaceFound:interfaces) {
				System.out.println(clazz.getSimpleName()+" have interface "+interfaceFound.getSimpleName());
			}
			
			System.out.println("============================");
			System.out.println();
		}
	}
	
	public static Object createObjectWithArguments(Class<?> clazz,Object[] values, Class<?>... arguments ) throws Exception {
		
		if(arguments == null || arguments.length == 0) {
			//no arg constructor
			Constructor<?> constructor = clazz.getDeclaredConstructor();
			constructor.setAccessible(true);
			Object newInstance = constructor.newInstance();
			System.out.println("argument size is 0 and hence creating 0 args constructor");
			System.out.println("Object instantiated "+newInstance);
			return newInstance;
		}else {
		List<Class<?>> argumentsList = Arrays.stream(arguments)
				//.map(Class::getSimpleName)
				.collect(Collectors.toList());
		//even private constructors are allowed
		Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
		Constructor<?> constructorFound = null;
		for (Constructor<?> constructor : declaredConstructors) {
			
			Parameter[] parameters = constructor.getParameters();
			if(arguments.length == parameters.length) {
				
				boolean allMatch = Arrays.stream(parameters)
					.map(Parameter::getType)
					//.map(Class::getSimpleName)
					//.allMatch(name -> argumentsList.contains(name));
					.allMatch(classFound -> argumentsList.contains(classFound));
				if(allMatch) {
					constructorFound = constructor;
					break;
				}
				
			}
		}
		if(constructorFound != null) {
			constructorFound.setAccessible(true);
			Object newInstance = constructorFound.newInstance(values);
			System.out.println("object instantiated while searching argument "+newInstance);
			return newInstance;
		}else {
			Constructor<?> constructor = clazz.getDeclaredConstructor();
			constructor.setAccessible(true);
			Object newInstance = constructor.newInstance();
			System.out.println("none constructor found and hence calling no arg coinstuctor");
			System.out.println("Object instantiated "+newInstance);
			return newInstance;
		}
		}
		
	}
	
	public  static <T> T createObjectWithArgumentsEasy(Class<T> clazz,Object[] values, Class<?>... arguments ) throws Exception {
		Constructor<T> declaredConstructor = null;
		T newInstance = null;
		if(values!= null  && values.length!=arguments.length) {
			System.out.println("Worng arguments passed and hence calling no arg constructor");
			declaredConstructor = clazz.getDeclaredConstructor();
			declaredConstructor.setAccessible(true);
			newInstance = declaredConstructor.newInstance();
		}else{
			System.out.println("valid arguments and hence finding approapriate constructor");
			declaredConstructor = clazz.getDeclaredConstructor(arguments);
			declaredConstructor.setAccessible(true);
			newInstance = declaredConstructor.newInstance(values);
		}
		
		
		System.out.println("new instance generated "+newInstance);
		return newInstance;
		
	}
	
	
}
