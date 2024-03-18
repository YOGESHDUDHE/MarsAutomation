package tests.DigitalLogBookApplication;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

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
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class Form2_ProductAccountability extends BaseExecutor {
	public static final String TESTCASENAME = "Demo Test1";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "";
	public static final String ENVIRONMENT = ""; // user test or prod only
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
		testlog.log(test.clickListboxElement(DigitalLogbook.location, "Las Vegas"), "Location Selected - Las Vegas");

		// Store
		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(DigitalLogbook.store, "6116 - MMW - Las Vegas"), "Store Selected - 6116 - MMW - Las Vegas");

		//proceed
		testlog.log(clickOnElement("xpath",DigitalLogbook.proceed), "Click on proceed");
		
		//Candy Operation
		testlog.log(clickOnElement("xpath",DigitalLogbook.candyOperationBtn), "Click on candy operation");
		
		
		//Product Accountability Form
		testlog.log(clickOnElement("xpath",DigitalLogbook.prouctAccountability.PABttn), "Click on Product Accountability Form");
		
		// Shift
		Thread.sleep(1000);
		testlog.log(test.clickListboxvalue(DigitalLogbook.prouctAccountability.shift, "MID"), "Shift Selected - Mid");
		
		//Add description
		Thread.sleep(1000);
		driver.findElement(By.xpath(DigitalLogbook.prouctAccountability.addDesc)).click();
		Thread.sleep(2000);
		
		String description = "descriptionTest";
		String upper = description.toUpperCase();
		driver.findElement(By.xpath(DigitalLogbook.prouctAccountability.descText)).sendKeys(description);
		Thread.sleep(1000);
		driver.findElement(By.xpath(DigitalLogbook.prouctAccountability.saveDesc)).click();	
		Thread.sleep(2000);

		
		
		// description
		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(DigitalLogbook.prouctAccountability.description, upper), "Desription Selected - added desc");
		
		// Code Date
		testlog.log(enterValues("xpath",DigitalLogbook.prouctAccountability.codeDate,"50"), "Entered temperature value- 50");
		
		// Quantity of Cases
		testlog.log(enterValues("xpath",DigitalLogbook.prouctAccountability.quantityOfCases,"100"), "Entered Humidity value- 0");
		
		// Shift
		Thread.sleep(1000);
		testlog.log(test.clickListboxvalue(DigitalLogbook.prouctAccountability.locationUsed, "printer"), "Location Selected - Upper");
		
		//Bin Number
		Thread.sleep(1000);
		testlog.log(enterValues("xpath",DigitalLogbook.prouctAccountability.binNumberField,"211"), "Entered Bin Number value- 211");
		
		// Comments
		testlog.log(enterValues("xpath",DigitalLogbook.prouctAccountability.comments,"Selenium test"), "Entered comments");
		
		// Save & Submit
		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath",DigitalLogbook.prouctAccountability.savenSubmit), "Clicked on Save&Submit");
		
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