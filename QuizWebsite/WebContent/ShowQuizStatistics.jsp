<%@page import="java.util.HashSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="webclasses.AchievementManager"%>
<%@page import="webclasses.AchievementsCalculator"%>
<%@page import="Quiz.QuizManager"%>
<%@page import="User.AccountManager"%>
<%@page import="Database.DBInfo"%>
<%@page import="Notification.NotificationManager"%>
<%@page import="WebSite.WebSiteInfo"%>
<%@page import="Quiz.QuizStatsManager"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String practiceMode = request.getParameter("practiceMode");
	String username = (String) request.getSession().getAttribute("username");
	NotificationManager notificationManager = (NotificationManager) getServletContext()
			.getAttribute(WebSiteInfo.NOTIFICATION_MANAGER_ATTR);
	String quizName = request.getParameter("quizName");
	int resultScore = Integer.parseInt(request.getParameter("score"));
	int resultTime = Integer.parseInt(request.getParameter("elapsedTime"));
	QuizStatsManager qsm = (QuizStatsManager) this.getServletContext()
			.getAttribute(WebSiteInfo.QUIZ_STATS_MANAGER);
	AccountManager accMan = (AccountManager) this.getServletContext()
			.getAttribute(WebSiteInfo.ACCOUNT_MANAGER_ATTR);
	if (!practiceMode.equals("on"))
		qsm.addQuizCompleted(username, quizName, resultScore, resultTime);
	ResultSet rs = qsm.getBestResultOfDefaultSize(quizName);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>quizName</title>
<head>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QuizWebsite</title>
<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
<link rel="stylesheet" type="text/css" href="css/HomePage.css">
<link rel="stylesheet" type="text/css" href="css/TableStat.css">
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

	<%
		AchievementsCalculator calc = new AchievementsCalculator();
		QuizStatsManager statManager = new QuizStatsManager();
		AchievementManager achManager = new AchievementManager();

		int completed = statManager.getCompletedQuizzesCount(username);
		ArrayList<String> achievementsNow = calc.getAchievements(0, completed);

		ArrayList<String> achievementsBefore = achManager.getAchievements(username);

		HashSet<String> achievementsNowSet = new HashSet<String>();
		for (int i = 0; i < achievementsNow.size(); i++) {
			achievementsNowSet.add(achievementsNow.get(i));
		}
		HashSet<String> achievementsBeforeSet = new HashSet<String>();
		for (int i = 0; i < achievementsBefore.size(); i++) {
			achievementsBeforeSet.add(achievementsBefore.get(i));
		}
		String practice = "Practice Makes Perfect";
		if (practiceMode.equals("on") && !achievementsBeforeSet.contains(practice)) {
			achManager.setAchievement(practice, username);
		}
		for (String s : achievementsNowSet) {
			if (!achievementsBeforeSet.contains(s)) {

				achManager.setAchievement(s, username);
			}
		}
	%>

	<%
		out.print("<h1>Top Scorers</h1>");
		out.println("<table id='table'>");
		out.println("<tr>");
		out.println("<th>Position</th>");
		out.println("<th>Username</th>");
		out.println("<th>Quiz</th>");
		out.println("<th>Score</th>");
		out.println("<th>Time</th>");
		out.println("</tr>");
		int position = 1;
		String best = "I am the Greatest";
		while (rs.next()) {

			String curUser = accMan.getUserNameById(rs.getInt(DBInfo.COMPLETED_QUIZZES_USER_ID));
			int score = rs.getInt(DBInfo.COMPLETED_QUIZZES_SCORE);
			int time = rs.getInt(DBInfo.COMPLETED_QUIZZES_SPENT_TIME);

			if (position == 1 && username.equals(curUser) && !achievementsBeforeSet.contains(best)
					&& !practiceMode.equals("on")) {
				achManager.setAchievement(best, username);
			}
			String urlPatternForUser = "profile.jsp?username=" + curUser;
			String urlForUser = "<a href=" + urlPatternForUser + " >" + curUser + "</a>";

			String urlPatternForQuiz = "StartQuizServlet?quizName=" + quizName;
			String urlForQuiz = "<a href=" + urlPatternForQuiz + " >" + quizName + "</a>";

			out.print(
					"<tr><td>" + position + "</td><td>" + urlForUser + "</td><td>" + urlForQuiz + "</td><td>" + score + "</td><td>" + time + "</td></tr>");
			position++;
		}
		out.print("</table>");
	%>

</body>
</html>