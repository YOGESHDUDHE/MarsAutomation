package tests.QITS;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class TC04_MaterialSpecification extends BaseExecutor {
	public static final String TESTCASENAME = "New Material Specification Creation";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "QITS";
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

	@Test
	public void Login() {
		//Open Application
		// Login Page
		testlog.log(openUrl("https://apps.powerapps.com/play/146394f3-7aed-4c8d-a9a8-4976fcd98a6c?tenantId=2fc13e34-f03f-498b-982a-7cb446e25bc6"), "Launch URL");
		testlog.log(enterValuesAndPressEnter("xpath",QITS.Homepage.EMAIL, "workflow.msflow@effem.com"), "Enter Username");
		//SignIn page
		waitForElement("xpath",QITS.Homepage.Username);
		testlog.log(enterValues("xpath",QITS.Homepage.Username, "workflow.msflow@effem.com"), "Enter Username");
		testlog.log(enterValues("xpath",QITS.Homepage.Password,"r8FMBD.6O9Bk"), "Enter Passwod");
		testlog.log(clickOnElement("xpath",QITS.Homepage.LogInBTN), "Click on SignIn");
		//Stay SignedIn page
		testlog.log(clickOnElement("xpath",QITS.Homepage.StaySignedInBTN), "Click on StaySignedIn");
	
	}
	
	@Test
	public void materialSpecification() throws InterruptedException {
		
		Thread.sleep(5000);
		iFrameByIndex(0);	
		Thread.sleep(2000);	
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.ForFarmersBTN),"Click on ForFarmers Plant");
		Thread.sleep(5000);
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.materialSpec),"Click on Material Specification Tab");
		//Click On Create Button
		Thread.sleep(2000);
//		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.CreateBTN),"Click on Create button");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.description,"testing Material"), "");
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.search), "Click on search");
		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.plus),"");
		Thread.sleep(2000);
		testlog.log(enterValues("xpath",QITS.Homepage.Application.primary,"primary"),"primary value entered");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.secondary,"secondary"),"secoundary value entered");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.docNo,"123"),"Doc No. entered");
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.owner),"");
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.yogesh),"owner selected");
		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.saveSpec),"Click on Save");
	//	Thread.sleep(2000);
	//	testlog.log(clickOnElement("xpath",QITS.Homepage.Application.addRequ), "Requirements-Click on ADD");
		


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