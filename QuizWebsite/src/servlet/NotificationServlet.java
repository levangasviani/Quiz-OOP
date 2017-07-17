package servlet;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Notification.Notification;
import Notification.NotificationManager;
import WebSite.WebSiteInfo;

/**
 * Servlet implementation class NotificationServlet
 */
@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NotificationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sender = request.getParameter("sender");
		String receiver = request.getParameter("receiver");
		int type = Integer.parseInt(request.getParameter("type"));
		String message = request.getParameter("message");
		NotificationManager manager = (NotificationManager) getServletContext()
				.getAttribute(WebSiteInfo.NOTIFICATION_MANAGER_ATTR);
		manager.addNotification(new Notification(sender, receiver, message, type));
		if (type == 1) {
			response.sendRedirect("StartQuizServlet?quizName=" + message);
		} else if (type == 2) {
			request.setAttribute("sender", sender);
			request.setAttribute("receiver", receiver);
			request.setAttribute("type", type);
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("FriendServlet");
			dispatcher.forward(request, response);
		} else if (type == 3) {
			request.setAttribute("sender", sender);
			request.setAttribute("receiver", receiver);
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("GradeServlet");
			dispatcher.forward(request, response);
		} else if (type == 4) {
			response.sendRedirect("notifications.jsp");
		}
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
