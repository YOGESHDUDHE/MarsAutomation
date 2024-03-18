package com.object_repository;

public class JDA {

	public static class Homepage {

		// Login Page
		public static final String UNAME = "loginUserName";
		public static final String UNAMEPD = "loginPassword";
		public static final String LOGINBTN = "loginButton";

		// HomePage
		public static final String HEADER = "jdaSiteButton-1015-btnInnerEl";
		public static final String DROPDOWNSITE = "ext-gen1100";
		public static final String CHOOSESITE = "//li[contains(text(),'TNNPLT - Chattanooga Wrigley Plant')]";
		public static final String SELECTBTN = "button-1038-btnIconEl";

		// Selecting Shipping and Production
		public static final String DROPDOWNBTN = "ext-comp-1017-btnIconEl";
		public static final String SHIPPING = "menuitem-1030-textEl";
		public static final String PRODUCTION = "//span[contains(text(),'PRODUCTION')]//parent::a";
	}

	public static class shipping {

		// Selecting Mars Tender Operations
		public static final String OPTIONBTN = "toolbar-1034-menu-trigger";
		public static final String MARSTENDEROPERATIONS = "//span[contains(text(),'MARS Tender Operations')]//parent::a";
		public static final String SELECTCHECKBOXUPPER = "//div[contains(text(),'ATLANTA BONDED WAREHOUSE CORP')]//ancestor::tr[@data-recordindex='2']//td[1]";
		public static final String OKPOPUP = "//div[@id=\"messagebox-1012-toolbar-targetEl\"]/a[@id='button-1016']";
		public static final String ACTIONBTNUPPER = "button-1050";
		public static final String REQUESTBTN = "menuitem-1052-textEl";
		// Class Toggle Request
		public static final String TOGGLEREQUEST = "toggle_switch";
		// name
		public static final String REQUESTVALUE = "carcod1107";
		public static final String REQUESTQUANTITY = "uc_qty1108";
		// xpath
		public static final String REQUESTEXECUTE = "//span[contains(text(),'Execute Action')]";

		// Bottom Table
		public static final String DATEFILTER = "//span[contains(text(),'Request Date')]/following-sibling::div";
		public static final String SORTDESCENDING = "//span[contains(text(),'Sort Descending')]";
		public static final String PAGE2 = "(//input[@name='inputItem'])[2]";
		public static final String SELECTCHECKBOXLOWER = "//tr[@data-recordindex='0']//td//div[contains(text(),'KLLM INC')]/../preceding-sibling::td[4]//div[@class='x-grid-cell-inner ']";
		public static final String ACTIONBTNLOWER = "button-1147";
		public static final String ACCEPTBTN = "//span[contains(text(),'Accept')]";
		public static final String CARRIERNAME = "(//label[contains(text(),'Carrier')]//ancestor::td//input)[3]";
		public static final String EXECUTEACTION = "(//span[contains(text(),'Execute Action')])[2]";
		public static final String WORKORDERSTAB = "(//span[contains(text(),'Work Orders')]//ancestor::a)[1]";

	}

	public static class production {

		public static final String WORKORDER = "(//div[contains(text(),'Unassigned')]/../..//td[2]/div/span)[1]";

		public static final String FILTEROPENWOS = "(//li[contains(text(),'Open WOs')])[1]";

	}

}
