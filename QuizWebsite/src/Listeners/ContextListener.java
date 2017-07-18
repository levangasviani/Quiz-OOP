package Listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import Notification.NotificationManager;
import Quiz.QuestionManager;
import Quiz.QuizManager;
import Quiz.QuizStatsManager;
import User.AccountManager;
import User.AnnouncementManager;
import WebSite.WebSiteInfo;
import friend.FriendManager;
import webclasses.AchievementManager;
import webclasses.SearchManager;

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

	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {

		arg0.getServletContext().setAttribute(WebSiteInfo.ACCOUNT_MANAGER_ATTR, new AccountManager());
		arg0.getServletContext().setAttribute(WebSiteInfo.QUESTION_MANAGER_ATTR, new QuestionManager());
		arg0.getServletContext().setAttribute(WebSiteInfo.QUIZ_MANAGER_ATTR, new QuizManager());
		arg0.getServletContext().setAttribute(WebSiteInfo.NOTIFICATION_MANAGER_ATTR, new NotificationManager());
		arg0.getServletContext().setAttribute(WebSiteInfo.FRIEND_MANAGER_ATTR, new FriendManager());
		arg0.getServletContext().setAttribute(WebSiteInfo.ACHIEVEMENT_MANAGER_ATTR, new AchievementManager());
		arg0.getServletContext().setAttribute(WebSiteInfo.SEARCH_MANAGER_ATTR, new SearchManager());
		arg0.getServletContext().setAttribute(WebSiteInfo.QUIZ_STATS_MANAGER, new QuizStatsManager());
		arg0.getServletContext().setAttribute(WebSiteInfo.ANNOUNCEMENT_ATTR, new AnnouncementManager());
	}
}
