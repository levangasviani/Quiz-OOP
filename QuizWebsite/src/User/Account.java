package User;


/** 
 * @author levani
 *
 *	Account class, stores information about user account;
 */
public class Account {
	
	private final String username;
	private final String password;
	private final String firstname;
	private final String lastname;
	private final String email;
	private final int type;
	
	
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
	
	
	/**
	 * Returns String representation of an account
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Username:  ").append(username).append("\n");
		sb.append("Password:  ").append(password).append("\n");
		sb.append("Firstname:  ").append(firstname).append("\n");
		sb.append("Lastname:  ").append(lastname).append("\n");
		sb.append("Email:  ").append(email).append("\n");
		sb.append("Type:  ").append(type).append("\n");
		
		return sb.toString();	
	}
}
