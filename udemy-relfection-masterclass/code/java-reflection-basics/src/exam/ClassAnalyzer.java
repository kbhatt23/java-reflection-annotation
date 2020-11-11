package exam;

import java.util.*;
import java.util.stream.Collectors;
public class ClassAnalyzer {
    private static final List<String> JDK_PACKAGE_PREFIXES =
                Arrays.asList("com.sun.", "java", "javax", "jdk", "org.w3c", "org.xml");
                
    public static PopupTypeInfo createPopupTypeInfoFromClass(Class<?> inputClass) {
        PopupTypeInfo popupTypeInfo = new PopupTypeInfo();
        
        /** Complete the Code **/
        
        
        popupTypeInfo.setPrimitive(inputClass.isPrimitive())
            .setInterface(inputClass.isInterface())
            .setEnum(inputClass.isEnum())
            .setName(inputClass.getName())
            .setJdk(isJdkClass(inputClass))
            .addAllInheritedClassNames(getAllInheritedClassNames(inputClass));
        
        return popupTypeInfo;
    }
    
    /*********** Helper Methods ***************/
    
    public static boolean isJdkClass(Class<?> inputClass) {
    	if(inputClass.isPrimitive()) {
    		return true;
    	}
    	return JDK_PACKAGE_PREFIXES.stream()
    						.anyMatch(a -> inputClass.getPackage().getName().contains(a));
    }
    
    public static String[] getAllInheritedClassNames(Class<?> inputClass) {
    	String[] items = new String[inputClass.getClasses().length];
    	Arrays.stream(inputClass.getClasses())
    				.map(a -> a.getSimpleName())
    				.collect(Collectors.toList())
    				.toArray(items);
    	return items;
    }
}