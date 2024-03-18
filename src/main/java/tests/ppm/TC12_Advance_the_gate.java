package tests.ppm;

import java.io.IOException;
import java.util.HashMap;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.framework.ReportDatabase;
import com.object_repository.*;
import actions.PPMAutomation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC12_Advance_the_gate extends PPMAutomation {
	public static final String TESTCASENAME = "TC12_Advance_the_gate";
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
	public void tc12_Advance_the_gate() throws InterruptedException, IOException {
		// Opens PPM Test Application
		testlog.log(openUrl("https://ppmtest2.mars-ad.net/"), "open url");
		// 3 rd scenario
		testlog.log(findTheProject(TC06_Migrate_the_Project.migratedprojectid), "Find the project");
		// 12th scenario
		// click on idea generation DB
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.CLK_IDEAGENERATION),
				"Click on idea generation DB");
		// click gates tab
		testlog.log(clickOnElement("xpath", PPM.advanceTheGate.GATES_TAB), "Click on gates tab");
		syncBrowser();
		// click on gate decision
		testlog.log(hoverOverElement("xpath", PPM.advanceTheGate.CLK_GATEDESICION), "Hover over element ");
		testlog.log(clickOnElement("xpath", PPM.advanceTheGate.CLK_GATEDESICION), "Click on gate decision");
		// click on approved
		testlog.log(clickOnElement("xpath", PPM.advanceTheGate.CLK_APPROVED), "Click on approved");
		testlog.log(Status.INFO, "Moving Gate",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Moving Gate")).build());
		// click on apply
		testlog.log(clickOnElement("xpath", PPM.advanceTheGate.CLK_APPLY), "Click Apply");
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