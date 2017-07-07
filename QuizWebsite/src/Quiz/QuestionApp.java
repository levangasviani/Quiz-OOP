package Quiz;

import java.sql.SQLException;
import java.util.HashMap;

public class QuestionApp {

	public static void main(String[] args) throws SQLException {
		QuestionManager q = new QuestionManager();
		String questionText = "pasuxebianad";
		int score = 5;
		String checkType = "COMPUTER";
		int time = 1000;
		int quizId = 1; 
		HashMap<String, String> m = new HashMap<String, String>();
		m.put("pq", "TRUE");
		m.put("basasda", "FALSE");
		m.put("vaavsdc", "TRUE");
		String order = "FALSE";
		Question q1 = new Multiple_Choice_Multiple_Answer(questionText, m, quizId, order, checkType, time, score);
		q.addQuestion(q1);
		//Question res = q.getQuestion(q1.getId());
		/* q1 da res */
		
		//q.deleteQuestion(q1.getId());

	}

}
