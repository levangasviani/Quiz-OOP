package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author levani
 *
 * DBConnection class makes connection to the database
 */
public class DBConnection {
	
	private static Connection connection;
	
	/**
	 * Creates connection to the database
	 * Otherwise throws exceptions
	 * 
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER;
			connection = DriverManager.getConnection(url, DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Invalid Database Constants!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Class Not Found!");
		}
	}
	
	/**
	 * @return connection
	 * 
	 */
	public static Connection getConnection() {
		return connection;
	}
	
	
	/**
	 * Closes connection
	 */
	public static void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
