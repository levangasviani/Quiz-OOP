package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection connection;
	
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
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
