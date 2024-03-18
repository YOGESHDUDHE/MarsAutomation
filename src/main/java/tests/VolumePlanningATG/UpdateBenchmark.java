package tests.VolumePlanningATG;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;

import actions.VolumePlanningMethods;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UpdateBenchmark extends BaseExecutor {
	public static final String TESTCASENAME = "UpdateBenchmark";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "";
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

	@Test
	(priority=1)
	public void updateBenchmark() throws InterruptedException {
		VolumePlanningMethods vp = new VolumePlanningMethods(driver);

		iFrameByIndex(0);
		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath", VolumePlanningATG.updateBenchmark.updateBenchmark), "Click on Update Benchmark");
		testlog.log(clickOnElement("xpath", VolumePlanningATG.updateBenchmark.activity), "");
		Thread.sleep(2000);
		testlog.log(vp.clickListboxElement("Sample Activity V"), "Activity selected");
		
		testlog.log(clickOnElement("xpath", VolumePlanningATG.updateBenchmark.sku), "");
		Thread.sleep(2000);
		testlog.log(vp.clickListboxElement("Sample SKU V"), "SKU selected");
		
		testlog.log(clickOnElement("xpath", VolumePlanningATG.updateBenchmark.proceed), "Click on Proceed");
		
		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath", VolumePlanningATG.updateBenchmark.b1), "select Benchmark");
		boolean radio= driver.findElement(By.xpath(VolumePlanningATG.updateBenchmark.b1)).isSelected();
		System.out.println(radio);
//		testlog.log(clickOnElement("xpath", VolumePlanningATG.updateBenchmark.updateBtn),"Clicked on Update Benchmark button");
		
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
//	driver.quit();
		setTestResults(testlog);
	}
}