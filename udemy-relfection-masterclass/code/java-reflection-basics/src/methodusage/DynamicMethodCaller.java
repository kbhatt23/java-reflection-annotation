package methodusage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DynamicMethodCaller {
public static void main(String[] args) {
	try {
		int number =23;
		callMethodDyncamically(CallThisclassMethods.class,number);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void callMethodDyncamically(Class<?> clazz,Object... arguments) throws Exception{

	
	List<Class<?>> argumentClasses = Arrays.stream(arguments).map(Object::getClass).collect(Collectors.toList());

	Method methodToRun = null;
	//chekcing if all arguments are actually matched
	for(Method method: clazz.getDeclaredMethods()) {
		if(method.getParameters().length == argumentClasses.size()) {
		boolean isValid = Arrays.stream(method.getParameters())
		.allMatch(parameter -> argumentClasses.contains(parameter.getType()));
		if(isValid)
			methodToRun=method;
		}
	}
	if(methodToRun == null)
		throw new RuntimeException("method not found");
	Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
	declaredConstructor.setAccessible(true);
	Object newInstance = declaredConstructor.newInstance();
	
	methodToRun.invoke(newInstance,arguments);
}
}
