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

		Quiz firstQuiz = new Quiz("FirstQuiz", "Quiz For Testing", false, true, true, false);
		quizMan.addQuiz(firstQuiz);
		
		addQuestionResponse(firstQuiz);
		addMultipleChoice(firstQuiz);
		addFillInTheBlank(firstQuiz);
		addPictureResponse(firstQuiz);
		addMultiAnswer(firstQuiz);
		addMultipleChoiceMultipleAnswer(firstQuiz);
		addMatching(firstQuiz);
		
		ArrayList<Question> questions = quizMan.getQuestions(firstQuiz);
		for(Question q : questions) {
			System.out.println(q.toString());
		}
	}
	
	private static void addQuestionResponse(Quiz quiz) {
		QuestionManager questMan = new QuestionManager();
		
		String questionText = "What is the capital of Georgia?";
		int score = 1;
		String checkType = DBInfo.CHECK_TYPE_COMPUTER;
		int time = 0;
		int quizId = quizMan.getQuizID(quiz);
		int typeId = DBInfo.QUESTION_TYPE_QUESTION_RESPONSE;
		HashMap<String, String> answers = new HashMap<String, String>();
		answers.put("Tbilisi", TRUE);
		String order = FALSE;
		
		Question questionResponse = new Question(questionText, score, checkType, time, quizId, typeId, answers, order);
		questMan.addQuestion(questionResponse);
	}
	
	
	private static void addMultipleChoice(Quiz quiz) {
		QuestionManager questMan = new QuestionManager();
		
		String questionText = "What is the capital of Georgia?";
		int score = 1;
		String checkType = DBInfo.CHECK_TYPE_COMPUTER;
		int time = 0;
		int quizId = quizMan.getQuizID(quiz);
		int typeId = DBInfo.QUESTION_TYPE_MULTIPLE_CHOICE;
		HashMap<String, String> answers = new HashMap<String, String>();
		answers.put("Tbilisi", TRUE);
		answers.put("Kutaisi", FALSE);
		answers.put("Batumi", FALSE);
		answers.put("Rustavi", FALSE);
		String order = FALSE;
		
		Question multipleChoice = new Question(questionText, score, checkType, time, quizId, typeId, answers, order);
		questMan.addQuestion(multipleChoice);
	}
	
	private static void addFillInTheBlank(Quiz quiz) {
		QuestionManager questMan = new QuestionManager();
		
		String questionText = "The capital of Georgia is ______";
		int score = 1;
		String checkType = DBInfo.CHECK_TYPE_COMPUTER;
		int time = 0;
		int quizId = quizMan.getQuizID(quiz);
		int typeId = DBInfo.QUESTION_TYPE_FILL_IN_THE_BLANK;
		HashMap<String, String> answers = new HashMap<String, String>();
		answers.put("Tbilisi", TRUE);
		String order = FALSE;
		
		Question fillInTheBlank = new Question(questionText, score, checkType, time, quizId, typeId, answers, order);
		questMan.addQuestion(fillInTheBlank);
	}
	
	
	private static void addPictureResponse(Quiz quiz) {
		QuestionManager questMan = new QuestionManager();
		
		String questionText = "link";
		int score = 1;
		String checkType = DBInfo.CHECK_TYPE_COMPUTER;
		int time = 0;
		int quizId = quizMan.getQuizID(quiz);
		int typeId = DBInfo.QUESTION_TYPE_PICTURE_RESPONSE;
		HashMap<String, String> answers = new HashMap<String, String>();
		answers.put("Tbilisi", TRUE);
		String order = FALSE;
		
		Question pictureResponse = new Question(questionText, score, checkType, time, quizId, typeId, answers, order);
		questMan.addQuestion(pictureResponse);
	}
	
	private static void addMultiAnswer(Quiz quiz) {
		QuestionManager questMan = new QuestionManager();
		
		String questionText = "Name the four cities with most population in Georgia";
		int score = 1;
		String checkType = DBInfo.CHECK_TYPE_COMPUTER;
		int time = 0;
		int quizId = quizMan.getQuizID(quiz);
		int typeId = DBInfo.QUESTION_TYPE_MULTI_ANSWER;
		HashMap<String, String> answers = new HashMap<String, String>();
		answers.put("Tbilisi, Kutaisi, Batumi, Rustavi", TRUE);
		String order = FALSE;
		
		Question multiAnswer = new Question(questionText, score, checkType, time, quizId, typeId, answers, order);
		questMan.addQuestion(multiAnswer);
	}
	
	private static void addMultipleChoiceMultipleAnswer(Quiz quiz) {
		QuestionManager questMan = new QuestionManager();
		
		String questionText = "Mark cities by the sea";
		int score = 1;
		String checkType = DBInfo.CHECK_TYPE_COMPUTER;
		int time = 0;
		int quizId = quizMan.getQuizID(quiz);
		int typeId = DBInfo.QUESTION_TYPE_MULTIPLE_CHOICE_WITH_MULTIPLE_ANSWERS;
		HashMap<String, String> answers = new HashMap<String, String>();
		answers.put("Tbilisi", FALSE);
		answers.put("Kutaisi", FALSE);
		answers.put("Batumi", TRUE);
		answers.put("Foti", TRUE);
		String order = FALSE;
		
		Question multipleChoiceMultipleAnwers = new Question(questionText, score, checkType, time, quizId, typeId, answers, order);
		questMan.addQuestion(multipleChoiceMultipleAnwers);
	}
	
	
	private static void addMatching(Quiz quiz) {
		QuestionManager questMan = new QuestionManager();
		
		String questionText = "Match cities with regions";
		int score = 1;
		String checkType = DBInfo.CHECK_TYPE_COMPUTER;
		int time = 0;
		int quizId = quizMan.getQuizID(quiz);
		int typeId = DBInfo.QUESTION_TYPE_MATCHING;
		HashMap<String, String> answers = new HashMap<String, String>();
		answers.put("Telavi_Kakheti", TRUE);
		answers.put("Kutaisi_Imereti", TRUE);
		answers.put("Batumi_Adjara", TRUE);
		answers.put("Foti_Samegrelo", TRUE);
		String order = FALSE;
		
		Question matching = new Question(questionText, score, checkType, time, quizId, typeId, answers, order);
		questMan.addQuestion(matching);
	}
}
