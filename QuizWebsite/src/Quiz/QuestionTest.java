package Quiz;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class QuestionTest {
	
	

	
	private Question_Response qr;
	private Matching match;
	private Multi_Answer multiAnsOrd;
	private Multi_Answer multiAnsUnord;
	private Multiple_Choice_Multiple_Answer multChoiceMultAns;
	private Multiple_Choice multChoice;
	
	
	
	/* creates question response object*/
	private void setResponse(){
		HashMap<String, String> qrAns=new HashMap<String, String>();
		qrAns.put("pasuxi", "TRUE");
		qr=new Question_Response("kitxva", qrAns, 2, "FALSE", "COMPUTER", -1, 1);
	}
	
	/* creates matching object*/
	private void setMatching(){
		int numOfPairs=5;
		HashMap<String, String> ans=new HashMap<String, String>();
		for(int i=0; i<numOfPairs; i++){
			ans.put("left"+i+":"+"right"+i, "TRUE");
		}
		match=new Matching("pasuxi", ans, 2, "FALSE", "COMPUTER", 10, numOfPairs);
	}
	
	
	/* creates multiAnswer object*/
	private void setMultiAnswer(){
		HashMap<String, String> ans=new HashMap<String, String>();
		String answer="0";
		for(int i=1; i<6; i++){
			answer+=":"+i;
		}
		ans.put(answer, "TRUE");
		multiAnsOrd=new Multi_Answer("kitxva", ans, 2, "TRUE", "COMPUTER", 20, 6);
		multiAnsUnord=new Multi_Answer("kitxva", ans, 2, "FALSE", "COMPUTER", 20, 6);
	}
	
	
	/* creates multipleChoiceMultipleAnswer object*/
	private void setMultChoiceMultAns(){
		HashMap<String, String> ans=new HashMap<String, String>();
		ans.put(0+"", "FALSE");
		ans.put(1+"", "TRUE");
		ans.put(2+"", "TRUE");
		ans.put(3+"", "FALSE");
		multChoiceMultAns=new Multiple_Choice_Multiple_Answer("kitxva", ans, 2, "FALSE", "COMPUTER", 11, 2);
	}
	
	
	/* creates Multiple choice object*/
	private void setMultChoice(){
		HashMap<String, String> ans=new HashMap<String, String>();
		ans.put("0", "FALSE");
		ans.put("1", "FALSE");
		ans.put("2", "TRUE");
		ans.put("3", "FALSE");
		multChoice=new Multiple_Choice("kitxva", ans, 2, "FALSE", "COMPUTER", 11, 1);
	}

	
	/* creates different question types of questions*/
	@Before
	public void setUp() throws Exception {
		setResponse();
		setMatching();
		setMultiAnswer();
		setMultChoiceMultAns();
		setMultChoice();
	}

	/* tests the answer correctness for Qestion_Response, the same applies to Fill_In_The_blank, Picture_Response*/
	@Test
	public void test_Response() {
		assertTrue(qr.getPoints("pasuxi")==1);
		assertTrue(qr.getPoints("pasux")==1);
		assertTrue(qr.getPoints("pasu")==0);
		assertTrue(qr.getPoints("pasuxii")==1);
		assertTrue(qr.getPoints("asuxi")==1);
		assertTrue(qr.getPoints("asuxii")==0);
		assertTrue(qr.getPoints("paux")==0);
	}
	
	
	/* tests matching points */
	@Test
	public void test_Matching() {
		String ans="left"+0+" :right"+0;
		int numOfQuestions=5;
		for(int i=1; i<numOfQuestions; i++){
			ans+=":left"+i+" :"+"right"+i;
		}
		assertTrue(match.getPoints(ans)==5);
		
		ans="left"+0+" :right"+0;
		for(int i=1; i<numOfQuestions; i++){
			ans+=":left"+(i+1)+" :"+"right"+(i+1);
		}
		
		assertTrue(match.getPoints(ans)==4);
		
		
		ans="left"+0+" :right"+0;
		for(int i=1; i<numOfQuestions; i++){
			ans+=":left"+(i+1)+" :"+"right"+i;
		}
		
		
		assertTrue(match.getPoints(ans)==1);
	}
	
	
	
	/* tests multiAnswer points */
	@Test
	public void test_multiAnswer(){
		assertTrue(multiAnsOrd.getPoints("0:1:2:3:4:5")==6);
		assertTrue(multiAnsUnord.getPoints("1:0:2:3:5:4")==6);
		
		assertTrue(multiAnsOrd.getPoints("1:0:2:3:5:4")==2);
		assertTrue(multiAnsUnord.getPoints("7:6:8:9:10:8")==0);
		assertTrue(multiAnsOrd.getPoints("5:3:4:2:1")==0);
		assertTrue(multiAnsUnord.getPoints("0:2::::")==2);
	}
	
	/* tests multiplechoicemultipleanswer points */
	@Test
	public void multChoiceMultAnswer(){
		assertTrue(multChoiceMultAns.getPoints("0:3")==0);
		assertTrue(multChoiceMultAns.getPoints("0")==0);
		assertTrue(multChoiceMultAns.getPoints("3")==0);
		assertTrue(multChoiceMultAns.getPoints("0:3:1")==0);
		assertTrue(multChoiceMultAns.getPoints("1")==1);
		assertTrue(multChoiceMultAns.getPoints("2")==1);
		assertTrue(multChoiceMultAns.getPoints("1:2")==2);
		assertTrue(multChoiceMultAns.getPoints("1:2:0:3")==0);
		assertTrue(multChoiceMultAns.getPoints("0:3:1")==0);
	}
	
	
	/* tests multiplechoice points */
	@Test
	public void multChoice(){
		assertTrue(multChoice.getPoints("1")==0);
		assertTrue(multChoice.getPoints("2")==1);
		assertTrue(multChoice.getPoints("3")==0);
		assertTrue(multChoice.getPoints("0")==0);
	}

}
