package tests.VolumePlanningATG;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;

import actions.VolumePlanningMethods;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class DataImport extends BaseExecutor {
	public static final String TESTCASENAME = "Data Import";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "Volume Planning-ATG Team";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "";
	public static final String ENVIRONMENT = "test"; // user test or prod only
	private ExtentTest testlog;
	private static WebDriver driver = null;

	@BeforeClass
	public void preCondition() {
		driver = initiateTest();
		testlog = extent.createTest(TESTCASENAME);
	}

	@Test(priority = 0)
	public void Login() {
		// Open Application
		// Login Page
		testlog.log(openUrl(
				"https://apps.powerapps.com/play/a8fd95ab-20e7-4597-a907-640bd02e1e76?tenantId=2fc13e34-f03f-498b-982a-7cb446e25bc6&source=portal&screenColor=RGBA(0%2C176%2C240%2C1)"),
				"Launch URL");
		testlog.log(enterValuesAndPressEnter("xpath", PackageValidation.LogInPage.EMAIL, "workflow.msflow@effem.com"),
				"Enter Username");
		// SignIn page
		waitForElement("xpath", PackageValidation.LogInPage.Username);
		testlog.log(enterValues("xpath", PackageValidation.LogInPage.Username, "workflow.msflow@effem.com"),
				"Enter Username");
		testlog.log(enterValues("xpath", PackageValidation.LogInPage.Password, "r8FMBD.6O9Bk"), "Enter Passwod");
		testlog.log(clickOnElement("xpath", PackageValidation.LogInPage.LogInBTN), "Click on SignIn");
		// Stay SignedIn page
		testlog.log(clickOnElement("xpath", PackageValidation.LogInPage.StaySignedInBTN), "Click on StaySignedIn");

	}

	@Test(priority = 1)
	public void dataImport() throws InterruptedException {
		VolumePlanningMethods vp = new VolumePlanningMethods(driver);

		iFrameByIndex(0);
		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.dataImport), "Click on Data Import");
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.activityName, VolumePlanningATG.Activity),
				"Activity name");
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.skuName, VolumePlanningATG.SKU),
				"SKU name");
		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.launchPeriod), "");
		Thread.sleep(2000);
		testlog.log(vp.clickListboxElement(VolumePlanningATG.LaunchPeriod), "Launch period selected");
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.year, VolumePlanningATG.Year), "Year");
		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.proceed), "Click on Proceed");

// 1) BookData Benchmark 1
		
		
		testlog.log(verifyElementText("xpath",VolumePlanningATG.createNewProject.ActNameText, VolumePlanningATG.Activity),"Activity Text Verified Successfully");
		testlog.log(verifyElementText("xpath",VolumePlanningATG.createNewProject.SKUNameText, VolumePlanningATG.SKU),"SKU Text Verified Successfully");
		testlog.log(verifyElementText("xpath",VolumePlanningATG.createNewProject.LPText, VolumePlanningATG.LaunchPeriod), "Launch Period Text Verified Successfully");
		testlog.log(verifyElementText("xpath",VolumePlanningATG.createNewProject.YearText, VolumePlanningATG.Year), "Year Text Verified Successfully");
		
		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.fileType), "");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath( VolumePlanningATG.createNewProject.fileType)).sendKeys(Keys.ARROW_DOWN);
		testlog.log(vp.clickListboxElement("Book Data - Benchmark 1"), "File Type selected");
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.attachFile,
				"C:\\Users\\YRAJUDUD\\OneDrive - Capgemini\\Documents\\MARS\\Volume Planing\\Samp Import Files\\Templates\\BookData - Blank Template (1).xlsx"),
				"File Selected");
		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.confirm), "File uploaded");
		Thread.sleep(2000);

		testlog.log(vp.verifyEntry("Book Data - Benchmark 1"), "File Uploaded");

// 2) BookData Benchmark 2

		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.fileType), "");
		Thread.sleep(2000);
		testlog.log(vp.clickListboxElement("Book Data - Benchmark 2"), "File Type selected");
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.attachFile,
				"C:\\Users\\YRAJUDUD\\OneDrive - Capgemini\\Documents\\MARS\\Volume Planing\\Samp Import Files\\Templates\\BookData - Blank Template (2).xlsx"),
				"File Selected");
		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.confirm), "File uploaded");
		Thread.sleep(2000);
		testlog.log(vp.verifyEntry("Book Data - Benchmark 2"), "File Uploaded");

// 3) BookData Benchmark 3

		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.fileType), "");
		Thread.sleep(2000);
		testlog.log(vp.clickListboxElement("Book Data - Benchmark 3"), "File Type selected");
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.attachFile,
				"C:\\Users\\YRAJUDUD\\OneDrive - Capgemini\\Documents\\MARS\\Volume Planing\\Samp Import Files\\Templates\\BookData - Blank Template (3).xlsx"),
				"File Selected");
		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.confirm), "File uploaded");
		Thread.sleep(2000);
		testlog.log(vp.verifyEntry("Book Data - Benchmark 3"), "File Uploaded");

// 4) Neilsen Data Benchmark 1

		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.fileType), "");
		Thread.sleep(2000);
		testlog.log(vp.clickListboxElement("Nielsen Data - Benchmark 1"), "File Type selected");
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.attachFile,
				"C:\\Users\\YRAJUDUD\\OneDrive - Capgemini\\Documents\\MARS\\Volume Planing\\Samp Import Files\\Templates\\NielsenData - Blank Template (1).xlsx"),
				"File Selected");
		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.confirm), "File uploaded");
		Thread.sleep(2000);
		testlog.log(vp.verifyEntry("Nielsen Data - Benchmark 1"), "File Uploaded");

// 5) Neilsen Data Benchmark 2

		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.fileType), "");
		Thread.sleep(2000);
		testlog.log(vp.clickListboxElement("Nielsen Data - Benchmark 2"), "File Type selected");
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.attachFile,
				"C:\\Users\\YRAJUDUD\\OneDrive - Capgemini\\Documents\\MARS\\Volume Planing\\Samp Import Files\\Templates\\NielsenData - Blank Template (2).xlsx"),
				"File Selected");
		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.confirm), "File uploaded");
		Thread.sleep(2000);
		testlog.log(vp.verifyEntry("Nielsen Data - Benchmark 2"), "File Uploaded");

// 6) Neilsen Data Benchmark 3

		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.fileType), "");
		Thread.sleep(2000);
		testlog.log(vp.clickListboxElement("Nielsen Data - Benchmark 3"), "File Type selected");
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.attachFile,
				"C:\\Users\\YRAJUDUD\\OneDrive - Capgemini\\Documents\\MARS\\Volume Planing\\Samp Import Files\\Templates\\NielsenData - Blank Template.xlsx"),
				"File Selected");
		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.confirm), "File uploaded");
		Thread.sleep(2000);
		testlog.log(vp.verifyEntry("Nielsen Data - Benchmark 3"), "File Uploaded");

// 7) IRI Benchmark 1

		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.fileType), "");
		Thread.sleep(2000);

		testlog.log(vp.clickListboxElement("IRI Data - Benchmark 1"), "File Type selected");
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.attachFile,
				"C:\\Users\\YRAJUDUD\\OneDrive - Capgemini\\Documents\\MARS\\Volume Planing\\Samp Import Files\\Templates\\IRIData - Blank Template (1).xlsx"),
				"File Selected");
		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.confirm), "File uploaded");
		Thread.sleep(2000);
		testlog.log(vp.verifyEntry("IRI Data - Benchmark 1"), "File Uploaded");

// 8) IRI Benchmark 2

		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.fileType), "");
		Thread.sleep(2000);

		testlog.log(vp.clickListboxElement("IRI Data - Benchmark 2"), "File Type selected");
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.attachFile,
				"C:\\Users\\YRAJUDUD\\OneDrive - Capgemini\\Documents\\MARS\\Volume Planing\\Samp Import Files\\Templates\\IRIData - Blank Template (2).xlsx"),
				"File Selected");
		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.confirm), "File uploaded");
		Thread.sleep(2000);
		testlog.log(vp.verifyEntry("IRI Data - Benchmark 2"), "File Uploaded");

// 9) IRI Benchmark 3

		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.fileType), "");
		Thread.sleep(2000);
		testlog.log(vp.clickListboxElement("IRI Data - Benchmark 3"), "File Type selected");
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.attachFile,
				"C:\\Users\\YRAJUDUD\\OneDrive - Capgemini\\Documents\\MARS\\Volume Planing\\Samp Import Files\\Templates\\IRIData - Blank Template.xlsx"),
				"File Selected");
		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.confirm), "File uploaded");
		Thread.sleep(2000);
		testlog.log(vp.verifyEntry("IRI Data - Benchmark 3"), "File Uploaded");

		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.perROSbttn),
				"Clicked proceed to ROS button");

	}

	@Test(priority = 2)
	public void ROSscreen() throws InterruptedException {
		Thread.sleep(2000);

		// Benchmark1
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.ROSbenchmarkvalue1, ""), "File Selected");
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.ROSbenchmarkcomment1, ""), "File Selected");

		// Benchmark2
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.ROSbenchmarkvalue2, ""), "File Selected");
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.ROSbenchmarkcomment2, ""), "File Selected");

		// Benchmark3
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.ROSbenchmarkvalue3, ""), "File Selected");
		testlog.log(enterValues("xpath", VolumePlanningATG.createNewProject.ROSbenchmarkcomment3, ""), "File Selected");

		testlog.log(clickOnElement("xpath", VolumePlanningATG.createNewProject.uploadnSubmitBttn),
				"Clicked Upload & Submit Data");

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
//		driver.quit();
		setTestResults(testlog);
	}
}