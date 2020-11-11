package customjacksonAPI;

public class BasicJSONBeanCopy {
	
	private String name;
	private int age;
	private double price;
	
	private String newProeprty;
	public BasicJSONBeanCopy(String name, int age, double price, String newProeprty) {
		this.name = name;
		this.age = age;
		this.price = price;
		this.newProeprty=newProeprty;
	}
	
	public BasicJSONBeanCopy() {
	}

	@Override
	public String toString() {
		return "BasicJSONBeanCopy [name=" + name + ", age=" + age + ", price=" + price + ", newProeprty=" + newProeprty
				+ "]";
	}

	
	
	
}
