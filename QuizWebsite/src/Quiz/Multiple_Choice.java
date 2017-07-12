package Quiz;

import java.util.ArrayList;
import java.util.HashMap;

import Database.DBInfo;

public class Multiple_Choice extends Question{

	public Multiple_Choice(String questionText, HashMap<String, String> answers, int quizId, String answerOrder, String checkType, int time, int maxScore) {
		super(questionText, answers, quizId, answerOrder, checkType, time, maxScore);
	}
	
	@Override
	public int getTypeId() {
		return DBInfo.QUESTION_TYPE_MULTIPLE_CHOICE;
	}
	
	
	
	/**
	 * returns the correct answer
	 * @return - string, correct answer
	 */
	private String getCorrectAnswer(){
		HashMap<String, String> answers=getAnswers();
		for(String s : answers.keySet()){
			if(answers.get(s).equals("TRUE")){
				return s;
			}
		}
		return "";
	}

	
	@Override
	public int getPoints(String chosenAnswer) {
		String correctAns=getCorrectAnswer();
		return correctAns.equals(chosenAnswer) ? 1 : 0;
	}

}
