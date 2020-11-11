package exam;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import fieldsusage.FieldUsageBean;

public class FileTestExamDifferentPAckageUpdate {
public static void main(String[] args) {
	Class<FieldUsageBean> clazz  =FieldUsageBean.class;
	try {
		//this shud give runtime exception -> as getfiled provided only public fields
		//Field field = clazz.getField("defaultString");
		
		Field field = clazz.getDeclaredField("defaultString");
		Constructor<FieldUsageBean> constructor = clazz.getConstructor();
		FieldUsageBean newInstance = constructor.newInstance();
		System.out.println("object befor eupdate "+newInstance);
		field.setAccessible(true);
		field.set(newInstance, "jai sita ram");
		System.out.println("object after eupdate "+newInstance);
		System.out.println("field value using reflection: "+field.get(newInstance));
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
