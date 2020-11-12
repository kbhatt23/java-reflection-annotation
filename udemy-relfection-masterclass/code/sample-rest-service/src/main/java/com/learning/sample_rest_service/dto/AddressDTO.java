package com.learning.sample_rest_service.dto;

public class AddressDTO {
	private int id;
	private String addressLine;
	private String state;
	private String country;
	public AddressDTO(int id, String addressLine, String state, String country) {
		this.id = id;
		this.addressLine = addressLine;
		this.state = state;
		this.country = country;
	}
	
	
	public AddressDTO() {
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}


	@Override
	public String toString() {
		return "Address [id=" + id + ", addressLine=" + addressLine + ", state=" + state + ", country=" + country + "]";
	}
	
	
}
