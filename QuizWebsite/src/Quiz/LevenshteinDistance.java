package Quiz;

public class LevenshteinDistance {
	
	
	/* minimal percentage of correctness for the input to get accepted */
	private static final int percentageOfCorrectness=75;
	
	
	/**
	 * returns the number of matchings of two strings
	 * @param answer -  correct answer
	 * @param userInput - answer by the user
	 * @return number of matchings
	 */
	private static double LevenshteinDist(String answer, String userInput){
		answer=answer.toLowerCase();
		userInput=userInput.toLowerCase();
		double[] costs =new double[userInput.length()+1];
		for(int j=0; j<costs.length; j++){
			costs[j]=j;
		}
		for(int i=1; i<=answer.length(); i++){
			costs[0]=i;
			double nw=i-1;
			for(int j=1; j<=userInput.length(); j++){
				double cj=Math.min(1+Math.min(costs[j], costs[j-1]), answer.charAt(i-1)==userInput.charAt(j-1) ? nw : nw+1);
				nw=costs[j];
				costs[j]=cj;
			}
		}
		return costs[userInput.length()];
	}
	
	
	/**
	 * decides whether the answer should be accepted or not
	 * @param answer - the correct answer
	 * @param userInput - answer of the user
	 * @return boolean, whether the answer gets accept or not
	 */
	public static boolean correct(String answer, String userInput){
		double res=LevenshteinDist(answer, userInput);
		double percentage=(double)((answer.length()-res))*100/answer.length();
		return percentage>=percentageOfCorrectness;
	}
	
}
