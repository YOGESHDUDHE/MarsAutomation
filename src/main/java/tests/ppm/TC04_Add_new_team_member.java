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

public class TC04_Add_new_team_member extends PPMAutomation {
	public static final String TESTCASENAME = "TC04_Add_new_team_member";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "PPM";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "TEST686";
	public static final String ENVIRONMENT = "test"; // user test or prod only
	private ExtentTest testlog;

	@BeforeClass
	public void preCondition() {
		driver = initiateTest();
		testlog = extent.createTest(TESTCASENAME);
	}

	@Test
	public void tC04_Add_new_team_member() throws InterruptedException, IOException {
		// Opens PPM Test Application
		testlog.log(openUrl("https://ppmtest2.mars-ad.net/"), "open url");
		testlog.log(findTheProject(TC02_Creation_of_a_Project.newprojectid), "Find the project");
		// 4th scenario - ADD A NEW USER
		syncBrowser();
		testlog.log(clickOnElementByJavascript("xpath", PPM.modificationAfterDevServerUpdate.CLK_TEAM),
				"Click on Teams Tab");
		syncBrowser();
		// Add User
		testlog.log(clickOnElement("id", PPM.teamsPage.BTN_ADDUSER), "Click on Add Users");
		testlog.log(Status.INFO, "Adding user",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Adding user")).build());
		syncBrowser();
		// Enter user name
		testlog.log(clickOnElement("id", PPM.teamsPage.CLK_USERNAME), "Click on Username Text Box");
		testlog.log(enterValues("id", PPM.teamsPage.CLK_USERNAME, getPPMTestData("PPM_Conf", "New_User_Name1")),
				"Enter Username and press enter");
		syncBrowser();
		testlog.log(hoverOverElement("xpath", PPM.modificationAfterDevServerUpdate.SELECT_USER),
				"Select the displayed user");
		testlog.log(clickOnElement("xpath", PPM.modificationAfterDevServerUpdate.SELECT_USER),
				"Select the displayed user");
		testlog.log(scrollToElement("xpath", PPM.teamsPage.BTN_ADDSELECTEDUSER), "Scroll to element");
		testlog.log(clickOnElement("xpath", PPM.teamsPage.BTN_ADDSELECTEDUSER), "Add the selected user");
		testlog.log(Status.INFO, "Add selected user",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Add selected user")).build());
		// Click on Done
		testlog.log(clickOnElement("xpath", PPM.modificationAfterDevServerUpdate.CLK_DONE), "Click on Done");
		// click on Apply button
		testlog.log(clickOnElement("id", PPM.teamsPage.BTN_APPLY), "Click on Apply Button");
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