package Quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import Database.DBInfo;

public class Multi_Answer extends Question{

	public Multi_Answer(String questionText, HashMap<String, String> answers, int quizId, String answerOrder, String checkType, int time, int maxScore) {
		super(questionText, answers, quizId, answerOrder, checkType, time, maxScore);
	}
	
	@Override
	public int getTypeId() {
		return DBInfo.QUESTION_TYPE_MULTI_ANSWER;
	}
	
	
	
	/**
	 * returns number of points in The case of Ordered answers
	 * @param chosenAnswer - user answer
	 * @return - number of points got by the user
	 */
	private int checkForOrdered(String chosenAnswer){
		String[] chosenanswers=chosenAnswer.split(":");
		HashMap<String, String> answers=getAnswers();
		String correct="";
		for(String s : answers.keySet()){
			correct=s;
		}
		String[] correctOrder=correct.split(":");
		int result=0;
		for(int i=0; i<chosenanswers.length; i++){
			if(chosenanswers[i].equals(correctOrder[i]))result++;
		}
		return result;
	}
	
	
	/**
	 * returns number of points in the case of unordered answers
	 * @param chosenAnswer - user answer
	 * @return - number of points got by the user
	 */
	private int checkForUnordered(String chosenAnswer){
		HashMap<String, String> answers=getAnswers();
		String correct="";
		for(String s : answers.keySet()){
			correct=s;
		}
		int result=0;
		String[] correctAns=correct.split(":");
		HashSet<String> set=new HashSet<String>();
		String[] chosenanswers=chosenAnswer.split(":");
		for(int i=0; i<chosenanswers.length; i++){
			set.add(chosenanswers[i]);
		}
		for(int i=0; i<correctAns.length; i++){
			if(set.contains(correctAns[i])){
				result++;
			}
		}
		return result;
	}


	@Override
	public int getPoints(String chosenAnswer) {
		boolean order=getAnswerOrder().equals("TRUE") ? true : false;
		if(order){
			return checkForOrdered(chosenAnswer);
		}
		else{
			return checkForUnordered(chosenAnswer);
		}
	}

}
