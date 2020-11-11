package customjacksonAPI;

public class ConvertBetweenObjects {
public static void main(String[] args) {
	BasicJSONBean instance = new BasicJSONBean("kannu", 23, 100.12);
	try {
		BasicJSONBeanCopy convertToObject = JacksonUtil.convertToObject(instance, BasicJSONBeanCopy.class);
		System.out.println(convertToObject);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
}
}
