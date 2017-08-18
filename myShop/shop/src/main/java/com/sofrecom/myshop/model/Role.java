package com.sofrecom.myshop.model;

public class Role {

	private int id;
	private String value;

	public Role() {
		super();
	}

	public Role(int id, String role) {
		super();
		this.id = id;
		this.value = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String role) {
		this.value = role;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + value + "]";
	}
	
	

}
