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

public class TC06_goodReceived extends BaseExecutor {
	public static final String TESTCASENAME = "Goods Received";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "QITS";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "";
	public static final String ENVIRONMENT = "test"; // user test or prod only
	private ExtentTest testlog;
	private static WebDriver driver = null;
	
	private static final String MaterialName="ABC123";

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
	public void process() throws InterruptedException {
		QITSmethods verify =new QITSmethods(driver);
		
		iFrameByIndex(0);
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.ForFarmersBTN),"Click on ForFarmers Plant");
		
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.other),"Click on Other tab");
		Thread.sleep(2000);
		testlog.log(clickOnElementByJavascript("xpath",QITS.Homepage.Application.goodReceivedTab),"Clicked Goods Received Tab");
		testlog.log(clickOnElementByJavascript("xpath",QITS.Homepage.Application.materialSearch),"Clicked material");
		Thread.sleep(2000);
		testlog.log(enterValues("xpath",QITS.Homepage.Application.searchForMat,"MaterialForTest"), "Enter Username");
		Thread.sleep(2000);
		testlog.log(verify.clickListboxElement("MaterialForTest"),"Selected Material");
//		testlog.log(clickOnElementByJavascript("xpath",QITS.Homepage.Application.supplierSearch),"Clicked material");
//		Thread.sleep(2000);
//		testlog.log(enterValues("xpath",QITS.Homepage.Application.searchForSupp,"testdesc"), "supplier entered");
//		Thread.sleep(2000);
//		testlog.log(verify.clickListboxElement("testdesc"),"Selected supplier");
//		
//		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.searchForDate), "Date selected");
//		testlog.log(verify.selectDate("30"),"30 date selected");
		
		testlog.log(clickOnElement("xpath", QITS.Homepage.Application.nextGood), "Entry Selected");
		
		
		// Good Receipt Details
		testlog.log(enterValues("xpath",QITS.Homepage.Application.lorry, "123"), "Enter Lorry");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.delNo, "123"), "Enter Del No.");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.sealingNo, "123"), "Enter Sealing No.");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.noGoodReceipt, "101"), "Enter Mat No.");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.killos, "5000"), "Enter Killos");
		
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.searchForDate), "Planned Date selected");
		testlog.log(verify.selectDate("30"),"30 date selected");
		
		
		testlog.log(enterValues("xpath",QITS.Homepage.Application.poNoGR, "501"), "Enter PO No.");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.line, "11"), "Enter Line");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.slTo, "50"), "Enter SL to");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.delNoteQty, "5000"), "Enter DelNote Qty");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.weighedQty, "1000"), "Enter Weighed Qty");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.suppBatch, "123"), "Enter Supplier Batch");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.warehouseBatch, "123"), "Enter warehouse Batch");
		
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.bestBeforeDate), "Best Before Date selected");
	testlog.log(verify.selectDate("30"),"30 date selected");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.sapBatch, "SAP1"), "Enter SAP Batch");
		
	testlog.log(clickOnElement("xpath",QITS.Homepage.Application.prodDate), "Prod Date selected");
		testlog.log(verify.selectDate("30"),"30 date selected");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.prodSite, "ind"), "Enter Prod Site");
		
		
		
		
		

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