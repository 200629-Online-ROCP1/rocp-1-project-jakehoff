package Models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Role;
import Models.User;
import Models.IDao.IRoleDao;
import Models.AccountStatus;
import Models.ConnectionUTI;

public class RoleDao implements IRoleDao {
	public List<Role> findAll() {
		try(Connection comm = ConnectionUTI.getConnection()){
			String selectAccount = "SELECT * FROM roles;";
			
			Statement statement = comm.createStatement();
		
			List<Role> AccountList = new ArrayList<>();
			
			ResultSet result = statement.executeQuery(selectAccount);
			
			while(result.next()) {
				Role a = new Role();
				a.setRoleId(result.getInt("role_id"));
				a.setRole(result.getString("role_type"));
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

	public Role findById(int id) {
		
		
		try(Connection comm = ConnectionUTI.getConnection()){
			//may need to use prepared statement for future parts
			String sql = "SELECT * FROM roles WHERE role_id = "+id+";";
			
			Statement statement = comm.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if(result.next()) {
				Role a = new Role();
				a.setRoleId(result.getInt("role_id"));
				a.setRole(result.getString("role_type"));
				//need to add forgein key objects
				//a.set_Status(result.get);
				return a;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addRole(Role a) {
		try(Connection comm = ConnectionUTI.getConnection()){
		//preapared statement to avoid sql injection
		
			int index = 0;
			String sql ="INSERT INTO account(role_type)"+"VALUES(?)";
			
			PreparedStatement statement = comm.prepareStatement(sql);

			statement.setString(++index, a.getRole());
			statement.execute();
				return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public AccountStatus ChangeStatus(AccountStatus a, String change) {
		try(Connection comm = ConnectionUTI.getConnection()){
			//preapared statement to avoid sql injection
			
				int index = 0;
				System.out.println(a.getStatus());
				String sql ="UPDATE account_status SET status = '"+change+"' WHERE status_id = "+a.getStatusId()+";";
				PreparedStatement statement = comm.prepareStatement(sql);
				statement.execute();
				return a;
				}catch(SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
	public User ChangeInfo(User u, String fieldchange, String change) {
		try(Connection comm = ConnectionUTI.getConnection()){
			//preapared statement to avoid sql injection
			
				int index = 0;
				String sql ="UPDATE users SET "+fieldchange+" = '"+change+"' WHERE users_id = "+u.getUserId()+";";
				
				PreparedStatement statement = comm.prepareStatement(sql);
	
				statement.execute();
				return u;
				}catch(SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
	public void DestroyUser(String table,String field, int id) {
		//DELETE FROM table_name WHERE condition;
		try(Connection comm = ConnectionUTI.getConnection()){
			//preapared statement to avoid sql injection
			
				int index = 0;
				String sql ="DELETE FROM "+table +" WHERE "+field +" = "+id+";";
				
				PreparedStatement statement = comm.prepareStatement(sql);
	
				statement.execute();
				}catch(SQLException e) {
				e.printStackTrace();
			}
	}
	public void DestroyUserSelf(User a) {
		try(Connection comm = ConnectionUTI.getConnection()){
			//preapared statement to avoid sql injection
			
				String sql ="DELETE FROM users WHERE users_id = "+a.getUserId()+";";
				
				PreparedStatement statement = comm.prepareStatement(sql);
				statement.execute();
				}catch(SQLException e) {
				e.printStackTrace();
			}
	}
}
