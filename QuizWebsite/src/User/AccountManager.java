package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.DBConnection;
import Database.DBInfo;

/**
 * @author levani
 *
 *         Controller Account Manager Class
 *
 */
public class AccountManager {

	private Connection connection;

	/**
	 * Public constructor for Account Manager Initializes starting variables
	 * 
	 * @throws SQLException
	 */
	public AccountManager() {
		connection = DBConnection.getConnection();
	}

	/**
	 * Adds passed account's information into the database Calls addAccount
	 * method with parsed parameters
	 * 
	 * @param account
	 * @throws SQLException
	 */
	public void addAccount(Account account) {
		String username = account.getUserName();
		String password = account.getPassword();
		String firstname = account.getFirstName();
		String lastname = account.getLastName();
		String email = account.getEmail();
		int type = account.getType();

		addAccount(username, password, firstname, lastname, email, type);
	}

	/**
	 * 
	 * Adds passed account's information into the database
	 * 
	 * @param username
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param type
	 */
	public void addAccount(String username, String password, String firstname, String lastname, String email,
			int type) {
		try {
			if (!containsAccount(username)) {
				String query = "INSERT INTO " + DBInfo.USERS
						+ " (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, TYPE_ID) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, firstname);
				preparedStatement.setString(4, lastname);
				preparedStatement.setString(5, email);
				preparedStatement.setInt(6, type);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method takes two user names, the first one must be admin's name and the
	 * second one must be ordinary. Only in this case account will be deleted
	 * from the database
	 * 
	 * @param username1
	 * @param username2
	 */
	public void deleteAccount(String username1, String username2) {
		try {
			Account acc1 = getAccount(username1);
			Account acc2 = getAccount(username2);

			if (containsAccount(username1) && containsAccount(username2)) {
				if (acc1.getType() == DBInfo.USER_TYPE_ADMIN && acc2.getType() == DBInfo.USER_TYPE_USER) {
					String query = "DELETE FROM " + DBInfo.USERS + " WHERE USERNAME = ?;";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, username2);
					preparedStatement.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns true if account is in the database
	 * 
	 * @param username
	 * @return boolean
	 */
	public boolean containsAccount(String username) {
		String query = "SELECT * FROM " + DBInfo.USERS + " WHERE USERNAME = ?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultset = preparedStatement.executeQuery();

			if (resultset.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * Returns Account which belongs to the passed user name
	 * 
	 * @param username
	 * @return account
	 */
	public Account getAccount(String username) {
		String query = "SELECT * FROM " + DBInfo.USERS + " WHERE USERNAME = ?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultset = preparedStatement.executeQuery();
			resultset.next();
			return getAccountFromResultset(resultset);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Returns Array List of Accounts which are in the database
	 * 
	 * @return ArrayList<Accounts>
	 * @throws SQLException
	 */
	public ArrayList<Account> getAccountsList() {
		ArrayList<Account> result = new ArrayList<Account>();
		String query = "SELECT * FROM " + DBInfo.USERS + ";";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(query);
			while (resultset.next()) {
				Account account = getAccountFromResultset(resultset);
				result.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Reads information from passed result set and makes a new account;
	 * 
	 * @param result
	 * @return account
	 * @throws SQLException
	 */
	private Account getAccountFromResultset(ResultSet result) {
		Account acc = null;

		try {
			String username = result.getString(DBInfo.USERS_USERNAME);
			String password = result.getString(DBInfo.USERS_PASSWORD);
			String firstname = result.getString(DBInfo.USERS_FIRSTNAME);
			String lastname = result.getString(DBInfo.USERS_LASTNAME);
			String email = result.getString(DBInfo.USERS_EMAIL);
			int type = result.getInt(DBInfo.USERS_TYPE_ID);

			acc = new Account(username, password, firstname, lastname, email, type);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return acc;
	}
}
