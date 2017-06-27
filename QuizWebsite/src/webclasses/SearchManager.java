package webclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DBConnection;
import Database.DBInfo;
import Quiz.Quiz;
import Quiz.QuizManager;
import User.Account;
import User.AccountManager;

/**
 * 
 * @author levanAmateur (lkara15)
 *
 */
public class SearchManager {
	
	private Connection con;
	private ArrayList<Quiz> quizzes;	
	private ArrayList<Account> accounts;
	
	
	/**
	 * Cosntructor
	 * initialises string searchValue.
	 * 
	 * @param searchValue
	 */
	public SearchManager() {
		con = DBConnection.getConnection();
		quizzes = new ArrayList<Quiz>();
		accounts = new ArrayList<Account>();
	}
	
	/**
	 * Returns ArrayList of Quizzes associated to searchValue.
	 * @return ArrayList<Quiz>
	 */
	public ArrayList<Quiz> getQuizzes(String searchValue) {
		quizzes.clear();
		String query = "SELECT * FROM " + DBInfo.QUIZ_NAMES + " WHERE NAME = ?;";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, searchValue);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String quizName = rs.getString(DBInfo.QUIZ_NAMES_QUIZ_NAME);
				Quiz quiz = new Quiz(quizName);
				quizzes.add(quiz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizzes;
	}

	/*
	 * Searches User in table of users, 
	 * considering that searchValue is userName
	 */
	private void searchUserByUserName(String searchValue) {
		String query = "SELECT * FROM " + DBInfo.USERS + " WHERE USERNAME = ?;";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, searchValue);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String username = rs.getString(DBInfo.USER_USERNAME);
				String password = rs.getString(DBInfo.USER_PASSWORD);
				String firstName = rs.getString(DBInfo.USER_FIRSTNAME);
				String lastName = rs.getString(DBInfo.USER_FIRSTNAME);
				String email = rs.getString(DBInfo.USER_EMAIL);
				int typeID = rs.getInt(DBInfo.USER_TYPE_ID);
				
				Account acc = new Account(username, password, firstName, lastName, email, typeID);
				accounts.add(acc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Searches User in table of users, 
	 * considering that searchValue is FirstName
	 */
	private void searchUserFirstName(String searchValue) {
		String query = "SELECT * FROM " + DBInfo.USERS + " WHERE FIRSTNAME = ?;";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, searchValue);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String username = rs.getString(DBInfo.USER_USERNAME);
				String password = rs.getString(DBInfo.USER_PASSWORD);
				String firstName = rs.getString(DBInfo.USER_FIRSTNAME);
				String lastName = rs.getString(DBInfo.USER_FIRSTNAME);
				String email = rs.getString(DBInfo.USER_EMAIL);
				int typeID = rs.getInt(DBInfo.USER_TYPE_ID);
				
				Account acc = new Account(username, password, firstName, lastName, email, typeID);
				accounts.add(acc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	/*
	 * Searches User in table of users, 
	 * considering that searchValue is LastName
	 */
	private void searchUserLastName(String searchValue) {
		String query = "SELECT * FROM " + DBInfo.USERS + " WHERE LASTNAME = ?;";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, searchValue);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String username = rs.getString(DBInfo.USER_USERNAME);
				String password = rs.getString(DBInfo.USER_PASSWORD);
				String firstName = rs.getString(DBInfo.USER_FIRSTNAME);
				String lastName = rs.getString(DBInfo.USER_FIRSTNAME);
				String email = rs.getString(DBInfo.USER_EMAIL);
				int typeID = rs.getInt(DBInfo.USER_TYPE_ID);
				
				Account acc = new Account(username, password, firstName, lastName, email, typeID);
				accounts.add(acc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	/*
	 * Searches User in table of users, 
	 * considering that searchValue is Email
	 */
	private void searchUserEmail(String searchValue) {
		String query = "SELECT * FROM " + DBInfo.USERS + " WHERE EMAIL = ?;";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, searchValue);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String username = rs.getString(DBInfo.USER_USERNAME);
				String password = rs.getString(DBInfo.USER_PASSWORD);
				String firstName = rs.getString(DBInfo.USER_FIRSTNAME);
				String lastName = rs.getString(DBInfo.USER_FIRSTNAME);
				String email = rs.getString(DBInfo.USER_EMAIL);
				int typeID = rs.getInt(DBInfo.USER_TYPE_ID);
				
				Account acc = new Account(username, password, firstName, lastName, email, typeID);
				accounts.add(acc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Returns ArrayList of Accounts associated to searchValue.
	 * @return ArrayList<Account>
	 */
	public ArrayList<Account> getAccounts(String searchValue) {
		accounts.clear();
		searchUserByUserName(searchValue);
		searchUserFirstName(searchValue);
		searchUserLastName(searchValue);
		searchUserEmail(searchValue);
		
		return accounts;
	}
	
	/*
	 * main
	 */
	public static void main(String[] args) {
		SearchManager sm = new SearchManager();
		QuizManager qm = new QuizManager();
		Quiz q1 = new Quiz("karanadze1");
		qm.addQuiz(q1);
		ArrayList<Quiz> qs = sm.getQuizzes(q1.getName());
		for(int i = 0; i < qs.size(); i++) {
			System.out.println(i + ": " + qs.get(i).getName());
		}
		
		AccountManager am = new AccountManager();
		/*am.addAccount("user0", "1234", "name0", "lname0", "mail0", 2);
		am.addAccount("user1", "1234", "name1", "lname1", "mail0", 2);
		am.addAccount("user2", "1234", "name1", "lname1", "mail0", 2);
		am.addAccount("user3", "1234", "name3", "lname1", "mail0", 2);*/
		ArrayList<Account> arr1 = new ArrayList<Account>(sm.getAccounts("user0"));
		ArrayList<Account> arr2 = new ArrayList<Account>(sm.getAccounts("name1"));
		ArrayList<Account> arr3 = new ArrayList<Account>(sm.getAccounts("lname"));
		ArrayList<Account> arr4 = new ArrayList<Account>(sm.getAccounts("mail0"));
		System.out.println("arr1");
		for(int i = 0; i < arr1.size(); i++) {
			System.out.println(i + ": " + arr1.get(i).toString());
		}
		System.out.println("arr2");
		for(int i = 0; i < arr2.size(); i++) {
			System.out.println(i + ": " + arr2.get(i).toString());
		}
		System.out.println("arr3");	
		for(int i = 0; i < arr3.size(); i++) {
			System.out.println(i + ": " + arr3.get(i).toString());
		}
		System.out.println("arr4");
		for(int i = 0; i < arr4.size(); i++) {
			System.out.println(i + ": " + arr4.get(i).toString());
		}
	}
	
	
}


