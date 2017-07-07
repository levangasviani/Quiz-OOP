<%@page import="WebSite.WebSiteInfo"%>
<%@page import="Quiz.Quiz"%>
<%@page import="Quiz.QuizManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	li{
		display: inline-block;
	}
</style>


</head>
<body>
	<div id="summary" style="width: 200px; height: 100px; border: 1px solid black">
    	<p style="text-align: center"><strong>Summary</strong></p>
        Questions Created: <p id="numOfQuestions">0</p>
    </div>
	<ul style="text-align: center">
   <li><button onclick="process('question_response')">question response</button></li>
   <li><br><button onclick="process('fill_blank')">Fill_in_The_Blank</button></li>
   <li><button onclick="process('graded')">Graded_Question</button></li>
   <li><button onclick="process('matching')">matching</button></li>
   <li><button onclick="process('multi_answer')">multiple_answer</button></li>
   <li><button onclick="process('multiple_choice')">multiple_choice</button></li>
   <li><button onclick="process('picture_response')">picture_response</button></li>
   <li><button onclick="process('multiple_choice_multiple_answer')">multiple_choice_multiple_answer</button></li>
   </ul>
   <div id="div1">
   </div>
   <form action="QuestionCreateServlet" method="get" name="questions">
   		<input type="hidden" name="quizName" value=<%=request.getParameter("quizName")%> >
   		<input type="hidden" name="quizDescription" value=<%=request.getParameter("description")%> >
   		<input type="hidden" name="orderOfQuestions" value=<%=request.getParameter("orderOfQuestions") %> >
   		<input type="hidden" name="practiceMode" value=<%=request.getParameter("practiceMode") %>>
   		<input type="submit" value="Finish" style="font-size: 24px; margin: 10px 10px 10px 10px">
   </form>
   
   <a href="CreateQuiz.html">Go Back</a>
   
   
 <script type="text/javascript">
	function process(file){
		var xml=new XMLHttpRequest();
        xml.onreadystatechange=function(){
        	if(xml.status==200 && xml.readyState==4){
            	document.getElementById("div1").innerHTML=xml.responseText;
            }
        }
        xml.open("GET", file+".html", true);
        xml.send();
    }
	
</script>

<script>
var addedQuestions=0;

function updateSummary(){
	addedQuestions++;
	document.getElementById("numOfQuestions").innerHTML=addedQuestions;
}
</script>


<script src="QuestionAddScripts.js"></script>

</body>
</html>