package tests.QITS;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;

import actions.QITSmethods;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

public class TC03_NewPlantMatSuppConnect extends BaseExecutor {
	public static final String TESTCASENAME = "New Plant Material Supplier Connect Entry Creation";
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
		public void NewPlantMatSuppConnectEntry() throws InterruptedException {
			QITSmethods verify =new QITSmethods(driver);
			Thread.sleep(5000);
			iFrameByIndex(0);	
			Thread.sleep(2000);

			testlog.log(clickOnElement("xpath",QITS.Homepage.Application.ForFarmersBTN),"Click on ForFarmers Plant");
			testlog.log(clickOnElement("xpath",QITS.Homepage.Application.PMSC),"Click on PlantSupplierMaterial Connect");
			testlog.log(clickOnElement("xpath",QITS.Homepage.Application.createBtn),"Click on CREATE button");
			testlog.log(clickOnElement("xpath",QITS.Homepage.Application.clickSelectMaterial),"");
			testlog.log(enterValues("xpath",QITS.Homepage.Application.SelectMaterial,"testing"),"");
			Thread.sleep(2000);
			testlog.log(verify.clickListboxElement("testing Material"),"Selected testing MAterial");
			testlog.log(clickOnElement("xpath",QITS.Homepage.Application.clickSelectSupplier),"");
			testlog.log(enterValues("xpath",QITS.Homepage.Application.SelectSupplier,"testing"),"");
			Thread.sleep(2000);
			testlog.log(verify.clickListboxElement("testing Supplier"),"Selected testing Supplier");
			testlog.log(clickOnElement("xpath",QITS.Homepage.Application.SelectStatus),"");
			testlog.log(clickOnElement("xpath",QITS.Homepage.Application.approved),"Approved Selected");
			testlog.log(clickOnElement("xpath",QITS.Homepage.Application.SelectPR),"");
			testlog.log(clickOnElement("xpath",QITS.Homepage.Application.no),"No PR Selected");
			testlog.log(enterValues("xpath",QITS.Homepage.Application.Modifier,"1"),"Modifier entered as 1");
			testlog.log(clickOnElement("xpath",QITS.Homepage.Application.savebtn),"Clicked on Save button");
			Thread.sleep(2000);
			
//			QITSmethpods verify =new QITSmethpods(driver);
			testlog.log(enterValues("xpath",QITS.Homepage.Application.searchbar,"testing"),"search for material");
			testlog.log(verify.verifyEntry("testing Supplier"),"New Entry Created");
			
			
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