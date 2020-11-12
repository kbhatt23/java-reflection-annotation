package arrayfields;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

public class CloneCompleObject {
	public static void main(String[] args) {
		String[] favouriteFootballers  = new String[] {"messi","xavi","iniesta"}; 
		
		Employee employee = new Employee(1, "radha krishna", favouriteFootballers, new Colleague(1, "everything"));
		System.out.println("cloning object "+employee+" with hashcode "+employee.hashCode());
		
		try {
			Employee cloneObject = cloneObject(employee);
			
			System.out.println("cloned object "+cloneObject+" with hashcode "+cloneObject.hashCode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//supporting only one dimension array
	public static <T> T cloneObject(T object) throws Exception {
		Class<?> objectClass = object.getClass();
		
		if(objectClass == String.class) {
			return object;
		}

		// clone to new object ocmpletely
		Constructor<?> declaredConstructor = objectClass.getDeclaredConstructor();
		declaredConstructor.setAccessible(true);
		Object newInstance = declaredConstructor.newInstance();
		

		// creating new object
		Field[] declaredFields = objectClass.getDeclaredFields();
		for (Field declaredField : declaredFields) {
			// skip syntehtic fields like compiler added instance variable for outer class
			// in inner class(non static inner class)
			if (declaredField.isSynthetic()) {
				continue;
			}
			declaredField.setAccessible(true);
			Class<?> fieldType = declaredField.getType();
			if (fieldType.isArray()) {
				Object arrayObjectOld = declaredField.get(object);
				int length = Array.getLength(arrayObjectOld);
				Object arrayObjectNew = Array.newInstance(fieldType.getComponentType(), length);
				for (int i = 0; i < length; i++) {
					Array.set(arrayObjectNew, i, cloneObject(Array.get(arrayObjectOld, i)));
				}
				declaredField.set(newInstance, arrayObjectNew);
			} else if (fieldType.isPrimitive() || fieldType == String.class) {
				declaredField.set(newInstance, declaredField.get(object));
			} else {
				// simple object instance
				declaredField.set(newInstance, cloneObject(declaredField.get(object)));
			}
		}
		return (T)newInstance;
	}
}
