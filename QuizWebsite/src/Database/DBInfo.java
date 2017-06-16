package Database;


/** 
 * @author levani
 *
 * DBInfo class stores database information as constants
 */
public class DBInfo {
	
	public static final String MYSQL_USERNAME = "root";
	public static final String MYSQL_PASSWORD = "sius25";
	public static final String MYSQL_DATABASE_NAME = "QuizWebsiteDatabase";
	public static final String MYSQL_DATABASE_SERVER = "localhost:3306/" + MYSQL_DATABASE_NAME + "?autoReconnect=true&useSSL=false";

	/*Database table names */
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
	
	/*USERS columns*/
	public static final int USER_USERNAME = 2;
	public static final int USER_PASSWORD = 3;
	public static final int USER_FIRSTNAME = 4;
	public static final int USER_LASTNAME = 5;
	public static final int USER_EMAIL = 6;
	public static final int USER_TYPE_ID = 7;
	
	/*USER_TYPE columns*/
	public static final int USER_TYPE_USER = 1;
	public static final int USER_TYPE_ADMIN = 2;
}
