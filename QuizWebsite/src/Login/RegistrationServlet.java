package Login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import User.AccountManager;
import WebSite.WebSiteInfo;

/**
 * Servlet implementation class RegistrationServelet
 * RegistrationServelet responsible for:
 *  -> creating accounts and adding it into Account Manager
 *  -> if DEBUG_MODE is on
 *  @author Lasha Kharshiladze
 */
@WebServlet("/" + WebSiteInfo.REGISTRATION_SERVLET)
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // getting parameters from user
	    String username  = request.getParameter("username");
        String password  = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname  = request.getParameter("lastname");
        String email     = request.getParameter("email");
        
        PrintWriter out = response.getWriter();
        
        // get Account Manager from servletContext
        AccountManager am = (AccountManager)this.getServletContext().getAttribute(WebSiteInfo.ACCOUNT_MANAGER_ATTR);
        
        // If am is null initialize it
        // TODO adding into account manager
        
        //DEBUG
        if(WebSiteInfo.DEBUG_MODE){
            out.println("username:"  + username);
            out.println("password:"  + password);
            out.println("firstname:" + firstname);
            out.println("lastname:"  + lastname);
            out.println("firstname:" + firstname);
            out.println("email:"     + email);
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
