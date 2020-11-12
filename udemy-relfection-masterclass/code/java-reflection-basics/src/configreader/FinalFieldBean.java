package configreader;

import java.lang.reflect.Field;

public class FinalFieldBean {

	private String name = "instance name";

	private final String finalString = "final string";

	private static final String staticFinalString = "final static string";

	public FinalFieldBean() {
		this.name = "constructor";
		// this.finalString"final string constructor";
		// this.staticFinalString = "static final string constructor";
	}

	public static void main(String[] args) {
		FinalFieldBean obj = new FinalFieldBean();
		System.out.println("before reflection update" + obj);
		try {
			String fieldName = "name";
			String updatedMsg = "name updated";
			updateProperty(obj, fieldName, updatedMsg);

			fieldName = "finalString";
			updatedMsg = "final string updated";
			updateProperty(obj, fieldName, updatedMsg);
			
			//final field can not be updated but do not give exception
			//final static field on update gives runtime exception
			
			//fieldName = "staticFinalString";
			//updatedMsg = "static final string updated";
			//updateProperty(obj, fieldName, updatedMsg);

			System.out.println("after reflection update" + obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void updateProperty(FinalFieldBean obj, String fieldName, String updatedMsg)
			throws NoSuchFieldException, IllegalAccessException {
		Field declaredField = obj.getClass().getDeclaredField(fieldName);
		declaredField.setAccessible(true);
		declaredField.set(obj, updatedMsg);
	}

	@Override
	public String toString() {
		return "FinalFieldBean [name=" + name + ", finalString=" + finalString + ",staticFinalString="
				+ staticFinalString + "]";
	}

}
