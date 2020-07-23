package Models.controllers;

import java.util.List;



import Models.services.RoleService;
import Models.AccountStatus;
import Models.Role;
import Models.User;
public class RoleController {
	private final RoleService rl = new RoleService();
	
	public List<Role> findAll(){
		return rl.findAll();
	}
	
	public Role findById(int id) {
		return rl.findById(id);
	}

	public boolean addUser(Role r) {
		return rl.addRole(r);
	}
	public AccountStatus ChangeStatus(AccountStatus a, String change) {
		return rl.ChangeStatus(a,change);
	}
	public User ChangeInfo(User u, String fieldchange, String change) {
		return rl.ChangeInfo(u, fieldchange, change);
	}
	public void DestroyUser(String table,String field, int id) {
		rl.DestroyUser(table,field, id);
	}
	public void DestroyUserSelf(User a) {
		rl.DestroyUserSelf(a);
	}
}
