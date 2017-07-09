<%@page import="java.util.HashMap"%>
<%@page import="Quiz.Question"%>
<%@page import="WebSite.WebSiteInfo"%>
<%@page import="Quiz.QuestionManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	Integer questionId = Integer.parseInt(request.getParameter("questionId"));
	QuestionManager questMan = (QuestionManager) this.getServletContext().getAttribute(WebSiteInfo.QUESTION_MANAGER_ATTR);
	Question q = questMan.getQuestion(questionId);
%>

<title><%=request.getParameter("quizName") %></title>
</head>
<body>

	<p class="ShowFillInTheBlank"><%=q.getQuestionText()%></p>

	<ul>
		<%
			out.print("<p>Answer</p>");
			out.print("<input type='text' name='possible-answer'><br/>");
		%>

	</ul>

</body>
</html>