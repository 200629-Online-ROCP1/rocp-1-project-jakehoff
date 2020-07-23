package Models.services;

import java.util.List;

import Models.AccountStatus;
import Models.Role;
import Models.User;
import Models.IDao.IRoleDao;
import Models.dao.RoleDao;

public class RoleService {
	private final IRoleDao dao = new RoleDao();
	
	public List <Role> findAll(){
		return dao.findAll();
	}
	public Role findById(int id) {
		return dao.findById(id);
	}
	public boolean addRole(Role a) {		
		List<Role> list = findAll();
		
		for(Role ac: list) {
			//might need to implement equals stuff
			if(ac.getRoleId()==(a.getRoleId())&&ac.getRole().equals(a.getRole()) ) {
				return false;
			}
		}
		
		boolean b = dao.addRole(a);
		return(b);
	}
	public AccountStatus ChangeStatus(AccountStatus a, String change) {
		
		return dao.ChangeStatus(a,change);
	}
	public User ChangeInfo(User u, String fieldchange, String change) {
		//check if admin
		return dao.ChangeInfo(u, fieldchange, change);
 
	}
	public void DestroyUser(String table,String field, int id) {
		dao.DestroyUser(table,field, id);
	}
	public void DestroyUserSelf(User a){
		dao.DestroyUserSelf(a);
	}
}
