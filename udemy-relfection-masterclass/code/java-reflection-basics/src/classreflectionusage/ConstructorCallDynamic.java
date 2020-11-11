package classreflectionusage;

import classreflectionusage.util.Person;
import classreflectionusage.util.ReflectionUtil;

public class ConstructorCallDynamic {
public static void main(String[] args) {
	try {
		//ReflectionUtil.createObjectWithArgumentsEasy(Class.forName("classreflectionusage.util.Address"),null);
		
		//ReflectionUtil.createObjectWithArgumentsEasy(Class.forName("classreflectionusage.util.Address"),new Object[] {1,"vaikunta log","narayan prabhu lok"},String.class);
	
		//ReflectionUtil.createObjectWithArgumentsEasy(Class.forName("classreflectionusage.util.Address"),new Object[] {1,"vaikunta lok","narayan prabhu lok"},int.class,String.class,String.class);
	
		//ReflectionUtil.createObjectWithArgumentsEasy(Class.forName("classreflectionusage.util.Person"),new Object[] {1,"vaikunta log","narayan prabhu lok"},int.class,String.class,String.class);
		
		Object address = ReflectionUtil.createObjectWithArgumentsEasy(Class.forName("classreflectionusage.util.Address"),new Object[] {1,"vaikunta log","narayan prabhu lok"},int.class,String.class,String.class);
		Person p = ReflectionUtil.createObjectWithArgumentsEasy(Person.class,new Object[] {"kannu",1,address},String.class,int.class,Class.forName("classreflectionusage.util.Address"));
	
		p.setId(23);
		System.out.println("updated person "+p);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
