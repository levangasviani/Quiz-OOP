package Quiz;

/** 
 * @author levanAmateur(lkara15)
 *
 *	Quiz class, stores information about quiz;
 */
public class Quiz {
	
	
	private String quizName;
	private String description;
	private boolean random;
	private boolean practiceMode;

	/**
	 * Constructor
	 * 
	 * @param quizName
	 * @param description
	 * @param isRandom
	 * @param canPracticeMode
	 */
	public Quiz(String quizName, String description, boolean random, boolean practiceMode) {
		this.quizName = quizName;
		this.description = description;
		this.random = random;
		this.practiceMode = practiceMode;
		
	}
	
	/**
	 * Copy Constructor
	 * 
	 * @param quiz
	 */
	public Quiz(Quiz quiz) {
		this.quizName = quiz.getName();
		this.description = quiz.getDescription();
		this.random = quiz.isRandom();
		this.practiceMode = quiz.canPracticeMode();
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
		return random;
	}
	
	
	/**
	 * Returns true if user can
	 * write this quiz in practice mode.
	 * 
	 * @return boolean canPracticeMode;
	 */
	public boolean canPracticeMode() {
		return practiceMode;
	}
	
	@Override
	public boolean equals(Object obj) {
		Quiz quiz = (Quiz) obj;
		if(this.quizName.equals(quiz.getName()) &&
				this.description.equals(quiz.getDescription()) &&
				this.random == quiz.isRandom() &&
				this.practiceMode == quiz.canPracticeMode()) return true;
		return false;
	}
	
	/**
	 * toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Quiz:  ").append(quizName).append("\n");
		sb.append("Description:  ").append(description).append("\n");
		sb.append("Random Order:  ").append(random).append("\n");
		sb.append("Parctice mode:  ").append(practiceMode).append("\n");
		return sb.toString();
	}

}