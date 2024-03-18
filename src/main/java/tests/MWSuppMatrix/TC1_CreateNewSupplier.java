package tests.MWSuppMatrix;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;
import actions.MWSupplierMatrixMethods;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

public class TC1_CreateNewSupplier extends BaseExecutor {
	public static final String TESTCASENAME = "New Supplier Audit Creation";
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
	}

	@Test
	public void process() throws InterruptedException {
		
		String GSLN ="700647";
		String supplierName ="A M TODD CO (Wild Flavors) (ADM)";
		
		
		
		iFrameByIndex(0);
		MWSupplierMatrixMethods test = new MWSupplierMatrixMethods(driver);
		
		Thread.sleep(5000);
		testlog.log(clickOnElement("xpath",MWSupplierMatrix.create.addSupplierBttn), "");
		
		//Goods Supplier Location Number
		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.GSLN, GSLN), " ");
		
		
		//Vendor Code
	//	Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.vendorCode, "15177856"), " ");
		
		//US State or Country
//		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.stateCountry, "KY"), " ");
		
		//City
//		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.city, "Karlsham"), " ");
		
		//Supplier Name
//		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.supplierName, supplierName ), " ");
		
		//Material Risk Rating
//		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.materialRiskRating, "Medium"), " ");
		
		//Veritas Category
//		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.veritasCategory, "Cartons"), " ");
		
		//Commercial Ctegory
//		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.commercialcategory, "Dairy"), " ");
		
		//Cross Segment
//		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.crossSegment, "Yes"), " ");
		
		//Cross Region
//		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.crossRegion, "Yes"), " ");
		
		//Distributor/Manufacturer/Transport
//		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.DMT, "MFG"), " ");
		
		//Supplier Status
//		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.supplierStatus, "Approved"), " ");
		
		//SQA Region
//		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.SQAregion, "NE"), " ");
		
		//SQA Responsible
//		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.SQAresponsible, "annmarie.meza@effem.com"), " ");
		
		
		// Prior Audit Date
		clickOnElement("xpath", MWSupplierMatrix.create.priorAuditDate);
//		Thread.sleep(2000);
		testlog.log(test.calender("20", "August"),"Date Selected");
		
		// Calculated Next Audit
//		clickOnElement("xpath", MWSupplierMatrix.create.nextAuditDate);
//		Thread.sleep(2000);
//		test.calender2("15", "September");
		
		//Scheduled Next Audit
		clickOnElement("xpath", MWSupplierMatrix.create.scheduledAuditDate);
//		Thread.sleep(2000);
		testlog.log(test.calender2("20", "September"),"Date Selected");
		
		//Audit Completion Date
//		clickOnElement("xpath", MWSupplierMatrix.create.auditCompletionDate);
//		Thread.sleep(2000);
//		testlog.log(test.calender("15", "September"),"Date Selected");
		
		//Active Critical Incident
		
		testlog.log(test.clickListboxvalue("No", MWSupplierMatrix.create.activeCriticalIncident), " ");
		
//Audit Performance
		
		testlog.log(test.clickListboxvalue("Good", MWSupplierMatrix.create.auditPerformance ), " ");
		
		//Audit Type
		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.auditType, "SQA"), " ");
		
		//AuditNumber
		Thread.sleep(1000);
		testlog.log(enterValues("xpath",MWSupplierMatrix.create.nexusAuditNumber, "12345"), " ");
		
		//Audit Status
		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.auditStatus, "Done"), " ");
		
		//comment
		testlog.log(clickOnElement("xpath", MWSupplierMatrix.create.comments), "");
		testlog.log(enterValues("xpath", MWSupplierMatrix.create.comments, "selenium test"), "comments");
		
		// Save
		testlog.log(clickOnElement("xpath", MWSupplierMatrix.create.saveBttn), "");
		
		
		// Validation
		Thread.sleep(5000);
		testlog.log(test.verifyEntry(GSLN), "Validate");
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