package classreflectionusage;

import java.util.Arrays;

import classreflectionusage.Nestedclass.InnerClass;

public class NestedClassUsage {
	public static void main(String[] args) {
		Class<InnerClass> claz = InnerClass.class;
		
		
		//provides all the public methods -> inherited or created in the class
		Arrays.stream(claz.getMethods())
			.forEach(a -> System.out.println(a.getName()));
			
		
		System.out.println("==================");
		//returns al private and public methods but only created int the class
		//super class public methods are ignoed
		Arrays.stream(claz.getDeclaredMethods())
		.forEach(a -> System.out.println(a.getName()));
	}
}
