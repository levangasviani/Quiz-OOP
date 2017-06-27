package Quiz;

/** 
 * @author levanAmateur(lkara15)
 *
 *	Quiz class, stores information about quiz;
 */
public class Quiz {
	
	
	private String quizName;
	private String description;
	private boolean isRandom;
	private boolean isOnePage;
	private boolean canPracticeMode;
	private boolean immediateCorrection;
	
	/**
	 * Constructor
	 * 
	 * @param quizName
	 */
	public Quiz(String quizName, String description, boolean isRandom, boolean isOnePage,
		boolean canPracticeMode, boolean immediateCorrection) {
		this.quizName = quizName;
		this.description = description;
		this.isRandom = isRandom;
		this.isOnePage = isOnePage;
		this.canPracticeMode = canPracticeMode;
		this.immediateCorrection = immediateCorrection;
	}
	
	/**
	 * Returns name of a quiz.
	 * 
	 * @return String quizName;
	 */
	public String getName() {
		return quizName;
	}
	
	/**
	 * Returns description of a quiz.
	 * 
	 * @return String description;
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Returns true if Quiz questions ordered randomly.
	 * 
	 * @return boolean isRandom;
	 */
	public boolean isRandom() {
		return isRandom;
	}
	
	/**
	 * Returns true if Quiz questions should be on one page.
	 * 
	 * @return boolean isOnePage;
	 */
	public boolean isOnePage() {
		return isOnePage;
	}
	
	/**
	 * Returns true if user can
	 * write this quiz in practice mode.
	 * 
	 * @return boolean canPracticeMode;
	 */
	public boolean canPracticeMode() {
		return canPracticeMode;
	}
	
	/**
	 * Returns true if quiz questions should
	 * be corrected immeadiately.
	 * 
	 * @return boolean immediateCorrection;
	 */
	public boolean immediateCorrection() {
		return immediateCorrection;
	}

}