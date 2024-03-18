package tests.ppm;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.framework.ExcelCell;
import com.framework.ReportDatabase;
import com.framework.TestData;
import com.object_repository.*;
import actions.PPMAutomation;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PPM_Scenarios_test extends PPMAutomation {
	public static final String TESTCASENAME = "PPM_Scenarios_test";
	public static final int MANUALEXECUTIONTIME = 1500;
	public static final String APPLICATIONNAME = "PPM";
	public static final String GEOGRAPHY = "Global";
	public static final String SEGMENT = "Global";
	public static final String PROJECTID = "TEST686";
	public static final String ENVIRONMENT = "test"; // user test or prod only
	private ExtentTest testlog;

	@BeforeClass
	public void preCondition() {
		driver = initiateTest();
		testlog = extent.createTest(TESTCASENAME);
	}

	@Test
	public void ppm_Scenarios_test() throws InterruptedException, IOException {
		String ProjectID = "";
		int ScenarioNumber = Integer.parseInt(getPPMTestData("PPM_Conf", "Scenario_number"));
		String location = "";
		String csf = "";
		String csf_sta = "";
		String targ = "";
		String targ_sta_val = "";
		// Opens PPM Test Application
		testlog.log(Status.INFO, "Case no1: open PPM...");
		testlog.log(loginToPPM("test", getPPMTestData("PPM_Conf", "username"), getPPMTestData("PPM_Conf", "password")),
				"open url");
		syncBrowser();
		if (ScenarioNumber == 1) {
			// first scenario
			// Clicks on New Project
			testlog.log(Status.INFO, "Case no2: Creation of project...");
			testlog.log(clickOnElement("xpath", PPM.ppmHomePage.LNK_PROJECT), "Click on Project");
			testlog.log(hoverOverElement("xpath", PPM.ppmHomePage.LNK_ADD_NEW_PROJECT), "Click on Add new");
			testlog.log(Status.INFO, "create new project",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("create new project", "")).build());
			testlog.log(clickOnElement("xpath", PPM.ppmHomePage.LNK_CONFECTIONERY), "Click on  confectionery Project");
			syncBrowser();
			// Enter Project Name & Access groups
			testlog.log(
					enterValues("id", PPM.addNewScreenpage.LNK_PROJECTNAME, getPPMTestData("PPM_Conf", "Project_Name")),
					"Enter Project Name in Name field");
			syncBrowser();
			testlog.log(enterValues("id", PPM.addNewScreenpage.LNK_ACCESSGROUP,
					getPPMTestData("PPM_Conf", "Access_Groups")), "Enter Access group");
			syncBrowser();
			// Select NPR Approval Date
			testlog.log(clickOnElement("xpath", PPM.addNewScreenpage.LNK_NPRAPPROVAL), "Click on NPR Approval");
			testlog.log(enterValuesAndPressEnter("xpath", PPM.addNewScreenpage.SELECT_NPR,
					getPPMTestData("PPM_Conf", "NPR_Date")), "Enter Enter date for NPR approval");
			syncBrowser();
			// select period
			testlog.log(clickOnElement("xpath", PPM.addNewScreenpage.LNK_LAUNCHPERIOD), "Click on period");
			testlog.log(selectFromDrpDwnByText("xpath", PPM.addNewScreenpage.DRPDWN_DATASELECT,
					getPPMTestData("PPM_Conf", "Period")), "Select period");
			// Select Launch year
			testlog.log(clickOnElement("xpath", PPM.addNewScreenpage.LNK_LAUNCHYEAR), "Click on launch year");
			testlog.log(selectFromDrpDwnByText("xpath", PPM.addNewScreenpage.DRPDWN_DATASELECT,
					getPPMTestData("PPM_Conf", "Launch_Year")), "Select launch year");
			// Select Business Units
			testlog.log(clickOnElement("xpath", PPM.addNewScreenpage.LNK_CONF_CATEGORYBUSINESSUNIT),
					"Click on category business unit");
			testlog.log(selectFromDrpDwnByText("xpath", PPM.addNewScreenpage.DRPDWN_DATASELECT,
					getPPMTestData("PPM_Conf", "Business_Unit")), "Select category business unit");
			// Select Volume Units
			testlog.log(clickOnElement("xpath", PPM.addNewScreenpage.LNK_CONF_CATEGORYVOLUMEUNIT),
					"Select Conf Volume Units");
			testlog.log(selectFromDrpDwnByText("xpath", PPM.addNewScreenpage.DRPDWN_DATASELECT,
					getPPMTestData("PPM_Conf", "Volume_Unit")), "Select category Volume  unit");
			testlog.log(Status.INFO, "specified values to  create new project", MediaEntityBuilder
					.createScreenCaptureFromPath(takeScreenShot("PPM", "specified values to  create new project"))
					.build());
			// Click on Create
			testlog.log(clickOnElement("id", PPM.addNewScreenpage.BTN_CREATE_PROJECT), "Click on create");
			// Buffer Project ID
			testlog.log(clickOnElement("id", PPM.newProjectpage.LNK_PROJECTID), "Buffer the project ID");
			testlog.log(Status.INFO, "Buffer the Project ID",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "verifying_input")).build());
			ProjectID = getText("id", PPM.newProjectpage.LNK_PROJECTID);
			testlog.log(Status.INFO, "Project id for the new project" + ProjectID);
		} else {
			ProjectID = getPPMTestData("PPM_Conf", "new_confectionery_npr_projectid_to_run_2_scenario");
		}
		testlog.log(Status.INFO, "Case 3: Find the project...");
		syncBrowser();
		// 3rd Scenario
		testlog.log(waitForElementClickable("xpath", PPM.ppmHomePage.LNK_PROJECT), "wait for element clickable");
		testlog.log(clickOnElement("xpath", PPM.ppmHomePage.LNK_PROJECT), "Click on project");
		testlog.log(clickOnElement("xpath", PPM.ppmHomePage.LNK_FIND_PROJECT), "Clicks on find");
		testlog.log(Status.INFO, "Findindg Project",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Findindg Project")).build());
		syncBrowser();
		// Enter Project ID
		changeFrame(PPM.findScreenPage.CHANGEFRAME);
		testlog.log(clickOnElementByJavascript("id", PPM.findScreenPage.TXT_PROJECT_ID), "Enter project id");
		testlog.log(enterValues("id", PPM.findScreenPage.TXT_PROJECT_ID, ProjectID), "Enter projoect id ");
		testlog.log(Status.INFO, "Enter the project ID",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Enter the project ID")).build());
		// Click on Submit
		testlog.log(scrollToElement("id", PPM.findScreenPage.BTN_SUBMIT), "Scroll to the submit");
		testlog.log(clickOnElementByJavascript("id", PPM.findScreenPage.BTN_SUBMIT), "Click on submit");
		releaseFromFrame();
		// Verify the value from Table
		changeFrame(PPM.findScreenPage.CHANGEFRAME);
		testlog.log(clickOnElement("xpath", PPM.findScreenPage.TABLE_SEARCHDATA), "Click on tabled search data");
		testlog.log(Status.INFO, "Click on Table",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Click on Table")).build());
		testlog.log(clickOnElement("xpath", PPM.findScreenPage.LNK_PROJECT_ID), "Click on project id");
		releaseFromFrame();
		syncBrowser();
		testlog.log(Status.INFO, "Case 4:Add a team member.");
		testlog.log(waitForElementClickable("xpath", PPM.findScreenPage.TEAMS_TAB), "Wait for element");
		// 4th scenario - ADD A NEW USER
		testlog.log(clickOnElement("xpath", PPM.findScreenPage.TEAMS_TAB), "Click on Teams Tab");
		syncBrowser();
		// Add User
		testlog.log(clickOnElement("id", PPM.teamsPage.BTN_ADDUSER), "Click on Add Users");
		testlog.log(Status.INFO, "Adding user",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Adding user")).build());
		syncBrowser();
		// Enter user name
		testlog.log(clickOnElement("id", PPM.teamsPage.CLK_USERNAME), "Click on Username Text Box");
		testlog.log(enterValues("id", PPM.teamsPage.CLK_USERNAME, getPPMTestData("PPM_Conf", "New_User_Name1")),
				"Enter Username and press enter");
		syncBrowser();
		testlog.log(hoverOverElement("xpath", PPM.teamsPage.SELECT_USER), "Select the displayed user");
		testlog.log(clickOnElement("xpath", PPM.teamsPage.SELECT_USER), "Select the displayed user");
		testlog.log(clickOnElement("xpath", PPM.teamsPage.BTN_ADDSELECTEDUSER), "Add the selected user");
		testlog.log(Status.INFO, "Add selected user",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Add selected user")).build());
		// Click on Done
		testlog.log(clickOnElement("xpath", PPM.teamsPage.BTN_DONE), "Click on Done");
		// click on Apply button
		testlog.log(clickOnElement("id", PPM.teamsPage.BTN_APPLY), "Click on Apply Button");
		syncBrowser();
		testlog.log(Status.INFO, "Case5: Input the deliverable");
		// 5th Scenario - INPUT THE DELIVERABLE
		// Click on Stages Tab
		testlog.log(clickOnElement("xpath", PPM.findScreenPage.STAGES_TAB), "Click on Stages Tab");
		syncBrowser();
		// Select Project Owner and click on Apply
		testlog.log(clickOnElement("xpath", PPM.stageScreen.CLK_ONOWNER), "Click on Owner");
		testlog.log(
				selectFromDrpDwnByText("xpath", PPM.stageScreen.DRP_DWN, getPPMTestData("PPM_Conf", "Select_Owner")),
				"select owner");
		testlog.log(Status.INFO, "Selecting Owners",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Selecting Owners")).build());
		testlog.log(clickOnElement("id", PPM.stageScreen.BTN_APPLYFORSTAGES), "Click on Apply Button");
		// Click on Quest : Project Charter
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		testlog.log(clickOnElementByJavascript("xpath", PPM.findScreenPage.PROJECTCHARTER),
				"Click on Quest : ProjectCharter");
		syncBrowser();
		// UnExpected Pop up
		testlog.log(clickOnElement("xpath", PPM.stageScreen.Done), "Click on Done");
		syncBrowser();
		// Enter the variables in the respective fields
		testlog.log(clickOnElement("xpath", PPM.stageScreen.TEXTCRITICALSUCCESSFACTOR), "Click on CSF1 text box");
		testlog.log(enterValues("xpath", PPM.stageScreen.TEXTCRITICALSUCCESSFACTOR,
				getPPMTestData("PPM_Conf", "Critical_Success_Factor")), "Enter CSF");
		testlog.log(enterValues("xpath", PPM.stageScreen.DRP_DWN_CSFSTATUS, getPPMTestData("PPM_Conf", "Csf_status")),
				"Select CSF Status");
		testlog.log(clickOnElement("xpath", PPM.stageScreen.TARGETTEXT), "Click on Target text box");
		testlog.log(enterValues("xpath", PPM.stageScreen.TARGETTEXT, getPPMTestData("PPM_Conf", "Target")),
				"Enter Target details");
		testlog.log(
				enterValues("xpath", PPM.stageScreen.DRP_DWN_TARGETSTATUS, getPPMTestData("PPM_Conf", "Target_status")),
				"Select Target Status");
		testlog.log(Status.INFO, "Providing inputs",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Providing inputs")).build());
		// Click on Apply button & Close
		testlog.log(clickOnElement("xpath", PPM.stageScreen.BTN_APPLY), "Click on Apply Button");
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		testlog.log(clickOnElementByJavascript("xpath", PPM.stageScreen.BTN_CLOSE), "Click on Close Button");
		testlog.log(Status.INFO, "Case6: Migrate project");
		// 6th scenario
		// click on manage Project
		testlog.log(clickOnElement("id", PPM.stageScreen.MANAGEPROJECT), "Click on manage project");
		// migrate or copy
		testlog.log(clickOnElementByJavascript("xpath", PPM.stageScreen.MIGRATEORCOPY), " Click on Migrate copy");
		testlog.log(Status.INFO, "Migrating into new project", MediaEntityBuilder
				.createScreenCaptureFromPath(takeScreenShot("PPM", "Migrating into new project")).build());
		syncBrowser();
		testlog.log(changeFrame(PPM.findScreenPage.CHANGEFRAME), "Swtich to Frame");
		// go to migrate map and select Confectionery - From NPR to QuEST
		testlog.log(clickOnElement("id", PPM.migrateTheProject.MIGRATEMAP), "Click on Migrate map");
		testlog.log(
				enterValues("id", PPM.migrateTheProject.MIGRATEMAP,
						getPPMTestData("PPM_Conf", "Confectionery - From NPR to QuEST")),
				"Select Confectionery - From NPR to QuEST");
		// click on continue and Finish
		testlog.log(scrollToElement("cssSelector", PPM.findScreenPage.CONTINUE), "Scroll to continue");
		syncBrowser();
		testlog.log(waitForElementClickable("cssSelector", PPM.findScreenPage.CONTINUE),
				"Wait For Element to be clickable");
		testlog.log(clickOnElement("cssSelector", PPM.findScreenPage.CONTINUE), "Click on continue");
		syncBrowser();
		releaseFromFrame();
		testlog.log(changeFrame(PPM.findScreenPage.CHANGEFRAME), "Swtich to Frame");
		syncBrowser();
		testlog.log(scrollToElement("cssSelector", PPM.findScreenPage.FINISH), "Scroll to finish");
		syncBrowser();
		testlog.log(waitForElementClickable("cssSelector", PPM.findScreenPage.FINISH),
				"Wait For Element to be clickable");
		testlog.log(clickOnElement("cssSelector", PPM.findScreenPage.FINISH), "Click on finish");
		releaseFromFrame();
		// buffer the project Id
		testlog.log(clickOnElement("id", PPM.newProjectpage.LNK_PROJECTID), "Buffer the project ID");
		testlog.log(Status.INFO, "Buffer the Migrated project ID", MediaEntityBuilder
				.createScreenCaptureFromPath(takeScreenShot("PPM", "Buffer the Migrated project ID")).build());
		testlog.log(Status.INFO, "ProjectID after migration :" + getText("id", PPM.newProjectpage.LNK_PROJECTID));
		syncBrowser();
		testlog.log(Status.INFO, "Case7: Add a team member for migrated project ");
		// 7th scenario
		// click on teams tab
		testlog.log(clickOnElement("xpath", PPM.findScreenPage.TEAMS_TAB), "Click on Teams Tab");
		syncBrowser();
		// click on add user
		testlog.log(clickOnElement("id", PPM.teamsPage.BTN_ADDUSER), "Click on Add Users");
		testlog.log(Status.INFO, "Adding Users To Migrated Project", MediaEntityBuilder
				.createScreenCaptureFromPath(takeScreenShot("PPM", "Adding Users To Migrated Project")).build());
		syncBrowser();
		// click on search field
		testlog.log(clickOnElement("id", PPM.teamsPage.CLK_USERNAME), "Click on Username Text Box");
		// enter user name to search
		testlog.log(enterValues("id", PPM.teamsPage.CLK_USERNAME, getPPMTestData("PPM_Conf", "Migrate_new_user")),
				"Enter Username and press enter");
		syncBrowser();
		// select the displayed user
		testlog.log(hoverOverElement("xpath", PPM.teamsPage.SELECT_USER), "Select the displayed user");
		testlog.log(clickOnElement("xpath", PPM.teamsPage.SELECT_USER), "Select the displayed user");
		// click on add
		testlog.log(clickOnElement("xpath", PPM.teamsPage.BTN_ADDSELECTEDUSER), "Add the selected user");
		// click on done
		testlog.log(clickOnElement("xpath", PPM.teamsPage.BTN_DONE), "Click on Done");
		// click on apply
		testlog.log(clickOnElement("id", PPM.teamsPage.BTN_APPLY), "Click on Apply Button");
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		syncBrowser();
		testlog.log(Status.INFO, " Case8: Input the deliverable");
		// 8th scenario
		// click on stages tab
		testlog.log(clickOnElement("xpath", PPM.findScreenPage.STAGES_TAB), "Click on Stages Tab");
		syncBrowser();
		// click on Project description owner
		testlog.log(waitForElementClickable("xpath", PPM.stageScreen.PROJECTDESCRIPTION_OWNER),
				"Wait for element clickable");
		testlog.log(clickOnElement("xpath", PPM.stageScreen.PROJECTDESCRIPTION_OWNER), "Click to select owner");
		testlog.log(selectFromDrpDwnByText("xpath", PPM.stageScreen.DRP_DWN,
				getPPMTestData("PPM_Conf", "Owner_for_migrated")), "Select owner");
		testlog.log(Status.INFO, "SelectOwner For Migrated Project", MediaEntityBuilder
				.createScreenCaptureFromPath(takeScreenShot("PPM", "SelectOwner For Migrated Project")).build());
		// click on apply
		testlog.log(clickOnElement("id", PPM.teamsPage.BTN_APPLY), "apply");
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		// click on project description tracker
		testlog.log(clickOnElement("xpath", PPM.stageScreen.PROJECTDESCRIPTIONTRACKER),
				"Click on project description tracker");
		// get the values and verify
		syncBrowser();
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.CSFVALUE), "Buffer the CSf Text");
		csf = getValue("xpath", PPM.projectDescriptionTracker.CSFVALUE);
		testlog.log(Status.INFO, csf);
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.CSFSTATUSVALUE),
				"buffer the CSF status value Text");
		csf_sta = getValue("xpath", PPM.projectDescriptionTracker.CSFSTATUSVALUE);
		testlog.log(Status.INFO, csf_sta);
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.TARGETVALUE), "Buffer the taget text");
		targ = getValue("xpath", PPM.projectDescriptionTracker.TARGETVALUE);
		testlog.log(Status.INFO, targ);
		testlog.log(Status.INFO, "verifying inputs",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "verifying_inputs")).build());
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.TARGETSTATUSVALUE),
				"Buffer the targetstatusvalue Text");
		targ_sta_val = getValue("xpath", PPM.projectDescriptionTracker.TARGETSTATUSVALUE);
		testlog.log(Status.INFO, targ_sta_val);
		if ((csf.contentEquals(getPPMTestData("PPM_Conf", "Critical_Success_Factor")))) {
			testlog.log(Status.PASS, "CSF1 filed  verified");
		} else {
			testlog.log(Status.FAIL, "CSF1 filed not verified");
		}
		if (csf_sta.contentEquals(getPPMTestData("PPM_Conf", "Csf_status"))) {
			testlog.log(Status.PASS, "CSF1_Status  verified");
		} else {
			testlog.log(Status.FAIL, "CSF1_Status  not verified");
		}

		if ((targ.contentEquals(getPPMTestData("PPM_Conf", "Target")))) {
			testlog.log(Status.PASS, "Target1 field  verified");
		} else {
			testlog.log(Status.FAIL, "Target1 field not verified");
		}
		if ((targ_sta_val.contentEquals(getPPMTestData("PPM_Conf", "Target_status")))) {
			testlog.log(Status.PASS, "Target1_status  verified");
		} else {
			testlog.log(Status.FAIL, "Target1_status not verified");
		}
		testlog.log(clickOnElement("xpath", PPM.stageScreen.BTN_CLOSE), "Click on Close Button");
		testlog.log(Status.INFO, "Case9: Input the task");
		// 9th Scenario
		testlog.log(clickOnElement("xpath", PPM.stageScreen.PROJECTDESCRIPTIONTRACKER),
				"Click on project description tracker");
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.TASKEXPANDER), "Expand the task");
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.PROJECTMEETINGMIN),
				"Click on project meeting min");
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.CONSUMERTESTRESULT), "Click on consumer");
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.PLANDATE), "Click on plan date");
		testlog.log(enterValuesAndPressEnter("xpath", PPM.projectDescriptionTracker.PLANDATE,
				getPPMTestData("PPM_Conf", "plandate")), "enter date");
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.ACTUALDATE), "Click on actual date");
		testlog.log(enterValuesAndPressEnter("xpath", PPM.projectDescriptionTracker.ACTUALDATE,
				getPPMTestData("PPM_Conf", "actualdate")), "enter date");
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.CLICKSTATUS), "click on status");
		testlog.log(
				enterValues("xpath", PPM.projectDescriptionTracker.CLICKSTATUS, getPPMTestData("PPM_Conf", "option")),
				"click on inprogress");
		testlog.log(Status.INFO, "click on Progress",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "click on Progress")).build());
		testlog.log(clickOnElementByJavascript("xpath", PPM.projectDescriptionTracker.APPLY), "click on apply");
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		testlog.log(clickOnElementByJavascript("xpath", PPM.stageScreen.BTN_CLOSE), "Click on close");
		testlog.log(Status.INFO, "Case 10:Download the templated");
		// 10th Scenario
		testlog.log(clickOnElement("xpath", PPM.stageScreen.PROJECTDESCRIPTIONTRACKER),
				"click on project description tracter");
		// add China to participating markets
		testlog.log(
				clickOnElement("xpath", PPM.projectDescriptionTracker.PROJECTCLASSIFICATION_PARTCIPATINGMARKET_CHINA),
				"select china in parcticipating market");
		// click on right sides button
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.BTN_RIGHT), "Click on [<]");
		// add brazil to participating market
		testlog.log(
				clickOnElement("xpath", PPM.projectDescriptionTracker.PROJECTCLASSIFICATION_PARTCIPATINGMARKET_BRAZIL),
				"select brazil in parcticipating market");
		// click on right sides button
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.BTN_RIGHT), "Click on [<]");
		testlog.log(Status.INFO, "Assigning China and Brazil Markets", MediaEntityBuilder
				.createScreenCaptureFromPath(takeScreenShot("PPM", "Assigning China and Brazil Markets")).build());
		// click on apply
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.BTN_APPLY), "Click on apply");
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		syncBrowser();
		// click on idea generation db
		testlog.log(clickOnElementByJavascript("xpath", PPM.projectDescriptionTracker.CLK_IDEAGENERATION),
				"Click on idea generation DB");
		// go to businees size of prize and select owner as my self
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		syncBrowser();
		testlog.log(clickOnElement("xpath", PPM.stageScreen.BUSSINESSSIZEPRIZETOPRIZEOWNER),
				"Select owner for business mode size for prize");
		testlog.log(selectFromDrpDwnByText("xpath", PPM.stageScreen.DRP_DWN,
				getPPMTestData("PPM_Conf", "owner_for_business_sizeofprize")), "Select owner");
		// click on expander
		testlog.log(clickOnElement("xpath", PPM.stageScreen.EXPANDER), "Click on expander");
		syncBrowser();
		// go to china and select owner
		testlog.log(scrollToElement("xpath", PPM.stageScreen.SELECT_OWNER_FOR_CHINA), "Scroll to Element");
		testlog.log(waitForElementClickable("xpath", PPM.stageScreen.SELECT_OWNER_FOR_CHINA),
				"wait for element clickable");
		testlog.log(clickOnElement("xpath", PPM.stageScreen.SELECT_OWNER_FOR_CHINA), "Select owner for china ");
		testlog.log(waitForElementClickable("xpath", PPM.stageScreen.DRP_DWN), "wait for element clickable");
		testlog.log(selectFromDrpDwnByText("xpath", PPM.stageScreen.DRP_DWN,
				getPPMTestData("PPM_Conf", "china_market_owner")), "Select owner");
		// click on apply
		testlog.log(clickOnElement("id", PPM.stageScreen.BTN_APPLYFORSTAGES), "Apply button");
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		// click on china
		testlog.log(clickOnElement("xpath", PPM.stageScreen.CLK_CHINA), "Click on china ");
		syncBrowser();
		location = getLocationOfFile();
		testlog.log(hoverOverElement("xpath", PPM.projectDescriptionTracker.DOWNLOADTEMPLATE),
				"Click on template to download ");
		// Download template
		testlog.log(clickOnElementByJavascript("xpath", PPM.projectDescriptionTracker.DOWNLOADTEMPLATE),
				"Click on template to download ");
		syncBrowser();
		testlog.log(waitForDownload(location), "Wait for file to download");
		syncBrowser();
		testlog.log(Status.INFO, "Downloading Template",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Downloading Template")).build());
		testlog.log(Status.INFO, "Case 11:Save to PPM");
		// update Excel Template
		testlog.log(updateExcelValuesInTemplate(location), "excel values updated");
		testlog.log(uploadFile(location), "upload file");
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		syncBrowser();
		waitForElement("id", PPM.projectDescriptionTracker.VERSIONAPPLY);
		testlog.log(clickOnElementByJavascript("id", PPM.projectDescriptionTracker.VERSIONAPPLY), "Click on Apply ");
		testlog.log(waitForElement("xpath", PPM.projectDescriptionTracker.VERFY_UPLOADING),
				"file uploaded successfully");
		syncBrowser();
		testlog.log(Status.INFO, "Case 12: Advance the gate");
		// 12th scenario
		// click on idea generation DB
		testlog.log(clickOnElement("xpath", PPM.projectDescriptionTracker.CLK_IDEAGENERATION),
				"Click on idea generation DB");
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		syncBrowser();
		// click gates tab
		testlog.log(clickOnElement("xpath", PPM.advanceTheGate.GATES_TAB), "Click on gates tab");
		testlog.log(waitForInvisiblityOfElement("xpath", PPM.ppmHomePage.SPINNER), "Wait for loading");
		syncBrowser();
		// click on gate decision
		testlog.log(hoverOverElement("xpath", PPM.advanceTheGate.CLK_GATEDESICION), "Hover over element ");
		testlog.log(clickOnElement("xpath", PPM.advanceTheGate.CLK_GATEDESICION), "Click on gate decision");
		// click on approved
		testlog.log(clickOnElement("xpath", PPM.advanceTheGate.CLK_APPROVED), "Click on approved");
		testlog.log(Status.INFO, "Moving Gate",
				MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("PPM", "Moving Gate")).build());
		// click on apply
		testlog.log(clickOnElement("xpath", PPM.advanceTheGate.CLK_APPLY), "Click on Apply");
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