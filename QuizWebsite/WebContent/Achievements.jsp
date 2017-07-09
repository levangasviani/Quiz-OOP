<%@page import="java.util.ArrayList"%>
<%@page import="webclasses.AchievementManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Achievements </title>
</head>

<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
<link rel="stylesheet" type="text/css" href="css/Achievements.css">

<h1> Achievements </h1>

<body>

<div class="navigation" id = "navigationID">
    <a id="home" href = "homepage.jsp" >Home</a>
    <a id="profile">Profile</a>
    <a id="achievements" href = Achievements.jsp>Achievements</a>
    <a id="messages">Messages</a>
    <a id="creatQuiz" href = "CreateQuiz.jsp">Create Quiz</a>
    <a id="logout" href = "index.html">Logout</a>
     <div class = "search" id = searchID>
  	<form action = "SearchPage.jsp">
		<input type = "text" name = "search" placeholder = "enter value here...">
		<button type="submit" value="searchValue">search</button>
	</form>
  </div>
  </div>
 <div id = title>
 	Your Achievements:
 </div>
 <div id = achievementsDivID>
 <%
	AchievementManager achManager = new AchievementManager();
	String username = request.getParameter("UserName");
	
	
	
	ArrayList<String> achievements = achManager.getAcievements("username");
	
	if(achievements.size() == 0) {
		out.println("<br>Unfortunatelly do not have achievements!");
	}
	for(int i = 0; i < achievements.size(); i++) {
		out.println("<li>");
		out.println(achievements.get(i));	
		out.println("</li>");;		
	}
%>
 </div>
</body>
</html>

