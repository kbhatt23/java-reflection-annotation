package fieldsusage;

import java.lang.reflect.Field;

public class FileTestExamSamePAckage {
public static void main(String[] args) {
	Class<FieldUsageBean> clazz  =FieldUsageBean.class;
	
	//getfields give all public fields only
	//super class variales are also there
	//even in same package this will return only public fields
	Field[] fields = clazz.getFields();
	for(Field field: fields) {
		System.out.println("field found "+field);
	}
	
	//simple rule -> getFields return all public only fields of class as well as super class
	//private methods and default and protected fields are lost here
	
	System.out.println("===============");
	//returns all public [private protected default feilds but only of main class
	//super class ones are ignored
	fields = clazz.getDeclaredFields();
	for(Field field: fields) {
		System.out.println("field found "+field);
		
		
	}
}
}
