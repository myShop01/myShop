package com.sofrecom.test.service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.sofrecom.myshop.model.User;
import com.sofrecom.myshop.service.UserService;
import com.sofrecom.myshop.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserServiceTest {

	
	@Test
	public void testFindUsers() {
		RestTemplate restTemplate = new RestTemplate();

		User[] users = restTemplate.getForObject("http://localhost:3000/users", User[].class);
		assertNotNull(users);
		assertNotEquals(users.length, 0);
	}

	@Test
	public void testFindUserByLoginPass() {
		RestTemplate restTemplate = new RestTemplate();

		String login = "user";
		String password = "user";

		User[] users = restTemplate.getForObject("http://localhost:3000/users?login=" + login + "&password=" + password,
				User[].class);
		assertNotNull(users);
		assertNotEquals(users.length, 0);
		assertEquals(users.length, 1);

	}

	@Test
	public void testFindUserByLogin() {

		RestTemplate restTemplate = new RestTemplate();

		String login = "admin";
		User[] users = restTemplate.getForObject("http://localhost:3000/users?login=" + login, User[].class);
		assertNotNull(users);
		assertNotEquals(users.length, 0);
		List<User> listUsers = Arrays.asList(users);
		assertNotEquals(listUsers.size(), 0);
		assertEquals(listUsers.get(0).getLogin(), login);
	}

	@Configuration
	static class ContextConfiguration {

		// this bean will be injected into Test class
		@Bean
		public UserService userService() {
			UserService userService = new UserServiceImpl();
			// set properties, etc.
			return userService;
		}
	}

}
