package tests.ppm;

import java.io.IOException;
import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.framework.ReportDatabase;
import com.framework.TestData;
import actions.PPMAutomation;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login_To_PPM extends PPMAutomation {
	public static final String TESTCASENAME = "Login_To_PPM";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "PPM";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "Test686";
	public static final String ENVIRONMENT = "Test"; // user test or prod only
	private ExtentTest testlog;

	@BeforeClass
	public void preCondition() {
		driver = initiateTest();
		testlog = extent.createTest(TESTCASENAME);
	}

	@Test
	public void login_To_PPM() throws IOException {
		// Opens PPM Test Application
		testlog.log(loginToPPM("test", getPPMTestData("PPM_Conf", "username"), getPPMTestData("PPM_Conf", "password")),
				"openurl");
		testlog.log(Status.INFO, "Log into PPM",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Log into PPM")).build());
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
