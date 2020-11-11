package classreflectionusage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.stream.Collectors;

import classreflectionusage.util.Person;

public class ConstructorUsage {
	public static void main(String[] args) {

		Class<Person> personClass = Person.class;
		analyzeconstructor(personClass);
		//addres sis default packaged and hence wont be accesible
		//analyzeconstructor(Address.class);
		
		try {
			analyzeconstructor(Class.forName("classreflectionusage.util.Address"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void analyzeconstructor(Class<?> clazz) {
		//will return even private constructors
		Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
		System.out.println(String.format("class %s have %d constructors", clazz.getSimpleName(),declaredConstructors.length));
		for (Constructor<?> constructor : declaredConstructors) {
			System.out.println("constructor found with arguments: "+
					Arrays.stream(
					constructor.getParameters())
					.map(Parameter::getType)
					.collect(Collectors.toList())
					);
			
		}
	}
}
