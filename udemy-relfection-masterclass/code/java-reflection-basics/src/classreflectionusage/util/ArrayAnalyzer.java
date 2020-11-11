package classreflectionusage.util;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayAnalyzer {

	public static void main(String[] args) {
		//analyzeArrayObject("jai shree ram");
		String[][] object = new String[][] {{"jai shree ram","jai ramduta hanuman"},{"jai shree krishna","jai krishnaduta kannu"}};
		analyzeArrayObject(object);
	}
	
	public static void analyzeArrayObject(Object arrayObject) {
		Class<? extends Object> arrayClass = arrayObject.getClass();
		boolean isArray =arrayClass.isArray();
		System.out.println(String.format("Object with name %s is array %s", arrayClass.getSimpleName(),isArray));
		
		if(isArray) {
			Class<?> componentType = arrayClass.getComponentType();
			System.out.println("Type of individual array entries is "+componentType.getSimpleName());
			
			int size = Array.getLength(arrayObject);
			for(int i=0 ; i< size ; i++) {
				Object object = Array.get(arrayObject, i);
				if(componentType.isArray()) {
					analyzeArrayObject(object);
				}else {
				System.out.println(String.format("Item found at index %s with value %s", i,object));}
			}
		}
	}
}
