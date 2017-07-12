<%@page import="Database.DBInfo"%>
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	QuizManager qm = (QuizManager) this.getServletContext().getAttribute(WebSiteInfo.QUIZ_MANAGER_ATTR);
	Quiz quiz = qm.getQuiz(request.getParameter("quizName"));
	ArrayList<Question> questions = qm.getQuestions(quiz);
%>

<%
	out.println("<div id='questionsinf'>");
	for(int i=0; i<questions.size(); i++){
		System.out.println(questions.get(i).getId());
		out.println("<input type='hidden' name='questionId' value='"+questions.get(i).getId()+"'>");
		out.println("<input type='hidden' name='questionType' value='"+questions.get(i).getTypeId()+"'>");
		out.println("<input type='hidden' name='time' value='"+questions.get(i).getTime()+"'>");
	}
		
	out.println("</div>");
	out.println("<button id='butt' onclick='MultiPageProcess()'"+">"+"Start!"+"</button>");
%>

<title><%=request.getParameter("quizName")%></title>

</head>
<body>

<div id="summaryID" style="width: 100px; height: 100px; border: 1px solid black">
		<div id = "title">
			Points Got:
		</div>
         <p id="points">0</p>
    </div>


<div id="elapsedtime" style="font-size: 15px"></div>

<div id="timeLeft"></div>

<div id="questions">


</div>


<div id="subm">
</div>




<script src="checkans.js"></script>
</body>
</html>