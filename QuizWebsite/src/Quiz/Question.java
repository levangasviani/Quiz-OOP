package Quiz;

import java.util.HashMap;

public class Question {

	private final String questionText;
	private final int score;
	private final String checkType;
	private final int time;
	private final int quizId;
	private final int typeId;
	private final HashMap<String, String> answers;
	private final String order;
	
	/**
	 * constructs a Question Object based on the parameters
	 * @param questionText - the statement of the question
	 * @param score - the maximal score for this question
	 * @param checkType - checking type
	 * @param time - time restriction on the question
	 * @param quizId - the id of the quiz to which this question belongs to
	 * @param typeId - what kind of question it is
	 * @param answers - answers of the question
	 * @param order - determines whether answer should be ordered or unordered
	 */
	public Question(String questionText, int score, String checkType, int time, int quizId, int typeId,
			HashMap<String, String> answers, String order) {
		this.questionText = questionText;
		this.score = score;
		this.checkType = checkType;
		this.time = time;
		this.quizId = quizId;
		this.typeId = typeId;
		this.answers = answers;
		this.order = order;
	}

	/**
	 * returns a question statement
	 * @return - question statement
	 */
	public String getQuestionText() {
		return questionText;
	}

	/**
	 *
	 * @return - time for this question
	 */
	public int getTime() {
		return time;
	}

	/**
	 *
	 * @return - maximal score for this question
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 
	 * @return - id of the quiz it belongs to
	 */
	public int getQuizId() {
		return quizId;
	}

	
	/**
	 * 
	 * @return - string of checking type
	 */
	public String getCheckType() {
		return checkType;
	}

	/**
	 * 
	 * @return - the type id of this question
	 */
	public int getTypeId() {
		return typeId;
	}

	/**
	 * 
	 * @return - possible answers of this question
	 */
	public HashMap<String, String> getAnswers() {
		HashMap<String, String> cloneAnswers = new HashMap<String, String>();
		for (String key : answers.keySet()) {
			cloneAnswers.put(key, answers.get(key));
		}
		return cloneAnswers;
	}

	
	/**
	 * 
	 * @return - information whether the correct should be ordered or not
	 */
	public String getAnswerOrder() {
		return order;
	}

	
	 /**
	 * toSring() method which shows all the instances of this object as a string
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("QUESTION:  ").append(questionText).append("\n");
		sb.append("SCORE:  ").append(score).append("\n");
		sb.append("CHECK_TYPE:  ").append(checkType).append("\n");
		sb.append("TIME:  ").append(time).append("\n");
		sb.append("QUIZ_ID:  ").append(quizId).append("\n");
		sb.append("TYPE_ID:  ").append(typeId).append("\n");
		sb.append("ANSWERS: ").append("\n");
		for (String key : answers.keySet()) {
			sb.append(key).append(" ").append(answers.get(key)).append("\n");
		}
		return sb.toString();
	}

}
