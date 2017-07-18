<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="friend.FriendManager"%>
<%@page import="Notification.NotificationManager"%>
<%@page import="User.AccountManager"%>
<%@page import="User.Account"%>
<%@page import="WebSite.WebSiteInfo"%>
<%@page import="Quiz.QuizStatsManager" %>
<%
	String username1 = (String) request.getSession().getAttribute("username");
	String username2 = request.getParameter("username");
	QuizStatsManager qsm=(QuizStatsManager)this.getServletContext().getAttribute(WebSiteInfo.QUIZ_STATS_MANAGER);
	FriendManager friendManager = (FriendManager) getServletContext()
			.getAttribute(WebSiteInfo.FRIEND_MANAGER_ATTR);
	NotificationManager notificationManager = (NotificationManager) getServletContext()
			.getAttribute(WebSiteInfo.NOTIFICATION_MANAGER_ATTR);
	AccountManager accountManager = (AccountManager) getServletContext()
			.getAttribute(WebSiteInfo.ACCOUNT_MANAGER_ATTR);
	if (username2 == null || !accountManager.containsAccount(username2))
		username2 = "la";
	if (username1 == null)
		username1 = "la";
	Account account = accountManager.getAccount(username2);
	ArrayList<String> friends = friendManager.getAllFriends(username2);
	ArrayList<String> recentlyCompleted=qsm.getRecentlyTakenQuizzes(username2);
	ArrayList<String> recentlyCreated=qsm.getRecentlyCreatedQuizzes(username2);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=account.getFirstName()%> <%=account.getLastName()%></title>
<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
<link rel="stylesheet" type="text/css" href="css/Profile.css">
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
	<div id="info">
	<div style="margin: 15px;">
		<%
			AccountManager am = new AccountManager();
			Account acc = am.getAccount(username2);
			out.println("<h1>Profile Information</h1>");
			out.println("Username: " + acc.getUserName() + "<br>");
			out.println("First Name :" + acc.getFirstName() + "<br>");
			out.println("Last name: " + acc.getLastName() + "<br>");
			
			if (!friendManager.areFriends(username1, username2) && !username1.equals(username2)) {
				if (friendManager.requestReceived(username1, username2)) {
		%>
		<form action="NotificationServlet">
			<input type="hidden" name="sender" value="<%=username1%>" /> <input
				type="hidden" name="receiver" value="<%=username2%>" /> <input
				type="hidden" name="type" value="2" /> <input type="hidden"
				name="message" id="message" value="" /> <input id="acceptID"
				type="submit" class="accept" value="Accept" /> <input id="rejectID"
				type="submit" class="reject" value="Reject" />
		</form>
		<%
			} else if (!friendManager.requestReceived(username2, username1)) {
		%>
		<form action="NotificationServlet">
			<input type="hidden" name="sender" value="<%=username1%>" /> <input
				type="hidden" name="receiver" value="<%=username2%>" /> <input
				type="hidden" name="type" value="2" /> <input type="hidden"
				name="message" value="SENT" /> <input id="addFriendID" type="submit"
				value="Add Friend" />
		</form>
		<%
			}
			}
		%>
			
	</div>

	<div style="margin: 15px;">
		<%
			out.println("<h1>Friends<h1>");
			for (String f : friends) {

				String urlPatternForUser = "profile.jsp?username=" + f;
				String urlForUser = "<a href=" + urlPatternForUser + " >" + f + "</a>";
				out.print("<li> " + urlForUser + "</li>");
			}
		%>

	</div>
	
	<div style="margin: 15px;">
	<% 
		out.println("<h1>Recently Created Quizzes<h1>");
		for(String s: recentlyCreated){
			String urlPattern="StartQuiz.jsp?quizName="+s;
			String urlForCreated="<a href="+urlPattern+" >"+s+"</a>";
			out.print("<li> "+urlForCreated+" </li>");
		}
	%>
	</div>
	
	
	
	<div style="margin: 15px;">
	<% 
		out.println("<h1>Recently Completed Quizzes<h1>");
		for(String s: recentlyCompleted){
			String urlPattern="StartQuiz.jsp?quizName="+s;
			String urlForCreated="<a href="+urlPattern+" >"+s+"</a>";
			out.print("<li> "+urlForCreated+" </li>");
		}
	%>
	</div>
	</div>
	

	<script>
		function accept() {
			document.getElementById("message").value = "ACCEPTED";
		}
		function reject() {
			document.getElementById("message").value = "REJECTED";
		}
		document.querySelector(".accept").addEventListener("click", accept);
		document.querySelector(".reject").addEventListener("click", reject);
	</script>

</body>
</html>