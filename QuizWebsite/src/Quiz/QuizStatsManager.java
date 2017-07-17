package Quiz;

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
 * @author levani
 *
 *         Manager class, that stores and gets informations about statistics
 *         from the base
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
	 * adds user's id and quiz id pair into the CreatedQuizzes table in the
	 * database
	 * 
	 * @param username
	 * @param quiz
	 *            name
	 */
	public void addQuizCreated(String username, String quizName) {
		AccountManager accMan = new AccountManager();
		QuizManager quizMan = new QuizManager();
		Quiz quiz = quizMan.getQuiz(quizName);

		int userId = accMan.getAccountId(username);
		int quizId = quizMan.getQuizID(quiz);

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
	 * @param String
	 *            user name -user's name
	 * @param String
	 *            quiz name -quiz name
	 * @param int
	 *            score -score that user got in the quiz
	 * @param int
	 *            timeSpent -time that user spent on the quiz in seconds
	 */
	public void addQuizCompleted(String username, String quizName, int score, int timeSpent) {
		AccountManager accMan = new AccountManager();
		QuizManager quizMan = new QuizManager();
		Quiz quiz = quizMan.getQuiz(quizName);

		int userId = accMan.getAccountId(username);
		int quizId = quizMan.getQuizID(quiz);

		String query = "INSERT INTO " + DBInfo.COMPLETED_QUIZZES
				+ " (USER_ID, QUIZ_ID, SCORE, SPENT_TIME) VALUES(?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, quizId);
			preparedStatement.setInt(3, score);
			preparedStatement.setInt(4, timeSpent);

			preparedStatement.executeUpdate();

			increaseQuizFrequency(quizName);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * Gets all completion statistics from database about passed Quiz, calls
	 * getBestResultsForQuiz method with max id value possible, that means no
	 * limit for ID
	 * 
	 * @param quiz
	 * @return Result set of all results
	 */
	public ResultSet getQuizStats(String quizName) {
		ResultSet rs = getBestResultsForQuiz(quizName, DBInfo.MAX_ID_VALUE);
		return rs;
	}

	/**
	 * Returns best results for the passed quiz of default statistic number
	 * 
	 * @param quizName
	 * @return
	 */
	public ResultSet getBestResultOfDefaultSize(String quizName) {
		ResultSet rs = getBestResultsForQuiz(quizName, DBInfo.DEFAULT_NUMBER_OF_RESULTS);
		return rs;
	}

	/**
	 * 
	 * Method return result set of best completions for the passed quiz,
	 * quantity is how many result you want to get, result is sorted by score
	 * (higher is better) if scores are equal, than it is sorted by spent time
	 * (lower is better)
	 * 
	 * @param quiz
	 * @param quantity
	 * @return Result set of best completions
	 */
	private ResultSet getBestResultsForQuiz(String quizName, int quantity) {
		QuizManager qm = new QuizManager();
		Quiz quiz = qm.getQuiz(quizName);
		int quizId = qm.getQuizID(quiz);

		if (quizId != -1) {
			String query = "SELECT * FROM " + DBInfo.COMPLETED_QUIZZES
					+ " WHERE QUIZ_ID = ? ORDER BY SCORE DESC, SPENT_TIME ASC LIMIT ?";

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

	/**
	 * Returns array list of recently created quiz names If there are no quizzes
	 * returns empty Array list
	 * 
	 * @return Array list of quiz names
	 */
	public ArrayList<String> getRecentlyCreatedQuizzes() {
		String query = "SELECT * FROM " + DBInfo.QUIZZES + " ORDER BY ID DESC LIMIT ?";

		ArrayList<String> quizNames = new ArrayList<>();

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, DBInfo.DEFAULT_NUMBER_OF_STATS);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String quizName = rs.getString(DBInfo.QUIZZES_NAME);
				quizNames.add(quizName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quizNames;
	}

	/**
	 * Returns array list of recently created quiz names by passed user If there
	 * are no quizzes returns empty Array list
	 * 
	 * @return Array list of quiz names
	 */
	public ArrayList<String> getRecentlyCreatedQuizzes(String username) {
		AccountManager accMan = new AccountManager();
		int userId = accMan.getAccountId(username);
		String query = "SELECT NAME FROM " + DBInfo.QUIZZES + " INNER JOIN " + DBInfo.CREATED_QUIZZES + " ON "
				+ DBInfo.QUIZZES + ".ID = " + DBInfo.CREATED_QUIZZES + ".QUIZ_ID WHERE USER_ID = ? " + " ORDER BY "
				+ DBInfo.CREATED_QUIZZES + ".ID DESC LIMIT ?";

		ArrayList<String> quizNames = new ArrayList<>();

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, DBInfo.DEFAULT_NUMBER_OF_STATS);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String quizName = rs.getString(1);
				quizNames.add(quizName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quizNames;
	}

	/**
	 * Increases the frequency of the quiz
	 * 
	 * @param quizName
	 */
	private void increaseQuizFrequency(String quizName) {
		String curValue = "SELECT * FROM " + DBInfo.QUIZZES + " WHERE NAME = ?;";
		String newValue = "UPDATE " + DBInfo.QUIZZES + " SET FREQUENCY = ? WHERE NAME = ?;";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(curValue);
			preparedStatement.setString(1, quizName);
			ResultSet rs = preparedStatement.executeQuery();

			rs.next();
			int curFrequency = rs.getInt(DBInfo.QUIZZES_FREQUENCY);
			int newFrequency = curFrequency + 1;

			preparedStatement = connection.prepareStatement(newValue);
			preparedStatement.setInt(1, newFrequency);
			preparedStatement.setString(2, quizName);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns default number of popular quizzes
	 * 
	 * @return array list of popular quizzes
	 */
	public ArrayList<String> getPopularQuizzes() {
		String query = "SELECT * FROM " + DBInfo.QUIZZES + " ORDER BY FREQUENCY DESC LIMIT ?;";

		ArrayList<String> quizNames = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, DBInfo.DEFAULT_NUMBER_OF_STATS);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				quizNames.add(rs.getString(DBInfo.QUIZZES_NAME));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quizNames;
	}

	/**
	 * Returns names f recently taken quizzes by passed user if user does not
	 * have any quizzes completed, then method returns empty array list
	 * 
	 * @param username
	 * @return array list of quiz names
	 */
	public ArrayList<String> getRecentlyTakenQuizzes(String username) {
		AccountManager accMan = new AccountManager();
		int userId = accMan.getAccountId(username);
		String query = "SELECT NAME FROM " + DBInfo.QUIZZES + " INNER JOIN " + DBInfo.COMPLETED_QUIZZES + " ON "
				+ DBInfo.QUIZZES + ".ID = " + DBInfo.COMPLETED_QUIZZES + ".QUIZ_ID WHERE USER_ID = ? " + " ORDER BY "
				+ DBInfo.COMPLETED_QUIZZES + ".ID DESC LIMIT ?";

		ArrayList<String> quizNames = new ArrayList<>();

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, DBInfo.DEFAULT_NUMBER_OF_STATS);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String quizName = rs.getString(1);
				quizNames.add(quizName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quizNames;
	}

	/**
	 * returns number of Created Quizzes by user
	 * 
	 * @param username
	 * @return int count of quizzes
	 */
	public int getCreatedQuizzesCount(String username) {
		AccountManager accMan = new AccountManager();
		int userId = accMan.getAccountId(username);

		String query = "SELECT COUNT(*) FROM " + DBInfo.CREATED_QUIZZES + " WHERE USER_ID = ?;";

		int res = 0;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);

			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			res = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * returns number of Created Quizzes by user
	 * 
	 * @param username
	 * @return int count of quizzes
	 */
	public int getCompletedQuizzesCount(String username) {
		AccountManager accMan = new AccountManager();
		int userId = accMan.getAccountId(username);
		System.out.print(userId);
		String query = "SELECT COUNT(*) FROM " + DBInfo.COMPLETED_QUIZZES + " WHERE USER_ID=?";

		int res = 0;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);

			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			res = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public void updateQuizScore(String receiver, String quizName) {
		AccountManager accountManager = new AccountManager();
		QuizManager quizManager = new QuizManager();
		String sql = "UPDATE " + DBInfo.COMPLETED_QUIZZES + " SET SCORE = SCORE + 1 WHERE USER_ID = ? AND QUIZ_ID = ? ORDER BY ID DESC LIMIT 1";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountManager.getAccountId(receiver));
			preparedStatement.setInt(2, quizManager.getQuizID(quizManager.getQuiz(quizName)));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
