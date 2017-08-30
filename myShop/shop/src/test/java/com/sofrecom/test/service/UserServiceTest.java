package com.sofrecom.test.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.sofrecom.myshop.model.User;
import com.sofrecom.myshop.service.UserService;
import com.sofrecom.myshop.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class UserServiceTest {

	@Autowired
	UserService userService;

	@Test
	public void testFindUsers() {

		List<User> users = userService.findUsers();
		assertNotNull(users);
		assertNotEquals(users.size(), 0);
	}

	@Test
	public void testFindUserByLoginPass() {

		String login = "userFille";
		String password = "userFille";

		User user = userService.findUser(login, password);
		assertNotNull(user);
		assertEquals(user.getLogin(), login);
		assertEquals(user.getPassword(), password);

	}

	@Test
	public void testFindUserByLogin() {
		String login = "adminHomme";
		User user = userService.findUser(login);
		assertEquals(user.getLogin(), login);
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
