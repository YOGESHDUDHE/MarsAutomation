package tests.MWSuppMatrix;



import java.util.HashMap;
import java.util.List;

import com.aventstack.extentreports.ExtentTest;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;

import actions.MWSupplierMatrixMethods;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TC_12_EditSupplier extends BaseExecutor {
	public static final String TESTCASENAME = "TC_12_EditSupplier";
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
		testlog.log(openUrl("https://apps.powerapps.com/play/33f42614-7f6a-4fdf-95b1-a9016faf84b0?tenantId=2fc13e34-f03f-498b-982a-7cb446e25bc6"), "Launch URL");
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
	public void editSupplier() throws InterruptedException {
		
		String CommercialChange ="Colors";
		
		iFrameByIndex(0);
		MWSupplierMatrixMethods test = new MWSupplierMatrixMethods(driver);
		
		Thread.sleep(5000);
		testlog.log(clickOnElement("xpath",MWSupplierMatrix.create.edit.editBttn), "");
		
		//Check Count of version
		
		testlog.log(clickOnElement("xpath",MWSupplierMatrix.create.vesion.VersionBttn), "");
		Thread.sleep(5000);
		List <WebElement> versions=driver.findElements(By.xpath(MWSupplierMatrix.create.vesion.versionCount));
		int count = versions.size();
		System.out.println(count);
		testlog.log(clickOnElement("xpath",MWSupplierMatrix.create.vesion.BACKBttn), "");
		
		//Commercial Ctegory
		Thread.sleep(1000);
		testlog.log(test.clickListboxElement(MWSupplierMatrix.create.edit.commCate, CommercialChange), " ");
		
		//change comment
		Thread.sleep(3000);
		testlog.log(enterValues("xpath", MWSupplierMatrix.create.edit.changeComment, "selenium test"), "comments");

		//Save & Close
		testlog.log(clickOnElement("xpath",MWSupplierMatrix.create.edit.saveClose ),"Save");
		
		//Save
		testlog.log(clickOnElement("xpath",MWSupplierMatrix.create.vesion.Save),"Save");
		
		//Check Count of version
		Thread.sleep(5000);
		testlog.log(clickOnElement("xpath",MWSupplierMatrix.create.edit.editBttn), "");
		Thread.sleep(3000);
		
					//validate changed record
		String update= driver.findElement(By.xpath(MWSupplierMatrix.create.edit.commCate)).getText();
		Assert.assertEquals(update, CommercialChange);
		
		testlog.log(clickOnElement("xpath",MWSupplierMatrix.create.vesion.VersionBttn), "");
		Thread.sleep(5000);
		List <WebElement> versions1=driver.findElements(By.xpath(MWSupplierMatrix.create.vesion.versionCount));
		int count1 = versions1.size();
		System.out.println(count1);
	
//		Validate Created or not
		Assert.assertEquals(count1, (count+1));
		
		
// Check highlighted colour

		testlog.log(clickOnElement("xpath",MWSupplierMatrix.create.vesion.goBtn), "");
		Thread.sleep(3000);
		// Get value of Css property border-bottom-color which will be returned in RGB format.
		WebElement changedBox = driver.findElement(By.xpath("//div[@data-control-name='CommercialCatChangeVer']/div"));
		String colorCode= changedBox.getCssValue("border-color");
		
		System.out.println("Color code in RGB" + colorCode);
		
		String expectedColorCodeInRGB= "rgb(255, 0, 0)";
		
		// Asserting actual and expected color codes
		Assert.assertEquals(colorCode, expectedColorCodeInRGB);

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