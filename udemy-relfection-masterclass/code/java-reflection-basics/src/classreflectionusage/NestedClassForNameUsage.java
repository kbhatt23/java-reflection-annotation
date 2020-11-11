package classreflectionusage;

import java.util.Arrays;

import classreflectionusage.Nestedclass.InnerClass;

public class NestedClassForNameUsage {
	public static void main(String[] args) throws ClassNotFoundException{
		//Class<InnerClass> claz = InnerClass.class;
		Class<InnerClass> claz = (Class<InnerClass>) Class.forName("classreflectionusage.Nestedclass$InnerClass");
		
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
