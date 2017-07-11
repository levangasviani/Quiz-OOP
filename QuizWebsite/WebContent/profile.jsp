<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="friend.FriendManager"%>
<%
	FriendManager friendManager = (FriendManager) getServletContext().getAttribute("friendManager");
	String username1 = (String) request.getSession().getAttribute("username");
	String username2 = request.getParameter("username");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=username2 %></title>
</head>

<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
<link rel="stylesheet" type="text/css" href="css/Achievements.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<h1> Achievements </h1>

<body>
<div class="navigation" id = "navigationID">
    	<a class="active" id="home" href = "homepage.jsp" ><i class="fa fa-home"></i> Home</a> 	  
	    <a class="active" id="profile" href = "profile.jsp"><i class="fa fa-user"></i> Profile</a>	    
	    <a class="active" id="achievements" href = "Achievements.jsp"><i class="fa fa-trophy"></i> Achievements</a>    
	    <a class="active" id="messages" href = "notifications.jsp" ><i class="fa fa-envelope"></i> Messages</a>
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
<%
	if (!friendManager.areFriends(username1, username2)) {
		if (friendManager.requestReceived(username1, username2)) {
%>
			<form action="NotificationServlet">
				<input type="hidden" name="sender" value="<%=username1 %>" />
				<input type="hidden" name="receiver" value="<%=username2 %>" />
				<input type="hidden" name="type" value="2" />
				<input type="hidden" name="message" id="message" value="" />
				<input type="submit" class="accept" value="Accept" />
				<input type="submit" class="reject" value="Reject" />
			</form>
<%
		} else if (!friendManager.requestReceived(username2, username1)) {
%>
			<form action="NotificationServlet">
				<input type="hidden" name="sender" value="<%=username1 %>" />
				<input type="hidden" name="receiver" value="<%=username2 %>" />
				<input type="hidden" name="type" value="2" />
				<input type="hidden" name="message" value="SENT" />
				<input type="submit" value="Add Friend" />
			</form>
<%
		}
	}
%>
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