<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Notification.NotificationManager"%>
<%@ page import="Notification.Notification"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="WebSite.WebSiteInfo"%>
<%
	String username = (String) request.getSession().getAttribute("username");
	NotificationManager notificationManager = (NotificationManager) getServletContext().getAttribute(WebSiteInfo.NOTIFICATION_MANAGER_ATTR);
	ArrayList<Notification> notifications = notificationManager.getNotifications(username);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Notifications</title>
	<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
	<link rel="stylesheet" type="text/css" href="css/Achievements.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<h1>Notifications</h1>
	<div class="navigation" id = "navigationID">
		<a class="active" id="home" href = "homepage.jsp" ><i class="fa fa-home"></i> Home</a> 	  
		<a class="active" id="profile" href = "profile.jsp?username=<%=username %>"><i class="fa fa-user"></i> Profile</a>	    
		<a class="active" id="achievements" href = "Achievements.jsp"><i class="fa fa-trophy"></i> Achievements</a>    
		<a class="active" id="messages" href = "notifications.jsp" ><i class="fa fa-envelope"></i> Notifications <%=notificationManager.getNotificationCount(username) %></a>
		<a class="active" id="creatQuiz" href = "CreateQuiz.jsp"><i class="fa fa-plus"></i> Create Quiz</a>	   	   
		<a id="logout" href = "index.html">Logout</a>
		<div class = "search" id = searchID>
			<div class = "search" id = searchID>
				<form action = "SearchPage.jsp">
					<input type = "text" name = "search" placeholder = "enter value here...">
					<i class="fa fa-search" aria-hidden="true"></i>
					<button type="submit" value="searchValue">search</button>
				</form>
			</div>
		</div>
	</div>
	<ul style="float:left; width:50%; height:100%; overflow:auto">
		<%
		for(Notification notification : notifications){
			if(notification.getType()==1){
		%>
				
			<%
			} else if(notification.getType()==2){
			%>
				<li><a href="profile.jsp?username=<%=notification.getSender() %>"><%=notification.toString() %> Friendship</a></li>
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