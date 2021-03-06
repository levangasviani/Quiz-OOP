<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Notification.NotificationManager"%>
<%@ page import="Notification.Notification"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.StringTokenizer"%>
<%@ page import="Quiz.QuestionManager"%>
<%@ page import="Quiz.Question"%>
<%@page import="WebSite.WebSiteInfo"%>
<%
	String username = (String) request.getSession().getAttribute("username");
	NotificationManager notificationManager = (NotificationManager) getServletContext()
			.getAttribute(WebSiteInfo.NOTIFICATION_MANAGER_ATTR);
	QuestionManager questionManager = (QuestionManager) getServletContext()
			.getAttribute(WebSiteInfo.QUESTION_MANAGER_ATTR);
	ArrayList<Notification> notifications = notificationManager.getNotifications(username);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notifications</title>
<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
<link rel="stylesheet" type="text/css" href="css/Notifications.css">
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
	<div id="resID">
		<ul style="float: left; width: 50%; height: 100%; overflow: auto">
			<%
				int counter = 0;
				for (Notification notification : notifications) {
					counter++;
					if (notification.getType() == 1) {
			%>
			<li><%=notification.getSender()%> sent quiz challenge: <a
				href="StartQuizServlet?quizName=<%=notification.getContent()%>"><%=notification.getContent()%></a></li>
			<%
				} else if (notification.getType() == 2) {
			%>
			<li><a
				href="profile.jsp?username=<%=notification.getSender()%>"><%=notification.toString()%>
					Friendship</a></li>
			<%
				} else if (notification.getType() == 3) {
						String content = notification.getContent();
						StringTokenizer tokenizer = new StringTokenizer(content, ":");
						String status = "";
						String st = tokenizer.nextToken();
						if (st.equals("SENT"))
							status = "sent grade request: ";
						else if (st.equals("ACCEPTED"))
							status = "accepted your answer: ";
						else if (st.equals("REJECTED"))
							status = "rejected your answer: ";
						else if (st.equals("CHECKED"))
							status = "sent grade request: ";
						int questionId = Integer.parseInt(tokenizer.nextToken());
						Question question = questionManager.getQuestion(questionId);
						String questionText = questionManager.getQuestion(questionId).getQuestionText();
						String answer = tokenizer.nextToken();
			%>
			<li><%=notification.getSender()%> <%=status%> <%=questionText%>
				- <%=answer%><br />
				<form action="NotificationServlet">
					<input type="hidden" name="sender"
						value="<%=notification.getReceiver()%>" /> <input type="hidden"
						name="receiver" value="<%=notification.getSender()%>" /> <input
						type="hidden" name="type" value="3" /> <input type="hidden"
						name="message" id=<%=counter%> value="" />
					<%
						if (st.equals("SENT")) {
					%>
					<input type="submit"
						onclick="acceptReq('<%=counter%>', '<%=questionId%>', '<%=answer%>')"
						value="Accept" /> <input type="submit"
						onclick="rejectReq('<%=counter%>', '<%=questionId%>', '<%=answer%>')"
						value="Reject" />
					<%
						}
					%>
				</form></li>
			<%
				} else if (notification.getType() == 4) {
			%>
			<li><%=notification.toString()%></li>
			<%
				}
			%>
			<%
				}
			%>
		</ul>
	</div>
	<div class="sender">
		<form action="NotificationServlet">
			<input type="hidden" name="sender" value="<%=username%>" /> <input
				type="hidden" name="type" value="4" /> <input id="receiverID"
				type="text" name="receiver" placeholder="To:" /> <input
				id="submitID" type="submit" value="Send" /><br />
			<textarea id="textAreaID" rows="20" cols="40" name="message"
				placeholder="text..."></textarea>
		</form>
	</div>
	<script>
		function acceptReq(counter, questionId, answer) {

			document.getElementById(counter).value = "ACCEPTED:" + questionId
					+ ":" + answer;
		}
		function rejectReq(counter, questionId, answer) {
			document.getElementById(counter).value = "REJECTED:" + questionId
					+ ":" + answer;
		}
	</script>
</body>
</html>