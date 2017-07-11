<%@page import="User.Account"%>
<%@page import="Quiz.Quiz"%>
<%@page import="java.util.ArrayList"%>
<%@page import="webclasses.SearchManager"%>
<%@page import="Notification.NotificationManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String username = (String) request.getSession().getAttribute("username");
	NotificationManager notificationManager = (NotificationManager) getServletContext().getAttribute("notificationManager");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Search </title>
</head>

<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
<link rel="stylesheet" type="text/css" href="css/SearchPage.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<h1> QuizWebsite </h1>

<body>

	<div class="navigation" id = "navigationID">
    	<a class="active" id="home" href = "homepage.jsp" ><i class="fa fa-home"></i> Home</a> 	  
	    <a class="active" id="profile" href = "profile.jsp?username=<%=username %>"><i class="fa fa-user"></i> Profile</a>	    
	    <a class="active" id="achievements" href = "Achievements.jsp"><i class="fa fa-trophy"></i> Achievements</a>    
	    <a class="active" id="messages" href = "notifications.jsp" ><i class="fa fa-envelope"></i> Notifications <%=notificationManager.getNotificationCount(username) %></a>
	    <a class="active" id="creatQuiz" href = "CreateQuiz.jsp"><i class="fa fa-plus"></i> Create Quiz</a>	   
   		 <a id="logout" href = "index.html">Logout</a>
  </div>
	
	<div class = "search" id = searchID>
  		<form action = "SearchPage.jsp">
			<input id = searchInput type = "text" name = "search" placeholder = "enter value here..."> <br>
    		<!-- <i class="fa fa-search" aria-hidden="true"></i> <br> -->
			<button id = searchButton type="submit" value="searchValue">search</button>
		</form>
  	 </div>
  	 <div id = res>
	<%
	SearchManager sManager = (SearchManager) getServletContext().getAttribute("searchManager");
	String value = request.getParameter("search");
	ArrayList<Quiz> quizzes = sManager.getQuizzes(value);
	ArrayList<Account> accounts = sManager.getAccounts(value);
	out.println("<div id = quizRes>");
	out.println("Quizzes (" + quizzes.size() + ")<br>");
	for(int i = 0; i < quizzes.size(); i++) {
		out.println(quizzes.get(i).getName() + " ");	
	}
	out.println("</div>");
	out.println("<br>");
	out.println("<div id = accountsRes>");
	out.println("Accounts (" + accounts.size() + ")<br>");
	%>
	<ul>
	<%
	for(int i = 0; i < accounts.size(); i++) {
	%>
		<li><a href="profile.jsp?username=<%=accounts.get(i).getUserName() %>"><%=accounts.get(i).getUserName() %></a></li>
	<%
	}
	%>
	</ul>
	<%
	out.println("</div>");
	%>
	</div>

</body>
</html>