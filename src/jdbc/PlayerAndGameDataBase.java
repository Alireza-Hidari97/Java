package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerAndGameDataBase {
	
	static final Logger logg = Logger.getLogger(PlayerAndGameDataBase.class.getName());
	static final String DRIVER = "oracle.jdbc.driver.OracleDrive";
	static final String DATABASE_URL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
	static final String USERNAME = "COMP228_F21_sy_80";
	static final String PASSWORD = "password";
	
	public static Connection connectionDB() throws SQLException{
		
		Connection conn = null;
		
		try {
			
			Class.forName(DRIVER);
		}
		catch(ClassNotFoundException e) {
			
			logg.log(Level.SEVERE, e.getMessage());
		}
		
		try {
			
			conn = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
			
			return conn;
			
		}
		catch(SQLException e) {
			
		logg.log(Level.SEVERE, e.getMessage());
		}
		
		return conn;
		
		
	}



}
