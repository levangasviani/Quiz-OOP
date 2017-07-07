package Quiz;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Question {
	
	
	private final String questionText;
	private final HashMap<String, String> answers;
	private final int quizId;
	private int time;
	private String checkType;
	private String answerOrder;
	private int maxScore;
	private int id;
	
	
	public Question(String questionText, HashMap<String, String> answers, int quizId, String answerOrder, String checkType, int time, int maxScore){
		this.questionText=questionText;
		this.answers=answers;
		this.quizId=quizId;
		this.answerOrder=answerOrder;
		this.checkType=checkType;
		this.time=time;
		this.maxScore=maxScore;
	}
	
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getId(){
		return id;
	}
	
	
	public int getMaxScore(){
		return maxScore;
	}
	
	
	
	
	public abstract int getPoints(ArrayList<String> chosenAnswers);
	
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
	public abstract int getTypeId();

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
		return answerOrder;
	}
	
}

	
