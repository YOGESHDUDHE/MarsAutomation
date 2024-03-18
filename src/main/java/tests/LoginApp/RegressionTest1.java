package tests.LoginApp;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegressionTest1 extends BaseExecutor {
	public static final String TESTCASENAME = "Regression Test1 - Test Login Page";
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
	public void RegressionTest1_Login_Page() throws Exception {
		// start test
		testlog.log(openUrl("https://azr-eus2w6116:8888/LoginAppTest/"), "Launch LoginApp");

		// Click on login
		testlog.log(clickOnElement("id", LoginApp.HomePage.LNK_LOGIN), "Click Login");

		// validate username exists
		testlog.log(waitForElement("id", LoginApp.Login.TXT_USERNAME), "Verify Username");
		
		// validate password exists
		testlog.log(waitForElement("id", LoginApp.Login.TXT_PASSWORD), "Verify Password");
		
		// validate password exists
		testlog.log(waitForElement("id", LoginApp.Login.BTN_LOGIN), "Verify Login Button");
		
		// validate password exists
		testlog.log(waitForElement("id", LoginApp.Login.BTN_SIGNUP), "Verify Singup Button");

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