package methodusage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SimpleTestRunner {
public static void main(String[] args) {
	try {
		runTest(JunitTestClass.class);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void runTest(Class<?> testClass) throws Exception{
	Method[] declaredMethods = testClass.getDeclaredMethods();
	Constructor<?> declaredConstructor = testClass.getDeclaredConstructor();
	declaredConstructor.setAccessible(true);
	Object newInstance = declaredConstructor.newInstance();
	for(Method declaredMethod: declaredMethods) {
		declaredMethod.invoke(newInstance);
	}
}
}
