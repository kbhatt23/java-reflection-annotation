package methodusage.validateGetterFramework;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import arrayfields.Employee;

public class ValidateGetterMethods {

	public static void main(String[] args) {
		try {
			//
			validateBenGetters(Employee.class);
			validateBeanSetter(Employee.class);
			System.out.println("Bean is valid");
			
			//System.out.println(findAllPRivateFieldsIncludingSuperclass(Employee.class));
		} catch (Exception e) {
			System.out.println("bean is not valid");
			e.printStackTrace();
		}
	}
	public static void validateBenGetters(Class<?> clazz) throws Exception {
		
		//getter are nothing but get + filedName first cahr in upper case + retst of chars of field
		//like jackson we need to have getter for the beans 
		//we can use this logic to validate a bean if that is used by jackson
		
		//Field[] declaredFields = clazz.getDeclaredFields();
		
		 List<Field> declaredFields = findAllPRivateFieldsIncludingSuperclass(clazz);
		for(Field declaredField:declaredFields) {
			String fieldName = declaredField.getName();
			StringBuilder appropraiteMethodName = new StringBuilder();
			appropraiteMethodName.append("get")
			.append(Character.toUpperCase(fieldName.charAt(0)))
			.append(fieldName.substring(1));
			
			//parameter type should be empty for getters
			//return type of method shud be same as the data type of field
			
			Method declaredMethod = clazz.getMethod(appropraiteMethodName.toString());
			if(declaredField.getType() != declaredMethod.getReturnType()){
				throw new RuntimeException("method with name "+declaredMethod+ "is not valid for field "+declaredField);
			}
		}
	}

	public static void validateBeanSetter(Class<?> clazz) throws Exception {
		//Field[] declaredFields = clazz.getDeclaredFields();
		 List<Field> declaredFields = findAllPRivateFieldsIncludingSuperclass(clazz);
		for (Field declaredField : declaredFields) {
			String fieldName = declaredField.getName();
			StringBuilder appropraiteMethodName = new StringBuilder();
			appropraiteMethodName.append("set")
			.append(Character.toUpperCase(fieldName.charAt(0)))
			.append(fieldName.substring(1));
			
			Method method = clazz.getMethod(appropraiteMethodName.toString(), declaredField.getType());
			if(method.getReturnType() != void.class) {
				throw new RuntimeException("method with name "+method+ "is not valid for field "+declaredField);
			}
		}
	}
	
	public static List<Field> findAllPRivateFieldsIncludingSuperclass(Class<?> clazz){
		if(clazz== null || clazz == Object.class) {
			return new ArrayList<Field>();
		}
		List<Field> currentClasfield =  Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList());
		currentClasfield.addAll(findAllPRivateFieldsIncludingSuperclass(clazz.getSuperclass()));
		return currentClasfield;
	}
}
