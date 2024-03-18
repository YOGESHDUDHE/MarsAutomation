package tests.LoginApp;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LowerEnvironmentSmoke1 extends BaseExecutor {
	public static final String TESTCASENAME = "LowerEnvironmentSmoke1";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "LoginApp";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "TEST999";
	public static final String ENVIRONMENT = "test"; // user test or prod only
	private ExtentTest testlog;

	@BeforeClass
	public void preCondition() {
		initiateTest();
		testlog = extent.createTest(TESTCASENAME);
	}

	@Test
	public void ProdSmoke() throws Exception {
		// start test
		testlog.log(openUrl("https://azr-eus2w6116:8888/LoginAppTest/"), "Launch LoginApp");

		// validate LOGIN Link
		testlog.log(waitForElement("id", LoginApp.HomePage.LNK_LOGIN), "Verify Login Link");
		// validate Singup Link
		testlog.log(waitForElement("id", LoginApp.HomePage.LNK_SIGNUP), "Verify Singup Link");
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