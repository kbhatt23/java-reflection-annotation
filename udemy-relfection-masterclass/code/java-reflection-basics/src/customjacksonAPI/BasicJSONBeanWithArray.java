package customjacksonAPI;

public class BasicJSONBeanWithArray {
	public static void main(String[] args) throws Exception {
		BasicJSONBeanWithArray instance = new BasicJSONBeanWithArray("kannu", 23, 100.12);
		System.out.println(JacksonUtil.convertToString(instance));
	}
	
	private String name;
	private int age;
	private double price;
	String[] names;
	private BasicJSONBean[] beans;
	public BasicJSONBeanWithArray(String name, int age, double price) {
		this.name = name;
		this.age = age;
		this.price = price;
		this.names = new String[] {"jai sita ram","jai radhe shyam"};
		this.beans= new BasicJSONBean[] {new BasicJSONBean("inner one", 10, 9),
				new BasicJSONBean("inner two", 20, 18),new BasicJSONBean("inner three", 30, 27)};
	}
	
	public BasicJSONBeanWithArray() {
	}

	@Override
	public String toString() {
		return "BasicObjectoToString [name=" + name + ", age=" + age + ", price=" + price + "]";
	}
	
	
	
}
