package Login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import User.AccountManager;
import WebSite.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/" + WebSiteInfo.LOGIN_SERVLET)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// getting parameters from request
	    String username  = request.getParameter("username");
		String password  = request.getParameter("password");
		PrintWriter out  = response.getWriter();
		
		// get Account Manager from servletContext
		AccountManager am = (AccountManager)this.getServletContext().getAttribute(WebSiteInfo.ACCOUNT_MANAGER_ATTR);
		
		// If am is null initialize it
		
		// TODO show profile page
		
		// Debug
		if(WebSiteInfo.DEBUG_MODE){
    		out.println("username:" + username);
    		out.println("password:" + password);
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
