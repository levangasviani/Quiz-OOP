package friend;

/**
 * 
 * Public class for Storing information About friendship
 *
 */
public class Friendship {
	private String sender;
	private String receiver;
	private String message;

	/**
	 * Public Constructor
	 * 
	 * @param sender
	 * @param receiver
	 * @param message
	 */
	public Friendship(String sender, String receiver, String message) {
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
	}

	/**
	 * 
	 * @return sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * 
	 * @return receiver
	 */
	public String getReceiver() {
		return receiver;
	}

	/**
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
}
