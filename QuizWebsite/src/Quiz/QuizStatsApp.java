package Quiz;

import java.util.ArrayList;

public class QuizStatsApp {

	public static void main(String[] args) {
		QuizStatsManager qsm = new QuizStatsManager();
		
		qsm.addQuizCreated("levangasviani", "FirstQuiz");
		qsm.addQuizCompleted("levangasviani", "FirstQuiz", 8, 60);
		
		ArrayList<String> a = qsm.getRecentlyCreatedQuizzes("SIUS25");
			
		for(String s : a) System.out.println(s);
	}

}
