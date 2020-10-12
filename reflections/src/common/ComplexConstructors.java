package common;

public class ComplexConstructors {

	private int value;
	private String name;

	public ComplexConstructors() {
	}

	private ComplexConstructors(int value) {
		this.value = value;
	}

	private ComplexConstructors(String name) {
		this.name = name;
	}

	public ComplexConstructors(int value, String name) {
		System.out.println("all arg called with args "+value+ " , "+name);
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return "ComplexConstructors [value=" + value + ", name=" + name + "]";
	}
	
	
}
