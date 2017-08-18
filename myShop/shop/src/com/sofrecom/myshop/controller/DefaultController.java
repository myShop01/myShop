package com.sofrecom.myshop.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sofrecom.myshop.model.User;
import com.sofrecom.myshop.service.UserService;

@Controller
public class DefaultController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
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

	@GetMapping("/")
	public String defaultPage(Model m) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			String login = authentication.getName();
			User user = userService.findUser(login);

			m.addAttribute(user);
			return "/home";
		}

		return "/login";
	}

	@GetMapping("/login")
	public String login(Model m) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			String login = authentication.getName();
			User user = userService.findUser(login);

			m.addAttribute(user);
			return "/home";
		}

		return "/login";
	}

	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}

}
