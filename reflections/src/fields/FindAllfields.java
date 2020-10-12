package fields;

import java.lang.reflect.Field;

import common.Entity;

public class FindAllfields {
public static void main(String[] args) {
	Class<?> clazz = Entity.class;
	Entity e = new Entity(99, "sita ram");
	//Class<?> clazz = e.getClass();
	
	//get visible instane variables -> for all super classes and currentl class -> private fields are ignored
	
	Field[] visibleFields = clazz.getFields();
	for(Field f : visibleFields) {
		System.out.println(f.getName() + " : "+f.getType());
	}
	
	System.out.println("===========");
	//all the fields private as well as public
	Field[] allFields = clazz.getDeclaredFields();
	for(Field f : allFields) {
		System.out.println(f.getName()+ " : "+f.getType() );
	}
	
	//modifying feilds
	try {
		System.out.println("inital  object "+e);
		//"value is instnace variable name"
		//modifuing public property
		Field fieldValue = clazz.getField("value");
		fieldValue.set(e, 108);
		
		//modifying private property -> wont be visible in getField MEthod
		//Field fieldName = clazz.getField("name");
		Field fieldName = clazz.getDeclaredField("name");
		
		
		//forcing acces for private variables -> if not set will caue secut=rity exception
		if(!fieldName.isAccessible())
			fieldName.setAccessible(true);
		fieldName.set(e, "shree ram");
	} catch (NoSuchFieldException | SecurityException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IllegalArgumentException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IllegalAccessException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	System.out.println("Final object "+e);
}
}
