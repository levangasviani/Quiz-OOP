<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Notification.NotificationManager"%>
<%@ page import="Notification.Notification"%>
<%@ page import="java.util.ArrayList"%>
<%
	String username = (String) request.getSession().getAttribute("username");
	NotificationManager manager = (NotificationManager) getServletContext().getAttribute("notificationManager");
	ArrayList<Notification> notifications = manager.getNotifications(username);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notifications</title>
</head>
<body>
	<ul style="float:left; width:50%; height:100%; overflow:auto">
	<%
	for(Notification notification : notifications){
		if(notification.getType()==1){
	%>
			
		<%
		} else if(notification.getType()==2){
		%>
			<li><a href="profile.jsp?username=<%=notification.getSender() %>"><%=notification.toString() %></a></li>
		<%
		} else if(notification.getType()==3){
		%>
			
		<%
		} else if(notification.getType()==4){
		%>
			<li><%=notification.toString() %></li>
		<%
		}
		%>
	<%
	}
	%>
	</ul>
	<div style="float:right">
	<form action="NotificationServlet">
	<input type="hidden" name="sender" value="<%=username %>" />
	<input type="hidden" name="type" value="4" />
	<input type="text" name="receiver" />
	<input type="submit" value="Send" /><br />
	<textarea rows="20" cols="40" name="message"></textarea>
	</form>
	</div>
</body>
</html>