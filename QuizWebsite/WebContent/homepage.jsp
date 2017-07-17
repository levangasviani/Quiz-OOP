<%@page import="Quiz.QuizStatsManager"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Notification.NotificationManager"%>
<%@page import="WebSite.WebSiteInfo"%>
<%@page import="User.AccountManager"%>
<%@page import="User.Account"%>
<%@page import="User.AnnouncementManager"%>
<%@page import="java.sql.ResultSet"%>
<%
	if(request.getParameter("status")!= null && request.getParameter("status").equals("logout")){
	    request.getSession().setAttribute("username", null);
	}
	String username = (String) request.getSession().getAttribute("username");
	NotificationManager notificationManager = (NotificationManager) getServletContext().getAttribute(WebSiteInfo.NOTIFICATION_MANAGER_ATTR);
	AnnouncementManager anm=(AnnouncementManager)this.getServletContext().getAttribute(WebSiteInfo.ANNOUNCEMENT_ATTR);
%>

<%
	AccountManager acm=new AccountManager();
	int type = 1;
	QuizStatsManager qsm = new QuizStatsManager();
	ArrayList<String> recentlyCreatedQuizzes = new ArrayList<String>();
	 	ArrayList<String> popularQuizzes = new ArrayList<String>();
	 	ArrayList<String> recentlyTakenQuizzes = new ArrayList<String>();
	 	ArrayList<String> recentlyTakenQuizzesByUser = new ArrayList<String>();
	 	Account acc;
	 	if(username != null){
	 		acc=acm.getAccount(username);
	 		type=acc.getType();
	 		recentlyTakenQuizzes = qsm.getRecentlyTakenQuizzes(username);
	 		recentlyTakenQuizzesByUser = qsm.getRecentlyCreatedQuizzes(username);
	 	}
	 	recentlyCreatedQuizzes = qsm.getRecentlyCreatedQuizzes();
 		popularQuizzes = qsm.getPopularQuizzes();
 		
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>QuizWebsite</title>
	
	<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
	<link rel="stylesheet" type="text/css" href="css/HomePage.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
</head>

<body>
	<script>
		$(document).ready(function(){
			$('#header').load('header.jsp');
		});
	</script>

	<div id="header">
		
	</div>

	<div id="generalInfo">
		<div id="popularQuizzes">
			popular quizzes
			<ul>
			<%
				for(String s : popularQuizzes){
					String urlPattern = "StartQuizServlet?quizName="+s;
					out.print("<li><a href=" + urlPattern + " >" + s + "</a></li>");
				}
			%>
			</ul>
		</div>

		<div id="recentQuizzes">
			recently created quizzes
			<ul>
			<%
				for(String s : recentlyCreatedQuizzes){
					String urlPattern = "StartQuizServlet?quizName="+s;
					out.print("<li><a href=" + urlPattern + " >" + s + "</a></li>");
				}
			%>
			</ul>
		</div>
	</div>

	<div id="myInfo">
		<div id="myRecentTakenQuizzes">
			my recently taken quizzes
			<ul>
			<%
				for(String s : recentlyTakenQuizzes){
					String urlPattern = "StartQuizServlet?quizName="+s;
					out.print("<li><a href=" + urlPattern + " >" + s + "</a></li>");
				}
			%>
			</ul>
		</div>

		<div id="myRecentCreatedQuizzes">
			my recently created quizzes
			<ul>
			<%
				for(String s : recentlyTakenQuizzesByUser){
					String urlPattern = "StartQuizServlet?quizName="+s;
					out.print("<li><a href=" + urlPattern + " >" + s + "</a></li>");
				}
			%>
			</ul>
		</div>

		<div id="friendsAchievements">
			friends' activities
			<ul>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>
	</div>

	<div id="announcements">
		<p id="announcement-title">Announcements</p>
		<%
			if(username!= null && anm != null){
   				ResultSet rs=anm.getAllAnnouncements();
   				while(rs.next()){
   					int admin=rs.getInt(3);
   					String adminName=acm.getUserNameById(admin);
   					String text=rs.getString(2);
   					out.print("<div name='announce' style='overflow-y: scroll; border: 1px green solid'>");
   					out.print("<p>Author: <strong>"+adminName+"</strong></p>");
   					out.print("<p>"+text+"</p>");
   					out.print("</div>");
   				}
			}
		%>
	</div>

</body>
</html>
