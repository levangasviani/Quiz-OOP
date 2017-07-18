<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="Notification.NotificationManager"%>
<%@page import="WebSite.WebSiteInfo"%>
<%
	String username = (String) request.getSession().getAttribute("username");
	NotificationManager notificationManager = (NotificationManager) getServletContext()
			.getAttribute(WebSiteInfo.NOTIFICATION_MANAGER_ATTR);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create A Quiz</title>
<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
<link rel="stylesheet" type="text/css" href="css/CreateQuiz.css">
<link rel="stylesheet" type="text/css"
	href="css/quizCreationProcess2.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<script>
		$(document).ready(function() {
			$('#header').load('header.jsp');
		});
	</script>

	<div id="header"></div>

	<div class=quizCreation id=quizCreationID>
		<form id=mainForm action="AddQuestions.jsp">
			Name of a quiz: <br> <input id=mainForm1 type="text"
				name="quizName"> <br> Description: <br>
			<textarea id=mainForm2 rows="6" cols="50" name="description"></textarea>
			<br> Practice Mode: <input type="radio" name="practiceMode"
				value="off" checked="checked"> Off <input type="radio"
				name="practiceMode" value="on"> On <br> Order of
			questions: <input type="radio" name="orderOfQuestions"
				value="defaultOrder" checked="checked">default <input
				type="radio" name="orderOfQuestions" value="randomOrder">random
			<br>


			<button id=start type="submit" value="submit">Start</button>
		</form>
	</div>
</body>
</html>