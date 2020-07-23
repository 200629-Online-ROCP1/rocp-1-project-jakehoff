package Models.IDao;

import java.util.List;

import Models.Role;
import Models.User;

public interface IUserDao {

	public List<User> findAll();
	public User findById(int id);
	public boolean addUser(User a,Role r);
}
