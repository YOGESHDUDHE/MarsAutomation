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
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class SalesTeamApplication extends BaseExecutor {
	public static final String TESTCASENAME = "Sales Team Application";
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
				"https://apps.powerapps.com/play/968d4fa2-3919-4938-b2b5-a0cf72cf8a88?tenantId=2fc13e34-f03f-498b-982a-7cb446e25bc6"),
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
	public void process() throws InterruptedException {
		
		VolumePlanningMethods vp = new VolumePlanningMethods(driver);

		iFrameByIndex(0);
		Thread.sleep(2000);
		
		testlog.log(clickOnElement("xpath", VolumePlanningATG.salesTeamApp.activityName), "");
		Thread.sleep(2000);
		testlog.log(vp.clickListboxElement("Aug activity"), "Activity selected");
		
		testlog.log(clickOnElement("xpath", VolumePlanningATG.salesTeamApp.level2), "");
		Thread.sleep(2000);
		testlog.log(vp.clickListboxElement("Asda"), "SKU selected");
		
		
		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath", VolumePlanningATG.salesTeamApp.account), "");
		Thread.sleep(2000);
		testlog.log(vp.clickListboxElement("Asda"), "SKU selected");
		
		testlog.log(clickOnElement("xpath", VolumePlanningATG.salesTeamApp.proceed),"Click proceed");

		testlog.log(clickOnElement("xpath", VolumePlanningATG.salesTeamApp.editBttn),"Click Edit Button");
		
		String volumeText = getText("xpath",VolumePlanningATG.salesTeamApp.volumeText);
		String distributionText = getText("xpath",VolumePlanningATG.salesTeamApp.distributionText);
		String newVolumeText = getText("xpath",VolumePlanningATG.salesTeamApp.newVolumeText);
		String perOfNewVolumeText = getText("xpath",VolumePlanningATG.salesTeamApp.perofNewVolumeText);
		String rnoUnitsText = getText("xpath",VolumePlanningATG.salesTeamApp.rnoUnitsText);
		String rnoDistributionText = getText("xpath",VolumePlanningATG.salesTeamApp.rnoDistributionText);
		
		System.out.println(volumeText);
		System.out.println(distributionText);
		System.out.println(newVolumeText);
		System.out.println(perOfNewVolumeText);
		System.out.println(rnoUnitsText);
		System.out.println(rnoDistributionText);
	
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