<%@page import="WebSite.WebSiteInfo"%>
<%@page import="Quiz.Quiz"%>
<%@page import="Quiz.QuizManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>

<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
<link rel="stylesheet" type="text/css" href="css/AddQuestions.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<h1>Create a Quiz</h1>
<body>
	
	
   <div class="navigation" id = "navigationID">
    	<a class="active" id="home" href = "homepage.jsp" ><i class="fa fa-home"></i> Home</a> 	  
	    <a class="active" id="profile" href = "profile.jsp"><i class="fa fa-user"></i> Profile</a>	    
	    <a class="active" id="achievements" href = "Achievements.jsp"><i class="fa fa-trophy"></i> Achievements</a>    
	    <a class="active" id="messages" href = "notifications.jsp" ><i class="fa fa-envelope"></i> Messages</a>
	    <a class="active" id="creatQuiz" href = "CreateQuiz.jsp"><i class="fa fa-plus"></i> Create Quiz</a>	   
   		 <a id="logout" href = "index.html">Logout</a>
      <div class = "search" id = searchID>
  		<form action = "SearchPage.jsp">
			<input type = "text" name = "search" placeholder = "enter value here...">
    		<i class="fa fa-search" aria-hidden="true"></i>
			<button type="submit" value="searchValue">search</button>
		</form>
  	 </div>
  </div>


	<div id="summaryID">
		<div id = "title">
			Questions Created:
		</div>
         <p id="numOfQuestions"></p>
    </div>
    <div id = questionsID>
		<a><button onclick="process('question_response')">question response</button></a>
		<a><br><button onclick="process('fill_blank')">Fill_in_The_Blank</button></a>
		<a><button onclick="process('graded')">Graded_Question</button></a>
		<a><button onclick="process('matching')">matching</button></a>
		<a><button onclick="process('multi_answer')">multiple_answer</button></a>
		<a><button onclick="process('multiple_choice')">multiple_choice</button></a>
		<a><button onclick="process('picture_response')">picture_response</button></a>
		<a><button onclick="process('multiple_choice_multiple_answer')">multiple_choice_multiple_answer</button></a>
   </div>
   <div id="div1">
   </div>
   <form action="QuestionCreateServlet" method="get" name="questions">
   		<input type="hidden" name="quizName" value=<%=request.getParameter("quizName")%> >
   		<input type="hidden" name="quizDescription" value=<%=request.getParameter("description")%> >
   		<input type="hidden" name="orderOfQuestions" value=<%=request.getParameter("orderOfQuestions") %> >
   		<input type="hidden" name="practiceMode" value=<%=request.getParameter("practiceMode") %>>
   		<input id = finishID type="submit" value="Finish" style="font-size: 24px; margin: 10px 10px 10px 10px">
   </form>
   
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