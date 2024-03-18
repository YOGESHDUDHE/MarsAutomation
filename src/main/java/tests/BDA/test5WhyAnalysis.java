package tests.BDA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;

import actions.BDAactions;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class test5WhyAnalysis extends BaseExecutor {
	public static final String TESTCASENAME = "test5WhyAnalysis";
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
				"https://apps.powerapps.com/play/e/4a07ac04-1bbe-e08b-b5a7-81ec96b85e4d/a/bd715065-c19f-4a6e-9786-af4ae8a8bed1?tenantId=2fc13e34-f03f-498b-982a-7cb446e25bc6"),
				"Launch URL");
		testlog.log(enterValuesAndPressEnter("xpath", PackageValidation.LogInPage.EMAIL, "workflow.msflow@effem.com"),
				"Enter Username");
		// SignIn page

		Thread.sleep(3000);
		testlog.log(clickOnElement("xpath", BDA.LogInBtn), "Click on SignIn");
		testlog.log(enterValuesAndPressEnter("xpath", PackageValidation.LogInPage.Password, "r8FMBD.6O9Bk"),
				"Enter Passwod");
		testlog.log(clickOnElement("xpath", PackageValidation.LogInPage.LogInBTN), "Click on SignIn");
		// Stay SignedIn page
		testlog.log(clickOnElement("xpath", PackageValidation.LogInPage.StaySignedInBTN), "Click on StaySignedIn");
//		Thread.sleep(10000);
	}

	String BDANumber = "PL50600004051";

	@Test
	public void process() throws InterruptedException {
		BDAactions test = new BDAactions(driver);
		Thread.sleep(10000);
		iFrameByIndex(0);
		Thread.sleep(10000);

		testlog.log(clickOnElement("xpath", BDA.bdaListingBtn), "Click on BDA Listing");
		Thread.sleep(3000);
		testlog.log(clickOnElement("xpath", BDA.filter), "Click on Filter");
		testlog.log(enterValues("xpath", BDA.bdaInput, BDANumber), "BDA Number Entered");
		testlog.log(clickOnElement("xpath", BDA.applyButton), "Click on Apply");

		testlog.log(clickOnElement("xpath", BDA.viewBDABtn), "Click on View BDA");
		Thread.sleep(3000);
		testlog.log(clickOnElement("xpath", BDA.fiveWhyBtn), "Click on 5 Why Analysis");
//		testlog.log(test.SearchnSelect(BDA.clickBDALead, BDA.bdaLead, "Dudhe, Yogesh Raju (Contractor)"),
//				"Select BDA Lead");
//		testlog.log(clearValue("xpath", BDA.bdaNumber), "");
//		testlog.log(enterValues("xpath", BDA.bdaNumber, "1001"), "BDA Number Entered");
		Thread.sleep(3000);
		testlog.log(clickOnElement("xpath", BDA.addWhyMain), "Click on Add Why");

		// Add Why

		Thread.sleep(2000);

		String whyM1 = "Test Description";
		testlog.log(enterValues("xpath", BDA.whyDesc, whyM1), "Why Desc");

		testlog.log(clickOnElement("xpath", BDA.addActionToggle), "Click on Add action toggle key");

		String ActionM1 = "test Action";
		testlog.log(enterValues("xpath", BDA.actionTitle, ActionM1), "Action Title");

		testlog.log(test.SearchnSelect(BDA.owner, BDA.searchOwner, "Dudhe, Yogesh Raju (Contractor)"), "Owner");

		testlog.log(test.clickListboxvalue("Completed", BDA.status), "Status");

		testlog.log(test.clickListboxvalue("No", BDA.SAP), "SAP");

		testlog.log(clickOnElement("xpath", BDA.addActionBtn), "Click Add Action Button");

		Thread.sleep(5000);

		// Node displayed

		WebElement mainWhy = driver.findElement(By.xpath("//div[@data-control-name='btn_WhyTitle']"));
		boolean mainWhyStatus = mainWhy.isDisplayed();
		Assert.assertEquals(mainWhyStatus, true);
		Assert.assertEquals(mainWhy.getText(), whyM1);

		// Validate Action

		List<WebElement> rowCountAfterActionM1 = driver
				.findElements(By.xpath("(//div[@class='react-gallery-items-window'])[3]/div"));
		int Count = rowCountAfterActionM1.size();
		Thread.sleep(2000);
		testlog.log(test.verifyActionEntry(BDANumber), "New Entry Created");

		// Why1 Entry

		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath", BDA.addWhy1), "Click on Add Why 1");

		String why1 = "Test Description 1";
		testlog.log(enterValues("xpath", BDA.whyDesc, why1), "Why Desc");

		testlog.log(clickOnElement("xpath", BDA.addActionToggle), "Click on Add action toggle key");

		String Action1 = "test Action 1";
		testlog.log(enterValues("xpath", BDA.actionTitle, Action1), "Action Title");

		testlog.log(test.SearchnSelect(BDA.owner, BDA.searchOwner, "Dudhe, Yogesh Raju (Contractor)"), "Owner");

		testlog.log(test.clickListboxvalue("Completed", BDA.status), "Status");

		testlog.log(test.clickListboxvalue("No", BDA.SAP), "SAP");

		testlog.log(clickOnElement("xpath", BDA.addActionBtn), "Click Add Action Button");

		Thread.sleep(5000);

		// Node displayed

		WebElement Why1 = driver.findElement(By.xpath("//div[@data-control-name='btn_WhyTitle_1']"));
		boolean Why1Status = Why1.isDisplayed();
		Assert.assertEquals(Why1Status, true);
		Assert.assertEquals(Why1.getText(), why1);

		// Validate Action

		List<WebElement> rowCountAfterAction1 = driver
				.findElements(By.xpath("(//div[@class='react-gallery-items-window'])[3]/div"));
		int Count1 = rowCountAfterAction1.size();

		// Assert Count increase

		Assert.assertEquals(Count1, Count + 1, "Count Not increase");
		Thread.sleep(2000);
		testlog.log(test.verifyActionEntry(BDANumber), "New Entry Created");

		// Why2 Entry ---------------------------------

		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath", BDA.addWhy2), "Click on Add Why 1");

		String why2 = "Test Description 2";
		testlog.log(enterValues("xpath", BDA.whyDesc, why2), "Why Desc");

		testlog.log(clickOnElement("xpath", BDA.addActionToggle), "Click on Add action toggle key");

		String Action2 = "test Action 2";
		testlog.log(enterValues("xpath", BDA.actionTitle, Action2), "Action Title");

		testlog.log(test.SearchnSelect(BDA.owner, BDA.searchOwner, "Dudhe, Yogesh Raju (Contractor)"), "Owner");

		testlog.log(test.clickListboxvalue("Completed", BDA.status), "Status");

		testlog.log(test.clickListboxvalue("No", BDA.SAP), "SAP");

		testlog.log(clickOnElement("xpath", BDA.addActionBtn), "Click Add Action Button");

		Thread.sleep(5000);

		// Node displayed

		WebElement Why2 = driver.findElement(By.xpath("//div[@data-control-name='btn_WhyTitle_2']"));
		boolean Why2Status = Why2.isDisplayed();
		Assert.assertEquals(Why2Status, true);
		Assert.assertEquals(Why2.getText(), why2);

		// Validate Action

		List<WebElement> rowCountAfterAction2 = driver
				.findElements(By.xpath("(//div[@class='react-gallery-items-window'])[3]/div"));
		int Count2 = rowCountAfterAction2.size();

		// Assert Count increase

		Assert.assertEquals(Count2, Count1 + 1, "Count Not increase");
		Thread.sleep(2000);
		testlog.log(test.verifyActionEntry(BDANumber), "New Entry Created");

		// Why3 Entry ---------------------------

		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath", BDA.addWhy3), "Click on Add Why 1");

		String why3 = "Test Description 3";
		testlog.log(enterValues("xpath", BDA.whyDesc, why3), "Why Desc");

		testlog.log(clickOnElement("xpath", BDA.addActionToggle), "Click on Add action toggle key");

		String Action3 = "test Action 3";
		testlog.log(enterValues("xpath", BDA.actionTitle, Action3), "Action Title");

		testlog.log(test.SearchnSelect(BDA.owner, BDA.searchOwner, "Dudhe, Yogesh Raju (Contractor)"), "Owner");

		testlog.log(test.clickListboxvalue("Completed", BDA.status), "Status");

		testlog.log(test.clickListboxvalue("No", BDA.SAP), "SAP");

		testlog.log(clickOnElement("xpath", BDA.addActionBtn), "Click Add Action Button");

		Thread.sleep(5000);

		// Node displayed

		WebElement Why3 = driver.findElement(By.xpath("//div[@data-control-name='btn_WhyTitle_3']"));
		boolean Why3Status = Why3.isDisplayed();
		Assert.assertEquals(Why3Status, true);
		Assert.assertEquals(Why3.getText(), why3);

		// Validate Action

		List<WebElement> rowCountAfterAction3 = driver
				.findElements(By.xpath("(//div[@class='react-gallery-items-window'])[3]/div"));
		int Count3 = rowCountAfterAction3.size();

		// Assert Count increase

		Assert.assertEquals(Count3, Count2 + 1, "Count Not increase");
		Thread.sleep(2000);
		testlog.log(test.verifyActionEntry(BDANumber), "New Entry Created");

		// Why4 Entry ------------------------

		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath", BDA.addWhy4), "Click on Add Why 1");

		String why4 = "Test Description 4";
		testlog.log(enterValues("xpath", BDA.whyDesc, why4), "Why Desc");

		testlog.log(clickOnElement("xpath", BDA.addActionToggle), "Click on Add action toggle key");

		String Action4 = "test Action 4";
		testlog.log(enterValues("xpath", BDA.actionTitle, Action4), "Action Title");

		testlog.log(test.SearchnSelect(BDA.owner, BDA.searchOwner, "Dudhe, Yogesh Raju (Contractor)"), "Owner");

		testlog.log(test.clickListboxvalue("Completed", BDA.status), "Status");

		testlog.log(test.clickListboxvalue("No", BDA.SAP), "SAP");

		testlog.log(clickOnElement("xpath", BDA.addActionBtn), "Click Add Action Button");

		Thread.sleep(5000);

		// Node displayed

		WebElement Why4 = driver.findElement(By.xpath("//div[@data-control-name='btn_WhyTitle_4']"));
		boolean Why4Status = Why4.isDisplayed();
		Assert.assertEquals(Why4Status, true);
		Assert.assertEquals(Why4.getText(), why4);

		// Validate Action

		List<WebElement> rowCountAfterAction4 = driver
				.findElements(By.xpath("(//div[@class='react-gallery-items-window'])[3]/div"));
		int Count4 = rowCountAfterAction4.size();
		// Assert Count increase

		Assert.assertEquals(Count4, Count3 + 1, "Count Not increase");
		Thread.sleep(2000);
		testlog.log(test.verifyActionEntry(BDANumber), "New Entry Created");

		// ------------------------------------- Verification Part ---------------------------------------

		// 5 Why Analysis Screen

		List<WebElement> rowCount2 = driver.findElements(By.xpath(BDA.whyActionGallery));
		List<String> stringList2 = new ArrayList<String>();
		for (WebElement a : rowCount2) {

			String whyActions = a.getText();
			stringList2.add(whyActions.toString());

		}

		System.out.println(stringList2);

		// -------------------------------------Verify in BDA Action Tab ---------------------------------

		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath", BDA.bdaActionTab), "Click Add Action Button"); // "Click on BDA Action list
																							// tab"
		System.out.println("Clicked");
		List<WebElement> rowCount = driver.findElements(By.xpath(BDA.actionGallery));
		List<String> expectedResult = new ArrayList<String>();

		for (WebElement e : rowCount) {
			String actionData = e.getText();
			expectedResult.add(actionData.toString());
		}

		for (String expected : stringList2) {
			if (expectedResult.contains(expected)) {
				System.out.println("Actions verified");
				testlog.log(Status.PASS, "Actions verified");
			} else {
				System.out.println("verification failed");
				testlog.log(Status.FAIL, "Failed - verification failed");
			}
		}
		System.out.println("done at AT");

		// --------------------------------- Verify in Action Listing -----------------------------------

		Thread.sleep(5000);

		testlog.log(clickOnElement("xpath", "//*[@data-control-name='Img_HamburgerIcon_41']"),
				"Click on Hamburger Menu");
		testlog.log(clickOnElement("xpath", "//*[@data-control-name='btn_start_41']//button"), "Click on Start button");

		testlog.log(clickOnElement("xpath", BDA.actionListButton), "Click on Action Listing");
		Thread.sleep(3000);
		testlog.log(clickOnElement("xpath", BDA.filterA), "Click on Filter");
		testlog.log(enterValues("xpath", BDA.bdaInputA, BDANumber), "BDA Number Entered");
		testlog.log(clickOnElement("xpath", BDA.applyButtonA), "Click on Apply");

		List<WebElement> actionCount = driver.findElements(By.xpath(BDA.galleryA));
		List<String> expectedActions = new ArrayList<String>();

		for (WebElement e : actionCount) {
			String actionData = e.getText();
			expectedActions.add(actionData.toString());
		}

		for (String expected : expectedResult) {
			if (expectedActions.contains(expected)) {
				System.out.println("Actions verified in Action Listing");
				testlog.log(Status.PASS, "Actions verified in Action Listing");
			} else {
				System.out.println("verification failed in Action Listing");
				testlog.log(Status.FAIL, "Failed - verification failed in Action Listing");
			}
		}
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