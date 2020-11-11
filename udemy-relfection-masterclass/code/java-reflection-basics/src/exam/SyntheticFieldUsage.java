package exam;

import java.lang.reflect.Field;

import fieldsusage.FieldSyntheticBean;
import fieldsusage.FieldSyntheticBean.InnerClass;

public class SyntheticFieldUsage {
public static void main(String[] args) {
	try {
	Class<InnerClass> clazz = (Class<InnerClass>) Class.forName("fieldsusage.FieldSyntheticBean$InnerClass");
	InnerClass manageSyntieticFeilds = manageSyntieticFeilds(clazz, new FieldSyntheticBean().new InnerClass("initial value"));
	System.out.println("updated to string "+manageSyntieticFeilds);
	
	}catch (Exception e) {
e.printStackTrace();
	}
	
}

public static <T> T manageSyntieticFeilds(Class<T> clazz, T instance ) throws Exception{
	Field[] declaredFields = clazz.getDeclaredFields();
	
	for(Field field: declaredFields) {
		System.out.println(String.format("Field found with name %s and with synthetic type %s", field.getName(),field.isSynthetic()));
		
		//inner class will have synthetic files of outer class , as inner class have access to outer class instanc variables without creating object
		if(!field.isSynthetic() && field.getType() == String.class) {
			field.setAccessible(true);
			field.set(instance, "jai shree ram says everyone");
		}
	}
	
	
	
	return instance;
}
}
