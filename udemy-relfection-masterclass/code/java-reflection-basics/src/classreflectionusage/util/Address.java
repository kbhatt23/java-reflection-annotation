package classreflectionusage.util;

/*public*/ class Address {

	private int id;
	private String addressLine;
	private String state;
	public Address(int id, String addressLine, String state) {
		super();
		this.id = id;
		this.addressLine = addressLine;
		this.state = state;
	}
	private Address() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", addressLine=" + addressLine + ", state=" + state + "]";
	}

	
	
}
