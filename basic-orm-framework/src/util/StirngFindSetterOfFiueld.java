package util;

public class StirngFindSetterOfFiueld {

	public static void main(String[] args) {
		String field = "firstName";
		//setter shud be setFirstName
		String setter = new StringBuilder().
		append("set")
		.append(
		Character.toUpperCase(field.charAt(0)))
		.append(field.substring(1))
		.toString()
		;
		System.out.println("setter of field "+field+" is: "+setter);
	}
}
