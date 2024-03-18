package tests.ppm;

import java.io.IOException;
import java.util.HashMap;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.framework.TestData;
import com.object_repository.*;
import actions.PPMAutomation;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC05_Input_the_variables extends PPMAutomation {
	public static final String TESTCASENAME = "TC05_input_the_variables";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "PPM";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "TEST686";
	public static final String ENVIRONMENT = "Test"; // user test or prod only
	private ExtentTest testlog;

	@BeforeClass
	public void preCondition() {
		driver = initiateTest();
		testlog = extent.createTest(TESTCASENAME);
	}

	@Test
	public void tC05_Input_the_variables() throws InterruptedException, IOException {
		// < Opens PPM Test Application >
		testlog.log(openUrl("https://ppmtest2.mars-ad.net/"), "open url");
		syncBrowser();
		// 3rd Scenario - FIND THE PROJECT
		testlog.log(findTheProject(TC02_Creation_of_a_Project.newprojectid), "Find the project");
		testlog.log(Status.INFO, "Case5: Input the deliverable");
		// 5th Scenario - INPUT THE DELIVERABLE
		// Click on Stages Tab
		testlog.log(waitForElementClickable("xpath", PPM.modificationAfterDevServerUpdate.CLK_STAGES),
				"Wait for element clickable");
		testlog.log(clickOnElement("xpath", PPM.modificationAfterDevServerUpdate.CLK_STAGES), "Click on Stages Tab");
		syncBrowser();
		// Select Project Owner and click on Apply
		testlog.log(clickOnElement("xpath", PPM.stageScreen.CLK_ONOWNER), "Click on Owner");
		testlog.log(
				selectFromDrpDwnByText("xpath", PPM.stageScreen.DRP_DWN, getPPMTestData("PPM_Conf", "Select_Owner")),
				"select owner");
		testlog.log(Status.INFO, "Selecting Owners",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Selecting Owners")).build());
		testlog.log(clickOnElement("id", PPM.stageScreen.BTN_APPLYFORSTAGES), "Click on Apply Button");
		// Click on Quest : Project Charter
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		testlog.log(clickOnElementByJavascript("xpath", PPM.findScreenPage.PROJECTCHARTER),
				"Click on Quest : ProjectCharter");
		syncBrowser();
		// UnExpected Pop up
		 testlog.log(clickOnElement("xpath", PPM.stageScreen.Done), "Click on Done");
		syncBrowser();
		// Enter the variables in the respective fields
		testlog.log(clickOnElement("xpath", PPM.stageScreen.TEXTCRITICALSUCCESSFACTOR), "Click on CSF1 text box");
		testlog.log(enterValues("xpath", PPM.stageScreen.TEXTCRITICALSUCCESSFACTOR,
				getPPMTestData("PPM_Conf", "Critical_Success_Factor")), "Enter CSF");
		testlog.log(enterValues("xpath", PPM.stageScreen.DRP_DWN_CSFSTATUS, getPPMTestData("PPM_Conf", "Csf_status")),
				"Select CSF Status");
		testlog.log(clickOnElement("xpath", PPM.stageScreen.TARGETTEXT), "Click on Target text box");
		testlog.log(enterValues("xpath", PPM.stageScreen.TARGETTEXT, getPPMTestData("PPM_Conf", "Target")),
				"Enter Target details");
		testlog.log(
				enterValues("xpath", PPM.stageScreen.DRP_DWN_TARGETSTATUS, getPPMTestData("PPM_Conf", "Target_status")),
				"Select Target Status");
		testlog.log(Status.INFO, "Providing inputs",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Providing inputs")).build());
		// Click on Apply button & Close
		testlog.log(clickOnElement("xpath", PPM.stageScreen.BTN_APPLY), "Click on Apply Button");
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		testlog.log(clickOnElementByJavascript("xpath", PPM.stageScreen.BTN_CLOSE), "Click on Close Button");
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