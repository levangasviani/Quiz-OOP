package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DBConnection;
import Database.DBInfo;

/**
 * 
 * Class for Announcement management in the database
 *
 */
public class AnnouncementManager {

	private Connection connection;

	/**
	 * Public Constructor
	 */
	public AnnouncementManager() {
		connection = DBConnection.getConnection();
	}

	/**
	 * Adds announcement into the database
	 * 
	 * @param adminId
	 * @param text
	 */
	public void addAnnouncement(int adminId, String text) {
		String query = "INSERT INTO " + DBInfo.ANNOUNCEMENTS + "(TEXT, ADMIN_ID) VALUES " + "(?, ?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, text);
			preparedStatement.setInt(2, adminId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gets all announcements from the database
	 * 
	 * @return ResultSet
	 */
	public ResultSet getAllAnnouncements() {

		try {
			String query = "SELECT * FROM " + DBInfo.ANNOUNCEMENTS + " ORDER BY ID DESC";
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultset = preparedStatement.executeQuery();
			return resultset;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
