package com.learning.dependencygraph.algorithm;

//represent footballer form a single team
public class Footballer {

	private String name;
	private int jersey;
	private String position;
	private int rating;
	public Footballer(String name, int jersey, String position, int rating) {
		super();
		this.name = name;
		this.jersey = jersey;
		this.position = position;
		this.rating=rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getJersey() {
		return jersey;
	}
	public void setJersey(int jersey) {
		this.jersey = jersey;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Footballer() {
		super();
	}
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "Footballer [name=" + name + ", jersey=" + jersey + ", position=" + position + ", rating=" + rating
				+ "]";
	}	
	
	
}
