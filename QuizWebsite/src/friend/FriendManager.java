package friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DBConnection;
import Database.DBInfo;
import User.AccountManager;

/**
 * 
 * Class for friends management in the database
 *
 */
public class FriendManager {
	private Connection connection;

	/**
	 * Public Constructor
	 * 
	 */
	public FriendManager() {
		connection = DBConnection.getConnection();
	}

	/**
	 * Checks if passed users are friends
	 * 
	 * @param username1
	 * @param username2
	 * @return boolean
	 */
	public boolean areFriends(String username1, String username2) {
		AccountManager accountManager = new AccountManager();
		String sql = "SELECT * FROM " + DBInfo.FRIENDS + " WHERE USER_ONE = ? AND USER_TWO = ? AND STATUS = 'ACCEPTED'";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountManager.getAccountId(username1));
			preparedStatement.setInt(2, accountManager.getAccountId(username2));
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * Returns true if receiver got request from sender
	 * 
	 * @param receiver
	 * @param sender
	 * @return boolean
	 */
	public boolean requestReceived(String receiver, String sender) {
		AccountManager accountManager = new AccountManager();
		String sql = "SELECT * FROM " + DBInfo.FRIENDS + " WHERE USER_TWO = ? AND USER_ONE = ? AND STATUS = 'SENT'";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountManager.getAccountId(receiver));
			preparedStatement.setInt(2, accountManager.getAccountId(sender));
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Defines friendshio status
	 * 
	 * @param friendship
	 */
	public void changeFriendship(Friendship friendship) {
		String sender = friendship.getSender();
		String receiver = friendship.getReceiver();
		String message = friendship.getMessage();
		if (message.equals("SENT")) {
			sendFriendship(sender, receiver);
		} else if (message.equals("ACCEPTED")) {
			acceptFriendship(sender, receiver);
		} else if (message.equals("REJECTED")) {
			rejectFriendship(sender, receiver);
		}
	}

	/**
	 * Sends friendship from sender to receiver
	 * 
	 * @param sender
	 * @param receiver
	 */
	private void sendFriendship(String sender, String receiver) {
		AccountManager accountManager = new AccountManager();
		String sql = "INSERT INTO " + DBInfo.FRIENDS + " (USER_ONE, USER_TWO, STATUS) VALUES (?, ?, 'SENT')";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountManager.getAccountId(sender));
			preparedStatement.setInt(2, accountManager.getAccountId(receiver));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Receiver accepts friendship from sender Information is stored in the
	 * database
	 * 
	 * @param sender
	 * @param receiver
	 */
	private void acceptFriendship(String sender, String receiver) {
		updateStatus(sender, receiver);
		duplicateFriendship(sender, receiver);
	}

	/**
	 * Updates friendship status
	 * 
	 * @param sender
	 * @param receiver
	 */
	private void updateStatus(String sender, String receiver) {
		AccountManager accountManager = new AccountManager();
		String sql = "UPDATE " + DBInfo.FRIENDS + " SET STATUS = 'ACCEPTED' WHERE USER_TWO = ? AND USER_ONE = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountManager.getAccountId(sender));
			preparedStatement.setInt(2, accountManager.getAccountId(receiver));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes friendship information for both receiver and sender in the
	 * database
	 * 
	 * @param sender
	 * @param receiver
	 */
	private void duplicateFriendship(String sender, String receiver) {
		AccountManager accountManager = new AccountManager();
		String sql = "INSERT INTO " + DBInfo.FRIENDS + " (USER_ONE, USER_TWO, STATUS) VALUES (?, ?, 'ACCEPTED')";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountManager.getAccountId(sender));
			preparedStatement.setInt(2, accountManager.getAccountId(receiver));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Receiver rejects sender's request
	 * 
	 * @param sender
	 * @param receiver
	 */
	private void rejectFriendship(String sender, String receiver) {
		AccountManager accountManager = new AccountManager();
		String sql = "DELETE FROM " + DBInfo.FRIENDS + " WHERE USER_TWO = ? AND USER_ONE = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountManager.getAccountId(sender));
			preparedStatement.setInt(2, accountManager.getAccountId(receiver));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Returns the array list of all friends for the passed user
	 * 
	 * @param username
	 * @return array list of Strings
	 */
	public ArrayList<String> getAllFriends(String username) {
		AccountManager accMan = new AccountManager();
		int userId = accMan.getAccountId(username);

		String query = "SELECT * FROM " + DBInfo.FRIENDS + " WHERE USER_ONE = ?;";
		ArrayList<String> friends = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int friendId = rs.getInt(DBInfo.FRIENDS_USER_TWO);
				String friendName = accMan.getUserNameById(friendId);
				friends.add(friendName);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friends;
	}

}
