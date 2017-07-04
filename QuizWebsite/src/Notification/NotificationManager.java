package Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DBConnection;
import Database.DBInfo;

/**
 * Public class that manages transferring information between database and
 * program about notifications
 *
 */
public class NotificationManager {
	private Connection connection;

	/**
	 * Public constructor for notification manager
	 */
	public NotificationManager() {
		connection = DBConnection.getConnection();
	}

	/**
	 * Adds notification into the database
	 * 
	 * @param notification
	 */
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
			e.printStackTrace();
		}

		if (hasNotification(receiver)) {
			increaseNotificationCount(receiver);
		} else {
			addNotificationCount(receiver);
		}
	}

	/**
	 * Reads notifications from database that according to the passed user name
	 * 
	 * @param username
	 * @return ArryList of Notifications
	 */
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
			e.printStackTrace();
		}

		deleteNotifications(getUserId(username));
		return result;
	}

	/**
	 * Returns how many notifications user has.
	 * 
	 * @param username
	 * @return
	 */
	public int getNotificationCount(String username) {
		String sql = "SELECT COUNT FROM " + DBInfo.NOTIFICATION_COUNT + " WHERE USER_ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, getUserId(username));
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Gets the id of passed user name
	 * 
	 * @param username
	 * @return ID of user
	 */
	private int getUserId(String username) {
		String sql = "SELECT ID FROM " + DBInfo.USERS + " WHERE USERNAME = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Returns the id of passed Quiz
	 * 
	 * @param quizName
	 * @return ID of quiz
	 */
	private int getQuizId(String quizName) {
		String sql = "SELECT ID FROM " + DBInfo.QUIZZES + " WHERE NAME = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, quizName);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * Checks if user on passed id has at least one notification
	 * 
	 * @param id
	 * @return
	 */
	private boolean hasNotification(int id) {
		String sql = "SELECT * FROM " + DBInfo.NOTIFICATION_COUNT + " WHERE USER_ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Increases the number of notifications for passed id user
	 * 
	 * @param id
	 */
	private void increaseNotificationCount(int id) {
		String sql = "UPDATE " + DBInfo.NOTIFICATION_COUNT + " SET COUNT = COUNT + 1 WHERE USER_ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * If there is no notification for passed id in the database adds one
	 * 
	 * @param id
	 */
	private void addNotificationCount(int id) {
		String sql = "INSERT INTO " + DBInfo.NOTIFICATION_COUNT + " (USER_ID, COUNT) VALUES (?, 1)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets user name for passed id
	 * 
	 * @param id
	 * @return user name
	 */
	private String getUserUsername(int id) {
		String sql = "SELECT USERNAME FROM " + DBInfo.USERS + " WHERE ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Gets quiz name for passed id
	 * 
	 * @param id
	 * @return quiz name
	 */
	private String getQuizName(int id) {
		String sql = "SELECT NAME FROM " + DBInfo.QUIZZES + " WHERE ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Deletes notifications for passed user id from database
	 * 
	 * @param id
	 */
	private void deleteNotifications(int id) {
		String sql = "DELETE FROM " + DBInfo.NOTIFICATIONS + " WHERE RECEIVER_ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deleteNotificationCount(id);
	}

	/**
	 * deletes notification count from database for passed id
	 * 
	 * @param id
	 */
	private void deleteNotificationCount(int id) {
		String sql = "DELETE FROM " + DBInfo.NOTIFICATION_COUNT + " WHERE USER_ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
