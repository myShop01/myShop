package com.sofrecom.myshop.service;

import java.util.List;

import com.sofrecom.myshop.model.User;

public interface UserService {

	public List<User> findUsers();

	public User findUser(String login, String password);
	
	public User findUser(String login);
}
