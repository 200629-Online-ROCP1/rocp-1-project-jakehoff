package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.IDao.IAStatusDao;
import Models.IDao.IATypeDao;
import Models.IDao.IUserDao;
import Models.dao.AStatusDao;
import Models.dao.ATypeDao;
import Models.dao.Userdao;

public class accountDao implements IAccountDAO {
	private static final IUserDao UserDao = new Userdao();
	private static final IAStatusDao StatusDao = new AStatusDao();
	private static final IATypeDao TypeDao = new ATypeDao();
	public Account ac;
	public List<Account> findAll() {
		try(Connection comm = ConnectionUTI.getConnection()){
			String selectAccount = "SELECT * FROM account;";
			
			Statement statement = comm.createStatement();
		
			List<Account> AccountList = new ArrayList<>();
			
			ResultSet result = statement.executeQuery(selectAccount);
			
			while(result.next()) {
				Account a = new Account();
				a.setAccountId(result.getInt("account_id"));
				a.setBalance(result.getFloat("account_balance"));
				
				AccountStatus s = StatusDao.findById(result.getInt("status_of_account"));
				a.setStatus(s);
				
				AccountType h = TypeDao.findById(result.getInt("type_of_account"));
				a.setType(h);
	
				User u = UserDao.findById(result.getInt("account_owner"));
				a.setOwner(u);
				
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

	public Account findById(int id) {
		
		
		try(Connection comm = ConnectionUTI.getConnection()){
			//may need to use prepared statement for future parts
			String sql = "SELECT * FROM account WHERE account_id = '"+id+"';";
			
			Statement statement = comm.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if(result.next()) {
				Account a = new Account();

				a.setAccountId(result.getInt("account_id"));
				a.setBalance(result.getFloat("account_balance"));
				
				AccountStatus s = StatusDao.findById(result.getInt("status_of_account"));
				a.setStatus(s);
				
				AccountType h = TypeDao.findById(result.getInt("type_of_account"));
				a.setType(h);
	
				User u = UserDao.findById(result.getInt("account_owner"));
				a.setOwner(u);
				
				return a;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addAccount(Account a,  User u, AccountStatus s, AccountType t) {
		try(Connection comm = ConnectionUTI.getConnection()){
		//preapared statement to avoid sql injection
		
			int index = 0;
			String sql ="INSERT INTO account(account_balance, status_of_account,type_of_account,account_owner)"+"VALUES(?,?,?,?)";
			
			PreparedStatement statement = comm.prepareStatement(sql);

			//change setfloat to set type
				statement.setFloat(++index, a.getBalance());

				statement.setInt(++index, a.getStatus().getStatusId());
				statement.setInt(++index, a.getType().getTypeId());
				statement.setInt(++index, a.getOwner().getUserId());
				statement.execute();


				return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public float Deposit(float i, Account a) {
		try(Connection comm = ConnectionUTI.getConnection()){
			//preapared statement to avoid sql injection
			
				
				a.setBalance(a.getBalance()+i);

				String sql ="UPDATE account SET account_balance = "+ a.getBalance() +" WHERE account_id = "+a.getAccountId()+";";

				PreparedStatement statement = comm.prepareStatement(sql);
				//change setfloat to set type
				statement.execute();
				return a.getBalance()-i;
				}catch(SQLException e) {
				e.printStackTrace();
				
		}
			
		return -1;
	}
	public float Withdraw(float i,Account a) {
		ac = a;
		if(ac.getBalance()-i>=0) {
		try(Connection comm = ConnectionUTI.getConnection()){
			//preapared statement to avoid sql injection
						 
				a.setBalance(a.getBalance()-i);
				String sql ="UPDATE account SET account_balance = "+ a.getBalance() +" WHERE account_id = "+a.getAccountId()+";";
				
				PreparedStatement statement = comm.prepareStatement(sql);
				
				statement.execute();
				return ac.getBalance()-i;
				}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	public float Transfer(float i, Account a, Account b) {
		if(a.getBalance()-i>=0) {
		try(Connection comm = ConnectionUTI.getConnection()){
			//preapared statement to avoid sql injection
			
				int index = 0;
				String sql1 ="UPDATE account SET account_balance=? WHERE status_id"+a.getAccountId()+";";
				String sql2 ="UPDATE account SET account_balance=? WHERE status_id"+b.getAccountId()+";";

				PreparedStatement statement1 = comm.prepareStatement(sql1);
				PreparedStatement statement2 = comm.prepareStatement(sql2);

				//change setfloat to set type
				statement1.setFloat(++index,a.getBalance()+i);
				statement1.execute();
				statement2.setFloat(index,b.getBalance()-i);
				statement2.execute();
				return (b.getBalance()-i);
				}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}


}
