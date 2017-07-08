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

	// Database table names
	public static final String ACHIEVEMENT_TYPES = "ACHIEVEMENT_TYPES";
	public static final String ACHIEVEMENTS = "ACHIEVEMENTS";
	public static final String ANSWERS = "ANSWERS";
	public static final String COMPLETED_QUIZZES = "COMPLETED_QUIZES";
	public static final String CREATED_QUIZZES = "CREATED_QUIZES";
	public static final String FRIENDS = "FRIENDS";
	public static final String NOTIFICATION_COUNT = "NOTIFICATION_COUNT";
	public static final String NOTIFICATION_TYPES = "NOTIFICATION_TYPES";
	public static final String NOTIFICATIONS = "NOTIFICATIONS";
	public static final String QUESTION_TYPES = "QUESTION_TYPES";
	public static final String QUESTIONS = "QUESTIONS";
	public static final String QUIZZES = "QUIZZES";
	public static final String USER_TYPES = "USER_TYPES";
	public static final String USERS = "USERS";

	// Question check type constants
	public static final String CHECK_TYPE_USER = "USER";
	public static final String CHECK_TYPE_COMPUTER = "COMPUTER";

	// Database table enumeration constants as strings
	public static final String TRUE = "TRUE";
	public static final String FALSE = "FALSE";

	// ACHIEVEMENT_TYPES columns
	public static final int ACHIEVEMENT_TYPES_ID = 1;
	public static final int ACHIEVEMENT_TYPES_NAME = 2;

	// ACHEIEVENTS columns
	public static final int ACHIEVEMENTS_ID = 1;
	public static final int ACHIEVEMENTS_USER_ID = 2;
	public static final int ACHEIVEMENTS_TYPE_ID = 3;

	// ANSWERS columns
	public static final int ANSWERS_ID = 1;
	public static final int ANSWERS_ANSWER = 2;
	public static final int ANSWERS_TF = 3;
	public static final int ANSWERS_ORDER = 4;
	public static final int ANSWERS_QUESTION_ID = 5;

	// COMPLETED_QUIZZES columns
	public static final int COMPLETED_QUIZZES_ID = 1;
	public static final int COMPLETED_QUIZZES_USER_ID = 2;
	public static final int COMPLETED_QUIZZES_QUIZ_ID = 3;
	public static final int COMPLETED_QUIZZES_SCORE = 4;
	public static final int COMPLETED_QUIZZES_SPENT_TIME = 5;

	// CREATED_QUIZZES columns
	public static final int CREATED_QUIZZES_ID = 1;
	public static final int CREATED_QUIZZES_USER_ID = 2;
	public static final int CREATED_QUIZZES_QUIZ_ID = 3;

	// FRIENDS columns
	public static final int FRIENDS_ID = 1;
	public static final int FRIENDS_USER_ONE = 2;
	public static final int FRIENDS_USER_TWO = 3;

	// NOTIFICATION_COUNT columns
	public static final int NOTIFICATION_COUNT_ID = 1;
	public static final int NOTIFICATION_COUNT_USER_ID = 2;
	public static final int NOTIFICATION_COUNT_COUNT = 3;

	// NOTIFICATION_TYPES columns
	public static final int NOTIFICATION_TYPES_ID = 1;
	public static final int NOTIFICATION_TYPES_NAME = 2;

	// NOTIFICATIONS columns
	public static final int NOTIFICATIONS_ID = 1;
	public static final int NOTIFICATIONS_SENDER_ID = 2;
	public static final int NOTIFICATIONS_RECEIVER_ID = 3;
	public static final int NOTIFICATIONS_MESSAGE = 4;
	public static final int NOTIFICATIONS_QUIZ_ID = 5;
	public static final int NOTIFICATIONS_QUESTION_ID = 6;
	public static final int NOTIFICATIONS_TYPE_ID = 7;

	// QUESTION_TYPES columns
	public static final int QUESTION_TYPES_ID = 1;
	public static final int QUESTION_TYPES_NAME = 2;

	// QUESTIONS columns
	public static final int QUESTIONS_ID = 1;
	public static final int QUESTIONS_QUESTION = 2;
	public static final int QUESTIONS_SCORE = 3;
	public static final int QUESTIONS_CHECK_TYPE = 4;
	public static final int QUESTIONS_TIME = 5;
	public static final int QUESTIONS_QUIZ_ID = 6;
	public static final int QUESTIONS_TYPE_ID = 7;

	// QUIZZES columns
	public static final int QUIZZES_ID = 1;
	public static final int QUIZZES_NAME = 2;
	public static final int QUIZZES_DESCRIPTION = 3;
	public static final int QUIZZES_RANDOM = 4;
	public static final int QUIZZES_ONE_PAGE = 5;
	public static final int QUIZZES_PRACTICE_MODE = 6;
	public static final int QUIZZES_IMMEDIATE_GRADE = 7;
	public static final int QUIZZES_FREQUENCY = 8;

	// USER_TYPES columns
	public static final int USER_TYPES_ID = 1;
	public static final int USER_TYPES_NAME = 2;

	// USERS columns
	public static final int USERS_ID = 1;
	public static final int USERS_USERNAME = 2;
	public static final int USERS_PASSWORD = 3;
	public static final int USERS_FIRSTNAME = 4;
	public static final int USERS_LASTNAME = 5;
	public static final int USERS_EMAIL = 6;
	public static final int USERS_TYPE_ID = 7;

	// NOTIFICATION_TYPES types
	public static final int NOTIFICATION_TYPE_CHALLENGE_REQUEST = 1;
	public static final int NOTIFICATION_TYPE_FRIEND_REQUEST = 2;
	public static final int NOTIFICATION_TYPE_GRADE_REQUEST = 3;
	public static final int NOTIFICATION_TYPE_MESSAGE = 4;

	// QUESTION_TYPES types
	public static final int QUESTION_TYPE_QUESTION_RESPONSE = 1;
	public static final int QUESTION_TYPE_FILL_IN_THE_BLANK = 2;
	public static final int QUESTION_TYPE_MULTIPLE_CHOICE = 3;
	public static final int QUESTION_TYPE_PICTURE_RESPONSE = 4;
	public static final int QUESTION_TYPE_MULTI_ANSWER = 5;
	public static final int QUESTION_TYPE_MULTIPLE_CHOICE_WITH_MULTIPLE_ANSWERS = 6;
	public static final int QUESTION_TYPE_MATCHING = 7;
	public static final int QUESTION_TYPE_GRADED = 8;

	// USER_TYPES types
	public static final int USER_TYPE_USER = 1;
	public static final int USER_TYPE_ADMIN = 2;

}
