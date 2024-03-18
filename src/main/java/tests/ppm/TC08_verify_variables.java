
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

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC08_verify_variables extends PPMAutomation {
	public static final String TESTCASENAME = "TC08_verify variables";
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
	public void tc08_verify_variables() throws InterruptedException, IOException {
		String csf = "";
		String csf_sta = "";
		String targ = "";
		String targ_sta_val = "";
		// < Opens PPM Test Application >
		testlog.log(openUrl("https://ppmtest2.mars-ad.net/"), "open url");
		// 3rd Scenario - FIND THE PROJECT
		testlog.log(findTheProject(TC06_Migrate_the_Project.migratedprojectid), "Find the project");
		testlog.log(Status.INFO, " Case8: Input the deliverable");
		// 8th scenario
		// click on stages tab
		testlog.log(waitForElementClickable("xpath", PPM.modificationAfterDevServerUpdate.CLK_STAGES),
				"Wait for element clickable");
		testlog.log(clickOnElement("xpath", PPM.modificationAfterDevServerUpdate.CLK_STAGES), "Click on Stages Tab");
		syncBrowser();
		// click on Project description owner
		testlog.log(clickOnElement("xpath", PPM.stageScreen.PROJECTDESCRIPTION_OWNER), "Select owner");
		testlog.log(selectFromDrpDwnByText("xpath", PPM.stageScreen.DRP_DWN,
				getPPMTestData("PPM_Conf", "Owner_for_migrated")), "Select owner");
		testlog.log(Status.INFO, "SelectOwner For Migrated Project", MediaEntityBuilder
				.createScreenCaptureFromPath(takeScreenShot("PPM", "SelectOwner For Migrated Project")).build());
		// click on apply
		testlog.log(clickOnElement("id", PPM.teamsPage.BTN_APPLY), "apply");
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		// click on project description tracker
		testlog.log(clickOnElement("xpath", PPM.stageScreen.PROJECTDESCRIPTIONTRACKER),
				"Click on project description tracker");
		// get the values and verify
		syncBrowser();
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.CSFVALUE), "Buffer the CSf Text");
		csf = getValue("xpath", PPM.projectDescriptionTracker.CSFVALUE);
		testlog.log(Status.INFO, csf);
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.CSFSTATUSVALUE),
				"buffer the CSF status value Text");
		csf_sta = getValue("xpath", PPM.projectDescriptionTracker.CSFSTATUSVALUE);
		testlog.log(Status.INFO, csf_sta);
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.TARGETVALUE), "Buffer the taget text");
		targ = getValue("xpath", PPM.projectDescriptionTracker.TARGETVALUE);
		testlog.log(Status.INFO, targ);
		testlog.log(Status.INFO, "verifying inputs",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "verifying_inputs")).build());
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.TARGETSTATUSVALUE),
				"Buffer the targetstatusvalue Text");
		targ_sta_val = getValue("xpath", PPM.projectDescriptionTracker.TARGETSTATUSVALUE);
		testlog.log(Status.INFO, targ_sta_val);
		if ((csf.contentEquals(getPPMTestData("PPM_Conf", "Critical_Success_Factor")))) {
			testlog.log(Status.PASS, "CSF1 filed  verified");
		} else {
			testlog.log(Status.FAIL, "CSF1 filed not verified");
		}
		if (csf_sta.contentEquals(getPPMTestData("PPM_Conf", "Csf_status"))) {
			testlog.log(Status.PASS, "CSF1_Status  verified");
		} else {
			testlog.log(Status.FAIL, "CSF1_Status  not verified");
		}

		if ((targ.contentEquals(getPPMTestData("PPM_Conf", "Target")))) {
			testlog.log(Status.PASS, "Target1 field  verified");
		} else {
			testlog.log(Status.FAIL, "Target1 field not verified");
		}
		if ((targ_sta_val.contentEquals(getPPMTestData("PPM_Conf", "Target_status")))) {
			testlog.log(Status.PASS, "Target1_status  verified");
		} else {
			testlog.log(Status.FAIL, "Target1_status not verified");
		}
		testlog.log(clickOnElement("xpath", PPM.stageScreen.BTN_CLOSE), "Click on Close Button");
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