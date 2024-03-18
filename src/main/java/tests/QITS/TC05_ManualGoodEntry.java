package tests.QITS;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;

import actions.QITSmethods;
import tests.QITS.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class TC05_ManualGoodEntry extends BaseExecutor {
	public static final String TESTCASENAME = "New Manual Good Entry Creation";
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
	
	@Test
	public void Login() throws InterruptedException {
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
		Thread.sleep(2000);
	}

	@Test
	public void newManualGoodEntry() throws InterruptedException {
		QITSmethods verify =new QITSmethods(driver);
		Thread.sleep(2000);
		iFrameByIndex(0);
		Thread.sleep(2000);
		
		testlog.log(verifyElementText("xpath",QITS.Homepage.Application.AppName,"QITS"),"Application Name Verified");
		testlog.log(waitForElement("xpath",QITS.Homepage.Application.ForFarmersBTN), "ForFarmers Plant Button Present");
		testlog.log(waitForElement("xpath",QITS.Homepage.Application.PetcomGermanyBTN), "Percom Germany Plant Button Present");
		
	//	testlog.log(clickOnElement("xpath",QITS.Homepage.Application.ForFarmersBTN),"Click on ForFarmers Plant");
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.other),"Click on Other tab");
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.entryManualGoodBttn),"Clicked Entry Manual Good Tab");
		Thread.sleep(4000);
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.CreateBTN),"Click on Create button");
		testlog.log(clickOnElementByJavascript("xpath",QITS.Homepage.Application.GRDCode), "");
		Thread.sleep(1000);
		testlog.log(enterValues("xpath",QITS.Homepage.Application.searchGRDcode,"20220727"), "");
		Thread.sleep(1000);
		testlog.log(verify.clickListboxElement("20220727"),"GRD Code Selected");
		
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.material), "");
		Thread.sleep(1000);
		testlog.log(verify.clickListboxElement("testing Material"),"Material Selected");
		
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.arrivlDate), "Date selected");
		testlog.log(verify.selectDate("30"),"30 date selected");
		
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.supplierCade), "");
		Thread.sleep(1000);
		testlog.log(enterValues("xpath",QITS.Homepage.Application.searchSuppliercode,"20220727"), "");
		Thread.sleep(1000);
		testlog.log(verify.clickListboxElement("20220727"),"Supplier Code Selected");
		
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.supplier), "");
		Thread.sleep(1000);
		testlog.log(verify.clickListboxElement("testing Supplier"),"Supplier Selected");
		
		testlog.log(enterValues("xpath",QITS.Homepage.Application.quantity, "100"),"Quanity Entered");

		Thread.sleep(2000);
//		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.savebtn), "Clicked on SAVE btton");
		
		testlog.log(enterValues("xpath",QITS.Homepage.Application.searchbar,"materialName"),"search for material");
		testlog.log(verify.verifyEntry("materialName"),"New Entry Created");
		
		
		


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