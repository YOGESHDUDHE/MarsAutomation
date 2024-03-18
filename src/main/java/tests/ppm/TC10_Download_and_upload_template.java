
package tests.ppm;

import java.io.*;
import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import com.framework.ExcelCell;
import com.framework.ReportDatabase;
import com.framework.TestData;
import com.object_repository.*;
import actions.PPMAutomation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.AWTException;

public class TC10_Download_and_upload_template extends PPMAutomation {
	public static final String TESTCASENAME = "TC10_Download_and_upload_template";
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
	public void tc10_Download_and_upload_template() throws InterruptedException, IOException, AWTException {
		String location = "";
		testlog.log(openUrl("https://ppmtest2.mars-ad.net/"), "open url");
		syncBrowser();
		// 3 rd scenario
		testlog.log(findTheProject(TC06_Migrate_the_Project.migratedprojectid), "Find the project");
		// 10th Scenario
		testlog.log(waitForElementClickable("xpath", PPM.stageScreen.PROJECTDESCRIPTIONTRACKER),
				"Wait for element clickable");
		testlog.log(clickOnElement("xpath", PPM.stageScreen.PROJECTDESCRIPTIONTRACKER),
				"click on project description tracter");
		// add China to participating markets
		testlog.log(
				clickOnElement("xpath", PPM.projectDescriptionTracker.PROJECTCLASSIFICATION_PARTCIPATINGMARKET_CHINA),
				"select china in parcticipating market");
		// click on right sides button
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.BTN_RIGHT), "Click on [<]");
		// add brazil to participating market
		testlog.log(
				clickOnElement("xpath", PPM.projectDescriptionTracker.PROJECTCLASSIFICATION_PARTCIPATINGMARKET_BRAZIL),
				"select brazil in parcticipating market");
		// click on right sides button
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.BTN_RIGHT), "Click on [<]");
		testlog.log(Status.INFO, "Assigning China and Brazil Markets", MediaEntityBuilder
				.createScreenCaptureFromPath(takeScreenShot("PPM", "Assigning China and Brazil Markets")).build());
		// click on apply
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.BTN_APPLY), "Click on apply");
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		syncBrowser();
		// click on idea generation db
		testlog.log(clickOnElementByJavascript("xpath", PPM.projectDescriptionTracker.CLK_IDEAGENERATION),
				"Click on idea generation DB");
		syncBrowser();
		// go to businees size of prize and select owner as my self
		testlog.log(clickOnElement("xpath", PPM.stageScreen.BUSSINESSSIZEPRIZETOPRIZEOWNER),
				"Select owner for business mode size for prize");
		testlog.log(selectFromDrpDwnByText("xpath", PPM.stageScreen.DRP_DWN,
				getPPMTestData("PPM_Conf", "owner_for_business_sizeofprize")), "Select owner");
		// click on expander
		testlog.log(clickOnElement("xpath", PPM.stageScreen.EXPANDER), "Click on expander");
		// go to china and select owner
		testlog.log(clickOnElement("xpath", PPM.stageScreen.SELECT_OWNER_FOR_CHINA), "Select owner for china ");
		testlog.log(selectFromDrpDwnByText("xpath", PPM.stageScreen.DRP_DWN,
				getPPMTestData("PPM_Conf", "china_market_owner")), "Select owner");
		// click on apply
		testlog.log(clickOnElement("id", PPM.stageScreen.BTN_APPLYFORSTAGES), "Apply button");
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		// click on china
		testlog.log(clickOnElementByJavascript("xpath", PPM.stageScreen.CLK_CHINA), "Click on china ");
		syncBrowser();
		location = getLocationOfFile();
		testlog.log(hoverOverElement("xpath", PPM.projectDescriptionTracker.DOWNLOADTEMPLATE),
				"Click on template to download ");
		// Download template
		testlog.log(clickOnElementByJavascript("xpath", PPM.projectDescriptionTracker.DOWNLOADTEMPLATE),
				"Click on template to download ");
		testlog.log(waitForDownload(location), "Wait for file to download");
		testlog.log(Status.INFO, "Downloading Template",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Downloading Template")).build());
		testlog.log(Status.INFO, "Case 11:Save to PPM");
		// update Excel Template
		testlog.log(updateExcelValuesInTemplate(location), "excel values updated");
		testlog.log(uploadFile(location), "upload file");
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		waitForElement("xpath", PPM.projectDescriptionTracker.VERSIONAPPLY);
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.VERSIONAPPLY), "Click on Apply ");
		testlog.log(waitForElement("xpath", PPM.projectDescriptionTracker.VERFY_UPLOADING),
				"file uploaded successfully");
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