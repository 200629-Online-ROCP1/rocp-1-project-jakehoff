package Models.controllers;

import Models.AccountService;
import Models.AccountStatus;
import Models.AccountType;
import Models.User;

import java.util.List;

import Models.Account;
public class AccountController {
	
	private final AccountService as = new AccountService();
	
	public List<Account> findAll(){
		return as.findAll();
	}
	
	public Account findById(int id) {
		return as.findById(id);
	}
	public float Withdraw(float i, Account b) {
		//Account z = as.findById(b.getId());
		return as.Withdraw(i, b);
	}
	public float Deposit(float i, Account b) {
		return as.Deposit(i, b);
	}
	public float Transfer(float i, Account b, Account c) {
		return as.Transfer(i,b, c);
	}
	public boolean addAccount(Account a) {
		return as.addAccount(a);
	}
}
