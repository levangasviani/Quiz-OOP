package Quiz;

import java.util.ArrayList;
import java.util.HashMap;

import Database.DBInfo;

public class Picture_Response extends Question {

	public Picture_Response(String questionText, HashMap<String, String> answers, int quizId, String answerOrder, String checkType, int time, int maxScore) {
		super(questionText, answers, quizId, answerOrder, checkType, time, maxScore);
	}
	
	@Override
	public int getTypeId() {
		return DBInfo.QUESTION_TYPE_PICTURE_RESPONSE;
	}

	
	@Override
	public int getPoints(ArrayList<String> chosenAnswers) {
		// TODO Auto-generated method stub
		return 0;
	}
}
