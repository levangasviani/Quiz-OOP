<%@page import="WebSite.WebSiteInfo"%>
<%@page import="Quiz.Question"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Quiz.Quiz"%>
<%@page import="Quiz.QuizManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Achievements</title>
	<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
	<link rel="stylesheet" type="text/css" href="css/Achievements.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title><%=request.getParameter("quizName")%></title>

</head>
<body>

<script>
		$(document).ready(function(){
			$('#header').load('header.jsp');
		});
	</script>

	<div id="header">
		
	</div>

<%
	QuizManager qm = (QuizManager) this.getServletContext().getAttribute(WebSiteInfo.QUIZ_MANAGER_ATTR);
	Quiz quiz = qm.getQuiz(request.getParameter("quizName"));
	String sender = (String) request.getSession().getAttribute("username");
	String receiver = (String) qm.getQuizCreator(quiz.getName());
	ArrayList<Question> questions = qm.getQuestions(quiz);
   	
%>
<input type="hidden" value=<%=request.getParameter("quizName")%> id="quizName">
<input type="hidden" value=<%=sender %> id="sender">
<input type="hidden" value=<%=receiver %> id="receiver">


<div id="summaryID">
		<div id = "title">
			Points Got:
			<p id="points">0</p>
		</div>
         <div id="elapsedtime">
		</div>
    </div>
<div align = center >
<%
	out.println("<div  id='questionsinf'>");
	for(int i=0; i<questions.size(); i++){
		out.println("<input type='hidden' name='questionId' value='"+questions.get(i).getId()+"'>");
		out.println("<input type='hidden' name='questionType' value='"+questions.get(i).getTypeId()+"'>");
		out.println("<input type='hidden' name='sender' value=\""  + sender + "\">");
		out.println("<input type='hidden' name='receiver' value=\""  + receiver + "\">");
	}
	out.println("</div>");
	out.println("<button  align = 'center' id='btn' onclick='OnePageProcess()'"+">"+"Start!"+"</button>");
%>
</div>
	<div id="questions">
		
	</div>
	
	
	
	
<script src="checkans.js">
</script>
</body>
</html>