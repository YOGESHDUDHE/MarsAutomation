package com.object_repository;

public class Nexus {

	public static class HomePage {
		// xpath - relative
		public static final String LNK_NON_SSO_LOGIN = "//a[contains(text(),'Login as a non-SSO user')]";
	}

	public static class Login {
		//name
		public static final String TXT_USERNAME ="ETQ$LOGIN_USERNAME";
		public static final String TXT_PASSWORD = "ETQ$LOGIN_PASSWORD";
		
		//id
		public static final String BTN_LOGIN = "loginActionId";
	}
	
	public static class Menu {
		// id
		public static final String LNK_USERMENU = "USER_MENU";
		public static final String LNK_5_Review_Records = "5._Review_Records_4";
		public static final String DIV_5_Review_Records_TREE_DISPLAY = "div_5._Review_Records_4";
		public static final String LNK_1_PENDING_BY_OWNER = "DOCWORK_REVIEW_RECORD_OPEN";
		public static final String LNK_ALL_APPROVED = "DOCWORK_CLOSEDDOCWORK_BYNUMBER";
		public static final String LNK_3_APPROVED_DOCUMENTS = "3._Approved_Documents_2";
		
		public static final String CHKBOX_SELECT_ALL = "SelectAll";
		public static final String LNK_ACTION_ON_SELECTED_DOCUMENTS = "MenuAction_On_Selected_Document(s)";
		
		public static final String LNK_REASSIGN_DOCUMENTS = "Reassign_Documents";
		public static final String LNK_RE_ROUTE = "Re-route";
		
		
		
		// xpath - relative
		public static final String SPAN_DOCUMENT_CONTROL = "//span[text()='Document Control']";
		public static final String TXT_LOCATION_SEARCH = "//input[contains(@name,'LOCATION_')]";
	}
	
	public static class DocumentContorl {
		//id
		public static final String LNK_ALL_APPROVED = "DOCWORK_CLOSEDDOCWORK_BYNUMBER";
		public static final String LNK_OBSOLETE_REQUEST = "obsolete_request";
		public static final String TXT_REASON_FOR_OBSOLETION = "OBSOLETION_REASON";
		public static final String LNK_CANCEL = "cancel_document";
		
		//xpath
		public static final String ROW_ALL = "//tr[contains(@class,'ColumnValue')]";
		public static final String FIRST_ROW = "//tr[contains(@class,'ColumnValue')][1]";
		public static final String FIRST_ROW_NUMBER = "//tr[contains(@class,'ColumnValue')][1]/td[4]";
		public static final String FIRST_DOCUMENT_NUMBER = "//tr[contains(@class,'ColumnValue')][1]/td[5]";
		
		public static final String APPROVED_TAB = "//a[contains(text(),'Approval')]";
	}
	
	public static class ReviewRecord {
		//name
		public static final String TXT_REVIEW_COMMENTS = "REVIEW_RECORD_COMMENTS";
		
		//xpath
		public static final String REVIEW_COMPLETED_TAB = "//a[contains(text(),'Review Compl')]";
	}
	
	public static class GlobalReassignDialog {
		//name
		public static final String CHKBOX_GLOBAL_REASSIGN_DIALOG_NO_EMAIL = "DIS$GLOBAL_REASSIGN_DIALOG_NO_EMAIL";
		public static final String TXT_COMMENT = "GLOBAL_REASSIGN_DIALOG_COMMENT";
		
		//id
		public static final String TXT_ASSIGN_TO = "GLOBAL_REASSIGN_DIALOG_ASSIGN_TO_AUTOCOMPLETE";
		public static final String LNK_OK = "global_reassign_ok";
		public static final String LNK_CANCEL = "global_reassign_cancel";
		
		//xpath
		public static final String SEARCH_RESULT = "//li[@class='AutoCompleteItemHover']/span";
		
	}
	
	public static class RoutingDialog {
		public static final String LNK_OK = "phase_dialog_ok";
		public static final String LNK_CANCEL = "phase_dialog_cancel";
	}
}
