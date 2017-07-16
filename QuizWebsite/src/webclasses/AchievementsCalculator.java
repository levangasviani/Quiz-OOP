package webclasses;

import java.util.ArrayList;

/**
 * 
 * @author levanAmateur (lkara15)
 *
 */
public class AchievementsCalculator {
	
	/**
	 * Constructor
	 */
	public AchievementsCalculator() {
		
	}
	
	/**
	 * Calculates achievements of user according
	 * to given paramaters
	 * 
	 * @param numOfCreatedQuizzes
	 * @param numOfCompletedQuizzes
	 * @return
	 */
	public ArrayList<String> getAchievements(int numOfCreatedQuizzes, int numOfCompletedQuizzes) {
		ArrayList<String> result = new ArrayList<String>();
		if(numOfCompletedQuizzes >= 1) {
			result.add("Amateur Author");
		}
		if(numOfCompletedQuizzes >= 5) {
			result.add("Prolific Author");
		}
		if(numOfCompletedQuizzes >= 10) {
			result.add("Prodigious Author");
		}
		
		if(numOfCreatedQuizzes >= 10) {
			result.add("Quiz Machine");
		}
		if(numOfCreatedQuizzes >= 1) {
			result.add("New one");
		}
		return result;
	}
	
}



