package customjacksonAPI;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class JacksonUtil {
	//as of now just ocnerting to string
	//later on other method to convert to another class can be done
	public static <T> String convertToString(T instance) throws Exception{
		
		Class<?> clz = instance.getClass();
		//get all fields private or protected or public etc
		Field[] declaredFields = clz.getDeclaredFields();
		
		return Arrays.stream(declaredFields)
				//ignoring synthetic fiuleds like co,mpiler provided instanc eof class in inner class
				.filter(f -> !f.isSynthetic())
				.map(f -> {f.setAccessible(true); return f;})
				.map(f -> {
					try {
						Object object = f.get(instance);
						if(f.getType().isPrimitive() || f.getType() == String.class) {
							return f.getName() + ":" + object;
						}else if(f.getType().isArray()) {
							//coud be 2 d array as well
							//Class<?> componentType = f.getType().getClass().getComponentType();
							StringBuilder sb = new StringBuilder();
							int length = Array.getLength(object);
							for(int i=0 ; i < length; i++) {
								Object innerObject = Array.get(object, i);
							 if(innerObject.getClass().isPrimitive() || innerObject.getClass() == String.class){
									sb.append(innerObject);
								}else {
									sb.append(convertToString(innerObject));
								}
								
								if(i != length-1) {
									sb.append(",");
								}
							}
							return f.getName() + ":"+ "["+sb.toString()+"]";
						}else {
							//simple object
							return f.getName() + ":" +convertToString(object);
						}
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return "";
				})
				.collect(Collectors.joining(",", "{", "}"));
				
				
		/*
		 * StringBuilder sb = new StringBuilder();
		 * 
		 * int iteration = 0;
		 */
		/*
		 * for (Field field : declaredFields) { field.setAccessible(true);
		 * sb.append(field.getName()).append(":").append(field.get(instance));
		 * if(!(iteration == declaredFields.length-1)) { sb.append(","); } iteration++;
		 * }
		 * 
		 * return sb.toString();
		 */
	}
	
	public static <T> T convertStringToObject(String str, Class<T> claz) throws Exception{
		str=str.substring(1);
		str = str.substring(0,str.length()-1);
		String[] entries = str.split(",");
		
		T newInstance = claz.getConstructor().newInstance();
		for(String entry: entries) {
			String[] valueParts = entry.split(":");
			String propertyName = valueParts[0];
			String propertyValue = valueParts[1];
			Field declaredField = claz.getDeclaredField(propertyName);
			if(declaredField.isSynthetic()) {
				//siwthc to next iteration
				continue;
			}
			declaredField.setAccessible(true);
			declaredField.set(newInstance, extractObjectType(propertyValue, declaredField));
		}
		
		return newInstance;
		
	}
	
	public static Object extractObjectType(String propertyValue,Field field) {
		Object val = null;
		String type = field.getType().getSimpleName();
		if(type.contains("String")) {
			val = propertyValue; 
		}else if(type.contains("int")) {
			val = Integer.parseInt(propertyValue);
		}
		else if(type.contains("double")) {
			val= Double.parseDouble(propertyValue);
		}
		return val;
	}
	
	public static <T,R> R convertToObject(T instance , Class<R> toClass) throws Exception{
		R newInstance = toClass.getDeclaredConstructor().newInstance();	
		try {
			Field[] declaredFields = instance.getClass().getDeclaredFields();
			for(Field field : declaredFields) {
				if(field.isSynthetic()) {
					//swithc to next iteration
					continue;
				}
				field.setAccessible(true);
				Field declaredField = toClass.getDeclaredField(field.getName());
				declaredField.setAccessible(true);
				declaredField.set(newInstance, field.get(instance));
			}
		}catch (Exception e) {

		}
		return newInstance;
	}
}
