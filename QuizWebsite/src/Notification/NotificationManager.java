package Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DBConnection;
import Database.DBInfo;

public class NotificationManager {
	private Connection connection;

	public NotificationManager() {
		connection = DBConnection.getConnection();
	}

	public void addNotification(Notification notification) {
		int sender = getUserId(notification.getSender()); // id of sender
		int receiver = getUserId(notification.getReceiver()); // id of receiver
		int typeId = notification.getType(); // notification type
		String column = ""; // which column must be changed, depending on type
		int id = 0; // if its challenge or grade, id of target
		if (typeId == DBInfo.NOTIFICATION_TYPE_CHALLENGE_REQUEST) {
			column = ", QUIZ_ID";
			id = getQuizId(notification.getContent());
		} else if (typeId == DBInfo.NOTIFICATION_TYPE_GRADE_REQUEST) {
			column = ", QUESTION_ID";
			id = Integer.parseInt(notification.getContent());
		} else if (typeId == DBInfo.NOTIFICATION_TYPE_MESSAGE) {
			column = ", MESSAGE";
		}
		String sql = "INSERT INTO " + DBInfo.NOTIFICATIONS + " (SENDER_ID, RECEIVER_ID" + column
				+ ", TYPE_ID) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, sender);
			preparedStatement.setInt(2, receiver);
			if (id != 0)
				preparedStatement.setInt(3, id);
			else
				preparedStatement.setString(3, notification.getContent());
			preparedStatement.setInt(4, typeId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Notification> getNotifications(String username) {
		ArrayList<Notification> result = new ArrayList<Notification>();
		String sql = "SELECT * FROM " + DBInfo.NOTIFICATIONS + " WHERE RECEIVER_ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, getUserId(username));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String content = "";
				if (resultSet.getString(DBInfo.NOTIFICATIONS_MESSAGE) != null)
					content = resultSet.getString(DBInfo.NOTIFICATIONS_MESSAGE);
				else if (resultSet.getInt(DBInfo.NOTIFICATIONS_QUIZ_ID) > 0)
					content = getQuizName(resultSet.getInt(DBInfo.NOTIFICATIONS_QUIZ_ID));
				else if (resultSet.getInt(DBInfo.NOTIFICATIONS_QUESTION_ID) > 0)
					content += resultSet.getInt(DBInfo.NOTIFICATIONS_QUESTION_ID);
				result.add(new Notification(getUserUsername(resultSet.getInt(2)), getUserUsername(resultSet.getInt(3)),
						content, resultSet.getInt(7)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private int getUserId(String username) {
		String sql = "SELECT ID FROM " + DBInfo.USERS + " WHERE USERNAME = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	private int getQuizId(String quizName) {
		String sql = "SELECT ID FROM " + DBInfo.QUIZZES + " WHERE NAME = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, quizName);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	private String getUserUsername(int id) {
		String sql = "SELECT USERNAME FROM " + DBInfo.USERS + " WHERE ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private String getQuizName(int id) {
		String sql = "SELECT NAME FROM " + DBInfo.QUIZZES + " WHERE ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
