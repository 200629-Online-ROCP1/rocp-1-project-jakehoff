package Models.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Models.ConnectionUTI;
import Models.Role;
import Models.User;
import Models.hashing;
import Models.loginDTO;
import Models.IDao.IRoleDao;
import Models.dao.RoleDao;

public class LoginService {
	private static final IRoleDao RolesDao = new RoleDao();

	public static User currentuser;
	public static UserService s;
		public boolean login(loginDTO l) {
			hashing hs = new hashing();
			try(Connection comm = ConnectionUTI.getConnection()){
				String selectAccount = "SELECT * FROM users;";
				
				Statement statement = comm.createStatement();
							
				ResultSet result = statement.executeQuery(selectAccount);
				//should iterate through table and get user name/passwords
				while(result.next()) {
					User a = new User();
					
					a.setUsername(result.getString("username"));
					a.setPassword((result.getString("users_password")));
					a.setEmail(result.getString("users_email"));
					a.setUserId(result.getInt("users_id"));
					a.setFirstName(result.getString("first_name"));
					Role h = RolesDao.findById(result.getInt("users_role"));
					a.setRole(h);
					if (a.Check_Username(l.username)&&a.Check_Password(l.password)) {
						currentuser = a;	
						return true;
						
					}
					//need to add forgein key objects
					//a.set_Status(result.get);
					
					//AccountList.add(a);
				}
				return false;
		}catch(SQLException e) {
			e.printStackTrace();
			}
			return false;
}
		public User getcurrentuser() {
			return currentuser;
		}
		/*
		 * 
		 */
		public User loginfinduser(loginDTO l) {
			try(Connection comm = ConnectionUTI.getConnection()){
				String selectAccount = "SELECT * FROM users;";
				
				Statement statement = comm.createStatement();
							
				ResultSet result = statement.executeQuery(selectAccount);
				//should iterate through table and get user name/passwords
				while(result.next()) {
					User a = new User();
					
					a.setUsername(result.getString("username"));
					a.setPassword(result.getString("users_password"));
					
					if (a.Check_Username(l.username)&&a.Check_Password(l.password)) {
							return a;
						
					}
					//need to add forgein key objects
					//a.set_Status(result.get);
					
					//AccountList.add(a);
				}
				return null;
		}catch(SQLException e) {
			e.printStackTrace();
			}
			return null;
}
}
