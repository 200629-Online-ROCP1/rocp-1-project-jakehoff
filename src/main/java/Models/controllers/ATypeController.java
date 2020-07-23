package Models.controllers;

import java.util.List;

import Models.AccountType;
import Models.services.ATypeService;

public class ATypeController {
	private final ATypeService us = new ATypeService();
	
	public List<AccountType> findAll(){
		return us.findAll();
	}
	
	public AccountType findById(int id) {
		return us.findById(id);
	}

	public boolean addUser(AccountType a) {
		return us.addType(a);
	}
}
