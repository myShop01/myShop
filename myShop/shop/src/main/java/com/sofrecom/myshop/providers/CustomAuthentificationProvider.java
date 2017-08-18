package com.sofrecom.myshop.providers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.sofrecom.myshop.model.User;
import com.sofrecom.myshop.service.UserService;

@Component
public class CustomAuthentificationProvider implements AuthenticationProvider {

	@Autowired
	UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) {

		String login = authentication.getName();
		String password = authentication.getCredentials().toString();
		User user = userService.findUser(login, password);
		if (user != null) {
			List<GrantedAuthority> grantedAuths = new ArrayList<>();

			user.getRoles().forEach(role -> grantedAuths.add(new SimpleGrantedAuthority(role.getValue())));

			return new UsernamePasswordAuthenticationToken(login, password, grantedAuths);

		} else {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}