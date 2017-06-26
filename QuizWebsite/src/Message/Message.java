package Message;

public class Message {
	private String message;
	private String sender;
	private String receiver;

	public Message(String message, String sender, String receiver) {
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public String getSender() {
		return sender;
	}

	public String getReceiver() {
		return receiver;
	}
}
