package tests.DigitalLogBookApplication;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;

import actions.DigitalLogbookMethods;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class Form4_ColorworksDailyWasteLog extends BaseExecutor {
	public static final String TESTCASENAME = "Colorworks Daily Waste Log";
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
	public void Login() {
		//Open Application
		
		// Login Page
		testlog.log(openUrl(DigitalLogbook.URL), "Launch URL");
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
	public void storeSelection() throws InterruptedException {
		DigitalLogbookMethods test = new DigitalLogbookMethods(driver);
		
		iFrameByIndex(0);
		
		// Location
		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(DigitalLogbook.location, "LAS"), "Location Selected - Las Vegas");

		// Store
		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(DigitalLogbook.store, "6116 - MMW - Las Vegas"), "Store Selected - 6116 - MMW - Las Vegas");

		//proceed
		testlog.log(clickOnElement("xpath",DigitalLogbook.proceed), "Click on proceed");
		
		//Candy Operation
		testlog.log(clickOnElement("xpath",DigitalLogbook.candyOperationBtn), "Click on candy operation");
		
		//Colorworks Daily Waste Log
		testlog.log(clickOnElement("xpath",DigitalLogbook.colorworksDaily.colorworksDailyBttn), "Click on Colorworks Daily Waste Log");
		
		//Scale Calibration
		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath",DigitalLogbook.colorworksDaily.scaleCalibration), "Click on Scale Calibration");
		
		
		//Waste Defaced
		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath",DigitalLogbook.colorworksDaily.wasteDefaced), "Click on Waste Defaced");
		
		//Add WasteType
		Thread.sleep(1000);
		driver.findElement(By.xpath(DigitalLogbook.colorworksDaily.addBttn)).click();
		Thread.sleep(2000);
		test.clickListboxElement(DigitalLogbook.colorworksDaily.wasteType, "WasteType2");
		driver.findElement(By.xpath(DigitalLogbook.colorworksDaily.qtyOfWaste)).sendKeys("100");
		driver.findElement(By.xpath(DigitalLogbook.colorworksDaily.addWasteBttn)).click();
		
		// Comments
		testlog.log(enterValues("xpath",DigitalLogbook.colorworksDaily.comments,"Selenium test"), "Entered comments");
		
		// Save & Submit
		Thread.sleep(2000);
//		testlog.log(clickOnElement("xpath",DigitalLogbook.colorworksDaily.savenSubmit), "Clicked on Save&Submit");


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