package tests.QITS;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.QITS;

import actions.QITSmethods;

public class TC02_NewSupplierEntry extends BaseExecutor {
	public static final String TESTCASENAME = "QITS-New Supplier Entry Creation";
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
	public void NewSupplierEntry() throws InterruptedException {
		Thread.sleep(5000);
		iFrameByIndex(0);	
		Thread.sleep(2000);

		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.ForFarmersBTN),"Click on ForFarmers Plant");
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.Supplier),"Click on Supplier Tab");
		//Click On Create Button
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.CreateBTN),"Click on Create button");
		//Fill the Details
		Date dNow = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.SupplierCode, ft.format(dNow)),"Supplier Code Entered");
		testlog.log(enterValues("xpath",QITS.Homepage.Application.DescriptionS,"testing Supplier"),"Description Entered");
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.StatusS),"");
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.ApprovedS),"Approved Selected");
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.SaveBTNS), "Click Save Button");
		Thread.sleep(5000);
		QITSmethods verify =new QITSmethods(driver);
		testlog.log(enterValues("xpath",QITS.Homepage.Application.searchbar,"testing Supplier"),"search for material");
		Thread.sleep(2000);
		testlog.log(verify.verifyEntry(ft.format(dNow)),"New Entry Created");
		


		//	testlog.log(handleBrowserAlert("Data saved successfully","cancel"),"Data saved successfully");
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