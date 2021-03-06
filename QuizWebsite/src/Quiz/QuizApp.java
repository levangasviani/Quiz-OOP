package Quiz;


import java.util.ArrayList;
import java.util.HashMap;

import Database.DBInfo;

public class QuizApp {

	public static final String TRUE = "TRUE";
	public static final String FALSE = "FALSE";
	private static QuizManager quizMan;
	
	public static void main(String[] args) {
		
		
		quizMan = new QuizManager();

		Quiz firstQuiz = new Quiz("FirstQuiz", "Quiz For Testing", false, true);
		quizMan.addQuiz(firstQuiz);
		
		addQuestionResponse(firstQuiz);
		addMultipleChoice(firstQuiz);
		addFillInTheBlank(firstQuiz);
		addPictureResponse(firstQuiz);
		addMultiAnswer(firstQuiz);
		addMultipleChoiceMultipleAnswer(firstQuiz);
		addMatching(firstQuiz);
		addGraded(firstQuiz);
		
		ArrayList<Question> questions = quizMan.getQuestions(firstQuiz);
		for(Question q : questions) {
			System.out.println(q.toString());
			
		}
		System.out.println(quizMan.getNumberOfQuizzes());
		//quizMan.deleteQuiz(firstQuiz.getName());
		
		
	}
	

	// QuestionResponse
	private static void addQuestionResponse(Quiz quiz) {
		QuestionManager questMan = new QuestionManager();
		
		String questionText = "What is the capital of Georgia?";
		int score = 1;
		String checkType = DBInfo.CHECK_TYPE_COMPUTER;
		int time = 0;
		int quizId = quizMan.getQuizID(quiz);
		HashMap<String, String> answers = new HashMap<String, String>();
		answers.put("Tbilisi", TRUE);
		String order = FALSE;
		
		Question questionResponse = new Question_Response(questionText,  answers,  quizId,  order,  checkType,  time,  score);
		questMan.addQuestion(questionResponse);
	}
	
	
	// MultipleChoice
	private static void addMultipleChoice(Quiz quiz) {
		QuestionManager questMan = new QuestionManager();
		
		String questionText = "What is the capital of Georgia?";
		int score = 1;
		String checkType = DBInfo.CHECK_TYPE_COMPUTER;
		int time = 0;
		int quizId = quizMan.getQuizID(quiz);
		HashMap<String, String> answers = new HashMap<String, String>();
		answers.put("Tbilisi", TRUE);
		answers.put("Kutaisi", FALSE);
		answers.put("Batumi", FALSE);
		answers.put("Rustavi", FALSE);
		String order = FALSE;
		
		Question multipleChoice = new Multiple_Choice(questionText,  answers,  quizId,  order,  checkType,  time,  score);
		questMan.addQuestion(multipleChoice);
	}
	
	
	// FillInTheBlank
	private static void addFillInTheBlank(Quiz quiz) {
		QuestionManager questMan = new QuestionManager();
		
		String questionText = "The capital of Georgia is ______";
		int score = 1;
		String checkType = DBInfo.CHECK_TYPE_COMPUTER;
		int time = 0;
		int quizId = quizMan.getQuizID(quiz);
		HashMap<String, String> answers = new HashMap<String, String>();
		answers.put("Tbilisi", TRUE);
		String order = FALSE;
		
		Question fillInTheBlank = new Fill_In_The_Blank(questionText,  answers,  quizId,  order,  checkType,  time,  score);
		questMan.addQuestion(fillInTheBlank);
	}
	
	// PictureResponse
	private static void addPictureResponse(Quiz quiz) {
		QuestionManager questMan = new QuestionManager();
		
		String questionText = "What is the name of this city?";
		int score = 1;
		String checkType = DBInfo.CHECK_TYPE_COMPUTER;
		int time = 0;
		int quizId = quizMan.getQuizID(quiz);
		HashMap<String, String> answers = new HashMap<String, String>();
		answers.put("Tbilisi", TRUE);
		String order = FALSE;
		
		Question pictureResponse = new Picture_Response(questionText,  answers,  quizId,  order,  checkType,  time,  score);
		questMan.addQuestion(pictureResponse);
	}
	
	
	// MultiAnswer
	private static void addMultiAnswer(Quiz quiz) {
		QuestionManager questMan = new QuestionManager();
		
		String questionText = "Name the four cities with most population in Georgia";
		int score = 1;
		String checkType = DBInfo.CHECK_TYPE_COMPUTER;
		int time = 0;
		int quizId = quizMan.getQuizID(quiz);
		HashMap<String, String> answers = new HashMap<String, String>();
		answers.put("Tbilisi:Kutaisi:Batumi:Rustavi", TRUE);
		String order = FALSE;
		
		Question multiAnswer = new Multi_Answer(questionText,  answers,  quizId,  order,  checkType,  time,  score);
		questMan.addQuestion(multiAnswer);
	}
	
	
	// MultipleChoiceMultipleAnswer
	private static void addMultipleChoiceMultipleAnswer(Quiz quiz) {
		QuestionManager questMan = new QuestionManager();
		
		String questionText = "Mark cities by the sea";
		int score = 1;
		String checkType = DBInfo.CHECK_TYPE_COMPUTER;
		int time = 0;
		int quizId = quizMan.getQuizID(quiz);
		HashMap<String, String> answers = new HashMap<String, String>();
		answers.put("Tbilisi", FALSE);
		answers.put("Kutaisi", FALSE);
		answers.put("Batumi", TRUE);
		answers.put("Foti", TRUE);
		String order = FALSE;
		
		Question multipleChoiceMultipleAnwers = new Multiple_Choice_Multiple_Answer(questionText,  answers,  quizId,  order,  checkType,  time,  score);
		questMan.addQuestion(multipleChoiceMultipleAnwers);
	}
	
	
	// Matching
	private static void addMatching(Quiz quiz) {
		QuestionManager questMan = new QuestionManager();
		
		String questionText = "Match cities with regions";
		int score = 1;
		String checkType = DBInfo.CHECK_TYPE_COMPUTER;
		int time = 0;
		int quizId = quizMan.getQuizID(quiz);
		HashMap<String, String> answers = new HashMap<String, String>();
		answers.put("Telavi:Kakheti", TRUE);
		answers.put("Kutaisi:Imereti", TRUE);
		answers.put("Batumi:Adjara", TRUE);
		answers.put("Foti:Samegrelo", TRUE);
		String order = FALSE;
		
		Question matching = new Matching(questionText,  answers,  quizId,  order,  checkType,  time,  score);
		questMan.addQuestion(matching);
	}
	
	// QuestionResponse
	private static void addGraded(Quiz quiz) {
		QuestionManager questMan = new QuestionManager();
		
		String questionText = "What is the capital of Georgia? (Checked by creator)";
		int score = 1;
		String checkType = DBInfo.CHECK_TYPE_USER;
		int time = 0;
		int quizId = quizMan.getQuizID(quiz);
		HashMap<String, String> answers = new HashMap<String, String>();
		String order = FALSE;
		
		Question graded = new Question_Response(questionText,  answers,  quizId,  order,  checkType,  time,  score);
		questMan.addQuestion(graded);
	}
	
}
