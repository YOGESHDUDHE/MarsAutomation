package tests.Nexus;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.*;
import com.object_repository.*;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import actions.NexusDemerge;

public class DemergeDocumentControl_AllApproved extends NexusDemerge {
	
	public static final String TESTCASENAME = "DemergeDocumentControl2";
	public static final int MANUALEXECUTIONTIME = 300000;
	public static final String APPLICATIONNAME = "";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "";
	public static final String ENVIRONMENT = "test"; // user test or prod only
	private ExtentTest testlog;
	private NexusDemerge nexusDemergeAction;
	private static WebDriver driver =  null;
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	@BeforeClass
	public void preCondition1() {
		System.out.println("@BeforeClass - " + TESTCASENAME);
		driver = initiateTest();
		testlog = extent.createTest(TESTCASENAME);
		nexusDemergeAction = new NexusDemerge(driver);

		long id = Thread.currentThread().getId();
		System.out.println(TESTCASENAME + ". Thread id is: " + id);
	}

	@Test
	public void process() {
		// start test
		testlog.log(openUrl("https://nexus-qa.mars/"), "Launch Nexus");

		testlog.log(nexusDemergeAction.loginToNexus(), "Login to Application");

		testlog.log(clickOnElement("xpath", Nexus.Menu.SPAN_DOCUMENT_CONTROL), "Document control");
		syncBrowser();
		testlog.log(nexusDemergeAction.openAllApprovedDocuments(), "Open All approved ");
		syncBrowser();
		testlog.log(nexusDemergeAction.searchForLocation("Lavazza : Drinks : Global Drinks : FSQ - Machine"),
				"seach for location");
		
		int i=1;
		for(;i<10;i++) {
			if(i%1==0) {
				testlog.log(Status.INFO,"Started iteration - " + i);
			}		
			log.info("Iteration #" +i);
			syncBrowser();
			clickOnElement("xpath", Nexus.DocumentContorl.FIRST_ROW);
			
			clickOnElement("id", Nexus.DocumentContorl.LNK_OBSOLETE_REQUEST);
			syncBrowser();
			enterValues("id", Nexus.DocumentContorl.TXT_REASON_FOR_OBSOLETION, "Enter by automation "+i);
			syncBrowser();
			clickOnElement("xpath", Nexus.DocumentContorl.APPROVED_TAB);
			syncBrowser();
			clickOnElement("id", Nexus.RoutingDialog.LNK_CANCEL);
			syncBrowser();
			clickOnElement("id", Nexus.DocumentContorl.LNK_CANCEL);
			
			handleBrowserAlert("*","accept");
			syncBrowser();
			clickOnElement("id", Nexus.DocumentContorl.LNK_CANCEL);
			//testlog.log(handleBrowserAlert(), "press ok on modal dialog");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		testlog.log(Status.INFO,"Completed iteration - " + i);
	}

	@AfterClass
	public void postCondition() throws Exception {
		System.out.println("@AfterClass - "+TESTCASENAME);
		HashMap<Object, Object> testCaseData = new HashMap<>();
		ReportDatabase objReport = new ReportDatabase();

		testCaseData.put("testCaseName", TESTCASENAME);
		testCaseData.put("manualExecutionTime", MANUALEXECUTIONTIME);
		testCaseData.put("applicationName", APPLICATIONNAME);
		testCaseData.put("geography", GEOGRAPHY);
		testCaseData.put("segment", SEGMENT);
		testCaseData.put("projectID", PROJECTID);
		testCaseData.put("environment", ENVIRONMENT);

		objReport.insertIntoReportDB(testlog, testCaseData);
		driver.quit();
		setTestResults(testlog);
	}

}