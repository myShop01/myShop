package com.sofrecom.myshop.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sofrecom.myshop.model.User;
import com.sofrecom.myshop.service.UserService;

@Controller
public class DefaultController {
	
	private static final String HOME_PAGE ="home";

	@Autowired
	UserService userService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username

		m.addAttribute("username", name);
		return HOME_PAGE;
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}


	@GetMapping("/userprofile")
	public String userprofile(Model m) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String login = authentication.getName();
		User user = userService.findUser(login);

		m.addAttribute(user);
		return "userprofile";
	}


	@GetMapping("/")
	public String defaultPage(final Principal principal) {
		if (null == principal)
			return "login";
		return HOME_PAGE;
	}

	@GetMapping("/login")
	public String login(final Principal principal) {
		if (null == principal)
			return "login";
		return HOME_PAGE;
	}

	@GetMapping("/403")
	public String error403() {
		return "error/403";
	}

}
