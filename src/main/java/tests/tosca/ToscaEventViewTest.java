package tests.tosca;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ToscaEventViewTest extends BaseExecutor {
	public static final String TESTCASENAME = "TOSCA Event view Verification";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "TOSCA Web";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "TEST12";
	public static final String ENVIRONMENT = "test"; // user test or prod only
	private ExtentTest testlog;

	@BeforeClass
	public void preCondition() {
		initiateTest();
		testlog = extent.createTest(TESTCASENAME);
	}

	@Test
	public void toscaEventView() throws Exception {
		// read test data here 

		/*
		 * TestData objTestData = new TestData(); HashMa p<String, String> testData =
		 * objTestData.getDataFromAPI("Atlas_FIN_Period_Tes",
		 * "FB50_Enter_GL_Acc_Document");
		 * System.out.println(testData.get("ProfitCenter2"));
		 */
		/*
		 * if (testData.toString().length() > 2) { testlog.log(Status.PASS,
		 * "Read Data from TDS"); } else { testlog.log(Status.FAIL,
		 * "failed to Read Data from TDS"); }
		 * 
		 */
		// write test data herr
		/*
		 * TestData objTestData = new TestData(); JSONObject json = new JSONObject();
		 * json.put("Company Code", "19988"); json.put("Transaction","Invoice277");
		 * json.put("Vendor Number", "125689"); json.put("Invoice date", "12.2.2021");
		 * // you can use item id to update the row String id =
		 * objTestData.writeDataToAPI("selenium", "test1", json);
		 */
		// Update test data here
		/*
		 * TestData objTestData1 = new TestData(); JSONObject updateJson = new
		 * JSONObject(); updateJson.put("Company Code", "19987");
		 * updateJson.put("Transaction","Invoice267"); updateJson.put("Vendor Number",
		 * "125600"); updateJson.put("Invoice date", "12.2.2021");
		 * objTestData1.updateDataToAPI("selenium", "test1",id, updateJson);
		 */
		// start test
		testlog.log(openUrl("https://vmww7144.mars-ad.net:500/"), "Launch URL");

		// Click on link
		testlog.log(clickOnElement("xpath", TricentisTOSCA.HomePage.LNKEVENTMONITOR), "Click EventMonitor");

		// validate header text
		testlog.log(verifyElementText("xpath", TricentisTOSCA.HomePage.EVENTMONITOR, "Event View"),"Verify Event View");
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