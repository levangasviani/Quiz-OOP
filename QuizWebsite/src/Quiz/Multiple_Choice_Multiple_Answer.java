package Quiz;

import java.util.HashMap;

import Database.DBInfo;

public class Multiple_Choice_Multiple_Answer extends Question {

	public Multiple_Choice_Multiple_Answer(String questionText, HashMap<String, String> answers, int quizId,
			String answerOrder, String checkType, int time, int maxScore) {
		super(questionText, answers, quizId, answerOrder, checkType, time, maxScore);
	}

	@Override
	public int getTypeId() {
		return DBInfo.QUESTION_TYPE_MULTIPLE_CHOICE_WITH_MULTIPLE_ANSWERS;
	}

	/**
	 * parses the answer into an array
	 * 
	 * @param chosenAnswer
	 *            - user's answer
	 * @return - parsed answer into the array
	 */
	private String[] parseAnswer(String chosenAnswer) {
		return chosenAnswer.split(":");
	}

	@Override
	public int getPoints(String chosenAnswer) {
		if (chosenAnswer.equals(""))
			return 0;
		HashMap<String, String> answers = getAnswers();
		String[] chosenAnswers = parseAnswer(chosenAnswer);
		int result = 0;
		for (int i = 0; i < chosenAnswers.length; i++) {
			if (!answers.get(chosenAnswers[i]).equals("TRUE")) {
				return 0;
			} else
				result++;
		}
		return result;
	}
}
