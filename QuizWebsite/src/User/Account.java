package User;


/** 
 * @author levani
 *
 *	Account class, stores information about user account;
 */
public class Account {
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private int type;
	
	/**
	 * Account constructor
	 * 
	 * @param username
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param type
	 */
	public Account(String username, String password, String firstname, String lastname, String email, int type) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.type = type;
	}

	/**
	 * Returns account user name
	 * 
	 * @return user name
	 */
	public String getUserName() {
		return username;
	}

	/**
	 * Returns account password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Returns user first name
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return firstname;
	}

	/**
	 * Returns user last name
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return lastname;
	}

	/**
	 * Returns account email
	 * 
	 * @return user email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns account type
	 * 
	 * @return account type
	 */
	public int getType() {
		return type;
	}
}
