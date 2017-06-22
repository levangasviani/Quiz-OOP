package Quiz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import Database.DBConnection;
import Database.DBInfo;

/**
 * @author levanAmateur(lkara15)
 *
 * Controller Quiz Manager Class
 *
 */
public class QuizManager {
	
	private Connection con;
	private Statement stm;
	
	
	/**
	 * Constructor
	 */
	public QuizManager(){
		con = DBConnection.getConnection();
		try {
			stm = con.createStatement();		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns true if database contains a quiz with
	 * a given name.
	 * 
	 * @param quizName - name of a quiz
	 * 
	 * @return boolean
	 */
	public boolean containsQuiz(String quizName) {
		String query = "select * from " + DBInfo.QUIZ_NAMES + " where name = \"" + quizName + "\";";
		try {
			ResultSet rs = stm.executeQuery(query);
			if(rs.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}

	/**
	 * Adds quiz into a database if such quiz does
	 * nor exists in a database.
	 * 
	 * @param quiz
	 */
	public void addQuiz(Quiz quiz) {
		String quizName = quiz.getName();
		if(containsQuiz(quizName)) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("insert into ").append(DBInfo.QUIZ_NAMES);
		sb.append(" (NAME) values ('");
		sb.append(quizName).append("');");
		
		String query = sb.toString();
		try {
			stm.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a quiz from a database
	 * with a given name, if it exists.
	 * 
	 * @param QuizName
	 * 
	 * @return Quiz
	 */
	public Quiz getQuiz(String quizName) {
		if(containsQuiz(quizName)) {
			Quiz q = new Quiz(quizName);
			return q;
		} 
		return null;
	}
	
	/**
	 * Returns all quizes from database
	 * 
	 * @return ArrayListn<Quiz>
	 */
	public ArrayList<Quiz>  getAllQuizes() {
		ArrayList<Quiz> result = new ArrayList<Quiz>();
		String query = "select * from " + DBInfo.QUIZ_NAMES + ";";
		ResultSet rs;
		try {
			rs = stm.executeQuery(query);
			while (rs.next()) {
				String quizName = rs.getString(DBInfo.QUIZ_NAMES_QUIZ_NAME);
				Quiz quiz = new Quiz(quizName);
				result.add(quiz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Returns ID of a given quiz if it exists.
	 * if such quiz does not exist returns -1.
	 * 
	 * @param quiz
	 * 
	 * @return int
	 */
	public int getQuizID(Quiz quiz) {
		if(containsQuiz(quiz.getName())) {
			String query = "select * from " + DBInfo.QUIZ_NAMES + " where NAME = \"" + quiz.getName() + "\";";
			try {
				ResultSet rs = stm.executeQuery(query);
				int result = -1;
				if(rs.next()) result = rs.getInt(DBInfo.QUIZ_NAMES_ID);
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
	 */
	public ArrayList<Question> getQuestions(Quiz quiz) {
		ArrayList<Question> result = new ArrayList<Question>();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from ").append(DBInfo.QUESTIONS);
		sb.append(" where QUIZ_ID = \"").append(getQuizID(quiz)).append("\";");
			
		String query = sb.toString();
		QuestionManager qM = null;
		try {
			qM = new QuestionManager();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()) {
				int questionID = rs.getInt(DBInfo.QUESTION_ID);
				Question question = qM.getQuestion(questionID);
				result.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		QuizManager qm = new QuizManager();
		Quiz q1 = new Quiz("levan1");
		Quiz q11 = new Quiz("levan1");
		Quiz q2 = new Quiz("levan2");
		qm.addQuiz(q1);
		qm.addQuiz(q11);
		qm.addQuiz(q2);
		if(qm.containsQuiz(q1.getName())) {
			System.out.println("Yes");
		}
		Quiz q4 = qm.getQuiz(q1.getName());
		System.out.println(q4.getName());
		ArrayList<Quiz> arr = qm.getAllQuizes();
		System.out.println(arr.size());
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).getName());
		}
		
		
		Quiz qz = new Quiz("shota1");
		qm.addQuiz(qz);
		if(qm.containsQuiz("shota1")) {	
			QuestionManager q = null;
			try {
				q = new QuestionManager();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String questionText = "bla1";
			int score = 5;
			String checkType = "COMPUTER";
			int time = 1000;
			int quizId = qm.getQuizID(qz);
			int typeId = 2;
			HashMap<String, String> m = new HashMap<String, String>();
			m.put("a", "TRUE");
			m.put("b", "TRUE");
			m.put("c", "TRUE");
			String order = "FALSE";
			Question qst1 = new Question(questionText, score, checkType, time, quizId, typeId, m, order);
			try {
				q.addQuestion(qst1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Question> arr1 = qm.getQuestions(qz);
			System.out.println(arr1.size());
			for(int i = 0; i < arr1.size(); i++) {
				System.out.println(arr1.get(i).toString());
			}
		}
	}
}



