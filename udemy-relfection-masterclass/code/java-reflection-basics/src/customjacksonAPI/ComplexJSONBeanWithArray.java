package customjacksonAPI;

public class ComplexJSONBeanWithArray {
	public static void main(String[] args) throws Exception {
		ComplexJSONBeanWithArray instance = new ComplexJSONBeanWithArray("kannu", 23, 100.12);
		System.out.println(JacksonUtil.convertToString(instance));
	}
	
	private String name;
	private int age;
	private double price;
	String[][] names;
	private BasicJSONBean[][] beans;
	public ComplexJSONBeanWithArray(String name, int age, double price) {
		this.name = name;
		this.age = age;
		this.price = price;
		this.names = new String[][] {{"jai sita ram","jai radhe shyam"},
				{"jai shiva shankar","jai shree ganesh"}
		};
		this.beans= new BasicJSONBean[][] {{new BasicJSONBean("inner one", 10, 9),
				new BasicJSONBean("inner two", 20, 18),new BasicJSONBean("inner three", 30, 27)},
			
			{new BasicJSONBean("inner two two", 3, 4),
					new BasicJSONBean("inner three three", 5, 6),new BasicJSONBean("inner four four", 7, 8)}
		};
	}
	
	public ComplexJSONBeanWithArray() {
	}

	@Override
	public String toString() {
		return "BasicObjectoToString [name=" + name + ", age=" + age + ", price=" + price + "]";
	}
	
	
	
}
