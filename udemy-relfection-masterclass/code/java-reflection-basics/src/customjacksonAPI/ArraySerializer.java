package customjacksonAPI;

public class ArraySerializer {
public static void main(String[] args) throws Exception {
	BasicJSONBeanWithArray obj = new BasicJSONBeanWithArray("kannu", 23, 100);
	System.out.println(JacksonUtil.convertToString(obj));
	
	//ComplexJSONBeanWithArray obj = new ComplexJSONBeanWithArray("kannu", 23, 100);
	//System.out.println(JacksonUtil.convertToString(obj));
}
}
