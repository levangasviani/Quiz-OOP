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

	
	/**
	 * constructs QuestionManager Object by connecting to the database
	 * 
	 * @throws SQLException
	 */
	public QuestionManager() {
		connection = DBConnection.getConnection();
	}

	
	/**
	 * adds a new question in the database using a Question Object
	 * 
	 * @param q
	 *            - Question object
	 */
	public void addQuestion(Question q) {
		String questionText = q.getQuestionText();
		int score = q.getMaxScore();
		String checkType = q.getCheckType();
		int time = q.getTime();
		int quizId = q.getQuizId();
		int typeId = q.getTypeId();
		HashMap<String, String> answers = q.getAnswers();
		String order = q.getAnswerOrder();
		addQuestion(questionText, score, checkType, time, quizId, typeId);
		q.setId(getQuestionId());
		addAnswers(answers, q.getId(), order);
	}


	/**
	 * adds a new Question Object in the database using parameters of a Question
	 * Object
	 * 
	 * @param questionText
	 *            - the question statement
	 * @param score
	 *            - maximal score of the question
	 * @param checkType
	 *            - checking type
	 * @param time
	 *            - time restriction on the question
	 * @param quizId
	 *            - the id of the quiz to which this question belongs to
	 * @param typeId
	 *            - what kind of question it is
	 * @param answers
	 *            - answers of the question
	 */
	public void addQuestion(String questionText, int score, String checkType, int time, int quizId, int typeId) {
		String query = "INSERT INTO " + DBInfo.QUESTIONS
				+ " (QUESTION, SCORE, CHECK_TYPE, TIME, QUIZ_ID, TYPE_ID) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, questionText);
			preparedStatement.setInt(2, score);
			preparedStatement.setString(3, checkType);
			preparedStatement.setInt(4, time);
			preparedStatement.setInt(5, quizId);
			preparedStatement.setInt(6, typeId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Deletes Question and Question answers from the database
	 * 
	 * @param questionId
	 */
	public void deleteQuestion(int questionId) {
		Question q = getQuestion(questionId);
		
		if(q != null) {
			deleteAnswers(questionId);
			String query = "DELETE FROM " + DBInfo.QUESTIONS + " WHERE ID = ?;";
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, questionId);
				preparedStatement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * Deletes answers of the passed question
	 * 
	 * @param questionId
	 */
	private void deleteAnswers(int questionId) {
		String query = "DELETE FROM " + DBInfo.ANSWERS + " WHERE QUESTION_ID = ?;";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, questionId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * returns a question id
	 * 
	 * @return - the id of the last added question
	 */
	private int getQuestionId() {
		String query = "SELECT * FROM QUESTIONS ORDER BY ID DESC LIMIT 1";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt(DBInfo.QUESTIONS_ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	/**
	 * adds possible answers in the database
	 * 
	 * @param answers
	 *            - possible answers
	 * @param questionId
	 *            - the id of the question the answers belong to
	 * @param order
	 *            - shows whether the correct answer should be ordered or
	 *            unordered
	 */
	private void addAnswers(HashMap<String, String> answers, int questionId, String order) {
		String insertIntoAnswers = "INSERT INTO " + DBInfo.ANSWERS
				+ " (ANSWER, `TF`, `ORDER`, QUESTION_ID) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(insertIntoAnswers);
			for (String key : answers.keySet()) {
				preparedStatement.setString(1, key);
				preparedStatement.setString(2, answers.get(key));
				preparedStatement.setString(3, order);
				preparedStatement.setInt(4, questionId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * returns a question object
	 * 
	 * @param questionId
	 *            - id of a question in the database
	 * @return - question object by the given id
	 */
	public Question getQuestion(int questionId) {
		String query = "SELECT * FROM " + DBInfo.QUESTIONS + " WHERE ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, questionId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String questionText = resultSet.getString(DBInfo.QUESTIONS_QUESTION);
			int score = resultSet.getInt(DBInfo.QUESTIONS_SCORE);
			String checkType = resultSet.getString(DBInfo.QUESTIONS_CHECK_TYPE);
			int time = resultSet.getInt(DBInfo.QUESTIONS_TIME);
			int quizId = resultSet.getInt(DBInfo.QUESTIONS_QUIZ_ID);
			int typeId = resultSet.getInt(DBInfo.QUESTIONS_TYPE_ID);
			HashMap<String, String> answers = new HashMap<String, String>();
			String order = getAnswersInformation(answers, questionId);
			Question q;
			if(typeId==DBInfo.QUESTION_TYPE_QUESTION_RESPONSE){
				q=new Question_Response(questionText, answers, quizId, order, checkType, time, score);
			}else if(typeId==DBInfo.QUESTION_TYPE_FILL_IN_THE_BLANK){
				q=new Fill_In_The_Blank(questionText, answers, quizId, order, checkType, time, score);
			}else if(typeId==DBInfo.QUESTION_TYPE_MULTIPLE_CHOICE){
				q=new Multiple_Choice(questionText, answers, quizId, order, checkType, time, score);
			}else if(typeId==DBInfo.QUESTION_TYPE_PICTURE_RESPONSE){
				q=new Picture_Response(questionText, answers, quizId, order, checkType, time, score);
			}else if(typeId==DBInfo.QUESTION_TYPE_MULTI_ANSWER){
				q=new Multi_Answer(questionText, answers, quizId, order, checkType, time, score);
			}else if(typeId==DBInfo.QUESTION_TYPE_MULTIPLE_CHOICE_WITH_MULTIPLE_ANSWERS){
				q=new Multiple_Choice_Multiple_Answer(questionText, answers, quizId, order, checkType, time, score);
			}else if(typeId==DBInfo.QUESTION_TYPE_MATCHING){
				q=new Matching(questionText, answers, quizId, order, checkType, time, score);
			}else{
				q=new Graded_Question(questionText, answers, quizId, order, checkType, time, score);
			}
			q.setId(questionId);
			return q;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * gets the information about the answers of the question with a given
	 * questionId
	 * 
	 * @param answers
	 *            - an empty map where we put the information
	 * @param questionId
	 *            - id of the question
	 * @return - String of order, whether the correct answer should be ordered
	 *         or unordered
	 */
	private String getAnswersInformation(HashMap<String, String> answers, int questionId) {
		String result = "";
		String query = "SELECT * FROM " + DBInfo.ANSWERS + " WHERE QUESTION_ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, questionId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String answer = resultSet.getString(DBInfo.ANSWERS_ANSWER);
				String correctness = resultSet.getString(DBInfo.ANSWERS_TF);
				answers.put(answer, correctness);
				result = resultSet.getString(DBInfo.ANSWERS_ORDER);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
