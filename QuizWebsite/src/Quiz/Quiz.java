package Quiz;

/** 
 * @author levanAmateur(lkara15)
 *
 *	Quiz class, stores information about quiz;
 */
public class Quiz {
	
	private String quizName;
	
	/**
	 * Constructor
	 * 
	 * @param quizName
	 */
	public Quiz(String quizName) {
		this.quizName = quizName;
	}
	
	/**
	 * Returns name of a quiz.
	 * 
	 * @return quizName;
	 */
	public String getName() {
		return quizName;
	}
}
