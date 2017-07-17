package servlet;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Notification.NotificationManager;
import Quiz.QuestionManager;
import Quiz.QuizManager;
import Quiz.QuizStatsManager;
import WebSite.WebSiteInfo;

/**
 * Servlet implementation class GradeServlet
 */
@WebServlet("/GradeServlet")
public class GradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GradeServlet() {
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
		String message = request.getParameter("message");
		QuizManager quizManager = (QuizManager) request.getServletContext().getAttribute(WebSiteInfo.QUIZ_MANAGER_ATTR);
		QuizStatsManager quizStatsManager = (QuizStatsManager) request.getServletContext()
				.getAttribute(WebSiteInfo.QUIZ_STATS_MANAGER);
		QuestionManager questionManager = (QuestionManager) request.getServletContext()
				.getAttribute(WebSiteInfo.QUESTION_MANAGER_ATTR);
		NotificationManager notificationManager = (NotificationManager) getServletContext()
				.getAttribute(WebSiteInfo.NOTIFICATION_MANAGER_ATTR);
		StringTokenizer tokenizer = new StringTokenizer(message, ":");
		String status = tokenizer.nextToken();
		int questionId = Integer.parseInt(tokenizer.nextToken());
		if (status.equals("ACCEPTED")) {
			quizStatsManager.updateQuizScore(receiver,
					quizManager.getQuizName(questionManager.getQuestion(questionId).getQuizId()));
			notificationManager.updateNotification(receiver, sender, questionId);
			response.sendRedirect("notifications.jsp");
		} else if (status.equals("REJECTED")) {
			notificationManager.updateNotification(receiver, sender, questionId);
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
