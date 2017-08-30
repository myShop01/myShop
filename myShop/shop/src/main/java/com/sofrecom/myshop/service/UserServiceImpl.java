package com.sofrecom.myshop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sofrecom.myshop.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	public static final String REST_URI_PREFIX = "http://localhost:3000";

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUsers() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(REST_URI_PREFIX + "/users", List.class);
	}

	@Override
	public User findUser(String login, String password) {
		RestTemplate restTemplate = new RestTemplate();
		User user = new User();

		User[] users = restTemplate.getForObject(REST_URI_PREFIX + "/users?login=" + login + "&password=" + password,
				User[].class);
		if (users.length > 0) {
			user = users[0];
		}

		return user;

	}

	@Override
	public User findUser(String login) {
		
		RestTemplate restTemplate = new RestTemplate();
		User user = new User();
		
		
		User[] users = restTemplate.getForObject(REST_URI_PREFIX + "/users?login=" + login, User[].class);

		if (users.length > 0) {
			user = users[0];
		}

		return user;
	}

}
