package modifiers;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import arrayfields.Employee;

public class ModifiersBasic {
public static void main(String[] args) throws Exception {
	Class<Employee> employeeclass =  Employee.class;
	Field declaredField = employeeclass.getDeclaredField("name");
	
	int modifiersBit = declaredField.getModifiers();
	int resultBit = modifiersBit & Modifier.PRIVATE;
	System.out.println("is private "+(resultBit != 0));
}

}
