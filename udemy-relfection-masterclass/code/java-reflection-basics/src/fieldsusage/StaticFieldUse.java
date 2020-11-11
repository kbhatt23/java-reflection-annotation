package fieldsusage;

import java.lang.reflect.Field;

public class StaticFieldUse {

	private static int VAL = 23;
	
	public static String STR = "jai shree ram";
	private int la = 99;
	
	public static void main(String[] args) {
		Class<StaticFieldUse> clazz = StaticFieldUse.class;
		
		try {
			Field field = clazz.getDeclaredField("VAL");
			//static field is indepenedt of object
			System.out.println("field val foiund "+field.get(null));
			
			 field = clazz.getDeclaredField("la");
			//static field is indepenedt of object
			System.out.println("field val foiund "+field.get(new StaticFieldUse()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
