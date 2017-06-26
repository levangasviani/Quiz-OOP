package Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import Database.DBConnection;
import Database.DBInfo;

public class MessageManager {
	private Connection connection;

	public MessageManager() {
		connection = DBConnection.getConnection();
	}

	public void sendMessage(Message message) {
		String query = "INSERT INTO " + DBInfo.MESSAGES + " (MESSAGE, SENDER, RECEIVER) VALUES (?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, message.getMessage());
			preparedStatement.setInt(2, getUserId(message.getSender()));
			preparedStatement.setInt(3, getUserId(message.getReceiver()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<String, String> getMessages(String username) {
		HashMap<String, String> result = new HashMap<String, String>();
		String query = "SELECT * FROM " + DBInfo.MESSAGES + " WHERE RECEIVER = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, getUserId(username));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result.put(getUserUsername(resultSet.getInt(DBInfo.MESSAGES_SENDER)),
						resultSet.getString(DBInfo.MESSAGES_MESSAGE));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private String getUserUsername(int id) {
		String query = "SELECT USERNAME FROM " + DBInfo.USERS + " WHERE ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getString(DBInfo.USER_USERNAME);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private int getUserId(String username) {
		String query = "SELECT ID FROM " + DBInfo.USERS + "WHERE USERNAME = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt(DBInfo.USER_ID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
