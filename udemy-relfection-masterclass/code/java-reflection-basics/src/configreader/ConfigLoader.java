package configreader;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ConfigLoader {

	public static <T> T loadConfig(String file,Class<T> configBeanClass) throws Exception {
		List<String> configLines = Files.readAllLines(Paths.get(file));
		Constructor<T> declaredConstructor = configBeanClass.getDeclaredConstructor();
		declaredConstructor.setAccessible(true);
		T configObject = declaredConstructor.newInstance();
		for(String configLine:configLines) {
			String[] lineSplit = configLine.split("=");
			String propertyName = lineSplit[0];
			String propertyVale = lineSplit[1];
			Field declaredField = configBeanClass.getDeclaredField(propertyName);
			declaredField.setAccessible(true);
			if(declaredField.isSynthetic()) {
				continue;
			}
			declaredField.set(configObject, extractConfigValue(declaredField.getType(),propertyVale));
		}
		return configObject;
	}

	
	//we are supporitng only int and string and array of string or int
	private static Object extractConfigValue(Class<?> type, String propertyVale) {
		if(type.isPrimitive()) {
			return Integer.parseInt(propertyVale);
		}else if(type == String.class) {
			return propertyVale;
		}else if(type.isArray()){
			//we support int/string and array of string and int
			String[] split = propertyVale.split(",");
			int length = split.length;
			Class<?> componentType = type.getComponentType();
			Object newArrayObject = Array.newInstance(componentType, length);
			for(int i=0 ; i<length;i++) {
				Array.set(newArrayObject, i, extractConfigValue(componentType, split[i]));
			}
			return newArrayObject;
		}
		else {
			throw new RuntimeException("do not support type "+type.getName());
		}
	}
	
	public static <T> void setConfigToFields(T loaderConfig, Object instance) {
		Class<?> serviceClass = instance.getClass();
		if(!serviceClass.isAnnotationPresent(Service.class)) {
			throw new RuntimeException(String.format("Class %s is not annottated with @Service", serviceClass.getSimpleName()));
		}
		Field[] fields = serviceClass.getDeclaredFields();
		for(Field field: fields) {
			field.setAccessible(true);
			Class<?> fieldType = field.getType();
			if(fieldType.isSynthetic()) {
				continue;
			}
			if(field.isAnnotationPresent(Value.class)) {
				Value annotation = field.getAnnotation(Value.class);
				//supporting int, String and String[]and int[]
				String property = annotation.property().equals("DEFAULT") ? field.getName(): annotation.property() ;
				try{
					Field declaredField = loaderConfig.getClass().getDeclaredField(property);
					declaredField.setAccessible(true);
					field.set(instance, declaredField.get(loaderConfig));
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
}
