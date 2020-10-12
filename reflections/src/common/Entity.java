package common;
public class Entity {
	public int value;
	private String name;
	public static int static_property = 23;
	
	public Entity(int value, String name) {
		this.value=value;
		this.name=name;
	}

	@Override
	public String toString() {
		return "Entity [value=" + value + ", name=" + name + "]";
	}
	
	private void privateMethod() {
		System.out.println("jai shre ram says private method with values "+toString());
	}
	
	public void publicMethod() {
		System.out.println("jai shre ram says publicMethod method with values "+toString());
	}
	
	public void publicMethod(String message) {
		System.out.println("printing message "+message);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
