<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Notification.NotificationManager"%>
<%@page import="WebSite.WebSiteInfo"%>
<%
	String username = (String) request.getSession().getAttribute("username");
	NotificationManager notificationManager = (NotificationManager) getServletContext().getAttribute(WebSiteInfo.NOTIFICATION_MANAGER_ATTR);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>QuizWebsite</title>
	<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
	<link rel="stylesheet" type="text/css" href="css/HomePage.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<h1>QuizWebsite</h1>
	<h2>
		<%
			if (username != null) {
				out.println("You are logged in as " + username + "<br>");
			} else {
				out.println("You are not  logged in<br>");
			}
		%>
	</h2>

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

	

	<div id="generalInfo">
		<div id="popularQuizzes">
			popular quizzes
			<ul>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>

		<div id="recentQuizzes">
			recently created quizzes
			<ul>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>
	</div>

	<div id="myInfo">
		<div id="myRecentTakenQuizzes">
			my recently taken quizzes
			<ul>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>

		<div id="myRecentCreatedQuizzes">
			my recently created quizzes
			<ul>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>

		<div id="friendsAchievements">
			friends' activities
			<ul>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>
	</div>

	<div id="announcements">
		<p id="announcement-title">Announcements</p>
	</div>

</body>
</html>
