<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QuizWebsite</title>
  
</head>

<link rel="stylesheet" type="text/css" href="css/MainDesign.css">
<link rel="stylesheet" type="text/css" href="css/HomePage.css">

<h1> QuizWebsite </h1>
<h2>
	<%
		String username = request.getParameter("username");
		if(username != null) {
			out.println("You are logged in as " + username + "<br>");
		} else {
			out.println("You are not  logged in<br>");
		}
	%>
</h2>
<body>

  <div class="navigation" id = "navigationID">
    <a id="home" href = "homepage.jsp" >Home</a>
    <a id="profile">Profile</a>
    <a id="achievements" href = Achievements.jsp>Achievements</a>
    <a id="messages">Messages</a>
    <a id="creatQuiz" href = "CreateQuiz.jsp">Create Quiz</a>
    <a id  href="StartQuizServlet?quizName=FirstQuiz">First Quiz</a>
    <%
    if(username != null) {
    	out.println(" <a id=\"logout\" href = \"index.html\">Logout</a>");
    }
    %>   
     <div class = "search" id = searchID>
  		<form action = "SearchPage.jsp">
		<input type = "text" name = "search" placeholder = "enter value here...">
		<button type="submit" value="searchValue">search</button>
	</form>
  </div>
  </div>
 
    <div id="generalInfo">
       <div id="popularQuizzes">popular quizzes
          <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
          </ul>
      </div>
      
      <div id="recentQuizzes">recently created quizzes
          <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
      </div>
  </div>
      
     <div id="myInfo">
        <div id="myRecentTakenQuizzes">my recently taken quizzes
          <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
          </ul>
        </div>
       
       <div id="myRecentCreatedQuizzes">my recently created quizzes
          <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
          </ul>
        </div>
       
         <div id="friendsAchievements">friends' activities
          <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
          </ul>
        </div>
      </div>
      
    <div id="announcements">
    	<p id="announcement-title">Announcements</p>
  	</div>

</body>
</html>
