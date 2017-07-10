<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="friend.FriendManager"%>
<%@ page import="java.io.PrintWriter"%>
<%
	FriendManager friendManager = (FriendManager) getServletContext().getAttribute("friendManager");
	String username1 = (String) request.getSession().getAttribute("username");
	String username2 = request.getParameter("username");
	response.setContentType("text/html");
	PrintWriter writer = response.getWriter();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if (!friendManager.areFriends(username1, username2)) {
			if (friendManager.requestReceived(username1, username2)) {
				writer.println("<form action=\"NotificationServlet\">");
				writer.println("<input type=\"hidden\" name=\"sender\" value=" + username1 + " />");
				writer.println("<input type=\"hidden\" name=\"receiver\" value=" + username2 + " />");
				writer.println("<input type=\"hidden\" name=\"type\" value=\"2\" />");
				writer.println("<input type=\"radio\" name=\"message\" value=\"ACCEPTED\" checked/>Accept");
				writer.println("<input type=\"radio\" name=\"message\" value=\"REJECTED\" />Reject");
				writer.println("<input type=\"submit\" value=\"Answer\">");
				writer.println("</form>");
			} else if (!friendManager.requestReceived(username2, username1)) {
				writer.println("<form action=\"NotificationServlet\">");
				writer.println("<input type=\"hidden\" name=\"sender\" value=" + username1 + " />");
				writer.println("<input type=\"hidden\" name=\"receiver\" value=" + username2 + " />");
				writer.println("<input type=\"hidden\" name=\"type\" value=\"2\" />");
				writer.println("<input type=\"hidden\" name=\"message\" value=\"SENT\" />");
				writer.println("<input type=\"submit\" value=\"Add Friend\">");
				writer.println("</form>");
			}
		}
	%>
</body>
</html>