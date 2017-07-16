package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DBConnection;
import Database.DBInfo;

public class AnnouncementManager {
	
	private Connection connection;
	
	public AnnouncementManager(){
		connection=DBConnection.getConnection();
	}
	
	
	public void addAnnouncement(int adminId, String text) {
		String query="INSERT INTO "+DBInfo.ANNOUNCEMENTS+"(TEXT, ADMIN_ID) VALUES "+"(?, ?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, text);
			preparedStatement.setInt(2, adminId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public ResultSet getAllAnnouncements(){
		
		try {
			String query="SELECT * FROM "+DBInfo.ANNOUNCEMENTS+" ORDER BY ID DESC";
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultset=preparedStatement.executeQuery();
			return resultset;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
