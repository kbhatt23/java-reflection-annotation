package com.learning.sample_rest_service.dto;

import java.util.List;

public class UserDTO {

	private int id;
	private String name;
	private List<String> favouriteFootballers;
	private AddressDTO address;
	public UserDTO(int id, String name, List<String> favouriteFootballers, AddressDTO address) {
		super();
		this.id = id;
		this.name = name;
		this.favouriteFootballers = favouriteFootballers;
		this.address = address;
	}
	public UserDTO() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getFavouriteFootballers() {
		return favouriteFootballers;
	}
	public void setFavouriteFootballers(List<String> favouriteFootballers) {
		this.favouriteFootballers = favouriteFootballers;
	}
	public AddressDTO getAddress() {
		return address;
	}
	public void setAddress(AddressDTO address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", favouriteFootballers=" + favouriteFootballers + ", address="
				+ address + "]";
	}
	
	
}
