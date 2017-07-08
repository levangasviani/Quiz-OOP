<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Notification.NotificationManager"%>
<%@ page import="Notification.Notification"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.io.PrintWriter"%>
<%
	String username = (String) request.getSession().getAttribute("username");
	NotificationManager manager = (NotificationManager) getServletContext().getAttribute("notificationManager");
	ArrayList<Notification> notifications = manager.getNotifications(username);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<ul style=\"float:left; width:50%; height:100%; overflow:auto\">");
		for (Notification notification : notifications)
			writer.println("<li>" + notification.toString() + "</li>");
		writer.println("</ul>");
		writer.println("<div style=\"float:right\">");
		writer.println("<form action=\"NotificationServlet\">");
		writer.println("<input type=\"hidden\" name=\"sender\" value=" + username + " />");
		writer.println("<input type=\"hidden\" name=\"type\" value=\"4\" />");
		writer.println("<input type=\"text\" name=\"receiver\" />");
		writer.println("<input type=\"submit\" value=\"Send\" /><br />");
		writer.println("<textarea rows=\"20\" cols=\"40\" name=\"message\"></textarea>");
		writer.println("</form>");
		writer.println("</div>");
	%>
</body>
</html>