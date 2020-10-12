package methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import common.Entity;

public class CallSetterAndGetter {
	public static void main(String[] args) {
		Entity e = new Entity(108, "jai shree ram");
		System.out.println("initial object "+e);
		//both methods are public hence using getField
		Class<? extends Entity> class1 = e.getClass();
		
			try {
				Method setValMethod = class1.getMethod("setValue", int.class);
				setValMethod.invoke(e, 101);
				
				//calling getter
				Method getValMethod = class1.getMethod("getValue");
				
				Object invokeresult = getValMethod.invoke(e);
				System.out.println(invokeresult + " "+ invokeresult.getClass().getName());
			} catch (NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			System.out.println("finla object "+e);
	}
	

}
