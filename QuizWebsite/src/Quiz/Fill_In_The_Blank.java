package Quiz;

import java.util.ArrayList;
import java.util.HashMap;

import Database.DBInfo;

public class Fill_In_The_Blank extends Question {
	
	
	public Fill_In_The_Blank(String questionText, HashMap<String, String> answers, int quizId, String answerOrder, String checkType, int time, int maxScore) {
		super(questionText, answers, quizId, answerOrder, checkType, time, maxScore);
	}

	@Override
	public int getTypeId() {
		return DBInfo.QUESTION_TYPE_FILL_IN_THE_BLANK;
	}


	@Override
	public int getPoints(ArrayList<String> chosenAnswers) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
