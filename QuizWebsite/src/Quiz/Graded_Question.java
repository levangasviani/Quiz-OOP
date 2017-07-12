package Quiz;

import java.util.ArrayList;
import java.util.HashMap;

import Database.DBInfo;

public class Graded_Question extends Question{

	public Graded_Question(String questionText, HashMap<String, String> answers, int quizId, String answerOrder, String checkType, int time, int maxScore) {
		super(questionText, answers, quizId, answerOrder, checkType, time, maxScore);
	}

	@Override
	public int getTypeId() {
		return DBInfo.QUESTION_TYPE_GRADED;
	}


	@Override
	public int getPoints(String chosenAnswers) {
		
		return 0;
	}
	

}
