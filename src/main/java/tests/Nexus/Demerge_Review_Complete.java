package tests.Nexus;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.*;
import com.object_repository.*;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import actions.NexusDemerge;

public class Demerge_Review_Complete extends NexusDemerge {

	public static final String TESTCASENAME = "DemergeDocumentControl";
	public static final int MANUALEXECUTIONTIME = 300000;
	public static final String APPLICATIONNAME = "";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "";
	public static final String ENVIRONMENT = "test"; // user test or prod only
	private ExtentTest testlog;
	private NexusDemerge nexusDemergeAction;
	private static WebDriver driver = null;

	@BeforeClass
	public void preCondition1() {
		driver = initiateTest();
		testlog = extent.createTest(TESTCASENAME);
		nexusDemergeAction = new NexusDemerge(driver);
	}

	@Test
	public void process() {
		// start test
		testlog.log(openUrl("https://nexus-qa.mars/"), "Launch Nexus");

		testlog.log(nexusDemergeAction.loginToNexus(), "Login to Application");

		testlog.log(clickOnElement("xpath", Nexus.Menu.SPAN_DOCUMENT_CONTROL), "Document control");

		testlog.log(nexusDemergeAction.openReviewRecordsPendingByOwner(), "Open pending orders by owner");
		
		String location = "Lavazza : Drinks : Global Drinks";
		
		testlog.log(nexusDemergeAction.searchForLocation(location), "seach for location - " + location);
		syncBrowser();
		
		testlog.log(clickOnElement("id", Nexus.Menu.CHKBOX_SELECT_ALL), "Select all result items");
		syncBrowser();
		
		testlog.log(clickOnElement("id", Nexus.Menu.LNK_ACTION_ON_SELECTED_DOCUMENTS),
				"open menu for selected documents");

		testlog.log(clickOnElement("id", Nexus.Menu.LNK_REASSIGN_DOCUMENTS), "Click on Reassign documents");

		
		testlog.log(nexusDemergeAction.assignToUser("jeena"), "Click on NO EMAILs");
		
		Integer i = 0;
		String reviewNumber = "";
		String documentNumber = "";
		TestData objTestData = new TestData();
		JSONObject json = new JSONObject();
		while (waitForElement("xpath", Nexus.DocumentContorl.FIRST_ROW) == Status.PASS) {
			i++;
			syncBrowser();
			reviewNumber = getText("xpath", Nexus.DocumentContorl.FIRST_ROW_NUMBER);
			documentNumber = getText("xpath", Nexus.DocumentContorl.FIRST_DOCUMENT_NUMBER);
			//Below if condition is holding execution upto 3 times only
			if (i < 3) {
				testlog.log(nexusDemergeAction.reviewCompleteFirstOrder(), "Closed Item - " + reviewNumber);
				json.put("item", i.toString());
				json.put("location", location);
				json.put("ReviewNumber", reviewNumber);
				json.put("DocumentNumber", documentNumber);
				String a = objTestData.writeDataToAPI("Nexus", "Review_Records", json);
				System.out.println("Entered with Entry - " + a);

			} else {
				break;
			}
		}

	}

	@AfterClass
	public void postCondition() throws Exception {
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