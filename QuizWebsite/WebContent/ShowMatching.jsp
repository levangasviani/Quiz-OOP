<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
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
	HashMap<String, String> answers = q.getAnswers();
%>

<title><%=request.getParameter("quizName") %></title>
</head>
<body>

	<p class="ShowMatching"><%=q.getQuestionText()%></p>

	<ul>
		<%	
			
			HashSet<String> left = new HashSet<>();
			HashSet<String> right = new HashSet<>(); 
			
			for (String s : answers.keySet()) {
				String parts[] = s.split(":");
				left.add(parts[0]);
				right.add(parts[1]);
			}
		
			out.print("<p>Match Answers</p>");
			for(String l : left) {
				
				out.print("<br>" + l);
				out.print("<select>");
				out.print("<option value=''> </option><br/>");
				for (String r : right) {
					out.print("<option value=" + r + ">" + r +"</option><br/>");
				}
				out.print("</select>");
				out.print("</br>");
			}
		%>

	</ul>
	
</body>
</html>