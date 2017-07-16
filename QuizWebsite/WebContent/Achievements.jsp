<%@page import="webclasses.AchievementsCalculator"%>
<%@page import="Quiz.QuizStatsManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="webclasses.AchievementManager"%>
<%@page import="Notification.NotificationManager"%>
<%@page import="WebSite.WebSiteInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Achievements</title>
<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
<link rel="stylesheet" type="text/css" href="css/Achievements.css">
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
	<div id=title>Your Achievements</div>
	<div id=achievementsDivID>
		<%
			AchievementManager manager = new AchievementManager();
		
			ArrayList<String> achievements = manager.getAchievements(username);
			
			out.println("<div id = res>");
			if (achievements.size() == 0) {
				out.println("<br>Unfortunatelly you do not have achievements!");
			}

			for (int i = 0; i < achievements.size(); i++) {
				out.println("<li>");
				out.println(achievements.get(i));
				out.println("</li>");
				;
			}
			out.println("</div>");
		%>
	</div>
</body>
</html>

