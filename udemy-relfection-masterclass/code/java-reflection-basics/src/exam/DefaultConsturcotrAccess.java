package exam;

import java.lang.reflect.Constructor;

import classreflectionusage.DefaultPAckageClass;

public class DefaultConsturcotrAccess {
public static void main(String[] args) throws Exception {
	Class<DefaultPAckageClass> clazz = DefaultPAckageClass.class;
	Constructor<DefaultPAckageClass> constructor=null;
	constructor= clazz.getConstructor();
	System.out.println(constructor.newInstance());
	
	System.out.println("=");
	 constructor = clazz.getDeclaredConstructor(int.class);
	System.out.println(constructor.newInstance(1));
	
	
}
}
