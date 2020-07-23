package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUTI {

	public static Connection getConnection() throws SQLException{
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//try catch end
		
		//might need to check url
		String url = "jdbc:postgresql://localhost:5432/oraclefinalproject";
		String username = "jakepro";
		String password = "drowssap";
		
		return DriverManager.getConnection(url, username, password);
	}
	
}
