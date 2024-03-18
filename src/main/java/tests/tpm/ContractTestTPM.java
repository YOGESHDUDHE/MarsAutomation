package tests.tpm;


import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.framework.TestData;
import com.object_repository.TPMGEM;

public class ContractTestTPM extends BaseExecutor {
	public static final String TESTCASENAME = "Demo Test1";
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
	public void contractTPM() {

		// Getting data from TDM
		TestData objTestData = new TestData();
		HashMap<String, String> testData = objTestData.getDataFromAPI("test", "TPMData");
		String value = testData.get("Singhje1");
		System.out.println(value);

		// Open TPM url
		testlog.log(openUrl("https://tpmtest.mars/Index.html"), "Launch TPM in Browser");

		// Login to TPM Application
		testlog.log(enterValues("id", TPMGEM.TPMApplication.UNAME, "Singhje1"), "Enter Username");
		// Failing this test case - using Xpath instead of Id
		testlog.log(enterValues("id", TPMGEM.TPMApplication.UNAMEVALUE, value), "Enter Password");
		testlog.log(clickOnElement("id", TPMGEM.TPMApplication.LOGIN), "Login to TPM");

		// Opening Contract Upload page
		testlog.log(hoverOverElement("xpath", TPMGEM.TPMApplication.CONTRACTOPTION),
				"Hover over Contract Option on TPM");
		testlog.log(clickOnElement("xpath", TPMGEM.TPMApplication.CONTRACTUPLOAD), "Open Contract Upload Page");
		testlog.log(changeFrame(TPMGEM.TPMApplication.CHANGEFRAME), "Swtich to Frame");

		/*
		 * //Uploading file testlog.log(enterValues("id",TPMGEM.ContractTPM.
		 * BrowseBtn,"OneDrive - Mars Inc\\ContractUpload -Copy.xlsx"),"Uploading File"
		 * ); testlog.log(clickOnElement("id", TPMGEM.ContractTPM.ContractSubmit),
		 * "Submit the uploaded file"); testlog.log(clickOnElement("id",
		 * TPMGEM.TPMApplication.login), "Login to TPM");
		 * 
		 * // Opening Contract Approval in TO-DO testlog.log(hoverOverElement("xpath",
		 * TPMGEM.TPMApplication.ToDoOption), "Hover over TO-DO Option on TPM");
		 * testlog.log(clickOnElement("xpath", TPMGEM.TPMApplication.ContractApproval),
		 * "Opening Contract Approval Page");
		 * 
		 * //Selecting Contract No from list testlog.log(verifyElementText("xpath",
		 * TPMGEM.ContractTPM.ContractStatus, "Pending"),"Verify Contract is Pending");
		 * testlog.log(verifyElementText("xpath", TPMGEM.ContractTPM.ContractCreatedBy,
		 * "Singhje1"),"Verify Created By"); testlog.log(clickOnElement("xpath",
		 * TPMGEM.ContractTPM.ClickRadioR1C1),
		 * "Selecting Contract from Contract Approval Page"); String ContractNo;
		 * ContractNo = getText("xpath", TPMGEM.ContractTPM.ContractNoR1C5);
		 */

	}

	@AfterClass
	public void postCondition() throws Exception {
		HashMap<Object, Object> testCaseData = new HashMap<Object, Object>();
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