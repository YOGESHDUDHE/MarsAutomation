package tests.Nexus;
import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.framework.*;
import com.object_repository.*;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import actions.NexusDemerge;

public class DemergeDocumentControl extends NexusDemerge {
	
	public static final String TESTCASENAME = "DemergeDocumentControl";
	public static final int MANUALEXECUTIONTIME = 300000;
	public static final String APPLICATIONNAME = "";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "";
	public static final String ENVIRONMENT = "test"; // user test or prod only
	private ExtentTest testlog;
	private NexusDemerge nexusDemergeAction;
	private static WebDriver driver =  null;

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
		System.out.println("@Test - "+TESTCASENAME);
		// start test
		testlog.log(openUrl("https://nexus-qa.mars/"), "Launch Nexus");
		
		  testlog.log(nexusDemergeAction.loginToNexus(), "Login to Application");
		  
		  testlog.log(clickOnElement("xpath", Nexus.Menu.SPAN_DOCUMENT_CONTROL),
		  "Document control");
		  
		  testlog.log(nexusDemergeAction.openReviewRecordsPendingByOwner(),
		  "Open pending orders by owner");
		  
		  testlog.log(nexusDemergeAction.
		  searchForLocation("Lavazza : Drinks : Global Drinks : FSQ - Machine"),
		  "seach for location");
		  
		  testlog.log(clickOnElement("id", Nexus.Menu.CHKBOX_SELECT_ALL),
		  "Select all result items");
		  
		  testlog.log(clickOnElement("id",
		  Nexus.Menu.LNK_ACTION_ON_SELECTED_DOCUMENTS),
		  "open menu for selected documents");
		  
		  testlog.log(clickOnElement("id", Nexus.Menu.LNK_REASSIGN_DOCUMENTS),
		  "Click on Reassign documents");
		  
		  testlog.log(nexusDemergeAction.assignToUser("jeena"), "Click on NO EMAILs");
		 
		
		/*
		 * List<WebElement> el =
		 * driver.findElements(By.xpath(Nexus.DocumentContorl.ROW_ALL));
		 * System.out.println("List Size = " + el.size());
		 * 
		 * try { Thread.sleep(1000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
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