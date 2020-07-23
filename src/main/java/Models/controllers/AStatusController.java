package Models.controllers;

import java.util.List;

import Models.AccountStatus;
import Models.services.AStatusService;



public class AStatusController {
	private final AStatusService us = new AStatusService();
	
	public List<AccountStatus> findAll(){
		return us.findAll();
	}
	
	public AccountStatus findById(int id) {
		return us.findById(id);
	}

	public boolean addStatus(AccountStatus a) {
		// TODO Auto-generated method stub
		return us.addStatus(a);
	}
}
