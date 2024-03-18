package tests.tosca;


import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.TricentisTOSCA;

public class ToscaTDMTest extends BaseExecutor {
	public static final String TESTCASENAME = "TOSCA TDM Verification";
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

	public void toscaTDMTest() {

		// start test
		testlog.log(openUrl("https://vmww7144.mars-ad.net:500/"), "Launch TOSCA in Browser");

		// Click on link
		testlog.log(clickOnElement("xpath", TricentisTOSCA.HomePage.LNKTDM), "Click on TDM");

		// validate header text
		testlog.log(verifyElementText("xpath", TricentisTOSCA.HomePage.HEADERTDM, "Test Data Management"),
				"Verify Header Text");
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