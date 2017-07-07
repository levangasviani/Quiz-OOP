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

	
	@Override
	public int getPoints(ArrayList<String> chosenAnswers) {
		// TODO Auto-generated method stub
		return 0;
	}

}
