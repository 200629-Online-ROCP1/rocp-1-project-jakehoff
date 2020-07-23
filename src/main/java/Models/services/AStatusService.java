package Models.services;

import java.util.List;


import Models.AccountStatus;
import Models.IDao.IAStatusDao;
import Models.dao.AStatusDao;

public class AStatusService {
	private final IAStatusDao dao = new AStatusDao();
	
	public List <AccountStatus> findAll(){
		return dao.findAll();
	}
	public AccountStatus findById(int id) {
		return dao.findById(id);
	}
	public boolean addStatus(AccountStatus a) {
		List<AccountStatus> list = findAll();
		
		for(AccountStatus ac: list) {
			//might need to implement equals stuff
			if(ac.getStatusId()==(a.getStatusId())&&ac.getStatus().equals(a.getStatus()) ) {
				return false;
			}
		}
		boolean b = dao.addStatus(a);
		return(b);
	}
}
