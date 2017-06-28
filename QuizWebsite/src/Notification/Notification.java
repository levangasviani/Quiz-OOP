package Notification;

public class Notification {
	private String sender; // username of sender
	private String receiver; // username of receiver
	private String content; // message or quiz name or question id(as String)
	private int type;

	public Notification(String sender, String receiver, String content, int type) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.type = type;
	}

	public String getSender() {
		return sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public String getContent() {
		return content;
	}

	public int getType() {
		return type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "" + sender + " " + receiver + " " + content + " " + type;
	}
}
