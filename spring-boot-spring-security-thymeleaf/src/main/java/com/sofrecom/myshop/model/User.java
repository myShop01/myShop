package com.sofrecom.myshop.model;

import java.util.Set;

public class User {

	private int id;
	private String name;
	private String lastName;
	private String login;
	private String password;
	private String email;
	private String phoneNumber;
	private String address;

	private Set<Role> roles;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String name, String lastName, String login, String password, String email, String phoneNumber,
			String address, Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.roles = roles;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", login=" + login + ", password="
				+ password + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", roles="
				+ roles + "]";
	}
	
	

}
