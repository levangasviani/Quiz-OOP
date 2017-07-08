package Login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import User.Account;
import User.AccountManager;
import WebSite.*;

/**
 * Servlet implementation class LoginServlet LoginServlet class is responsible
 * for: -> if login and password was given correctly dispatch to profile page ->
 * if given login is not in database show message -> if password was incorrect
 * show message
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		// getting parameters from request
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Debug
		if (WebSiteInfo.DEBUG_MODE) {
			out.println("given parameters:");
			out.println("username:" + username);
			out.println("password:" + password);
		}

		// get Account Manager from servletContext
		AccountManager am = (AccountManager) this.getServletContext().getAttribute(WebSiteInfo.ACCOUNT_MANAGER_ATTR);

		// If am is null initialize it
		while (am == null) {
			am = new AccountManager();
		}

		if (!am.containsAccount(username)) {
			// Debug
			if (WebSiteInfo.DEBUG_MODE) {
				out.println("\n-------------------------------------");
				out.println("username:" + username + " is not in database");
			}
			return;
		}

		Account acc = am.getAccount(username);
		if (!acc.getPassword().equals(Cracker.StringToHash(password, am.getSalt(username)))) {
			out.println("\n-------------------------------------");
			out.println("wrong password!");
			return;
		}
		// TODO show profile page
		out.println("\n-------------------------------------");
		out.println("you profile:");
		out.println(acc);
		request.getSession().setAttribute("username", username);
		RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
