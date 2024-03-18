package tests.EFA;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;

import actions.EFA_Actions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class Template_Master extends BaseExecutor {
	public static final String TESTCASENAME = "Template_Master";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "";
	public static final String ENVIRONMENT = ""; // user test or prod only
	private ExtentTest testlog;
	private static WebDriver driver = null;

	@BeforeClass
	public void preCondition() {
		driver = initiateTest();
		testlog = extent.createTest(TESTCASENAME);
	}
	@Test(priority = 0)
	public void Login() throws InterruptedException {
		// Open Application
		// Login Page
		testlog.log(openUrl(
				"https://apps.powerapps.com/play/e/d6ea9198-463a-4c22-adaf-1e5653dbedd3/a/c6845eea-99e0-4929-8c3c-5594fc0a71cc?tenantId=2fc13e34-f03f-498b-982a-7cb446e25bc6&sourcetime=2023-08-09%2013%3A40%3A13Z"),
				"Launch URL");
		testlog.log(enterValuesAndPressEnter("xpath", PackageValidation.LogInPage.EMAIL, "workflow.msflow@effem.com"),
				"Enter Username");
		// SignIn page

		Thread.sleep(3000);
		testlog.log(clickOnElement("xpath", BDA.LogInBtn), "Click on SignIn");
		testlog.log(enterValuesAndPressEnter("xpath", PackageValidation.LogInPage.Password, "r8FMBD.6O9Bk"),
				"Enter Passwod");
		testlog.log(clickOnElement("xpath", PackageValidation.LogInPage.LogInBTN), "Click on SignIn");
		// Stay SignedIn page
		testlog.log(clickOnElement("xpath", PackageValidation.LogInPage.StaySignedInBTN), "Click on StaySignedIn");
//		Thread.sleep(10000);
	}
	
	
	@Test
	public void process() throws InterruptedException {
		EFA_Actions act = new EFA_Actions(driver);
		Thread.sleep(3000);
		iFrameByIndex(0);
		Thread.sleep(5000);
		testlog.log(clickOnElement("xpath", EFA.TemplateMaster.Template_Master_Button), "Click on TemplateMasterButton");
		Thread.sleep(2000);
		testlog.log(enterValues("xpath", EFA.TemplateMaster.tempName, "Test Template to Test"), "Template Name");
		Thread.sleep(2000);
		testlog.log(act.clickListboxvalue("Trade Terms", EFA.TemplateMaster.territory),"Territory");
		Thread.sleep(5000);
		
		

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