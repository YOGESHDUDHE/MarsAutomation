package tests.ppm;

import java.util.HashMap;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.ReportDatabase;
import com.framework.TestData;
import actions.PPMAutomation;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC03_Find_the_Project extends PPMAutomation {
	public static final String TESTCASENAME = "TC03_Find the Project";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "PPM";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "TEST686";
	public static final String ENVIRONMENT = "Test";
	private ExtentTest testlog;

	@BeforeClass
	public void preCondition() {
		driver = initiateTest();
		testlog = extent.createTest(TESTCASENAME);
	}

	@Test
	public void tc03_Find_the_Project() throws InterruptedException {
		// Opens PPM Test Application
		testlog.log(openUrl("https://ppmtest2.mars-ad.net/"), "open url");
		// 3rd Scenario - FIND THE PROJECT
		testlog.log(findTheProject(TC02_Creation_of_a_Project.newprojectid), "Find the project");
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