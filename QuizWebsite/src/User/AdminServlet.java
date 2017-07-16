package User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Quiz.QuizManager;
import WebSite.WebSiteInfo;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    private void addAnnouncement(String username, String text){
    	AnnouncementManager am=(AnnouncementManager) this.getServletContext().getAttribute(WebSiteInfo.ANNOUNCEMENT_ATTR);
    	AccountManager acm=(AccountManager) this.getServletContext().getAttribute(WebSiteInfo.ACCOUNT_MANAGER_ATTR);
    	int adminId=acm.getAccountId(username);
    	am.addAnnouncement(adminId, text);
    }
    
    
    private boolean removeAccount(String adminUsername, String user){
    	AccountManager acm=(AccountManager) this.getServletContext().getAttribute(WebSiteInfo.ACCOUNT_MANAGER_ATTR);
    	if(!acm.containsAccount(user) || user.equals(adminUsername))return false;
    	acm.deleteAccount(adminUsername, user);
    	return true;
    }
    
    
    
    private boolean removeQuiz(String quizName){
    	QuizManager qm=(QuizManager) this.getServletContext().getAttribute(WebSiteInfo.QUIZ_MANAGER_ATTR);
    	if(!qm.containsQuiz(quizName))return false;
    	qm.deleteQuiz(quizName);
    	return true;
    }
    
    
    
    private boolean promote(String username){
    	AccountManager acm=(AccountManager) this.getServletContext().getAttribute(WebSiteInfo.ACCOUNT_MANAGER_ATTR);
    	if(!acm.containsAccount(username))return false;
    	acm.promoteAccount(username);
    	return true;
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		if(Integer.parseInt(request.getParameter("orderNum"))==AdminOrders.ADD_ANNOUNCEMENT){
			String username=(String) request.getSession().getAttribute("username");
			addAnnouncement(username, request.getParameter("text"));
			out.print("Announcement successfully added!");
		}else if(Integer.parseInt(request.getParameter("orderNum"))==AdminOrders.REMOVE_ACCOUNT){
			String adminUsername=(String) request.getSession().getAttribute("username");
			String user=request.getParameter("username");
			if(removeAccount(adminUsername, user)){
				out.print("Account successfully removed!");
			}
			else{
				out.print("Account with that name does not exist");
			}
		}else if(Integer.parseInt(request.getParameter("orderNum"))==AdminOrders.REMOVE_QUIZ){
			String quizName=request.getParameter("quizName");
			if(removeQuiz(quizName)){
				out.print("Quiz successfully removed!");
			}
			else{
				out.print("Quiz with that name does not exist");
			}
		}else{
			String username=request.getParameter("username");
			if(promote(username)){
				out.print("Account successfully got promoted!");
			}
			else{
				out.print("Account with that name does not exist");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
