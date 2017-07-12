<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Start Quiz</title>
</head>

	<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
	<link rel="stylesheet" type="text/css" href="css/StartQuiz.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
<body>
	
	<h1 id="h1"><%=request.getAttribute("quizName") %> </h1>
	<h1 id="h1"><%=request.getAttribute("description") %> </h1>
	<div id = "mainID">								
    <form action="ShowQuestionsServlet" >
   
    <input type = "hidden" name = "quizName" value = <%=request.getAttribute("quizName") %>>
     
    Practice Mode: <input type = "radio" name = "practiceMode" value = "off" checked = "checked"> Off
	<input type = "radio" name = "practiceMode" value = "on"> On <br>		
			
	Display settings: <input type = "radio" name = "pageDisplay" value = "onePage" checked = "checked"> One Page
	<input type = "radio" name = "pageDisplay" value = "multiplePage"> Multiple Page <br>
   
    <br><input id= "start" type="submit" value="Start Quiz"><br>
   
    </form>
	</div>

</body>
</html>