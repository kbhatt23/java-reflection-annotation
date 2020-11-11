package classreflectionusage;

import classreflectionusage.util.ReflectionUtil;

public class ConstructorCallDynami {
public static void main(String[] args) {
	try {
		//ReflectionUtil.createObjectWithArguments(Class.forName("classreflectionusage.util.Address"),null);
		
		//ReflectionUtil.createObjectWithArguments(Class.forName("classreflectionusage.util.Address"),new Object[] {1,"vaikunta log","narayan prabhu lok"},String.class);
	
		//ReflectionUtil.createObjectWithArguments(Class.forName("classreflectionusage.util.Address"),new Object[] {1,"vaikunta log","narayan prabhu lok"},int.class,String.class,String.class);
	
		//ReflectionUtil.createObjectWithArguments(Class.forName("classreflectionusage.util.Person"),new Object[] {1,"vaikunta log","narayan prabhu lok"},int.class,String.class,String.class);
		
		Object address = ReflectionUtil.createObjectWithArguments(Class.forName("classreflectionusage.util.Address"),new Object[] {1,"vaikunta log","narayan prabhu lok"},int.class,String.class,String.class);
		
		ReflectionUtil.createObjectWithArguments(Class.forName("classreflectionusage.util.Person"),new Object[] {"kannu",1,address},String.class,int.class,Class.forName("classreflectionusage.util.Address"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
