package classreflectionusage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import classreflectionusage.Nestedclass.InnerClass;
import classreflectionusage.util.ReflectionUtil;

public class NestedClassForNameanalyze {
	public static void main(String[] args) throws ClassNotFoundException{
		//Class<InnerClass> claz = InnerClass.class;
		Class<InnerClass> claz = (Class<InnerClass>) Class.forName("classreflectionusage.Nestedclass$InnerClass");
		
		Map<String, Integer> itemMap = new HashMap<String, Integer>();
		
		ReflectionUtil.analyzeClasses(claz,String.class,int.class,itemMap.getClass());
	}
}
