package annotaion_reflection_usage;

import java.lang.reflect.Method;

public class MethodUtilRunner {
public static void main(String[] args) throws Exception{
	MethodUtility obj = new MethodUtility();
	Class<?> class1 = Class.forName("annotaion_reflection_usage.MethodUtility");
	//get all accessible methods present in the class inlcuding super class
	Method[] methods = class1.getMethods();
	for(Method method :methods) {
		//System.out.println("cecking method "+method.getName());
		if(method.isAnnotationPresent(CallThisMethod.class)) {
			//System.out.println("cecking method "+method.getName());
			 CallThisMethod annotation = method.getAnnotation(CallThisMethod.class);
			 int intArgument =annotation.intArgument();
			 String strArgument = annotation.stringArgument();
			 boolean isNorg = annotation.noArgProperty();
			 if(intArgument != 0) {
				 //int case
				 method.invoke(obj,intArgument);
			 }else if(strArgument != null && !strArgument.equals("")) {
				 method.invoke(obj,strArgument);
			 }else if(isNorg){
				 //default is no arg
				 method.invoke(obj);
			 }
			
		}
	}
	
}
}
