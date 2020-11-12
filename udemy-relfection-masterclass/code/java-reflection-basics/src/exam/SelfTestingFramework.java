package exam;
import java.util.*;
import java.lang.reflect.*;
import java.util.stream.Collectors;

public class SelfTestingFramework {

    public void runTestSuite(Class<?> testClass) throws Throwable {

    	Method[] methods = testClass.getMethods();
    	Method beforeClassMethod = findMethodByName(methods, "beforeClass");
    	Object newInstance = testClass.getConstructor().newInstance();
    	if(beforeClassMethod != null) {
    		beforeClassMethod.invoke(null);
    	}
    	List<Method> testMethods = findMethodsByPrefix(methods, "test");
    	if(testMethods != null) {
    	for (Method testMethod : testMethods) {
    		Method setupTestMethod = findMethodByName(methods, "setupTest");
    		if(setupTestMethod!=null) {
    			setupTestMethod.invoke(newInstance);
    		}
    		Object testNEwInstance = testClass.getConstructor().newInstance();
    		testMethod.invoke(testNEwInstance);
		}
    	}
    	
    	Method afterClassMethod = findMethodByName(methods, "afterClass");
    	if(afterClassMethod != null) {
    		afterClassMethod.invoke(null);
    	}
    	
    
    }

    /**
     * Helper method to find a method by name
     * Returns null if a method with the given name does not exist
     */
    private Method findMethodByName(Method[] methods, String name) {

    	
    	return Arrays.stream(methods)
    			.filter(method -> method.getName().equals(name))
    			.findFirst()
    			.orElse(null);
    
    }

    /**
     * Helper method to find all the methods that start with the given prefix
     */
    private List<Method> findMethodsByPrefix(Method[] methods, String prefix) {

    	return Arrays.stream(methods)
    			.filter(method -> method.getName().startsWith(prefix))
    			.collect(Collectors.toList());
    			
    			
    
    }

}
