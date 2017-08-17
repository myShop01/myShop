package com.sofrecom.myshop.service;

import org.springframework.stereotype.Service;

import com.sofrecom.myshop.model.User;

@Service("roleService")
@FunctionalInterface
public interface RoleService {
	public User findRoleByID(int id);
}
