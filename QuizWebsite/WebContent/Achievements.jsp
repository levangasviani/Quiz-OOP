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
<h1> Achievements </h1>
<body>
<%
	AchievementManager achManager = new AchievementManager();
	String username = request.getParameter("UserName");
	
	//ArrayList<String> achievements = achManager.getAcievements(username);
	
	ArrayList<String> achievements = achManager.getAcievements("user0");
	
	for(int i = 0; i < achievements.size(); i++) {
		out.println(achievements.get(i));	
		out.println("<br>");		
	}
	
%>
</body>
</html>