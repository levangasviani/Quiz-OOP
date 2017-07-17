package friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DBConnection;
import Database.DBInfo;
import User.AccountManager;

public class FriendManager {
	private Connection connection;

	public FriendManager() {
		connection = DBConnection.getConnection();
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

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

	private void sendFriendship(String sender, String receiver) {
		AccountManager accountManager = new AccountManager();
		String sql = "INSERT INTO " + DBInfo.FRIENDS + " (USER_ONE, USER_TWO, STATUS) VALUES (?, ?, 'SENT')";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountManager.getAccountId(sender));
			preparedStatement.setInt(2, accountManager.getAccountId(receiver));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void acceptFriendship(String sender, String receiver) {
		updateStatus(sender, receiver);
		duplicateFriendship(sender, receiver);
	}

	private void updateStatus(String sender, String receiver) {
		AccountManager accountManager = new AccountManager();
		String sql = "UPDATE " + DBInfo.FRIENDS + " SET STATUS = 'ACCEPTED' WHERE USER_TWO = ? AND USER_ONE = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountManager.getAccountId(sender));
			preparedStatement.setInt(2, accountManager.getAccountId(receiver));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void duplicateFriendship(String sender, String receiver) {
		AccountManager accountManager = new AccountManager();
		String sql = "INSERT INTO " + DBInfo.FRIENDS + " (USER_ONE, USER_TWO, STATUS) VALUES (?, ?, 'ACCEPTED')";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountManager.getAccountId(sender));
			preparedStatement.setInt(2, accountManager.getAccountId(receiver));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void rejectFriendship(String sender, String receiver) {
		AccountManager accountManager = new AccountManager();
		String sql = "DELETE FROM " + DBInfo.FRIENDS + " WHERE USER_TWO = ? AND USER_ONE = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountManager.getAccountId(sender));
			preparedStatement.setInt(2, accountManager.getAccountId(receiver));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
