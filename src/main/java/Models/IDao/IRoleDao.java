package Models.IDao;

import java.util.List;

import Models.Role;
import Models.User;
import Models.AccountStatus;

public interface IRoleDao {

	public List<Role> findAll();
	public Role findById(int id);
	public boolean addRole(Role a);
	public AccountStatus ChangeStatus(AccountStatus a, String change);
	public User ChangeInfo(User u, String fieldchange, String change);
	public void DestroyUser(String table,String field, int id);
	public void DestroyUserSelf(User a);
}
