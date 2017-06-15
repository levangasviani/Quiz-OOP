package User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.DBConnection;
import Database.DBInfo;


/**
 * @author levani
 *
 * Controller Account Manager Class
 *
 */
public class AccountManager {

	private Connection connection;
	private Statement statement;
	private ResultSet resultset;
	private Account account;
	private ArrayList<Account> accountsList;

	
	/**
	 * Public constructor for Account Manager
	 * Initializes starting variables
	 * 
	 * @throws SQLException
	 */
	public AccountManager() throws SQLException {
		connection = DBConnection.getConnection();
		statement = connection.createStatement();
		account = null;
		accountsList = new ArrayList<>();
	}
	
	
	/**
	 * Adds passed account's information into the database
	 * Calls addAccount method with parsed parameters
	 * 
	 * @param account
	 * @throws SQLException
	 */
	public void addAccount(Account account) throws SQLException {
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
	 * @throws SQLException
	 */
	public void addAccount(String username, String password, String firstname, String lastname, String email, int type)
			throws SQLException {
		if(!containsAccount(username)) {
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO ").append(DBInfo.USERS);
			sb.append(" (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, TYPE_ID) VALUES ( '");
			sb.append(username).append("', '");
			sb.append(password).append("', '");
			sb.append(firstname).append("', '");
			sb.append(lastname).append("', '");
			sb.append(email).append("', '");
			sb.append(type).append("');");

			String query = sb.toString();
			statement.executeUpdate(query);
			
			Account acc = new Account(username, password, firstname, lastname, email, type);
			accountsList.add(acc);
		}
	}

	
	/**
	 * Returns true if account is in the database
	 * 
	 * @param username
	 * @return boolean
	 * @throws SQLException
	 */
	boolean containsAccount(String username) throws SQLException {
		String query = "SELECT * FROM " + DBInfo.USERS + " WHERE USERNAME = \"" + username + "\";";
		resultset = statement.executeQuery(query);
		
		if(resultset.next()) return true;
		return false;
	}

	
	
	/**
	 * Returns Account which belongs to the passed user name
	 * 
	 * @param username
	 * @return account
	 * @throws SQLException
	 */
	public Account getAccount(String username) throws SQLException {

		if (containsAccount(username)) {
			account = getAccountFromResultset(resultset);
		}

		return account;
	}

	
	
	/**
	 * Returns Array List of Accounts which are in the database
	 * 
	 * @return ArrayList<Accounts>
	 * @throws SQLException
	 */
	public ArrayList<Account> getAccountsList() throws SQLException {
		if (accountsList.isEmpty()) {
			String query = "SELECT * FROM " + DBInfo.USERS + ";";
			resultset = statement.executeQuery(query);

			while (resultset.next()) {
				account = getAccountFromResultset(resultset);
				accountsList.add(account);
			}
		}
		return accountsList;
	}
	
	
	
	/**
	 * Reads information from passed result set and makes a new account; 
	 * 
	 * @param result
	 * @return account
	 * @throws SQLException
	 */
	private Account getAccountFromResultset(ResultSet result) throws SQLException {
		String username = result.getString(DBInfo.USER_USERNAME);
		String password = result.getString(DBInfo.USER_PASSWORD);
		String firstname = result.getString(DBInfo.USER_FIRSTNAME);
		String lastname = result.getString(DBInfo.USER_LASTNAME);
		String email = result.getString(DBInfo.USER_EMAIL);
		int type = result.getInt(DBInfo.USER_TYPE_ID);
		
		Account acc = new Account(username, password, firstname, lastname, email, type);
		
		return acc;
	}
}
