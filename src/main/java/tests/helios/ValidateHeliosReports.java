package tests.helios;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.framework.TestData;
import com.object_repository.*;

import actions.HelioIntelligence;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;


public class ValidateHeliosReports extends HelioIntelligence {
	public static final String TESTCASENAME = "Compare Helios Report on FED";
	public static final int MANUALEXECUTIONTIME = 600;
	public static final String APPLICATIONNAME = "HeliosIntelligence";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "Test708";
	public static final String ENVIRONMENT = "test"; // user test or prod only
	private ExtentTest testlog;

	@BeforeClass
	public void preCondition() {
		driver = initiateTest();
		testlog = extent.createTest(TESTCASENAME);
	}

	@Test
	public void process() {
		TestData objTestData = new TestData();
		HashMap<String, JSONObject> testData = objTestData.getDataFromAPIGeneric("HeliosIntelligence", "ReportStatus");
		Set<String> reportSet =  testData.keySet();
		HashMap<String, String> prodScreenshotPaths = new HashMap<String, String>();
		HashMap<String, String> testScreenshotPaths = new HashMap<String, String>();
		HashMap<String, Status> testResult =  new HashMap<String, Status>();
		
		/*save current date in timeStamp*/
		String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
		
		testlog.log(openUrl("http://testheliosbiweb.apps.mars/Longview/BT_HTML5_LV.html"), "Launch Helios");
		testlog.log(loginToHelios(), "Login into Helios");
		syncBrowser(); 
		testlog.log(clickOnElementByJavascript("xpath", HeliosIntelligenceObjects.HomePage.LNK_STANDARD), "Click on Standard");
		
		JSONObject updateTdsPayload = new JSONObject();
		for(String currentReportId :  reportSet) {
			JSONObject currentTestData =  testData.get(currentReportId);
			String currentReport =  currentTestData.getString("ReportName");
			
			syncBrowser();
			testlog.log(enterValuesAndPressEnter("xpath",HeliosIntelligenceObjects.StandardPage.SPAN_SEARCH_BOX,currentReport), ("Search for Report "+currentReport));
			prodScreenshotPaths.put(currentReport, currentTestData.getString("Prod_SS"));
			testScreenshotPaths.put(currentReport, takeReportScreenshot(currentReport));
			
			testResult.put(currentReport, compareWithProdReport(prodScreenshotPaths.get(currentReport), testScreenshotPaths.get(currentReport)));
			
			testlog.log(testResult.get(currentReport), "Comparing " + currentReport);
			
			clickOnElementByJavascript("xpath", HeliosIntelligenceObjects.ReportPage.DIV_BACK);
			
			/*update results in TDS alongwith Test execution date and test screenshot path */
			updateTdsPayload.put("ReportName", currentReport);
			updateTdsPayload.put("TestExecutionDate", timeStamp);
			updateTdsPayload.put("Result", (testResult.get(currentReport)==Status.PASS?"Pass":"Fail"));
			updateTdsPayload.put("Prod_SS", currentTestData.getString("Prod_SS"));
			updateTdsPayload.put("Test_SS", testScreenshotPaths.get(currentReport));
			objTestData.updateDataToAPI("HeliosIntelligence", "ReportStatus", currentReportId, updateTdsPayload);			
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