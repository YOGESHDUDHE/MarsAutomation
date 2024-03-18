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

public class UpdateGSVNSV extends BaseExecutor {
	public static final String TESTCASENAME = "Update GSV, NSV & CaseCount";
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
	
	@Test(priority=1)
	public void GSV() throws InterruptedException {
		
		
		VolumePlanningMethods vp = new VolumePlanningMethods(driver);
		Thread.sleep(3000);
		iFrameByIndex(0);
		Thread.sleep(5000);
		testlog.log(clickOnElement("xpath",VolumePlanningATG.GSV.updateGSV), "Select Update GSV button");
		
		testlog.log(clickOnElement("xpath", VolumePlanningATG.GSV.activity), "");
		Thread.sleep(2000);
		testlog.log(vp.clickListboxElement("A1"), "Activity selected");
		
		testlog.log(clickOnElement("xpath", VolumePlanningATG.GSV.proceed), "Click on Proceed");
		testlog.log(clearValue("xpath",VolumePlanningATG.GSV.GPU1),"");
		testlog.log(enterValues("xpath",VolumePlanningATG.GSV.GPU1, "0.1"),
				"Enter GSV");
		testlog.log(clearValue("xpath",VolumePlanningATG.GSV.NPU1),"");
		testlog.log(enterValues("xpath",VolumePlanningATG.GSV.NPU1, "0.1"),
				"Enter NSV");
		testlog.log(clearValue("xpath",VolumePlanningATG.GSV.CC1),"");
		testlog.log(enterValues("xpath",VolumePlanningATG.GSV.CC1, "1"),
				"Enter CaseCount");
		
		Thread.sleep(2000);
		
		testlog.log(enterValues("xpath",VolumePlanningATG.GSV.GPU2, "0.1"),
				"Enter GSV");
		testlog.log(enterValues("xpath",VolumePlanningATG.GSV.NPU2, "0.1"),
				"Enter NSV");
		testlog.log(enterValues("xpath",VolumePlanningATG.GSV.CC2, "2"),
				"Enter CaseCount");
		
//		testlog.log(clickOnElement("xpath", VolumePlanningATG.GSV.updateBtn), "Click on Update Metrics");

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