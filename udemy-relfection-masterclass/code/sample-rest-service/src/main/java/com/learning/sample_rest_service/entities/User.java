package com.learning.sample_rest_service.entities;

import java.util.List;

public class User {

	private int id;
	private String name;
	private List<String> favouriteFootballers;
	private Address address;
	public User(int id, String name, List<String> favouriteFootballers, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.favouriteFootballers = favouriteFootballers;
		this.address = address;
	}
	public User() {
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", favouriteFootballers=" + favouriteFootballers + ", address="
				+ address + "]";
	}
	
	
}
