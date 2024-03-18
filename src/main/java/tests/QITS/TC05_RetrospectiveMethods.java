package tests.QITS;

import java.io.IOException;
import java.util.HashMap;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.object_repository.*;

import actions.QITSmethods;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.http.client.ClientProtocolException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class TC05_RetrospectiveMethods extends BaseExecutor {
	public static final String TESTCASENAME = "Demo Test1";
	public static final int MANUALEXECUTIONTIME = 300;
	public static final String APPLICATIONNAME = "";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "";
	public static final String ENVIRONMENT = ""; // user test or prod only
	private ExtentTest testlog;
	private static WebDriver driver = null;
	
	public String parID="MCH00260";
	public String IMparID="10001041";
	public String UOM="g/l";
	public String Frequency="Each Delivery";

	@BeforeClass
	public void preCondition() {
		driver = initiateTest();
		testlog = extent.createTest(TESTCASENAME);
	}
	
	@Test
	public void Login() throws InterruptedException {
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
	public void process() throws InterruptedException, ClientProtocolException, IOException {
		
		QITSmethods verify =new QITSmethods(driver);
		Thread.sleep(2000);
		iFrameByIndex(0);	
		Thread.sleep(2000);

		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.ForFarmersBTN),"Click on ForFarmers Plant");
		Thread.sleep(3000);
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.materialSpec),"Click on Material Specification Tab");
		Thread.sleep(3000);
		testlog.log(enterValues("xpath",QITS.Homepage.Application.description,"testing Material"), "");
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.search), "Click on search");
		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.plus),"");
		Thread.sleep(2000);
		
		
		Thread.sleep(1000);
		testlog.log(clickOnElement("xpath",QITS.Homepage.Application.checkType),"Click on Checkype dropdown");
		Thread.sleep(2000);
		testlog.log(clickOnElement("xpath",QITS.Homepage.frequencyCheck.retro),"Select Retrospective method");
		Thread.sleep(1000);
		testlog.log(clickOnElement("xpath",QITS.Homepage.frequencyCheck.addmethodbttn),"Click on ADD button");
		
		Thread.sleep(3000);
		

		testlog.log(clickOnElement("xpath",QITS.Homepage.frequencyCheck.parID),"");
		Thread.sleep(2000);
		verify.clickListboxvalue(parID, QITS.Homepage.frequencyCheck.selectParID);

		Thread.sleep(1000);
		testlog.log(clickOnElement("xpath",QITS.Homepage.frequencyCheck.IMparID),"");
		Thread.sleep(2000);
		verify.clickListboxvalue(IMparID, QITS.Homepage.frequencyCheck.selectIMParID);
		Thread.sleep(1000);
		testlog.log(clickOnElement("xpath",QITS.Homepage.frequencyCheck.uom),"");
		Thread.sleep(2000);
		verify.clickListboxvalue(UOM, QITS.Homepage.frequencyCheck.selectUOM);
		Thread.sleep(1000);
		testlog.log(clickOnElement("xpath",QITS.Homepage.frequencyCheck.decimals),"");
		Thread.sleep(2000);
		verify.clickListboxvalue("0", QITS.Homepage.frequencyCheck.selectDecimal);
		Thread.sleep(1000);
		
		testlog.log(enterValues("xpath",QITS.Homepage.frequencyCheck.min , "1"), "min value");
		testlog.log(enterValues("xpath",QITS.Homepage.frequencyCheck.max , "2"), "max value");

		testlog.log(enterValues("xpath",QITS.Homepage.frequencyCheck.target ,"2"), "target value");

		testlog.log(clickOnElement("xpath",QITS.Homepage.frequencyCheck.frequency),"");
		Thread.sleep(2000);
		verify.clickListboxvalue(Frequency, QITS.Homepage.frequencyCheck.selectFrequency);
		Thread.sleep(1000);
		
		String method = getText("xpath",QITS.Homepage.frequencyCheck.methodName);
	String IMmethod= getText("xpath",QITS.Homepage.frequencyCheck.IMmethod);
	System.out.println(method);
	System.out.println(IMmethod);
	
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
//		driver.quit();
		setTestResults(testlog);
	}
}