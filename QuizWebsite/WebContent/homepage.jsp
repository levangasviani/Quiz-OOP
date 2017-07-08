<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Notification.NotificationManager"%>
<%
	NotificationManager manager = (NotificationManager) request.getServletContext()
			.getAttribute("notificationManager");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QuizWebsite</title>
<style>
.navigation-bar {
	background-color: blue;
	text-align: center;
}

#background {
	height: 200px;
	background-color: #00BFFF;
}

#generalSections {
	height: 170px;
	border: 1px solid red;
	font-family: arial;
	font-weight: bold;
	font-size: 20px;
	text-align: center;
	margin: 10px;
	overflow: auto;
}

#announcements {
	border: 1px solid red;
	float: left;
	overflow: scroll;
	width: 45%;
	height: 100%;
}

#generalInfo {
	width: 27%;
	float: left;
}

#announcement-title {
	font-weight: bold;
	font-size: 30px;
}

#my-inf {
	height: 170px;
	border: 1px solid red;
	font-family: arial;
	font-weight: bold;
	font-size: 20px;
	text-align: center;
	margin: 10px;
	overflow: auto;
}

#own-information {
	float: right;
	width: 27%;
}

#bar-item {
	font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
	font-size: 24px;
	color: white;
	font-weight: bold;
	margin: 10px;
}
</style>
</head>
<body>
	<div id="background">
		<a href="index.html">log out</a>
		<form action="servleti" method="get">
			<input type="text"> <input type="submit" value="Search">
		</form>
	</div>
	<div class="navigation-bar">
		<a id="bar-item">Home</a> <a id="bar-item">Profile</a> <a
			id="bar-item">Achievements</a> <a id="bar-item"
			href="notifications.jsp">Notifications <%=manager.getNotificationCount((String) request.getSession().getAttribute("username"))%></a>
		<a id="bar-item">Create Quiz</a>
	</div>
	<div class="sections">
		<div id="generalInfo">
			<div id="generalSections">
				popular quizzes
				<ul>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>

			<div id="generalSections">
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

		<div id="announcements">
			<p id="announcement-title">Announcement</p>
			<p>djahsjkahsdkasdhad</p>
		</div>

		<div id="own-information">
			<div id="my-inf">
				my recently taken quizzes
				<ul>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>

			<div id="my-inf">
				my recently created quizzes
				<ul>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>

			<div id="my-inf">
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
	</div>
</body>
</html>