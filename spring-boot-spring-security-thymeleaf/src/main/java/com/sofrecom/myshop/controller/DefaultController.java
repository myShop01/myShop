package com.sofrecom.myshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sofrecom.myshop.model.User;
import com.sofrecom.myshop.service.UserService;

@Controller
public class DefaultController {
	
	@Autowired
	UserService userService;

	@GetMapping("/")
	public String home1() {
		return "/login";
	}

	@GetMapping("/home")
	public String home(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username

		m.addAttribute("username", name);
		return "/home";
	}

	@GetMapping("/admin")
	public String admin() {
		return "/admin";
	}

	@GetMapping("/user")
	public String user() {
		return "/user";
	}
	
	@GetMapping("/userprofile")
	public String userprofile(Model m) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String login = authentication.getName();
		User user = userService.findUser(login);
		
		m.addAttribute(user);
		return "/userprofile";
	}
	

	@GetMapping("/about")
	public String about() {
		return "/about";
	}

	@GetMapping("/login")
	public String login() {
		return "/login";
	}

	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}

}
