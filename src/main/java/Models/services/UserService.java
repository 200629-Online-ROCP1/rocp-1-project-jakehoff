package Models.services;

import java.util.List;

import Models.Role;
import Models.User;
import Models.IDao.IRoleDao;
import Models.IDao.IUserDao;
import Models.dao.RoleDao;
import Models.dao.Userdao;

public class UserService {
	private final IUserDao dao = new Userdao();
	private final IRoleDao rdao = new RoleDao();
	public List <User> findAll(){
		return dao.findAll();
	}
	public User findById(int id) {
		return dao.findById(id);
	}
	public Role MatchRole(User a) {
		//public Role findById(int id) 
		int x = a.WantRoleId();
		return (rdao.findById(x));
	}
	public boolean addUser(User a) {		
		List<User> list = findAll();
		System.out.println("b");
		for(User ac: list) {
			//might need to implement equals stuff
			if(ac.Check_ID(a.getUserId())&&ac.Check_Username(a.getUsername())&&ac.Check_Password(a.getPassword()) &&ac.Check_Email(a.getEmail())) {
				return false;
			}
		}
		Role theRole = this.MatchRole(a);
		boolean b = dao.addUser(a,theRole);
		return(b);
	}
}
