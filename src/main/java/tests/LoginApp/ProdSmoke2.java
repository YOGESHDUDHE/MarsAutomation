package tests.LoginApp;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class ProdSmoke2 extends BaseExecutor {
	public static final String TESTCASENAME = "ProdSmoke2";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "LoginApp";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "TEST999";
	public static final String ENVIRONMENT = "prod"; // user test or prod only
	private ExtentTest testlog;
	private static WebDriver driver =  null;

	@BeforeClass
	public void preCondition1() {
		System.out.println("@BeforeClass - " + TESTCASENAME);
		driver = initiateTest();
		testlog = extent.createTest(TESTCASENAME);

		long id = Thread.currentThread().getId();
		System.out.println(TESTCASENAME + ". Thread id is: " + id);

	}

	@Test
	public void ProdSmoke() {
		// start test
		testlog.log(openUrl("https://azr-eus2w6116:8888/LoginApp/"), "Launch LoginApp");
		/*
		 * // validate LOGIN Link testlog.log(waitForElement("id",
		 * LoginApp.HomePage.LNK_LOGIN), "Verify Login Link"); // validate Singup Link
		 * testlog.log(waitForElement("id", LoginApp.HomePage.LNK_SIGNUP),
		 * "Verify Singup Link");
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