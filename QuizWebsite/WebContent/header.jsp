<%@page import="Notification.NotificationManager" %>
<%@page import="WebSite.WebSiteInfo" %>
<%@page import="User.AccountManager" %>
<%@page import="User.Account" %>
	<h1>QuizWebsite</h1>
	<h2>
		<%
		AccountManager acm=(AccountManager)this.getServletContext().getAttribute(WebSiteInfo.ACCOUNT_MANAGER_ATTR);
		NotificationManager notificationManager = (NotificationManager) getServletContext().getAttribute(WebSiteInfo.NOTIFICATION_MANAGER_ATTR);
			String username = (String) request.getSession().getAttribute("username");
			int type=acm.getAccount(username).getType();
			if (username != null) {
				out.println("You are logged in as " + username + "<br>");
			} else {
				out.println("You are not  logged in<br>");
			}
		%>
	</h2>

	<div class="navigation" id = "navigationID">
		<a class="active" id="home" href = "homepage.jsp" ><i class="fa fa-home"></i> Home</a> 	  
		<a class="active" id="profile" href = "profile.jsp?username=<%=username %>"><i class="fa fa-user"></i> Profile</a>	    
		<a class="active" id="achievements" href = "Achievements.jsp"><i class="fa fa-trophy"></i> Achievements</a>    
		<a class="active" id="messages" href = "notifications.jsp" ><i class="fa fa-envelope"></i> Notifications <%=notificationManager.getNotificationCount(username) %></a>
		<a class="active" id="creatQuiz" href = "CreateQuiz.jsp"><i class="fa fa-plus"></i> Create Quiz</a>
		<%
			if(type==2){
				out.print("<a class='active' id='admin' href = 'Admin.jsp'> Administrator Panel</a>");
			}
		%>  	   
		<a id="logout" href = "index.html">Logout</a>
		<div class = "search" id = searchID>
			<div class = "search" id = searchID>
				<form action = "SearchPage.jsp">
					<input type = "text" name = "search" placeholder = "enter value here...">
					<i class="fa fa-search" aria-hidden="true"></i>
					<button type="submit" value="searchValue">search</button>
				</form>
			</div>
		</div>
	</div>