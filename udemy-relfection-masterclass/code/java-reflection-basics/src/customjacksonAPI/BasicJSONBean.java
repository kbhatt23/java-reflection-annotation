package customjacksonAPI;

public class BasicJSONBean {
	public static void main(String[] args) throws Exception {
		BasicJSONBean instance = new BasicJSONBean("kannu", 23, 100.12);
		System.out.println(JacksonUtil.convertToString(instance));
	}
	
	private String name;
	private int age;
	private double price;
	public BasicJSONBean(String name, int age, double price) {
		this.name = name;
		this.age = age;
		this.price = price;
	}
	
	public BasicJSONBean() {
	}

	@Override
	public String toString() {
		return "BasicObjectoToString [name=" + name + ", age=" + age + ", price=" + price + "]";
	}
	
	
	
}
