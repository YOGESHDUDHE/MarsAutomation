package tests.tpm;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;
import com.framework.ReportDatabase;
import com.framework.TestData;
import com.object_repository.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PaymentTestTPM extends BaseExecutor {

	public static final String TESTCASENAME = "TPM Test Payment";
	public static final int MANUALEXECUTIONTIME = 600;
	public static final String APPLICATIONNAME = "TPM Gem";
	public static final String GEOGRAPHY = "AP";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "Test552";
	public static final String ENVIRONMENT = "test"; // user test or prod only
	private ExtentTest testlog;

	@BeforeClass
	public void preCondition() {
		initiateTest();
		testlog = extent.createTest(TESTCASENAME);
	}

	@Test
	public void process() {

		int RandomNo = (int) (Math.random() * (99999 - 10000 + 1) + 10000);// Getting data from TDM
		TestData objTestData = new TestData();
		HashMap<String, String> testData = objTestData.getDataFromAPI("test", "TPMData");
		String value = testData.get("Singhje1");

		// Open TPM url
		testlog.log(openUrl("https://tpmtest.mars/Index.html"), "Launch TPM in Browser");

		// Login to TPM Application
		testlog.log(enterValues("id", TPMGEM.TPMApplication.UNAME, "Singhje1"), "Enter Username");
		testlog.log(enterValues("id", TPMGEM.TPMApplication.UNAMEVALUE, value), "Enter Password");
		testlog.log(clickOnElement("id", TPMGEM.TPMApplication.LOGIN), "Login to TPM");

		testlog.log(hoverOverElement("xpath", TPMGEM.TPMApplication.PAYMENTOPTION), "Hover over Payment Option on TPM");
		testlog.log(clickOnElement("xpath", TPMGEM.TPMApplication.ADDCLAIM), "Open Add Claim");
		testlog.log(changeFrame(TPMGEM.TPMApplication.CHANGEFRAME), "Switch to Frame");

		// Entering Payment no, Invoice No and Invoice date

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String x = date.format(formatter);
		testlog.log(enterValues("id", TPMGEM.PaymentTPM.PAYMENTNO, Integer.toString(RandomNo)), "Enter Payment Number");
		testlog.log(enterValues("id", TPMGEM.PaymentTPM.INVOICENO, Integer.toString(RandomNo)), "Enter Invoice Number");
		testlog.log(enterValues("id", TPMGEM.PaymentTPM.INVOICEDATE, x), "Enter Invoice Date");
		testlog.log(clickOnElement("xpath", TPMGEM.PaymentTPM.PAYTOCODESEARCH), "Clicking on Pay to search");
		releaseFromFrame();

		// Entering value in Pay To code
		testlog.log(iFrameByIndex(1), "Switch to Frame");
		testlog.log(enterValues("id", TPMGEM.PaymentTPM.CUSTOMERSEARCH, "10404181"), "Enter Customer Number");
		testlog.log(clickOnElement("id", TPMGEM.PaymentTPM.CUSTOMERSEARCHBTN), "Clicking on Customer search");
		testlog.log(clickOnElement("xpath", TPMGEM.PaymentTPM.RADIOVENDOR), "Selecting First row");
		releaseFromFrame();

		// Adding Payment Details
		testlog.log(changeFrame(TPMGEM.TPMApplication.CHANGEFRAME), "Switch to Frame");

		testlog.log(scrollToElement("id", TPMGEM.PaymentTPM.ADDLNK), "Scroll to Add link");
		testlog.log(clickOnElementByJavascript("id", TPMGEM.PaymentTPM.ADDLNK), "Click to Add link");
		releaseFromFrame();
		// waitForElementClickable("id",TPMGEM.TPMApplication.AddLnk);
		/*
		 * testlog.log(changeFrame(TPMGEM.TPMApplication.CHANGEFRAME),
		 * "Switch to Frame"); //Thread.sleep(5000);
		 * testlog.log(clickOnElement("xpath",testlog.log(clickOnElement("id",
		 * TPMGEM.PaymentTPM.ADDLNK), "Click on add Link");
		 */

		testlog.log(iFrameByIndex(1), "Switch to Frame");
		testlog.log(clickOnElement("xpath", TPMGEM.PaymentTPM.SEARCHCONTACTNO), "Click on Search button");
		releaseFromFrame();

		// Selecting Customer detail from Select Contract Page
		testlog.log(iFrameByIndex(2), "Switch to Frame");
		testlog.log(clickOnElement("xpath", TPMGEM.PaymentTPM.RADIOCUSTOMERSEARCH), "Select Customer Detail");
		releaseFromFrame();
		testlog.log(iFrameByIndex(1), "Switch to Frame");
		testlog.log(scrollToElement("xpath", TPMGEM.PaymentTPM.SAVEPAYMENTDETAIL), "Scroll and click on save");
		// testlog.log(clickOnElement("xpath", TPMGEM.PaymentTPM.SAVEPAYMENTDETAIL),
		// "Save Customer Detail");
		releaseFromFrame();

		// Submitting the claim
		testlog.log(clickOnElement("id", TPMGEM.PaymentTPM.SUBMITADDCLAIM), "Submitting details of Claim");
		testlog.log(clickOnElement("xpath", TPMGEM.PaymentTPM.OKTIPSPOPUP), "Confirm ok");

		// Selecting Payment No from Payment List
		if (verifyElementText("xpath", TPMGEM.PaymentTPM.CELLPAYMENTNO, Integer.toString(RandomNo)) == Status.PASS) {

			testlog.log(Status.PASS, "Verified Payment No in Payment List");
		} else {
			testlog.log(Status.FAIL, "Payment No not Verified");
		}
		if (verifyElementText("xpath", TPMGEM.PaymentTPM.CELLINVOICENO, Integer.toString(RandomNo)) == Status.PASS) {

			testlog.log(Status.PASS, "Verified Invoice No in Payment List");
		} else {
			testlog.log(Status.FAIL, "Invoice No not Verified");
		}

		// Opening Payment Approval in TO-DO
		testlog.log(hoverOverElement("xpath", TPMGEM.TPMApplication.TODOOPTION), "Hover over TO-DO Option on TPM");
		testlog.log(clickOnElement("xpath", TPMGEM.TPMApplication.PAYMENTAPPROVAL), "Opening Payment Approval Page");

		// Selecting Payment No from table

		while (verifyElementText("xpath", TPMGEM.PaymentTPM.LNKPAYMENTNOR1C4,
				Integer.toString(RandomNo)) == Status.PASS) {

			testlog.log(clickOnElement("xpath", TPMGEM.PaymentTPM.LNKPAYMENTNOR1C4), "Click on Payment No");

			// Verifying Payment No and Invoice No on PaymentInfo Page
			testlog.log(iFrameByIndex(0), "Switch to Parent Frame");
			testlog.log(iFrameByIndex(0), "Switch to Child Frame");
			if (verifyElementText("xpath", TPMGEM.PaymentTPM.VERIFYPAYMENTINFO,
					Integer.toString(RandomNo)) == Status.PASS) {

				testlog.log(Status.PASS, "Verified Payment No on Payment Info Page");
			} else {
				testlog.log(Status.FAIL, "Payment No not Verified");
			}
			if (verifyElementText("xpath", TPMGEM.PaymentTPM.VERIFYPAYMENTINFO,
					Integer.toString(RandomNo)) == Status.PASS) {

				testlog.log(Status.PASS, "Verified Invoice No in Payment Info Page");
			} else {
				testlog.log(Status.FAIL, "Invoice No not Verified");
			}
			releaseFromFrame();
			// Click on approve
			testlog.log(iFrameByIndex(0), "Switch to Parent Frame");
			testlog.log(clickOnElement("id", TPMGEM.PaymentTPM.APPROVEBTN), "Payment Info Approved");
			testlog.log(clickOnElement("xpath", TPMGEM.PaymentTPM.OKAPPROVEINFO), "Confirmation");

		}

		testlog.log(clickOnElement("xpath", TPMGEM.PaymentTPM.APPROVEDBYME), "Clicking on Approved by Me");
		if (verifyElementText("xpath", TPMGEM.PaymentTPM.VERIFYAPPROVEDPAYMENTINFO,
				Integer.toString(RandomNo)) == Status.PASS) {

			testlog.log(verifyElementText("xpath", TPMGEM.PaymentTPM.APPROVEDSTATUS, "Approved"),
					"Verified Payment No on Payment Info Page");
		} else {
			testlog.log(Status.FAIL, "Payment No not Verified");

		}

		// Logout
		testlog.log(clickOnElement("xpath", TPMGEM.TPMApplication.LOGOUTUNAME), "Clicking on Username for Logout");
		testlog.log(clickOnElement("xpath", TPMGEM.TPMApplication.LNKLOGOUT), "Logout");

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