package webclasses;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DBConnection;
import Database.DBInfo;
import User.AccountManager;

/**
 * @author levanAmateur(lkara15)
 *
 * Controller AchievementManager
 *
 */
public class AchievementManager {
	
	
	private Connection con;

	/**
	 * Constructor
	 */
	public AchievementManager() {
		con = DBConnection.getConnection();
	}
	
	/*
	 * Returns acheivement ID according to a user ID.
	 */
	private ArrayList<Integer> getAchievementIDs(int userID) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		String query = "SELECT * FROM " + DBInfo.ACHIEVEMENTS + " WHERE USER_ID = ?;";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, userID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(3);
				result.add(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public ArrayList<String> getAchievements(String username) {
		ArrayList<String> result = new ArrayList<>();
		AccountManager am = new AccountManager();
		int userID = am.getAccountId(username);
		ArrayList<Integer> achievementIDs = getAchievementIDs(userID);
		for(int i = 0; i < achievementIDs.size(); i++) {
			int id = achievementIDs.get(i);
			String query = "SELECT * FROM " + DBInfo.ACHIEVEMENT_TYPES + " WHERE ID = ?;";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, id);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					String achievement = rs.getString(DBInfo.ACHIEVEMENT_TYPES_NAME);
					result.add(achievement);
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private int getAchievementTypeID(String achievement) {
		int result = 0;
		String query = "SELECT * FROM " + DBInfo.ACHIEVEMENT_TYPES + " WHERE NAME = ?;";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, achievement);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(DBInfo.ACHIEVEMENT_TYPES_ID);
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void setAchievement(String achievement, String username) {
		int achievementTypeID = getAchievementTypeID(achievement);
		AccountManager am = new AccountManager();
		int userID = am.getAccountId(username);
		String query = "INSERT INTO " + DBInfo.ACHIEVEMENTS + " (USER_ID, TYPE_ID) VALUES (?, ?)";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, userID);
			preparedStatement.setInt(2, achievementTypeID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * main
	 */
	public static void main(String[] args) {
		AchievementManager am = new AchievementManager();
		am.setAchievement("New one", "levan");
		ArrayList<String> arr = am.getAchievements("levan");
		System.out.println(arr.size());
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
		am.setAchievement("Amateur Author", "levan");
		/*System.out.println(am.getAchievementTypeID("Prolific Author"));
		System.out.println(am.getAchievementTypeID("Prodigious Author"));
		System.out.println(am.getAchievementTypeID("Quiz Machine"));
		System.out.println(am.getAchievementTypeID("I am the Greatest"));
		System.out.println(am.getAchievementTypeID("Practice Makes Perfect"));
		System.out.println(am.getAchievementTypeID("New one"));*/
		
	}
	
}
