package exam;

import java.util.*;

public class Exercise {

	/**
	 * Returns all the interfaces that the current input class implements. Note: If
	 * the input is an interface itself, the method returns all the interfaces the
	 * input interface extends.
	 */
	public static Set<Class<?>> findAllImplementedInterfaces(Class<?> input) {

		Set<Class<?>> allInterfaces = new HashSet<Class<?>>();
		Class<?>[] interfaces = input.getInterfaces();
		if (interfaces != null && interfaces.length > 0) {
			for (Class<?> interfaceFound : interfaces) {
				allInterfaces.add(interfaceFound);
				if (interfaceFound.isInterface()) {
					allInterfaces.addAll(findAllImplementedInterfaces(interfaceFound));
				}
			}
		}
		return allInterfaces;
	}
}