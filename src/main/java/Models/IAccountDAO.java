package Models;

import java.util.List;
public interface IAccountDAO {
	
	public List<Account> findAll();
	public Account findById(int id);
	public boolean addAccount(Account a, User u, AccountStatus s, AccountType t);
	public float Deposit(float i, Account a);
	public float Withdraw(float i, Account a);
	public float Transfer(float i, Account a, Account b);
	
}
