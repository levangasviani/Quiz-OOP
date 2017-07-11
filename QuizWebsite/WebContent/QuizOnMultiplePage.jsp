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
	for(Question q : questions){
		int questionType = q.getTypeId();
		int questionId = q.getId();
		
		if(questionType == DBInfo.QUESTION_TYPE_QUESTION_RESPONSE) {
			
		}
	}
%>

<%
	out.println("<div id='questionsinf'>");
	for(int i=0; i<questions.size(); i++){
		out.println("<input type='hidden' name='questionId' value='"+questions.get(i).getId()+"'>");
		out.println("<input type='hidden' name='questionType' value='"+questions.get(i).getTypeId()+"'>");
	}
	out.println("</div>");
	out.println("<button id='butt' onclick='process()'"+">"+"Start!"+"</button>");
%>

<title><%=request.getParameter("quizName")%></title>

</head>
<body>


<div id="questions">


</div>


<script>

var num=0;
var questionIds=document.getElementsByName("questionId");
var questionTypes=document.getElementsByName("questionType");
var length=questionIds.length;

		function process(){
			if(num==length){
				window.location="homepage.jsp";
			}
			document.getElementById("butt").style.display = "none";
			var questionTyp=questionTypes[num].value;
			var questionI=questionIds[num].value;
				var file="";
				if(questionTyp==1){
					file="ShowQuestionResponse";
				}else if(questionTyp==2){
					file="ShowFillInTheBlank";
				}else if(questionTyp==3){
					file="ShowMultipleChoice";
				}else if(questionTyp==4){
					file="ShowPictureResponse";
				}else if(questionTyp==5){
					file="ShowMultipleAnswers";
				}else if(questionTyp==6){
					file="ShowMultipleChoiceMultipleAnswers";
				}else if(questionTyp==7){
					file="ShowMatching";
				}else if(questionTyp==8){
					file="ShowGradedQuestion"
				}
					$.post(file+".jsp", { questionId: questionI, questionType: questionTyp}, function(data, status){
						document.getElementById("questions").innerHTML=data+"<input type='submit' value='submit' onclick='process()'>";
					});
				num++;
			}
	</script>
</body>
</html>