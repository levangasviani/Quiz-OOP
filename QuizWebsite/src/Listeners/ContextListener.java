package Listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import Quiz.QuestionManager;
import Quiz.QuizManager;
import User.AccountManager;
import WebSite.WebSiteInfo;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public ContextListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		arg0.getServletContext().setAttribute(WebSiteInfo.ACCOUNT_MANAGER_ATTR, new AccountManager());
		arg0.getServletContext().setAttribute(WebSiteInfo.QUESTION_MANAGER_ATTR, new QuestionManager());
		arg0.getServletContext().setAttribute(WebSiteInfo.QUIZ_MANAGER_ATTR, new QuizManager());
	}
}
