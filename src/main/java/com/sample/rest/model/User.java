package com.sample.rest.model;

import org.springframework.core.style.ToStringCreator;

public class User implements Comparable<User>{

	private int id;
	

	private String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
				.append("id : ", this.id)
				.append("name : ",this.name)
				.toString();	
	}

	@Override
	public int compareTo(User o) {
		return this.getName().compareTo(o.getName());
	}
	
}
