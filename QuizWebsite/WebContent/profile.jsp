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
<body>
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