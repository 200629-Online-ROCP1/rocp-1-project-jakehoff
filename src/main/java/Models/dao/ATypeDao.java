package Models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Models.AccountType;
import Models.ConnectionUTI;
import Models.IDao.IATypeDao;

public class ATypeDao implements IATypeDao{
	public List<AccountType> findAll() {
		try(Connection comm = ConnectionUTI.getConnection()){
			String selectAccount = "SELECT * FROM account_type;";
			
			Statement statement = comm.createStatement();
		
			List<AccountType> AccountList = new ArrayList<>();
			
			ResultSet result = statement.executeQuery(selectAccount);
			
			while(result.next()) {
				AccountType a = new AccountType();
				a.setTypeId(result.getInt("type_id"));
				a.setType(result.getString("type_account"));
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

	public AccountType findById(int id) {
		
		
		try(Connection comm = ConnectionUTI.getConnection()){
			//may need to use prepared statement for future parts
			String sql = "SELECT * FROM account_type WHERE type_id = "+ id + ";";
			
			Statement statement = comm.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if(result.next()) {
				AccountType a = new AccountType();
				a.setTypeId(result.getInt("type_id"));
				a.setType(result.getString("type_account"));
				//need to add forgein key objects
				//a.set_Status(result.get);
				return a;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addType(AccountType a) {
		try(Connection comm = ConnectionUTI.getConnection()){
		//preapared statement to avoid sql injection
		
			int index = 0;
			String sql ="INSERT INTO account(role_type)"+"VALUES(?)";
			
			PreparedStatement statement = comm.prepareStatement(sql);

			statement.setString(++index, a.getType());
			statement.execute();
				return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
