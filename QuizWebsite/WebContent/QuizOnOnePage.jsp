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

<%
	QuizManager qm = (QuizManager) this.getServletContext().getAttribute(WebSiteInfo.QUIZ_MANAGER_ATTR);
	Quiz quiz = qm.getQuiz(request.getParameter("quizName"));
	ArrayList<Question> questions = qm.getQuestions(quiz);
   	
%>


<title><%=request.getParameter("quizName")%></title>

</head>
<body>

<%
	out.println("<div id='questionsinf'>");
	for(int i=0; i<questions.size(); i++){
		out.println("<input type='hidden' name='questionId' value='"+questions.get(i).getId()+"'>");
		out.println("<input type='hidden' name='questionType' value='"+questions.get(i).getTypeId()+"'>");
	}
	out.println("</div>");
	out.println("<button id='butt' onclick='process()'"+">"+"Start!"+"</button>");
%>

	<div id="questions">
		
	</div>
	
	
	
	
	<script>
		function process(){
			document.getElementsByTagName("body")[0].innerHTML+="<input type='Submit' value='submit' onclick='finishQuiz()'>"
			
			document.getElementById("butt").style.display = "none";
			var questionIds=document.getElementsByName("questionId");
			var questionTypes=document.getElementsByName("questionType");
			
			var i;
			for(i=0; i<questionIds.length; i++){
				var questionI=questionIds[i].value;
				var questionTyp=questionTypes[i].value;
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
						document.getElementById("questions").innerHTML+=data;
					});
				
			}
		}
		
		
		function finishQuiz(){
			window.location="homepage.jsp";
		}
	</script>

</body>
</html>