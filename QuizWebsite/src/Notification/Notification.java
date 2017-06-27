package Notification;

import java.util.HashMap;

public interface Notification {
	public void sendNotification(String message, String sender, String receiver);

	public HashMap<String, String> getNotifications(String receiver);
}
