
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

public class TC07_Add_new_team_member_for_migrated_project extends PPMAutomation {
	public static final String TESTCASENAME = "TC07_Add_new_team_member_for_migrated_projet";
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
	public void tC07_Add_new_team_member_for_migrated_project() throws InterruptedException, IOException {
		// < Opens PPM Test Application >
		testlog.log(openUrl("https://ppmtest2.mars-ad.net/"), "open url");
		// 3rd Scenario - FIND THE PROJECT
		testlog.log(findTheProject(TC06_Migrate_the_Project.migratedprojectid), "Find the project");
		syncBrowser();
		testlog.log(Status.INFO, "Case7: Add a team member for migrated project ");
		// 7th scenario
		// click on teams tab
		testlog.log(clickOnElement("xpath", PPM.findScreenPage.TEAMS_TAB), "Click on Teams Tab");
		syncBrowser();
		// click on add user
		testlog.log(clickOnElement("id", PPM.teamsPage.BTN_ADDUSER), "Click on Add Users");
		testlog.log(Status.INFO, "Adding Users To Migrated Project", MediaEntityBuilder
				.createScreenCaptureFromPath(takeScreenShot("PPM", "Adding Users To Migrated Project")).build());
		syncBrowser();
		// click on search field
		testlog.log(clickOnElement("id", PPM.teamsPage.CLK_USERNAME), "Click on Username Text Box");
		// enter user name to search
		testlog.log(enterValues("id", PPM.teamsPage.CLK_USERNAME, getPPMTestData("PPM_Conf", "Migrate_new_user")),
				"Enter Username and press enter");
		syncBrowser();
		// select the displayed user
		testlog.log(hoverOverElement("xpath", PPM.teamsPage.SELECT_USER), "Hover Over Dispayed user");
		testlog.log(clickOnElement("xpath", PPM.teamsPage.SELECT_USER), "Select the displayed user");
		// click on add
		testlog.log(clickOnElement("xpath", PPM.teamsPage.BTN_ADDSELECTEDUSER), "Add the selected user");
		// click on done
		testlog.log(clickOnElement("xpath", PPM.teamsPage.BTN_DONE), "Click on Done");
		// click on apply
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