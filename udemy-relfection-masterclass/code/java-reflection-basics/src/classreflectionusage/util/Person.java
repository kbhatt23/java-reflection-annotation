package classreflectionusage.util;

public class Person {

	private String name;
	private int id;
	
	private Address address;

	public Person(String name, int id, Address address) {
		this( name, address);
		this.id = id;
	}
	//forcing default constructor
	public Person() {
		super();
	}
	
	private Person(String name, Address address) {
		this.name = name;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", id=" + id + ", address=" + address + "]";
	}
	
	
	
}
