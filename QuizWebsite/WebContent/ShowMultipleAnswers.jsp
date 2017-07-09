<%@page import="WebSite.WebSiteInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="Quiz.Question"%>
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
	HashMap<String, String> answers = q.getAnswers();
%>

<title><%=request.getParameter("quizName") %></title>
</head>
<body>

	<p class="ShowMultipleAnswers"><%=q.getQuestionText()%></p>
	
	<%
			
		int size = 0;
		for (String s : answers.keySet()) {
			String parts[] = s.split(":");
			size = parts.length;
		}	
	
		for(int i = 0; i < size; i++) {
			out.print("<br><input type='text' name='possible_answer'><br>");
		}
	%>

</body>
</html>