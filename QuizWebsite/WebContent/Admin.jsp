<%@page import="Quiz.QuizStatsManager"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Notification.NotificationManager"%>
<%@page import="WebSite.WebSiteInfo"%>
<%@page import="User.AccountManager"%>
<%@page import="User.Account"%>
<%@page import="User.AdminOrders"%>
<%@page import="Quiz.QuizManager"%>
<%
	String username = (String) request.getSession().getAttribute("username");
	NotificationManager notificationManager = (NotificationManager) getServletContext()
			.getAttribute(WebSiteInfo.NOTIFICATION_MANAGER_ATTR);
%>

<%
	AccountManager acm = new AccountManager();
	Account acc = acm.getAccount(username);
	QuizManager qm = new QuizManager();
	int type = acc.getType();
	QuizStatsManager qsm = new QuizStatsManager();
	ArrayList<String> recentlyCreatedQuizzes = qsm.getRecentlyCreatedQuizzes();
	ArrayList<String> popularQuizzes = qsm.getPopularQuizzes();
	ArrayList<String> recentlyTakenQuizzes = qsm.getRecentlyTakenQuizzes(username);
	ArrayList<String> recentlyTakenQuizzesByUser = qsm.getRecentlyCreatedQuizzes(username);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QuizWebsite</title>
<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
<link rel="stylesheet" type="text/css" href="css/HomePage.css">
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

	<br>
	<textarea rows="4" cols="50" id="announcement"
		placeholder="Enter announcement text here..."></textarea>
	<button onclick='addAnnouncement()'>Add Announcement</button>
	<br>

	<br>
	<input type="text" id="removeAccount"
		placeholder="Enter username here...">
	<button onclick='removeAccount()'>Remove Account</button>
	<br>

	<br>
	<input type="text" id="removeQuiz"
		placeholder="Enter quiz name here...">
	<button onclick='removeQuiz()'>Remove Quiz</button>
	<br>

	<br>
	<input type="text" id="promote" placeholder="Enter username here...">
	<button onclick='promote()'>Promote User</button>
	<br>


	<p>
		Number of accounts:
		<%=acm.getNumberOfaccounts()%></p>
	<p>
		Number of quizzes:
		<%=qm.getNumberOfQuizzes()%></p>

	<script>
		function addAnnouncement() {
			$.post("AdminServlet", {
				text : document.getElementById("announcement").value,
				orderNum : 1
			}, function(data) {
				alert(data);
			});
		}

		function removeAccount() {
			$.post("AdminServlet", {
				username : document.getElementById("removeAccount").value,
				orderNum : 2
			}, function(data) {
				alert(data);
			});
		}

		function removeQuiz() {
			$.post("AdminServlet", {
				quizName : document.getElementById("removeQuiz").value,
				orderNum : 3
			}, function(data) {
				alert(data);
			});
		}

		function promote() {
			$.post("AdminServlet", {
				username : document.getElementById("promote").value,
				orderNum : 4
			}, function(data) {
				alert(data);
			});
		}
	</script>


</body>
</html>