package exam;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.reflect.*;

public class TestingFramework {
	public static void main(String[] args) {
		TestingFramework o = new TestingFramework();
		try {
			o.runTestSuite(SampleFrameworkTest.class);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//copy from below
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
    			.filter(method -> method.getParameterCount() == 0 &&method.getReturnType() == void.class)
    			.findFirst()
    			.orElse(null);
    }

    /**
     * Helper method to find all the methods that start with the given prefix
     */
    private List<Method> findMethodsByPrefix(Method[] methods, String prefix) {
    	return Arrays.stream(methods)
    			.filter(method -> method.getName().startsWith(prefix))
    			.filter(method -> method.getParameterCount() == 0 &&method.getReturnType() == void.class)
    			.collect(Collectors.toList());
    			
    			
    }
}