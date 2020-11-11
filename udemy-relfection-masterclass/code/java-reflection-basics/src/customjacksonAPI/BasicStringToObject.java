package customjacksonAPI;

public class BasicStringToObject {
	public static void main(String[] args) throws Exception {
		BasicJSONBean instance = new BasicJSONBean("kannu", 23, 100.12);
		String convertToString = JacksonUtil.convertToString(instance);
		System.out.println(convertToString);
		
		System.out.println(JacksonUtil.convertStringToObject(convertToString, BasicJSONBean.class));
		
	}
	
	
	
	
}
