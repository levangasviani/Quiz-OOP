package Quiz;

import java.sql.SQLException;
import java.util.HashMap;

public class QuestionApp {

	public static void main(String[] args) throws SQLException {
		QuestionManager q = new QuestionManager();
		String questionText = "bla";
		int score = 5;
		String checkType = "COMPUTER";
		int time = 1000;
		int quizId = 1;
		int typeId = 2;
		HashMap<String, String> m = new HashMap<String, String>();
		m.put("a", "TRUE");
		m.put("b", "FALSE");
		m.put("c", "TRUE");
		String order = "FALSE";
		Question q1 = new Question(questionText, score, checkType, time, quizId, typeId, m, order);
		q.addQuestion(q1);
		Question res = q.getQuestion(53);
		/* q1 da res */

		if (q1.toString().equals(res.toString())) {
			System.out.print("sworia");
		}

	}

}
