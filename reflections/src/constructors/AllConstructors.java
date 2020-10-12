package constructors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AllConstructors {
public static void main(String[] args) {
	try {
		Class<?> compleClass = Class.forName("common.ComplexConstructors");
		//shows all private and public constructors
		Constructor<?>[] declaredConstructors = compleClass.getDeclaredConstructors();
		for(Constructor c :declaredConstructors) {
			System.out.println(c.getName()+ " : "+c.getParameters().length);
		}
		System.out.println("=============");
		//shows all private and public constructors
		Constructor<?>[] publicOnlyconstruotr = compleClass.getConstructors();
		for(Constructor c :publicOnlyconstruotr) {
			System.out.println(c.getName()+ " : "+c.getParameters().length);
		}
		System.out.println("=============");
		//using individual constructors
		//based on argument -> but onnly public one will be returned
		
		//no arg construcotr is public so no issue
		Constructor<?> c1 = compleClass.getConstructor();
		Object newInstance = c1.newInstance();
		System.out.println("object of type "+newInstance.getClass().getName()+" with value "+newInstance);
		
		//all arg construcotr i also public
		Constructor<?> c2 = compleClass.getConstructor(int.class,String.class);
		Object newInstance2 = c2.newInstance(11, "sita ram");
		
		System.out.println("object of type "+newInstance2.getClass().getName()+" with value "+newInstance2);
		
		//single args contructors are private
		Constructor<?> c3 = compleClass.getDeclaredConstructor(int.class);
		//if not using delacread construcot  -> nosuchmethod exceptin is thrown
		//if not setting access to true privtae method not be called exception occurs
		c3.setAccessible(true);
		Object newInstance3 = c3.newInstance(11);
		
		System.out.println("object of type "+newInstance3.getClass().getName()+" with value "+newInstance3);
		
		//single args contructors are private
				Constructor<?> c4 = compleClass.getDeclaredConstructor(String.class);
				//if not using delacread construcot  -> nosuchmethod exceptin is thrown
				//if not setting access to true privtae method not be called exception occurs
				c4.setAccessible(true);
				Object newInstance4 = c4.newInstance("jai shree ram");
				
				System.out.println("object of type "+newInstance4.getClass().getName()+" with value "+newInstance4);
		
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
