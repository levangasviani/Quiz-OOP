package webclasses;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DBConnection;
import Database.DBInfo;

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
	 * Returns userID according to a username.
	 */
	private int getUserID(String username) {
		int result = 0;
		String query = "SELECT * FROM " + DBInfo.USERS + " WHERE USERNAME = ?;";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) result = rs.getInt(DBInfo.USERS_ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
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
				int id = rs.getInt(DBInfo.ACHIEVEMENTS_ID);
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
	public ArrayList<String> getAcievements(String username) {
		ArrayList<String> result = new ArrayList<>();
		int userID = getUserID(username);
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
	
	/*
	 * main
	 */
	public static void main(String[] args) {
		AchievementManager am = new AchievementManager();
		ArrayList<String> arr = am.getAcievements("user0");
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}
	
}

