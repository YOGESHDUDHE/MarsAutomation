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

public class TC02_Creation_of_a_Project extends PPMAutomation {
	public static final String TESTCASENAME = "TC03_Find the Project";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "PPM";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "TEST686";
	public static final String ENVIRONMENT = "Test";
	private ExtentTest testlog;
	public static String newprojectid = "";

	@BeforeClass
	public void preCondition() {
		driver = initiateTest();
		testlog = extent.createTest(TESTCASENAME);
	}

	@Test
	public void tc03_Find_the_Project() throws InterruptedException, IOException {
		// first scenario
		testlog.log(openUrl("https://ppmtest2.mars-ad.net/"), "open url");
		// Clicks on New Project
		testlog.log(Status.INFO, "Case no2: Creation of project...");
		testlog.log(clickOnElement("xpath", PPM.ppmHomePage.LNK_PROJECT), "Click on Project");
		testlog.log(hoverOverElement("xpath", PPM.ppmHomePage.LNK_ADD_NEW_PROJECT), "Click on Add new");
		testlog.log(Status.INFO, "create new project",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "create new project")).build());
		testlog.log(clickOnElement("xpath", PPM.ppmHomePage.LNK_CONFECTIONERY), "Click on  confectionery Project");
		syncBrowser();
		// Enter Project Name & Access groups
		testlog.log(enterValues("id", PPM.addNewScreenpage.LNK_PROJECTNAME, getPPMTestData("PPM_Conf", "Project_Name")),
				"Enter Project Name in Name field");
		syncBrowser();
		testlog.log(
				enterValues("id", PPM.addNewScreenpage.LNK_ACCESSGROUP, getPPMTestData("PPM_Conf", "Access_Groups")),
				"Enter Access group");
		syncBrowser();
		// Select NPR Approval Date
		testlog.log(clickOnElement("xpath", PPM.modificationAfterDevServerUpdate.NPR_APPROVAL),
				"Click on NPR Approval");
		testlog.log(enterValuesAndPressEnter("xpath", PPM.addNewScreenpage.SELECT_NPR,
				getPPMTestData("PPM_Conf", "NPR_Date")), "Enter Enter date for NPR approval");
		syncBrowser();
		// select period
		testlog.log(clickOnElement("xpath", PPM.modificationAfterDevServerUpdate.LAUNCH_PERIOD), "Click on period");
		testlog.log(selectFromDrpDwnByText("xpath", PPM.addNewScreenpage.DRPDWN_DATASELECT,
				getPPMTestData("PPM_Conf", "Period")), "Select period");
		// Select Launch year
		testlog.log(clickOnElement("xpath", PPM.modificationAfterDevServerUpdate.LAUNCH_YEAR), "Click on launch year");
		testlog.log(selectFromDrpDwnByText("xpath", PPM.addNewScreenpage.DRPDWN_DATASELECT,
				getPPMTestData("PPM_Conf", "Launch_Year")), "Select launch year");
		// Select Business Units
		testlog.log(clickOnElement("xpath", PPM.modificationAfterDevServerUpdate.BUSSINESS_UNIT),
				"Click on category business unit");
		testlog.log(selectFromDrpDwnByText("xpath", PPM.addNewScreenpage.DRPDWN_DATASELECT,
				getPPMTestData("PPM_Conf", "Business_Unit")), "Select category business unit");
		// Select Volume Units
		testlog.log(clickOnElement("xpath", PPM.modificationAfterDevServerUpdate.VOLUME_UNIT),
				"Select Conf Volume Units");
		testlog.log(selectFromDrpDwnByText("xpath", PPM.addNewScreenpage.DRPDWN_DATASELECT,
				getPPMTestData("PPM_Conf", "Volume_Unit")), "Select category Volume  unit");
		testlog.log(Status.INFO, "specified values to  create new project", MediaEntityBuilder
				.createScreenCaptureFromPath(takeScreenShot("PPM", "specified values to  create new project")).build());
		// Click on Create
		testlog.log(clickOnElement("id", PPM.addNewScreenpage.BTN_CREATE_PROJECT), "Click on create");
		// Buffer Project ID
		testlog.log(clickOnElement("id", PPM.newProjectpage.LNK_PROJECTID), "Buffer the project ID");
		testlog.log(Status.INFO, "Buffer the Project ID",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "verifying_input")).build());
		newprojectid = getText("id", PPM.newProjectpage.LNK_PROJECTID);
		testlog.log(Status.INFO, "newly created project id" + newprojectid);
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