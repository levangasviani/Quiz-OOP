<%@page import="Notification.NotificationManager" %>
<%@page import="WebSite.WebSiteInfo" %>
<%@page import="User.AccountManager" %>
<%@page import="User.Account" %>
	<link rel="stylesheet"  type="text/css" href="css/loginstyle.css">
	<link rel="stylesheet" type="text/css" href="css/HomePage.css">
	<a href= "homepage.jsp" >
	<div style = "background: white;">
		<div style = "background-image:url('img/greenquiz_hd.jpg'); background-repeat-x: no-repeat;
background-repeat-y: no-repeat;height: 150px; float: center; margin-left: 23%;
		" >
		</div>
	</div>
	</a>
		<%
		AccountManager acm=(AccountManager)this.getServletContext().getAttribute(WebSiteInfo.ACCOUNT_MANAGER_ATTR);
		NotificationManager notificationManager = (NotificationManager) getServletContext().getAttribute(WebSiteInfo.NOTIFICATION_MANAGER_ATTR);
		String username = (String) request.getSession().getAttribute("username");
		int type = 1;
		int notnum = 0;
		if(username != null){
		 	type = acm.getAccount(username).getType();
		 	notnum = notificationManager.getNotificationCount(username);
		 }
		%>
	<%
	  if (username == null){
	 %>
	<%@include  file="loginindex.html" %>
	<%} %>
  	
	<div class="navigation" id = "navigationID">
		<a class="active" id="home" href = "homepage.jsp" ><i class="fa fa-home"></i> Home</a>
		<%
 		if (username != null){
   		 %>
		
		<a class="active" id="profile" href = "profile.jsp?username=<%=username %>"><i class="fa fa-user"></i> Profile</a>	    
		<a class="active" id="achievements" href = "Achievements.jsp"><i class="fa fa-trophy"></i> Achievements</a>    
		<a class="active" id="messages" href = "notifications.jsp" ><i class="fa fa-envelope"></i> Notifications <%=notnum %></a>
		<a class="active" id="creatQuiz" href = "CreateQuiz.jsp"><i class="fa fa-plus"></i> Create Quiz</a>
		<%
 		 }
	    %>
	    <%
	     if (username == null){
	     %>
	     	<a class="active" id="profile" href="javascript:void(0)" onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'"><i class="fa fa-user"></i> Profile</a>	    
			<a class="active" id="achievements" href="javascript:void(0)" onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'"><i class="fa fa-trophy"></i> Achievements</a>    
			<a class="active" id="messages" href="javascript:void(0)" onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'" ><i class="fa fa-envelope"></i> Notifications <%=notnum %></a>
			<a class="active" id="creatQuiz" href="javascript:void(0)" onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'"><i class="fa fa-plus"></i> Create Quiz</a>
	     	
	     	<label align = "right"> <a href="javascript:void(0)" onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'"><button id = "btn">Sign In</button>   </label> </a>
	     <%} 
	     %>
	     <%
			if(type==2){
				out.print("<a class='active' id='admin' href = 'Admin.jsp'> Administrator Panel</a>");
			}
			if(username != null){
			    out.println("<a  id = 'logout' href = 'homepage.jsp?status=logout'> <img src = 'http://icons.veryicon.com/ico/System/100%20Flat%20Vol.%202/inside%20logout.ico' width = 30 height = 30/></a>");	
			}
		%>  	   
		
		<div class = "search" id = searchID>
			<div class = "search" id = searchID>
				<form action = "SearchPage.jsp">
					<input  type = "text" name = "search" placeholder = "enter value here...">
					<button  id = "btn" type="submit" value="searchValue" >search</button>
				</form>
			</div>
		</div>
	</div>