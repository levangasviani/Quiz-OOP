package Notification;

public class Notification {
	private String sender; // username of sender
	private String receiver; // username of receiver
	private String content; // message or quiz name or question id(as String)
	private int type;

	
	/**
	 * Public constructor stores information for notifications
	 * 
	 * @param sender
	 * @param receiver
	 * @param content
	 * @param type
	 */
	public Notification(String sender, String receiver, String content, int type) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.type = type;
	}

	
	/**
	 * Returns sender user name
	 * 
	 * @return sender user name
	 */
	public String getSender() {
		return sender;
	}

	
	/**
	 * Returns receiver user name
	 * 
	 * @return receiver user name
	 */
	public String getReceiver() {
		return receiver;
	}

	
	/**
	 * Returns notification content
	 * 
	 * @return notification content
	 */
	public String getContent() {
		return content;
	}

	
	/**
	 * Returns type
	 * 
	 * @return type
	 */
	public int getType() {
		return type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "" + sender + " " + receiver + " " + content + " " + type;
	}
}
