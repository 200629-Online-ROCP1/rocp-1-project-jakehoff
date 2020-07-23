package Models.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Models.AccountStatus;
import Models.ConnectionUTI;
import Models.IDao.IAStatusDao;

public class AStatusDao implements IAStatusDao{
	public List<AccountStatus> findAll() {
		try(Connection comm = ConnectionUTI.getConnection()){
			String selectAccount = "SELECT * FROM account_status;";
			
			Statement statement = comm.createStatement();
		
			List<AccountStatus> AccountList = new ArrayList<>();
			
			ResultSet result = statement.executeQuery(selectAccount);
			
			while(result.next()) {
				AccountStatus a = new AccountStatus();
				a.setStatusId(result.getInt("status_id"));
				a.setStatus(result.getString("status"));
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

	public AccountStatus findById(int id) {
		
		
		try(Connection comm = ConnectionUTI.getConnection()){
			//may need to use prepared statement for future parts
			String sql = "SELECT * FROM account_status WHERE status_id = "+ id +";";
			
			Statement statement = comm.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if(result.next()) {
				AccountStatus a = new AccountStatus();
				a.setStatusId(result.getInt("status_id"));
				a.setStatus(result.getString("status"));
				//need to add forgein key objects
				//a.set_Status(result.get);
				return a;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addStatus(AccountStatus a) {
		// TODO Auto-generated method stub
		return false;
	}
}
