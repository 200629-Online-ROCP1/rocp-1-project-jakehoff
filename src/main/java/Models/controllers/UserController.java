package Models.controllers;

import java.util.List;


import Models.User;
import Models.services.UserService;

public class UserController {
	private final UserService us = new UserService();
	
	public List<User> findAll(){
		return us.findAll();
	}
	
	public User findById(int id) {
		return us.findById(id);
	}

	public boolean addUser(User z) {
		return us.addUser(z);
	}
}
