package tests.BDA;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;

import actions.BDAactions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class ActionClick extends BaseExecutor {
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
	
	@Test(priority = 0)
	public void Login() throws InterruptedException {
		// Open Application
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
//		Thread.sleep(10000);
	}

	@Test
	public void process() throws InterruptedException {
		
		
//		BDAactions test = new BDAactions(driver);
		Thread.sleep(10000);
		iFrameByIndex(0);
		Thread.sleep(10000);
		String BDANumber = "NL01629111996";
		testlog.log(clickOnElement("xpath", BDA.bdaListingBtn), "Click on BDA Listing");
		
		testlog.log(clickOnElement("xpath", BDA.filter), "Click on Filter");
		testlog.log(enterValues("xpath", BDA.bdaInput, BDANumber),"BDA Number Entered");
		testlog.log(clickOnElement("xpath", BDA.applyButton), "Click on Apply");
		
		testlog.log(clickOnElement("xpath", BDA.viewBDABtn), "Click on View BDA");
		
		testlog.log(clickOnElement("xpath", BDA.fiveWhyBtn), "Click on 5 Why Analysis");
		
		Thread.sleep(5000);
		
		String whyM1 = "Test Description";
		WebElement mainWhy =driver.findElement(By.xpath("//div[@data-control-name='btn_WhyTitle']"));
		boolean mainWhyStatus = mainWhy.isDisplayed();
		Assert.assertEquals(mainWhyStatus, true);
		Assert.assertEquals(mainWhy.getText(), whyM1);
		
		String why1 = "Test Description 1";
		WebElement Why1 = driver.findElement(By.xpath("//div[@data-control-name='btn_WhyTitle_1']"));
		boolean Why1Status = Why1.isDisplayed();
		Assert.assertEquals(Why1Status, true);
		Assert.assertEquals(Why1.getText(), why1);
		
		String why2 = "Test Description 2";
		WebElement Why2 = driver.findElement(By.xpath("//div[@data-control-name='btn_WhyTitle_2']"));
		boolean Why2Status = Why2.isDisplayed();
		Assert.assertEquals(Why2Status, true);
		Assert.assertEquals(Why2.getText(), why2);
		
		String why3 = "Test Description 3";
		WebElement Why3 = driver.findElement(By.xpath("//div[@data-control-name='btn_WhyTitle_3']"));
		boolean Why3Status = Why3.isDisplayed();
		Assert.assertEquals(Why3Status, true);
		Assert.assertEquals(Why3.getText(), why3);
		
		String why4 = "Test Description 4";
		WebElement Why4 = driver.findElement(By.xpath("//div[@data-control-name='btn_WhyTitle_4']"));
		boolean Why4Status = Why4.isDisplayed();
		Assert.assertEquals(Why4Status, true);
		Assert.assertEquals(Why4.getText(), why4);
		
		
		
		
		
		
		
		
		
		

		driver.findElement(By.xpath( BDA.bdaActionTab)).click();   
		Thread.sleep(10000);
		
		testlog.log(clickOnElement("xpath", "//*[@data-control-name='Img_HamburgerIcon_41']"), "Click on Hamburger Menu");
		testlog.log(clickOnElement("xpath", "//*[@data-control-name='btn_start_41']//button"), "Click on Start button");
		
		testlog.log(clickOnElement("xpath", BDA.actionListButton), "Click on Action Listing");

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