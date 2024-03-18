package tests.ppm;

import java.io.IOException;
import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.framework.ReportDatabase;
import com.framework.TestData;
import com.object_repository.PPM;
import actions.PPMAutomation;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC06_Migrate_the_Project extends PPMAutomation {
	public static final String TESTCASENAME = "TC06_Migrate_the_Project";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "PPM";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "TEST686";
	public static final String ENVIRONMENT = "Test";
	private ExtentTest testlog;
	public static String migratedprojectid = "";

	@BeforeClass
	public void preCondition() {
		driver = initiateTest();
		testlog = extent.createTest(TESTCASENAME);
	}

	@Test
	public void tC06_Migrate_the_Project() throws InterruptedException, IOException {
		// < Opens PPM Test Application >
		testlog.log(openUrl("https://ppmtest2.mars-ad.net/"), "open url");
		syncBrowser();
		// 3rd Scenario - FIND THE PROJECT
		testlog.log(findTheProject(TC02_Creation_of_a_Project.newprojectid), "Find the project");
		testlog.log(Status.INFO, "Case6: Migrate project");
		// 6th scenario
		testlog.log(clickOnElement("id", PPM.stageScreen.MANAGEPROJECT), "Click on manage project");
		// migrate or copy
		testlog.log(clickOnElementByJavascript("xpath", PPM.stageScreen.MIGRATEORCOPY), " Click on Migrate copy");
		testlog.log(Status.INFO, "Migrating into new project", MediaEntityBuilder
				.createScreenCaptureFromPath(takeScreenShot("PPM", "Migrating into new project")).build());
		syncBrowser();
		testlog.log(changeFrame(PPM.findScreenPage.CHANGEFRAME), "Swtich to Frame");
		// go to migrate map and select Confectionery - From NPR to QuEST
		testlog.log(clickOnElement("id", PPM.migrateTheProject.MIGRATEMAP), "Click on Migrate map");
		testlog.log(
				enterValues("id", PPM.migrateTheProject.MIGRATEMAP,
						getPPMTestData("PPM_Conf", "Confectionery - From NPR to QuEST")),
				"Select Confectionery - From NPR to QuEST");
		// click on continue and Finish
		testlog.log(scrollToElement("cssSelector", PPM.findScreenPage.CONTINUE), "Scroll to continue");
		syncBrowser();
		testlog.log(waitForElementClickable("cssSelector", PPM.findScreenPage.CONTINUE),
				"Wait For Element to be clickable");
		testlog.log(clickOnElement("cssSelector", PPM.findScreenPage.CONTINUE), "Click on continue");
		releaseFromFrame();
		testlog.log(changeFrame(PPM.findScreenPage.CHANGEFRAME), "Swtich to Frame");
		syncBrowser();
		testlog.log(scrollToElement("cssSelector", PPM.findScreenPage.FINISH), "Scroll to finish");
		syncBrowser();
		testlog.log(waitForElementClickable("cssSelector", PPM.findScreenPage.FINISH),
				"Wait For Element to be clickable");
		testlog.log(clickOnElement("cssSelector", PPM.findScreenPage.FINISH), "Click on finish");
		releaseFromFrame();
		// buffer the project Id
		testlog.log(clickOnElement("id", PPM.newProjectpage.LNK_PROJECTID), "Buffer the project ID");
		testlog.log(Status.INFO, "Buffer the Migrated project ID", MediaEntityBuilder
				.createScreenCaptureFromPath(takeScreenShot("PPM", "Buffer the Migrated project ID")).build());
		testlog.log(Status.INFO, "ProjectID after migration :" + getText("id", PPM.newProjectpage.LNK_PROJECTID));
		syncBrowser();
		migratedprojectid = getText("id", PPM.newProjectpage.LNK_PROJECTID);
		testlog.log(Status.INFO, "ProjectID after migration :" + migratedprojectid);
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
