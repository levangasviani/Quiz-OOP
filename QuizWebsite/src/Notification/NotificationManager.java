package Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Database.DBConnection;
import Database.DBInfo;
import Quiz.QuizManager;
import User.AccountManager;

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
		AccountManager accountManager = new AccountManager();
		QuizManager quizManager = new QuizManager();
		int sender = accountManager.getAccountId(notification.getSender()); // id
																			// of
																			// sender
		int receiver = accountManager.getAccountId(notification.getReceiver()); // id
																				// of
																				// receiver
		int typeId = notification.getType(); // notification type
		String column = ""; // which column must be changed, depending on type
		String additionalColumn = ""; // adds additional ? if necessary
		int id = 0; // if its challenge or grade, id of target
		String message = ""; // notification content
		String status = ""; // status of notification

		if (typeId == DBInfo.NOTIFICATION_TYPE_CHALLENGE_REQUEST) {
			column = ", QUIZ_ID";
			id = quizManager.getQuizID(quizManager.getQuiz(notification.getContent()));
		} else if (typeId == DBInfo.NOTIFICATION_TYPE_FRIEND_REQUEST) {
			column = ", FRIEND_STATUS";
			message = notification.getContent();
		} else if (typeId == DBInfo.NOTIFICATION_TYPE_GRADE_REQUEST) {
			column = ", QUESTION_ID, MESSAGE, FRIEND_STATUS";
			additionalColumn = ", ?, ?";
			String content = notification.getContent();
			StringTokenizer tokenizer = new StringTokenizer(content, ":");
			status = tokenizer.nextToken();
			id = Integer.parseInt(tokenizer.nextToken());
			message = tokenizer.nextToken();
			if (status.equals("SENT"))
				deleteOldGradeRequests(sender, receiver, id);
		} else if (typeId == DBInfo.NOTIFICATION_TYPE_MESSAGE) {
			column = ", MESSAGE";
			message = notification.getContent();
		}
		String sql = "INSERT INTO " + DBInfo.NOTIFICATIONS + " (SENDER_ID, RECEIVER_ID, TYPE_ID" + column
				+ ") VALUES (?, ?, ?, ?" + additionalColumn + ")";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, sender);
			preparedStatement.setInt(2, receiver);
			preparedStatement.setInt(3, typeId);
			if (id != 0)
				preparedStatement.setInt(4, id);
			else
				preparedStatement.setString(4, message);
			if (!additionalColumn.equals("")) {
				preparedStatement.setString(5, message);
				preparedStatement.setString(6, status);
			}
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
		AccountManager accountManager = new AccountManager();
		QuizManager quizManager = new QuizManager();
		ArrayList<Notification> result = new ArrayList<Notification>();
		ArrayList<Notification> resultTemp = new ArrayList<Notification>();
		String sql = "SELECT * FROM " + DBInfo.NOTIFICATIONS + " WHERE RECEIVER_ID = ?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountManager.getAccountId(username));
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String content = "";
				if (resultSet.getInt(DBInfo.NOTIFICATIONS_TYPE_ID) == 4)
					content = resultSet.getString(DBInfo.NOTIFICATIONS_MESSAGE);
				else if (resultSet.getInt(DBInfo.NOTIFICATIONS_TYPE_ID) == 2)
					content = resultSet.getString(DBInfo.NOTIFICATIONS_FRIEND_STATUS);
				else if (resultSet.getInt(DBInfo.NOTIFICATIONS_TYPE_ID) == 1)
					content = quizManager.getQuizName(resultSet.getInt(DBInfo.NOTIFICATIONS_QUIZ_ID));
				else if (resultSet.getInt(DBInfo.NOTIFICATIONS_TYPE_ID) == 3)
					content += resultSet.getString(DBInfo.NOTIFICATIONS_FRIEND_STATUS) + ":"
							+ resultSet.getInt(DBInfo.NOTIFICATIONS_QUESTION_ID) + ":"
							+ resultSet.getString(DBInfo.NOTIFICATIONS_MESSAGE);
				resultTemp.add(new Notification(getUserUsername(resultSet.getInt(DBInfo.NOTIFICATIONS_SENDER_ID)),
						getUserUsername(resultSet.getInt(DBInfo.NOTIFICATIONS_RECEIVER_ID)), content,
						resultSet.getInt(DBInfo.NOTIFICATIONS_TYPE_ID)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = resultTemp.size() - 1; i >= 0; i--)
			result.add(resultTemp.get(i));
		deleteNotificationCount(accountManager.getAccountId(username));
		return result;
	}

	/**
	 * Returns how many notifications user has.
	 * 
	 * @param username
	 * @return
	 */
	public int getNotificationCount(String username) {
		AccountManager accountManager = new AccountManager();
		String sql = "SELECT COUNT FROM " + DBInfo.NOTIFICATION_COUNT + " WHERE USER_ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountManager.getAccountId(username));
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return resultSet.getInt(1);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}

	public void updateNotification(String sender, String receiver, int questionId) {
		AccountManager accountManager = new AccountManager();
		String sql = "UPDATE " + DBInfo.NOTIFICATIONS
				+ " SET FRIEND_STATUS = 'CHECKED' WHERE TYPE_ID = 3 AND SENDER_ID = ? AND RECEIVER_ID = ? AND QUESTION_ID = ? AND FRIEND_STATUS = 'SENT'";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountManager.getAccountId(sender));
			preparedStatement.setInt(2, accountManager.getAccountId(receiver));
			preparedStatement.setInt(3, questionId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void deleteOldGradeRequests(int sender, int receiver, int questionId) {
		String sql = "SELECT * FROM " + DBInfo.NOTIFICATIONS
				+ " WHERE TYPE_ID = 3 AND SENDER_ID = ? AND RECEIVER_ID = ? AND QUESTION_ID = ? AND FRIEND_STATUS = 'SENT' ORDER BY ID DESC";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, sender);
			preparedStatement.setInt(2, receiver);
			preparedStatement.setInt(3, questionId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				deleteNotification(resultSet.getInt(DBInfo.NOTIFICATIONS_ID));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	 * Deletes notifications for passed user id from database
	 * 
	 * @param id
	 */
	private void deleteNotification(int id) {
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
