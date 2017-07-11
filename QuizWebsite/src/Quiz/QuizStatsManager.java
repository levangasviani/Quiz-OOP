package Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DBConnection;
import Database.DBInfo;


/**
 * 
 * @author levani
 *
 * Manager class, that stores and gets informations about statistics 
 * from the base
 */
public class QuizStatsManager {

	private Connection connection;
	
	
	/**
	 * Constructor
	 */
	public QuizStatsManager() {
		connection = DBConnection.getConnection();
	}
	
	
	/**
	 * 
	 * adds user's id and quiz id pair into the CreatedQuizzes table
	 * in the database
	 * 
	 * @param userId
	 * @param quizId
	 */
	public void addQuizCreated(int userId, int quizId) {
		String query = "INSERT INTO " + DBInfo.CREATED_QUIZZES + " (USER_ID, QUIZ_ID) VALUES(?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, quizId);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * writes information into the database after completing the quiz
	 *
	 *
	 * @param userId
	 * 					-user's ID
	 * @param quizId
	 * 					-quiz ID
	 * @param score
	 * 					-score that user got in the quiz
	 * @param timeSpent
	 * 					-time that user spent on the quiz
	 */
	public void addQuizCompleted(int userId, int quizId, int score, int timeSpent) {
		String query = "INSERT INTO " + DBInfo.COMPLETED_QUIZZES + " (USER_ID, QUIZ_ID, SCORE, SPENT_TIME) VALUES(?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, quizId);
			preparedStatement.setInt(3, score);
			preparedStatement.setInt(4, timeSpent);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
