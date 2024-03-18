package tests.jda;

import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.framework.TestData;
import com.object_repository.JDA;

public class testJDA extends BaseExecutor {
	public static final String TESTCASENAME = "JDA Test";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "";
	public static final String ENVIRONMENT = "test"; // user test or prod only
	private ExtentTest testlog;

	@BeforeClass
	public void preCondition() {
		initiateTest();
		testlog = extent.createTest(TESTCASENAME);
	}

	@Test
	public void JDA() throws InterruptedException {

		String workOrder;
		// Getting data from TDM
		TestData objTestData = new TestData();
		HashMap<String, String> testData = objTestData.getDataFromAPI("test","JDAData");
		String value = testData.get("gollanee");

		// Open JDA url
		testlog.log(openUrl("https://azr-eus2w6354.mars-ad.net:8060/"), "Launch JDA in Browser");

		// Login to JDA Application
		testlog.log(enterValues("id", JDA.Homepage.UNAME, "gollanee"), "Enter Username");
		testlog.log(enterValues("id", JDA.Homepage.UNAMEPD, value), "Enter Password");
		testlog.log(clickOnElement("id", JDA.Homepage.LOGINBTN), "Login to JDA");

		// Changing Site Information

		waitForElementClickable("id", JDA.Homepage.HEADER);
		testlog.log(clickOnElement("id", JDA.Homepage.HEADER), "Click to change Site information");
		testlog.log(iFrameByIndex(1), "Switch to Frame");
		testlog.log(clickOnElement("id", JDA.Homepage.DROPDOWNSITE), "Click to Drop Down");
//		testlog.log(clickOnElement("xpath", JDA.Homepage.CHOOSESITE), "Entering information of site");
		testlog.log(clickOnElement("id", JDA.Homepage.SELECTBTN), "Click Ok change Site information");
		releaseFromFrame();

		// Selecting shipping waitForElementClickable("id", JDA.Homepage.header);
		testlog.log(clickOnElement("xpath", JDA.Homepage.DROPDOWNBTN), "Click on drop down");

		testlog.log(clickOnElement("id", JDA.Homepage.SHIPPING), "Select Shipping");

		// selecting Mars Tender Operations
		testlog.log(clickOnElement("id", JDA.shipping.OPTIONBTN), "Select Option Menu");
		waitForElementClickable("id", JDA.Homepage.HEADER);
		testlog.log(clickOnElement("xpath", JDA.shipping.MARSTENDEROPERATIONS), "Select Mars Tender Operations");
		releaseFromFrame();

		// Operations on Mars Tender Operations
		testlog.log(iFrameByIndex(2), "Switch to Frame");
		testlog.log(clickOnElement("xpath", JDA.shipping.SELECTCHECKBOXUPPER), "Select Checkbox in Table");
		testlog.log(clickOnElement("xpath", JDA.shipping.OKPOPUP), "Click Ok");
		testlog.log(clickOnElement("id", JDA.shipping.ACTIONBTNUPPER), "Click on Action Button");
		testlog.log(clickOnElement("id", JDA.shipping.REQUESTBTN), "Opening Request Page");

		// Request Page
//		testlog.log(clickOnElement("className", JDA.shipping.TOGGLEREQUEST), "Click to ON the Toggle Button");
		testlog.log(enterValues("name", JDA.shipping.REQUESTVALUE, "50000749"), "Entering Request Value");
		testlog.log(enterValues("name", JDA.shipping.REQUESTQUANTITY, "2"), "Entering Quantity");
		testlog.log(clickOnElement("xpath", JDA.shipping.REQUESTEXECUTE), "Click to Execute Button");

		// waitForElementClickable("id", JDA.Homepage.header);
		testlog.log(clickOnElement("xpath", JDA.shipping.SELECTCHECKBOXUPPER), "Select Checkbox in Table");
		testlog.log(clickOnElement("xpath", JDA.shipping.OKPOPUP), "Click Ok");

		testlog.log(scrollToElement("xpath", JDA.shipping.DATEFILTER), "Scroll to filter Date");
		testlog.log(clickOnElementByJavascript("xpath", JDA.shipping.DATEFILTER), "Click to filter Date");
		testlog.log(clickOnElement("xpath", JDA.shipping.PAGE2), "Entering Page No");

		testlog.log(scrollToElement("xpath", JDA.shipping.SELECTCHECKBOXLOWER), "Scroll to Row");
		testlog.log(clickOnElementByJavascript("xpath", JDA.shipping.SELECTCHECKBOXLOWER), "Click to Row");
		testlog.log(clickOnElement("id", JDA.shipping.ACTIONBTNLOWER), "Click on Action Button");
		testlog.log(clickOnElement("xpath", JDA.shipping.ACCEPTBTN), "Click on Accept Button");
		testlog.log(enterValues("xpath", JDA.shipping.CARRIERNAME, "50000749"), "Entering Carrier Value");
		testlog.log(clickOnElement("xpath", JDA.shipping.EXECUTEACTION), "Click on Execute Action Button");
		releaseFromFrame();

		// Production waitForElementClickable("id", JDA.Homepage.header);
		testlog.log(clickOnElement("id", JDA.Homepage.DROPDOWNBTN), "Click on drop down");
		testlog.log(clickOnElement("xpath", JDA.Homepage.PRODUCTION), "Select Production");
		iFrameByIndex(3);
		testlog.log(scrollToElement("xpath", JDA.production.WORKORDER), "Scroll to Element");
		workOrder = getText("xpath", JDA.production.WORKORDER);
		System.out.println(workOrder);
		releaseFromFrame();
		testlog.log(clickOnElement("xpath", JDA.production.WORKORDER), "Click on Work Order Tab");
		iFrameByIndex(3);
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