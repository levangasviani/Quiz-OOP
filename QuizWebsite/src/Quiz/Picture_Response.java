package Quiz;

import java.util.ArrayList;
import java.util.HashMap;

import Database.DBInfo;

public class Picture_Response extends Question {

	public Picture_Response(String questionText, HashMap<String, String> answers, int quizId, String answerOrder,
			String checkType, int time, int maxScore) {
		super(questionText, answers, quizId, answerOrder, checkType, time, maxScore);
	}

	@Override
	public int getTypeId() {
		return DBInfo.QUESTION_TYPE_PICTURE_RESPONSE;
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

	/**
	 * returns the text part of the questionStatement
	 * 
	 * @return - text part as a string
	 */
	public String getText() {
		String text = getQuestionText();
		return text.substring(0, text.indexOf(":"));
	}

	/**
	 * returns an image url for the image file
	 * 
	 * @return - image url as a string
	 */
	public String getImageURL() {
		String text = getQuestionText();
		return text.substring(text.indexOf(":") + 1);
	}

	@Override
	public int getPoints(String chosenAnswer) {
		String answer = getCorrectAnswer();
		return LevenshteinDistance.correct(answer, chosenAnswer) ? 1 : 0;
	}
}
