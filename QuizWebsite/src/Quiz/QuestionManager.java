package Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import Database.DBConnection;
import Database.DBInfo;

/**
 * 
 * @author shotiko
 *
 */
public class QuestionManager {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String insertIntoQuestions = "INSERT INTO " + DBInfo.QUESTIONS
			+ " (QUESTION, SCORE, CHECK_TYPE, TIME, QUIZ_ID, TYPE_ID) VALUES (?, ?, ?, ?, ?, ?)";
	private String insertIntoAnswers = "INSERT INTO " + DBInfo.ANSWERS
			+ " (ANSWER, `T/F`, `ORDER`, QUESTION_ID) VALUES (?, ?, ?, ?)";
	private String selectFromQuestions = "SELECT * FROM " + DBInfo.QUESTIONS + " WHERE ID=?";
	private String selectFromAnswers = "SELECT * FROM " + DBInfo.ANSWERS + " WHERE QUESTION_ID=?";
	private String getLastId = "SELECT * FROM QUESTIONS ORDER BY ID DESC LIMIT 1";

	/**
	 * constructs QuestionManager Object by connecting to the database 
	 * @throws SQLException
	 */
	public QuestionManager() throws SQLException {
		connection = DBConnection.getConnection();
	}

	/**
	 * adds a new question in the database using a Question Object
	 * @param q - Question object
	 * @throws SQLException
	 */
	public void addQuestion(Question q) throws SQLException {
		String questionText = q.getQuestionText();
		int score = q.getScore();
		String checkType = q.getCheckType();
		int time = q.getTime();
		int quizId = q.getQuizId();
		int typeId = q.getTypeId();
		HashMap<String, String> answers = q.getAnswers();
		String order = q.getAnswerOrder();
		addQuestion(questionText, score, checkType, time, quizId, typeId, answers, order);
	}

	/**
	 * adds a new Question Object in the database using parameters of a Question Object
	 * @param questionText - the question statement
	 * @param score - maximal score of the question
	 * @param checkType - checking type
	 * @param time - time restriction on the question
	 * @param quizId - the id of the quiz to which this question belongs to
	 * @param typeId - what kind of question it is
	 * @param answers - answers of the question
	 * @param order - determines whether answer should be ordered or unordered
	 * @throws SQLException 
	 */
	public void addQuestion(String questionText, int score, String checkType, int time, int quizId, int typeId,
			HashMap<String, String> answers, String order) throws SQLException {
		preparedStatement = connection.prepareStatement(insertIntoQuestions);
		preparedStatement.setString(1, questionText);
		preparedStatement.setInt(2, score);
		preparedStatement.setString(3, checkType);
		preparedStatement.setInt(4, time);
		preparedStatement.setInt(5, quizId);
		preparedStatement.setInt(6, typeId);
		preparedStatement.executeUpdate();
		int id = getQuestionId();
		addAnswers(answers, id, order);
	}

	
	/**
	 * returns a question id
	 * @return - the id of the last added question 
	 * @throws SQLException
	 */
	private int getQuestionId() throws SQLException {
		preparedStatement = connection.prepareStatement(getLastId);
		resultSet = preparedStatement.executeQuery();
		resultSet.next();
		int id = resultSet.getInt(DBInfo.QUESTION_ID);
		return id;
	}

	
	/**
	 * adds possible answers in the database
	 * @param answers - possible answers  
	 * @param questionId - the id of the question the answers belong to
	 * @param order - shows whether the correct answer should be ordered or unordered
	 * @throws SQLException
	 */
	private void addAnswers(HashMap<String, String> answers, int questionId, String order) throws SQLException {
		for (String key : answers.keySet()) {
			preparedStatement = connection.prepareStatement(insertIntoAnswers);
			preparedStatement.setString(1, key);
			preparedStatement.setString(2, answers.get(key));
			preparedStatement.setString(3, order);
			preparedStatement.setInt(4, questionId);
			preparedStatement.executeUpdate();
		}
	}

	
	/**
	 * returns a question object
	 * @param questionId - id of a question in the database
	 * @return - question object by the given id
	 * @throws SQLException
	 */
	public Question getQuestion(int questionId) throws SQLException {
		preparedStatement = connection.prepareStatement(selectFromQuestions);
		preparedStatement.setInt(1, questionId);
		resultSet = preparedStatement.executeQuery();
		resultSet.next();
		String questionText = resultSet.getString(DBInfo.QUESTION);
		int score = resultSet.getInt(DBInfo.SCORE);
		String checkType = resultSet.getString(DBInfo.CHECK_TYPE);
		int time = resultSet.getInt(DBInfo.TIME);
		int quizId = resultSet.getInt(DBInfo.QUIZ_ID);
		int typeId = resultSet.getInt(DBInfo.TYPE_ID);
		HashMap<String, String> answers = new HashMap<String, String>();
		String order = getAnswersInformation(answers, questionId);
		Question resultQuestion = new Question(questionText, score, checkType, time, quizId, typeId, answers, order);
		return resultQuestion;
	}

	
	/**
	 * gets the information about the answers of the question with a given questionId
	 * @param answers - an empty map where we put the information
	 * @param questionId - id of the question
	 * @return - String of order, whether the correct answer should be ordered or unordered
	 * @throws SQLException
	 */
	private String getAnswersInformation(HashMap<String, String> answers, int questionId) throws SQLException {
		String order = "";
		preparedStatement = connection.prepareStatement(selectFromAnswers);
		preparedStatement.setInt(1, questionId);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {

			String answer = resultSet.getString(DBInfo.ANSWER);
			String correctness = resultSet.getString(DBInfo.TF);
			answers.put(answer, correctness);
			order = resultSet.getString(DBInfo.ORDER);
		}
		return order;
	}

}
