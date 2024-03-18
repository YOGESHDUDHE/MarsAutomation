package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.framework.*;
import com.object_repository.*;

public class NexusDemerge extends BaseExecutor{
	
	public NexusDemerge(WebDriver protectedDriver) {
		driver = protectedDriver;
		action = new Actions(driver);
	}
	
	public NexusDemerge() {
	}

	/**
	 * @Method - loginToNexus
	 * @Description - To login into nexus application
	 * @return - status
	 * @author - singhje1
	 * @DateCreated - 01-04-2021
	 * @DateModified - ?
	 */
	public Status loginToNexus() {
		try {
			clickOnElement("xpath", Nexus.HomePage.LNK_NON_SSO_LOGIN);	
			enterValues("name", Nexus.Login.TXT_USERNAME, "singhje1");
			enterValues("name", Nexus.Login.TXT_PASSWORD, "Abcd@1234Abcd");		
			clickOnElement("id", Nexus.Login.BTN_LOGIN);
			Status result = waitForElement("id", Nexus.Menu.LNK_USERMENU);
			if(result==Status.PASS){
				return Status.PASS;
			}
			else
				return Status.FAIL;
		} catch(Exception e) {
			return Status.FAIL;
		}
	}
	
	/**
	 * @Method - openReviewRecordsPendingByOwner
	 * @Description - to open Review Records Pending By Owner
	 * @return - status
	 * @author - singhje1
	 * @DateCreated - 01-04-2021
	 * @DateModified - ?
	 */
	public Status openReviewRecordsPendingByOwner() {
		try {
			syncBrowser();
			Status result = clickOnElement("id", Nexus.Menu.LNK_1_PENDING_BY_OWNER);
			if(result==Status.PASS){
				return Status.PASS;
			}
			else {
			clickOnElement("id", Nexus.Menu.LNK_5_Review_Records);	
			clickOnElement("id", Nexus.Menu.LNK_1_PENDING_BY_OWNER);
			return Status.PASS;
			}
		} catch(Exception e) {
			return Status.FAIL;
		}
		
	}
	
	/**
	 * @Method - searchForLocation
	 * @Description - searching for location
	 * @return - status
	 * @author - singhje1
	 * @DateCreated - 01-04-2021
	 * @DateModified - ?
	 */
	public Status searchForLocation(String strLocation) {
		try {
			Status result = clickOnElement("xpath", Nexus.Menu.TXT_LOCATION_SEARCH);
			if(result==Status.PASS){
				result = enterValuesAndPressEnter("xpath", Nexus.Menu.TXT_LOCATION_SEARCH, strLocation);
			}
			if(result==Status.PASS){
				return Status.PASS;
			}
			else
				return Status.FAIL;
		} catch(Exception e) {
			return Status.FAIL;
		}	
	}
	
	/**
	 * @Method - assignToUser
	 * @Description - assign all requests to userName
	 * @return - status
	 * @author - singhje1
	 * @DateCreated - 01-04-2021
	 * @DateModified - ?
	 */
	public Status assignToUser(String userName) {
		try {
			syncBrowser();
			Status result = clickOnElement("name", Nexus.GlobalReassignDialog.CHKBOX_GLOBAL_REASSIGN_DIALOG_NO_EMAIL);
			if(result==Status.PASS){
				clickOnElement("id", Nexus.GlobalReassignDialog.TXT_ASSIGN_TO);
				enterValuesAndPressEnter("id", Nexus.GlobalReassignDialog.TXT_ASSIGN_TO, userName+",");
				clickOnElement("xpath", Nexus.GlobalReassignDialog.SEARCH_RESULT);
				clickOnElement("name", Nexus.GlobalReassignDialog.TXT_COMMENT);
				enterValues("name", Nexus.GlobalReassignDialog.TXT_COMMENT,"Comment from Automation");
				clickOnElement("id", Nexus.GlobalReassignDialog.LNK_OK);
				handleBrowserAlert("Are you sure you want to reassign selected documents?", "accept");
			}
			if(result==Status.PASS){
				return Status.PASS;
			}
			else
				return Status.FAIL;
		} catch(Exception e) {
			return Status.FAIL;
		}
	}
	
	/**
	 * @Method - openAllApprovedDocuments
	 * @Description - approve all open documents
	 * @return - status
	 * @author - singhje1
	 * @DateCreated - 01-04-2021
	 * @DateModified - ?
	 */
	public Status openAllApprovedDocuments() {
		try {
			syncBrowser();
			Status result = clickOnElement("id", Nexus.Menu.LNK_ALL_APPROVED);
			if(result==Status.PASS){
				return Status.PASS;
			}
			else {
			clickOnElement("id", Nexus.Menu.LNK_3_APPROVED_DOCUMENTS);	
			clickOnElement("id", Nexus.Menu.LNK_ALL_APPROVED);
			return Status.PASS;
			}
		} catch(Exception e) {
			return Status.FAIL;
		}
		
	}
	
	/**
	 * @Method - reviewCompleteFirstOrder
	 * @Description - complete review for first order
	 * @return - status
	 * @author - singhje1
	 * @DateCreated - 01-04-2021
	 * @DateModified - ?
	 */
	public Status reviewCompleteFirstOrder() {
		try{
			syncBrowser();
			Status result = clickOnElement("xpath", Nexus.DocumentContorl.FIRST_ROW);
			if(result==Status.FAIL){
				return Status.FAIL;
			}

			enterValues("name", Nexus.ReviewRecord.TXT_REVIEW_COMMENTS, "Comment from Automation");

			/*
			 * clickOnElement("xpath", Nexus.ReviewRecord.REVIEW_COMPLETED_TAB);
			 * clickOnElement("id", Nexus.RoutingDialog.LNK_OK);
			 */
			
			//Below two lines will cancel the operation
			clickOnElement("id", Nexus.DocumentContorl.LNK_CANCEL);
			handleBrowserAlert("Changes made since the last save will be lost. Continue?", "accept");

			return Status.PASS;
			
		} catch(Exception e) {
			return Status.FAIL;
		}
	}
}