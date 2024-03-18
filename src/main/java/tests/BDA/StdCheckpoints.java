package tests.BDA;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;

import actions.BDAactions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class StdCheckpoints extends BaseExecutor {
	public static final String TESTCASENAME = "Demo Test1";
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
				"https://apps.powerapps.com/play/e/d6ea9198-463a-4c22-adaf-1e5653dbedd3/a/c50b00d9-eefe-492a-8bc0-46866bfb9d89?tenantId=2fc13e34-f03f-498b-982a-7cb446e25bc6"),
				"Launch URL");
		testlog.log(enterValuesAndPressEnter("xpath", PackageValidation.LogInPage.EMAIL, "workflow.msflow@effem.com"),
				"Enter Username");
		// SignIn page

		Thread.sleep(3000);
		testlog.log(clickOnElement("xpath", BDA.LogInBtn), "Click on SignIn");
		testlog.log(enterValuesAndPressEnter("xpath", PackageValidation.LogInPage.Password, "r8FMBD.6O9Bk"), "Enter Passwod");
		testlog.log(clickOnElement("xpath", PackageValidation.LogInPage.LogInBTN), "Click on SignIn");
		// Stay SignedIn page
		testlog.log(clickOnElement("xpath", PackageValidation.LogInPage.StaySignedInBTN), "Click on StaySignedIn");
//		Thread.sleep(10000);
	}
	
	
	@Test
	public void process() throws InterruptedException {
		
		BDAactions test = new BDAactions(driver);
		Thread.sleep(10000);
		iFrameByIndex(0);
		Thread.sleep(10000);
	

		testlog.log(clickOnElement("xpath", BDA.bdaListingBtn), "Click on StaySignedIn");
		
		testlog.log(clickOnElement("xpath", BDA.viewBDABtn), "Click on View BDA");
		
		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath", BDA.stdCheckpointsTab), "Click on Standard Checkpoints Tab");
		
		testlog.log(test.clickListboxvalue("Yes", BDA.checkpoint1), "");
		
		
	
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