package exam;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import recursivedependecyinjection.Player;

public class DependecnyIinjectionAutomation {
	public static void main(String[] args) {
		try {
			Object dependecyInjection = dependecyInjection(Player.class);
			System.out.println(dependecyInjection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Object dependecyInjection(Class<?> clazz) throws Exception{
		Constructor<?> findConstructor = findConstructor(clazz);
		Class<?>[] parameterTypes = findConstructor.getParameterTypes();
		List<Object> parammerterObjects = new ArrayList<Object>();
		for(Class<?> paramterClass :parameterTypes) {
			parammerterObjects.add(dependecyInjection(paramterClass));
		}
		findConstructor.setAccessible(true);
		return parammerterObjects.size() > 0 ? findConstructor.newInstance(parammerterObjects.toArray()) : findConstructor.newInstance();
	}
	
	//assuming only one constructor per class
	//either no arg constructor // or a single cnostructor that have parameters
	public static Constructor<?> findConstructor(Class<?> clazz) {
		Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
		if(declaredConstructors == null ||declaredConstructors.length==0) {
			throw new RuntimeException("no vlaid constructor found");
		}
		return declaredConstructors[0];
	}
}
