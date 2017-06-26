package Database;

/**
 * @author levani
 *
 *         DBInfo class stores database information as constants
 */
public class DBInfo {

	public static final String MYSQL_USERNAME = "root";
	public static final String MYSQL_PASSWORD = "";
	public static final String MYSQL_DATABASE_NAME = "QuizWebsiteDatabase";
	public static final String MYSQL_DATABASE_SERVER = "localhost:3306/" + MYSQL_DATABASE_NAME
			+ "?autoReconnect=true&useSSL=false";

	/* Database table names */
	public static final String ACHIEVEMENTS = "ACHIEVEMENTS";
	public static final String ACHIEVEMENT_TYPES = "ACHIEVEMENT_TYPES";
	public static final String ANSWERS = "ANSWERS";
	public static final String COMPLETED_QUIZES = "COMPLETED_QUIZES";
	public static final String CREATED_QUIZES = "CREATED_QUIZES";
	public static final String FRIENDS = "FRIENDS";
	public static final String QUESTIONS = "QUESTIONS";
	public static final String QUESTION_TYPES = "QUESTION_TYPES";
	public static final String QUIZ_NAMES = "QUIZ_NAMES";
	public static final String USERS = "USERS";
	public static final String USER_TYPES = "USER_TYPES";
	public static final String MESSAGES = "MESSAGES";

	/* USERS columns */
	public static final int USER_ID = 1;
	public static final int USER_USERNAME = 2;
	public static final int USER_PASSWORD = 3;
	public static final int USER_FIRSTNAME = 4;
	public static final int USER_LASTNAME = 5;
	public static final int USER_EMAIL = 6;
	public static final int USER_TYPE_ID = 7;

	/* USER_TYPE columns */
	public static final int USER_TYPE_USER = 1;
	public static final int USER_TYPE_ADMIN = 2;

	/* QUESTIONS columns */
	public static final int QUESTION_ID = 1;
	public static final int QUESTION = 2;
	public static final int SCORE = 3;
	public static final int CHECK_TYPE = 4;
	public static final int TIME = 5;
	public static final int QUIZ_ID = 6;
	public static final int TYPE_ID = 7;

	/* ANSWERS columns */
	public static final int ANSWER = 2;
	public static final int TF = 3;
	public static final int ORDER = 4;

	/* QUIZ_NAMES columns */
	public static final int QUIZ_NAMES_ID = 1;
	public static final int QUIZ_NAMES_QUIZ_NAME = 2;

	/* MESSAGES columns */
	public static final int MESSAGES_MESSAGE = 1;
	public static final int MESSAGES_SENDER = 2;
	public static final int MESSAGES_RECEIVER = 3;

}
