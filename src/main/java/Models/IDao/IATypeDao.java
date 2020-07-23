package Models.IDao;

import java.util.List;

import Models.AccountType;

public interface IATypeDao {
	
	public List<AccountType> findAll();
	public AccountType findById(int id);
	public boolean addType(AccountType a);
}
