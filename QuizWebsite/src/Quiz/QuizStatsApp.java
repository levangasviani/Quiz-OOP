package Quiz;

public class QuizStatsApp {

	public static void main(String[] args) {
		QuizStatsManager qsm = new QuizStatsManager();
		
		qsm.addQuizCreated(1, 1);
		qsm.addQuizCompleted(1, 1, 8, 60);

	}

}
