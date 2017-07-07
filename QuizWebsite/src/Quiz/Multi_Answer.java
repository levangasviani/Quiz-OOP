package Quiz;

import java.util.ArrayList;
import java.util.HashMap;

import Database.DBInfo;

public class Multi_Answer extends Question{

	public Multi_Answer(String questionText, HashMap<String, String> answers, int quizId, String answerOrder, String checkType, int time, int maxScore) {
		super(questionText, answers, quizId, answerOrder, checkType, time, maxScore);
	}
	
	@Override
	public int getTypeId() {
		return DBInfo.QUESTION_TYPE_MULTI_ANSWER;
	}


	@Override
	public int getPoints(ArrayList<String> chosenAnswers) {
		// TODO Auto-generated method stub
		return 0;
	}



}
