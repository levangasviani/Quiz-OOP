package Quiz;

import java.util.ArrayList;
import java.util.HashMap;

import Database.DBInfo;

public class Matching extends Question {

	public Matching(String questionText, HashMap<String, String> answers, int quizId, String answerOrder,
			String checkType, int time, int maxScore) {
		super(questionText, answers, quizId, answerOrder, checkType, time, maxScore);
	}

	@Override
	public int getTypeId() {
		return DBInfo.QUESTION_TYPE_MATCHING;
	}

	/**
	 * returns parsed answers as a hashmap
	 * 
	 * @param chosenAnswer
	 *            - user's answer as a string
	 * @return - hashmap containing pairs for the matching
	 */
	private HashMap<String, String> parseAnswer(String chosenAnswer) {
		HashMap<String, String> chosenAnswers = new HashMap<String, String>();
		String[] parts = chosenAnswer.split(":");
		for (int i = 0; i < parts.length; i += 2) {
			chosenAnswers.put(parts[i], parts[i + 1]);
		}
		return chosenAnswers;
	}

	/**
	 * returns correct matching as ahashmap
	 * 
	 * @return - hashmap of pairs
	 */
	public HashMap<String, String> getPairs() {
		HashMap<String, String> answers = getAnswers();
		HashMap<String, String> pairs = new HashMap<String, String>();
		for (String s : answers.keySet()) {
			String[] parts = s.split(":");
			pairs.put(parts[0], parts[1]);
		}
		return pairs;
	}

	@Override
	public int getPoints(String chosenAnswer) {
		HashMap<String, String> chosenAnswers = parseAnswer(chosenAnswer);
		HashMap<String, String> correctAnswers = getPairs();
		int result = 0;
		for (String s : chosenAnswers.keySet()) {
			if (chosenAnswers.get(s).equals(correctAnswers.get(s)))
				result++;
		}
		return result;
	}

}
