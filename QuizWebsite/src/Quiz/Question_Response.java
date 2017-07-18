package Quiz;

import java.util.HashMap;

import Database.DBInfo;

public class Question_Response extends Question {

	public Question_Response(String questionText, HashMap<String, String> answers, int quizId, String answerOrder,
			String checkType, int time, int maxScore) {
		super(questionText, answers, quizId, answerOrder, checkType, time, maxScore);
	}

	@Override
	public int getTypeId() {
		return DBInfo.QUESTION_TYPE_QUESTION_RESPONSE;
	}

	/**
	 * returns the correct answer
	 * 
	 * @return - correct answer as a string
	 */
	private String getCorrectAnswer() {
		HashMap<String, String> answers = getAnswers();
		for (String s : answers.keySet()) {
			return s;
		}
		return "";
	}

	@Override
	public int getPoints(String chosenAnswer) {
		String answer = getCorrectAnswer();
		return LevenshteinDistance.correct(answer, chosenAnswer) ? 1 : 0;
	}
}
