package Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	
	/**
	 * 
	 * Gets all completion statistics from database about passed Quiz,
	 * calls getBestResultsForQuiz method with max id value possible,
	 * that means no limit for ID
	 * 
	 * @param quiz
	 * @return Result set of all results
	 */
	public ResultSet getQuizStats(Quiz quiz) {
		ResultSet rs = getBestResultsForQuiz(quiz, DBInfo.MAX_ID_VALUE);
		return rs;
	}
	
	
	/**
	 * 
	 * Method return result set of best completions for the passed quiz,
	 * quantity is how many result you want to get, result is sorted by score (higher is better)
	 * if scores are equal, than it is sorted by spent time (lower is better)
	 * 
	 * @param quiz
	 * @param quantity
	 * @return Result set of best completions
	 */
	public ResultSet getBestResultsForQuiz(Quiz quiz, int quantity) {
		QuizManager qm = new QuizManager();
		int quizId = qm.getQuizID(quiz);
		
		if(quizId != -1) {
			String query = "SELECT * FROM " + DBInfo.COMPLETED_QUIZZES + " WHERE QUIZ_ID = ? ORDER BY SCORE DESC, SPENT_TIME ASC LIMIT ?";
			
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, quizId);
				preparedStatement.setInt(2, quantity);
				
				ResultSet rs = preparedStatement.executeQuery();
				return rs;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
}
