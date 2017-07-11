<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Notification.NotificationManager"%>
<%
	String username = (String) request.getSession().getAttribute("username");
	NotificationManager notificationManager = (NotificationManager) getServletContext().getAttribute("notificationManager");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create A Quiz</title>
</head>
 
<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
<link rel="stylesheet" type="text/css" href="css/CreateQuiz.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<h1>Create a Quiz</h1>

<body>
	
  <div class="navigation" id = "navigationID">
    	<a class="active" id="home" href = "homepage.jsp" ><i class="fa fa-home"></i> Home</a> 	  
	    <a class="active" id="profile" href = "profile.jsp?username=<%=username %>"><i class="fa fa-user"></i> Profile</a>	    
	    <a class="active" id="achievements" href = "Achievements.jsp"><i class="fa fa-trophy"></i> Achievements</a>    
	    <a class="active" id="messages" href = "notifications.jsp" ><i class="fa fa-envelope"></i> Notifications <%=notificationManager.getNotificationCount(username) %></a>
	    <a class="active" id="creatQuiz" href = "CreateQuiz.jsp"><i class="fa fa-plus"></i> Create Quiz</a>	   
   		 <a id="logout" href = "index.html">Logout</a>
      <div class = "search" id = searchID>
  		<form action = "SearchPage.jsp">
			<input type = "text" name = "search" placeholder = "enter value here...">
    		<i class="fa fa-search" aria-hidden="true"></i>
			<button type="submit" value="searchValue">search</button>
		</form>
  	 </div>
  </div>

	<div class = quizCreation id = quizCreationID>
		<form id = mainForm  action = "AddQuestions.jsp">
			Name of a quiz: <br>
			<input id = mainForm1 type = "text" name = "quizName" > <br>
			Description: <br>
			<textarea id = mainForm2 rows = "6" cols = "50" name="description"></textarea> <br>
			
			
			Practice Mode: <input type = "radio" name = "practiceMode" value = "off" checked = "checked"> Off
			<input type = "radio" name = "practiceMode" value = "on"> On <br>
			
			Order of questions: <input type = "radio" name = "orderOfQuestions" value = "defaultOrder" checked = "checked">default 
			<input type = "radio" name = "orderOfQuestions" value = "randomOrder">random <br>
			
			
			<button id = start type="submit" value="submit">Start</button>
		</form>
	</div>
</body>
</html>