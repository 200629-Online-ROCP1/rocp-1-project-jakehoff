package Models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.ConnectionUTI;
import Models.Role;
import Models.User;
import Models.IDao.IRoleDao;
import Models.IDao.IUserDao;

public class Userdao implements IUserDao{
	private static final IRoleDao RolesDao = new RoleDao();
	public List<User> findAll() {
		try(Connection comm = ConnectionUTI.getConnection()){
			String selectAccount = "SELECT * FROM users;";
			
			Statement statement = comm.createStatement();
		
			List<User> AccountList = new ArrayList<>();
			
			ResultSet result = statement.executeQuery(selectAccount);
			
			while(result.next()) {
				User a = new User();
				//a.Set_UserID(result.getInt("user_id"));
				a.setUsername(result.getString("username"));
				a.setPassword(result.getString("users_password"));
				a.setFirstName(result.getString("first_name"));
				a.setLastName(result.getString("last_name"));
				a.setEmail(result.getString("users_email"));
				
				//int temp = result.getInt("user_role");
				Role h = RolesDao.findById(result.getInt("users_role"));
				a.setRole(h);
				//need to add forgein key objects
				//a.set_Status(result.get);
				
				AccountList.add(a);
			}
			return AccountList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User findById(int id) {
		
		
		try(Connection comm = ConnectionUTI.getConnection()){
			//may need to use prepared statement for future parts
			String sql = "SELECT * FROM users WHERE users_id = "+id+";";
			
			Statement statement = comm.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if(result.next()) {
				User a = new User();
				a.setUserId(result.getInt("users_id"));
				a.setUsername(result.getString("username"));
				a.setPassword(result.getString("users_password"));
				a.setFirstName(result.getString("first_name"));
				a.setLastName(result.getString("last_name"));
				a.setEmail(result.getString("users_email"));
				Role h = RolesDao.findById(result.getInt("users_role"));
				a.setRole(h);
				//need to add forgein key objects
				//a.set_Status(result.get);
				return a;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addUser(User a,Role r) {
		try(Connection comm = ConnectionUTI.getConnection()){
		//preapared statement to avoid sql injection
			int index = 0;
			String sql ="INSERT INTO users(username,first_name,last_name,users_password,users_email,users_role)"+"VALUES(?,?,?,?,?,?)";
			
			PreparedStatement statement = comm.prepareStatement(sql);
			//change setfloat to set type
			statement.setString(++index, a.getUsername());
			statement.setString(++index, a.getFirstName());
			statement.setString(++index, a.getLastName());
			statement.setString(++index, a.getPassword());
			statement.setString(++index, a.getEmail());
			statement.setInt(++index, a.getRole().getRoleId());
			statement.execute();
			
				return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
