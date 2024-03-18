package tests.QITS;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;

import actions.QITSmethods;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class TC07_DisasterCheck extends BaseExecutor {
	public static final String TESTCASENAME = "DisasterCheck";
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

	
	@Test(priority = 0)
	public void Login() {
		//Open Application
		// Login Page
		testlog.log(openUrl("https://apps.powerapps.com/play/146394f3-7aed-4c8d-a9a8-4976fcd98a6c?tenantId=2fc13e34-f03f-498b-982a-7cb446e25bc6"), "Launch URL");
		testlog.log(enterValuesAndPressEnter("xpath",QITS.Homepage.EMAIL, "workflow.msflow@effem.com"), "Enter Username");
//		testlog.log(clickOnElement("xpath",QITS.Homepage.SIGNINBTN ), "Click on SignIn");
		//SignIn page
		waitForElement("xpath",QITS.Homepage.Username);
		testlog.log(enterValues("xpath",QITS.Homepage.Username, "workflow.msflow@effem.com"), "Enter Username");
		testlog.log(enterValues("xpath",QITS.Homepage.Password,"r8FMBD.6O9Bk"), "Enter Passwod");
		testlog.log(clickOnElement("xpath",QITS.Homepage.LogInBTN), "Click on SignIn");
		//Stay SignedIn page
		testlog.log(clickOnElement("xpath",QITS.Homepage.StaySignedInBTN), "Click on StaySignedIn");
	
	}
	
	@Test
	public void disasterCheck() throws InterruptedException {
		QITSmethods verify =new QITSmethods(driver);
		Thread.sleep(5000);
		
		iFrameByIndex(0);
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.ForFarmersBTN),"Click on ForFarmers Plant");
		
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.other),"Click on Other tab");
		Thread.sleep(2000);
		testlog.log(clickOnElementByJavascript("xpath",QITS.Homepage.Application.disasterCheckTab),"Clicked Goods Received Tab");
		testlog.log(clickOnElementByJavascript("xpath",QITS.Homepage.Application.materialSearchDC),"Clicked material");
		Thread.sleep(2000);
		testlog.log(enterValues("xpath",QITS.Homepage.Application.searchForMatDC,"MaterialForTest"), "Enter Username");
		Thread.sleep(2000);
		testlog.log(verify.clickListboxElement("MaterialForTest"),"Selected Material");
		
		testlog.log(clickOnElement("xpath", QITS.Homepage.Application.nextGood), "Entry Selected");
		
		//Add Exception
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.addProblemBttn),"Click on Add Exception Button");
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.selectExceptionDrpDwn),"Click Select Exception");
		Thread.sleep(1000);
		testlog.log(enterValues("xpath",QITS.Homepage.Application.exceptionInput,"Bulk "),"Click Select Exception");
		Thread.sleep(2000);
		testlog.log(verify.clickListboxElement("Bulk Density"),"Exception selected");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.qtyException,"1000"),"Entered Quantity");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.descArea,"selenium testing"),"Entered Description");
		

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