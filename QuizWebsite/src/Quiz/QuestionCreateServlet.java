package Quiz;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import Database.DBInfo;
import WebSite.WebSiteInfo;


/**
 * Servlet implementation class QuestionCreateServlet
 */
@WebServlet("/QuestionCreateServlet")
public class QuestionCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionCreateServlet() {
        super();
    }

    
    private void processQuestions(JSONObject[] questions, int quizId) throws NumberFormatException, JSONException{
    	for(int i=0; i<questions.length; i++){
    		int type=Integer.parseInt(questions[i].getString("type"));
    		processQuestionResponse(questions[i], type, quizId);
    	}
    }
    


	private void processQuestionResponse(JSONObject jsonObject, int type, int quizId) throws JSONException {
		String questionText=getQuestionText(jsonObject);
		String order=getQuestionOrder(jsonObject);
		String checkType=getCheckType(jsonObject);
		int time=getTime(jsonObject);
		HashMap<String, String> map=getAnswers(jsonObject);
		int maxScore=getMaxScore(map, type);
		Question q;
		if(type==DBInfo.QUESTION_TYPE_QUESTION_RESPONSE){
			 q=new Question_Response(questionText, map, quizId, order, checkType, time, maxScore);
		}else if(type==DBInfo.QUESTION_TYPE_FILL_IN_THE_BLANK){
			 q=new Fill_In_The_Blank(questionText, map, quizId, order, checkType, time, maxScore);
		}else if(type==DBInfo.QUESTION_TYPE_MULTIPLE_CHOICE){
			 q=new Multiple_Choice(questionText, map, quizId, order, checkType, time, maxScore);
		}else if(type==DBInfo.QUESTION_TYPE_MULTI_ANSWER){
			 q=new Multi_Answer(questionText, map, quizId, order, checkType, time, maxScore);
		}else if(type==DBInfo.QUESTION_TYPE_MATCHING){
			 q=new Matching(questionText, map, quizId, order, checkType, time, maxScore);
		}else if(type==DBInfo.QUESTION_TYPE_MULTIPLE_CHOICE_WITH_MULTIPLE_ANSWERS){
			 q=new Multiple_Choice_Multiple_Answer(questionText, map, quizId, order, checkType, time, maxScore);
		}else if(type==DBInfo.QUESTION_TYPE_PICTURE_RESPONSE){
			 q=new Picture_Response(questionText, map, quizId, order, checkType, time, maxScore);
		}else{
			 q=new Graded_Question(questionText, map, quizId, order, checkType, time, maxScore);
		}
		QuestionManager qm=(QuestionManager) this.getServletContext().getAttribute(WebSiteInfo.QUESTION_MANAGER_ATTR);
		qm.addQuestion(q);
	}
	
	
	
	private int getMaxScore(HashMap<String, String> answers, int type) {
		if(type==DBInfo.QUESTION_TYPE_QUESTION_RESPONSE){
			return 1;
		}else if(type==DBInfo.QUESTION_TYPE_FILL_IN_THE_BLANK){
			return 1;
		}else if(type==DBInfo.QUESTION_TYPE_MULTIPLE_CHOICE){
			return 1;
		}else if(type==DBInfo.QUESTION_TYPE_MULTI_ANSWER){
			int res=1;
			for(String key : answers.keySet()){
				for(int i=0; i<key.length(); i++){
					if(key.charAt(i)==':'){
						res++;
					}
				}
			}
			return res;
		}else if(type==DBInfo.QUESTION_TYPE_MATCHING){
			return answers.size();
		}else if(type==DBInfo.QUESTION_TYPE_MULTIPLE_CHOICE_WITH_MULTIPLE_ANSWERS){
			return 4;
		}else if(type==DBInfo.QUESTION_TYPE_PICTURE_RESPONSE){
			return 1;
		}else{
			return 1;
		}
	}


	private String getQuestionText(JSONObject jsonObject) throws JSONException{
		return jsonObject.getString("questionText");
	}
	
	
	private String getQuestionOrder(JSONObject jsonObject) throws JSONException{
		return jsonObject.getString("answerOrder");
	}
	
	
	private String getCheckType(JSONObject jsonObject) throws JSONException{
		return jsonObject.getString("checkType");
	}
	
	private int getTime(JSONObject jsonObject) throws JSONException{
		int time=-1;
		try{
			time=Integer.parseInt(jsonObject.getString("time"));
		}catch(NumberFormatException e){
			
		}
		return time;
	}
	
	
	private HashMap<String, String> getAnswers(JSONObject jsonObject) throws JSONException{
		JSONObject answers=new JSONObject(jsonObject.getString("answer"));
		Iterator<String> it=answers.keys();
		HashMap<String, String> map=new HashMap<String, String>();
		while(it.hasNext()){
			String ans=it.next();
			map.put(ans, answers.getString(ans));
		}
		return map;
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] q=request.getParameterValues("question");
		JSONObject[] questions=new JSONObject[q.length];
		for(int i=0; i<q.length; i++){
			try {
				questions[i]=new JSONObject(q[i]);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			QuizManager qm=(QuizManager) this.getServletContext().getAttribute(WebSiteInfo.QUIZ_MANAGER_ATTR);
			String quizName=request.getParameter("quizName");
			String quizDescription=request.getParameter("quizDescription");
			String order=request.getParameter("orderOfQuestions");
			String practicemode=request.getParameter("practiceMode");
			boolean orderr=false;
			boolean practice=false;
			if(order.equals("randomOrder"))orderr=true;
			if(practicemode.equals("on"))practice=true;
			Quiz qq=new Quiz(quizName, quizDescription, orderr, practice);
			qm.addQuiz(qq);
			processQuestions(questions, qm.getQuizID(qq));
			response.sendRedirect("CreateQuiz.html");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
