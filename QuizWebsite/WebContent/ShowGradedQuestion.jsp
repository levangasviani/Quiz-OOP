<%@page import="WebSite.WebSiteInfo"%>
<%@page import="Quiz.Question"%>
<%@page import="Quiz.QuestionManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="css/ShowQuestions.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/ShowQuestions.css">

<%
	Integer questionId = Integer.parseInt(request.getParameter("questionId"));
	QuestionManager questMan = (QuestionManager) this.getServletContext()
			.getAttribute(WebSiteInfo.QUESTION_MANAGER_ATTR);
	Question q = questMan.getQuestion(questionId);
%>

<title><%=request.getParameter("quizName")%></title>
</head>
<body>

	<div id=showDIV>
		<hr>
		<i id="icon" class="fa fa-question-circle " aria-hidden="true"
			style="font-size: 60px; margin-left: 5px; color: white;"></i>
		<div id=showQuestion>
			<p class="ShowGradedQuestion"><%=q.getQuestionText()%></p>
		</div>
		<ul>
			<%
				out.print("<p>Answer</p>");
				out.print("<input type='text' name='possible-answer'><br/>");
			%>

		</ul>
		<hr>
	</div>
</body>
</html>