package com.sofrecom.myshop.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sofrecom.myshop.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{

	
	@Override
	public List<User> findUsers() {
		RestTemplate restTemplate = new RestTemplate();
        List<User> users = restTemplate.getForObject("http://localhost:3000/users", List.class);
		return users;
	}

	@Override
	public User findUser(String login, String password) {
		RestTemplate restTemplate = new RestTemplate();
	
        User[] users = restTemplate.getForObject("http://localhost:3000/users?login="+login+"&password="+password, User[].class);
        if(users.length>0){
        	List<User> listUsers = Arrays.asList(users); 
    		return listUsers.get(0);
        }
        else{
        	return null;
        }
        
	}
	
	@Override
	public User findUser(String login) {
		RestTemplate restTemplate = new RestTemplate();
	
        User[] users = restTemplate.getForObject("http://localhost:3000/users?login="+login, User[].class);
        List<User> listUsers = Arrays.asList(users); 
		return listUsers.get(0);
	}

}
