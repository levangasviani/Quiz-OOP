package Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.DBConnection;
import Database.DBInfo;

/**
 * @author levanAmateur(lkara15)
 *
 *         Controller Quiz Manager Class
 *
 */
public class QuizManager {

	private Connection con;

	/**
	 * Constructor
	 */
	public QuizManager() {
		con = DBConnection.getConnection();
	}
	
	/**
	 * Returns true if database contains a quiz with a given name.
	 * 
	 * @param quizName
	 *            - name of a quiz
	 * 
	 * @return boolean
	 */
	public boolean containsQuiz(String quizName) {
		String query = "SELECT * FROM " + DBInfo.QUIZZES + " WHERE NAME = ?;";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, quizName);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Adds quiz into a database if such quiz does nor exists in a database.
	 * 
	 * @param quiz
	 */
	public void addQuiz(Quiz quiz) {
		String quizName = quiz.getName();
		if (containsQuiz(quizName)) {
			return;
		}
		String query = "INSERT INTO " + DBInfo.QUIZZES + " (NAME, DESCRIPtioN, RANDOM, ONE_PAGE, PRACTICE_MODE, IMMEDIATE_GRADE, FREQUENCY) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, quizName);
			preparedStatement.setString(2, quiz.getDescription());
			if(quiz.isRandom()) {
				preparedStatement.setString(3, "TRUE");	
			} else preparedStatement.setString(3, "FALSE");
			
			if(quiz.isOnePage()) {
				preparedStatement.setString(4, "TRUE");	
			} else preparedStatement.setString(4, "FALSE");
			
			if(quiz.isRandom()) {
				preparedStatement.setString(5, "TRUE");	
			} else preparedStatement.setString(5, "FALSE");
			
			if(quiz.immediateCorrection()) {
				preparedStatement.setString(6, "TRUE");	
			} else preparedStatement.setString(6, "FALSE");
			
			preparedStatement.setInt(7, 0);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns a quiz from a database with a given name, if it exists.
	 * 
	 * @param QuizName
	 * 
	 * @return Quiz
	 */
	public Quiz getQuiz(String quizName) {
		String query = "SELECT * FROM " + DBInfo.QUIZZES + " WHERE NAME = ?;";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, quizName);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				String description = rs.getString(DBInfo.QUIZZES_DESCRIPTION);	
				String random = rs.getString(DBInfo.QUIZZES_RANDOM);
				boolean isRandom = false;
				if(random.equals("TRUE")) isRandom = true;
				String onePage = rs.getString(DBInfo.QUIZZES_ONE_PAGE);
				boolean isOnePage = false;
				if(onePage.equals("TRUE")) isRandom = true;
				String practiceMode = rs.getString(DBInfo.QUIZZES_PRACTICE_MODE);
				boolean canPracticeMode = false;
				if(practiceMode.equals("TRUE")) isRandom = true;
				String immediateGrade = rs.getString(DBInfo.QUIZZES_IMMEDIATE_GRADE);
				boolean immediateCorrection = false;
				if(immediateGrade.equals("TRUE")) isRandom = true;
				Quiz quiz = new Quiz(quizName, description, isRandom, isOnePage, canPracticeMode, immediateCorrection);
				return quiz;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Returns all quizes from database
	 * 
	 * @return ArrayListn<Quiz>
	 */
	public ArrayList<Quiz> getAllQuizes() {
		ArrayList<Quiz> result = new ArrayList<Quiz>();
		String query = "SELECT * FROM " + DBInfo.QUIZZES + ";";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String quizName = rs.getString(DBInfo.QUIZZES_NAME);
				String description = rs.getString(DBInfo.QUIZZES_DESCRIPTION);	
				String random = rs.getString(DBInfo.QUIZZES_RANDOM);
				boolean isRandom = false;
				if(random.equals("TRUE")) isRandom = true;
				String onePage = rs.getString(DBInfo.QUIZZES_ONE_PAGE);
				boolean isOnePage = false;
				if(onePage.equals("TRUE")) isRandom = true;
				String practiceMode = rs.getString(DBInfo.QUIZZES_PRACTICE_MODE);
				boolean canPracticeMode = false;
				if(practiceMode.equals("TRUE")) isRandom = true;
				String immediateGrade = rs.getString(DBInfo.QUIZZES_IMMEDIATE_GRADE);
				boolean immediateCorrection = false;
				if(immediateGrade.equals("TRUE")) isRandom = true;
				Quiz quiz = new Quiz(quizName, description, isRandom, isOnePage, canPracticeMode, immediateCorrection);
				result.add(quiz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Returns ID of a given quiz if it exists. if such quiz does not exist
	 * returns -1.
	 * 
	 * @param quiz
	 * 
	 * @return int
	 */
	public int getQuizID(Quiz quiz) {
		if (containsQuiz(quiz.getName())) {
			String query = "SELECT * FROM " + DBInfo.QUIZZES + " WHERE NAME = ?;";
			try {
				PreparedStatement preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, quiz.getName());
				ResultSet rs = preparedStatement.executeQuery();
				int result = -1;
				if (rs.next())
					result = rs.getInt(DBInfo.QUIZZES_ID);
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * Returns Arraylist of a given quiz questions.
	 * 
	 * @param quiz
	 * @return ArrayList of questions
	 */
	public ArrayList<Question> getQuestions(Quiz quiz) {
		ArrayList<Question> result = new ArrayList<Question>();
		String query = "SELECT * FROM " + DBInfo.QUESTIONS + " WHERE QUIZ_ID = ?";
		QuestionManager qM = new QuestionManager();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, getQuizID(quiz));
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int questionID = rs.getInt(DBInfo.QUESTIONS_ID);
				Question question = qM.getQuestion(questionID);
				result.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}

