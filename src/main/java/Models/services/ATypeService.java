package Models.services;

import java.util.List;

import Models.AccountType;
import Models.IDao.IATypeDao;
import Models.dao.ATypeDao;

public class ATypeService {
	private final IATypeDao dao = new ATypeDao();
	
	public List <AccountType> findAll(){
		return dao.findAll();
	}
	public AccountType findById(int id) {
		return dao.findById(id);
	}
	public boolean addType(AccountType a) {		
		List<AccountType> list = findAll();
		
		for(AccountType ac: list) {
			//might need to implement equals stuff
			if(ac.getTypeId()==(a.getTypeId())&&ac.getType().equals(a.getType()) ) {
				return false;
			}
		}
		
		boolean b = dao.addType(a);
		return(b);
	}
}
