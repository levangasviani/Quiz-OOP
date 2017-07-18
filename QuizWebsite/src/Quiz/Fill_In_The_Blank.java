package Quiz;

import java.util.HashMap;

import Database.DBInfo;

public class Fill_In_The_Blank extends Question {

	public Fill_In_The_Blank(String questionText, HashMap<String, String> answers, int quizId, String answerOrder,
			String checkType, int time, int maxScore) {
		super(questionText, answers, quizId, answerOrder, checkType, time, maxScore);
	}

	@Override
	public int getTypeId() {
		return DBInfo.QUESTION_TYPE_FILL_IN_THE_BLANK;
	}

	/**
	 * returns correct answer
	 * 
	 * @return - string, the correct answer
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
