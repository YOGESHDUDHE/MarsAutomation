
package tests.ppm;

import java.io.IOException;
import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

//import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.framework.TestData;
import com.object_repository.*;
import actions.PPMAutomation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC09_input_the_task extends PPMAutomation {
	public static final String TESTCASENAME = "TC09_input_the_task";
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
	public void tc09_input_the_task() throws InterruptedException, IOException {
		// < Opens PPM Test Application >
		testlog.log(openUrl("https://ppmtest2.mars-ad.net/"), "open url");
		// 3rd Scenario - FIND THE PROJECT
		testlog.log(findTheProject(TC06_Migrate_the_Project.migratedprojectid), "Find  the project");
		testlog.log(Status.INFO, "Case9: Input the task");
		// 9th Scenar
		testlog.log(waitForElementClickable("xpath", PPM.stageScreen.PROJECTDESCRIPTIONTRACKER),
				"Wait for element clickable");
		testlog.log(clickOnElement("xpath", PPM.stageScreen.PROJECTDESCRIPTIONTRACKER),
				"Click on project description tracker");
		testlog.log(waitForElementClickable("xpath", PPM.projectDescriptionTracker.TASKEXPANDER),
				"Wait for element clickable");
		testlog.log(clickOnElementByJavascript("xpath", PPM.projectDescriptionTracker.TASKEXPANDER), "Expand the task");
		syncBrowser();
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.PROJECTMEETINGMIN),
				"Click on project meeting min");
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.CONSUMERTESTRESULT), "Click on consumer");
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.PLANDATE), "Click on plan date");
		testlog.log(enterValuesAndPressEnter("xpath", PPM.projectDescriptionTracker.PLANDATE,
				getPPMTestData("PPM_Conf", "plandate")), "enter date");
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.ACTUALDATE), "Click on actual date");
		testlog.log(enterValuesAndPressEnter("xpath", PPM.projectDescriptionTracker.ACTUALDATE,
				getPPMTestData("PPM_Conf", "actualdate")), "enter date");
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.CLICKSTATUS), "click on status");
		testlog.log(
				enterValues("xpath", PPM.projectDescriptionTracker.CLICKSTATUS, getPPMTestData("PPM_Conf", "option")),
				"click on inprogress");
		testlog.log(Status.INFO, "click on Progress",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "click on Progress")).build());
		testlog.log(clickOnElementByJavascript("xpath", PPM.projectDescriptionTracker.APPLY), "click on apply");
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		testlog.log(clickOnElementByJavascript("xpath", PPM.stageScreen.BTN_CLOSE), "Click on close");
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