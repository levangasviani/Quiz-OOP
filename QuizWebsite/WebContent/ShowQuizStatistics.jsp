<%@page import="Quiz.QuizManager"%>
<%@page import="User.AccountManager"%>
<%@page import="Database.DBInfo"%>
<%@page import="Notification.NotificationManager"%>
<%@page import="WebSite.WebSiteInfo"%>
<%@page import="Quiz.QuizStatsManager"%>
<%@page import="java.sql.ResultSet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String username = (String) request.getSession().getAttribute("username");
	NotificationManager notificationManager = (NotificationManager) getServletContext().getAttribute(WebSiteInfo.NOTIFICATION_MANAGER_ATTR);
	String quizName = request.getParameter("quizName");
	int resultScore = Integer.parseInt(request.getParameter("score"));
	int resultTime = Integer.parseInt(request.getParameter("elapsedTime"));
	QuizStatsManager qsm = (QuizStatsManager)this.getServletContext().getAttribute(WebSiteInfo.QUIZ_STATS_MANAGER);
	AccountManager accMan = (AccountManager)this.getServletContext().getAttribute(WebSiteInfo.ACCOUNT_MANAGER_ATTR);
	qsm.addQuizCompleted(username, quizName, resultScore, resultTime);
	ResultSet rs = qsm.getBestResultOfDefaultSize(quizName);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>quizName</title>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>QuizWebsite</title>
	<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
	<link rel="stylesheet" type="text/css" href="css/HomePage.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<h1>QuizWebsite</h1>

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
	
	
	
	<%
		out.print("<ul>");
		int position=1;
		while(rs.next()){
			String curUser = accMan.getUserNameById(rs.getInt(DBInfo.COMPLETED_QUIZZES_USER_ID));
			int score = rs.getInt(DBInfo.COMPLETED_QUIZZES_SCORE);
			int time = rs.getInt(DBInfo.COMPLETED_QUIZZES_SPENT_TIME);
			
			String urlPatternForUser = "profile.jsp?username="+curUser;
			String urlForUser = "<a href=" + urlPatternForUser + " >" + curUser + "</a>";
			
			String urlPatternForQuiz = "StartQuizServlet?quizName="+quizName;
			String urlForQuiz = "<a href=" + urlPatternForQuiz + " >" + quizName + "</a>";
			
			out.print("<li>"+position+ ") "+ urlForUser + " " + urlForQuiz + " "+score +" "+time+ "</li>");
			position++;
		}
		out.print("</ul>");
	
	%>

</body>
</html>