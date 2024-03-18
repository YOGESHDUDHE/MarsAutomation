package com.object_repository;

public class PPM {
	public static class ppmHomePage {
		// HomeScreen Options
		//xpath
		public static final String LNK_PROJECT = "//*[text()='Project']";
		public static final String LNK_ADD_NEW_PROJECT = "//*[text()='Add New']";
		public static final String LNK_CONFECTIONERY = "(//a[@onclick='window.createProject({ classId: 78 });'])[2]";
		public static final String LNK_FIND_PROJECT = "//a[text()='Find']";
		public static final String SPINNER = "(//div[@class='spinner'])";
		public static final String LOGOUT="//a[text()='Logout']";
		//id 
		public static final String USER_MENU = "userMenu";
	}

	public static class addNewScreenpage {
		// AddNew Screen
		//id
		public static final String BTN_CREATE_PROJECT = "BtnCreateProject";
		public static final String LNK_PROJECTNAME = "ProjectName";
		public static final String LNK_ACCESSGROUP = "AccessNodeID";
		// xpath
		public static final String DRPDWN_CLASSTYPE = "//select[contains(@data-bind,'Name') and @id='Classes']";
		public static final String DRPDWN_MODELTYPE = "//select[contains(@data-bind,'Name') and @id='ProcessModels']";
		public static final String LNK_NPRAPPROVAL = "(//div[text()='Click to Edit...'])[1]";
		public static final String SELECT_NPR = "(//a[@class='ui-state-default'])[6]";
		public static final String LNK_CONF_CATEGORYBUSINESSUNIT = "(//div[@class='projectfieldvalue emptyDisplayTextItalic defaultCursor pre-wrap editable'])[4]";
		public static final String DRPDWN_DATASELECT = "//select[@class='cte_editor']";
		public static final String LNK_CONF_CATEGORYVOLUMEUNIT = "(//div[@class='projectfieldvalue emptyDisplayTextItalic defaultCursor pre-wrap editable'])[5]";
		public static final String LNK_LAUNCHPERIOD = "(//div[@class='projectfieldvalue emptyDisplayTextItalic defaultCursor pre-wrap editable'])[2]";
		public static final String LNK_LAUNCHYEAR = "(//div[@class='projectfieldvalue emptyDisplayTextItalic defaultCursor pre-wrap editable'])[3]";
		
	}

	public static class saveToPPM {
		// xpath
		public static final String CLK_BUSINESSSIZEOFPRIZE = "//a[text()='BUSINESS MODEL: Size of prize']";
		public static final String UPLOADFILE = "//input[@class='uploadVersionInput']";
	}

	public static class advanceTheGate {
		// xapth
		public static final String GATES_TAB = "//*[text()='Gates']";
		public static final String CLK_GATEDESICION = "//label[@class='projectFieldTitle'][text()='Gate Decision']//following-sibling::div";
		public static final String CLK_APPROVED = "//option[text()='Approved']";
		public static final String CLK_APPLY = "//button[@id='LayoutApply'][text()='Apply']";
	}

	public static class newProjectpage {
		// New Project Request
		//id
		public static final String LNK_PROJECTID = "projectCodeLabel";
	}

	public static class projectDescriptionTracker {
		// 8th scenario objects
		// xpath
		public static final String CSFVALUE = "//div[text()='CSF 1']/ancestor::td/following-sibling::td[1]/div/input";
		public static final String CSFSTATUSVALUE = "//div[text()='CSF 1']/ancestor::td/following-sibling::td[2]/div/select";
		public static final String TARGETVALUE = "//div[text()='Target 1']/ancestor::td/following-sibling::td[1]/div/input";
		public static final String TARGETSTATUSVALUE = "//div[text()='Target 1']/ancestor::td/following-sibling::td[2]/div/select";
		// 9th scenario objects
		// xpath
		public static final String TASKEXPANDER = "//a[text()='Tasks']/preceding-sibling::span";
		public static final String PROJECTMEETINGMIN = "//a[text()='Project meeting minutes with actions and milestones']";
		public static final String CONSUMERTESTRESULT = "//span[text()='Consumer test results available']";
		public static final String PLANDATE = "//span[text()='Consumer test results available']/ancestor::td/following::td[1]/div/input";
		public static final String ACTUALDATE = "//span[text()='Consumer test results available']/ancestor::td/following::td[2]/div/input";
		public static final String CLICKSTATUS = "//select[@class='quickgridControl']";
		public static final String STATUSVALUE = "(//option[text()='In Progress'])[3]";
		public static final String APPLY = "//button[text()='Apply']";
		// 10 th scenario objects
		// xpath
		public static final String PROJECTCLASSIFICATION_PARTCIPATINGMARKET_CHINA = "(//select[@class='middle50Wide ignorechangeforqbl'])[5]/option[text()='China']";
		public static final String PROJECTCLASSIFICATION_PARTCIPATINGMARKET_BRAZIL = "(//select[@class='middle50Wide ignorechangeforqbl'])[5]/option[text()='Brazil']";
		public static final String BTN_RIGHT = "(//button[@class='buttonStandard autoBlock'])[5]";
		public static final String BTN_APPLY = "//button[@class='apply buttonPrimary cursorPointer'][text()='Apply']";
		public static final String CLK_IDEAGENERATION = "//a[@class='dialogTitleLink diaglogTitleLinkHref'][text()='Idea Generation (DB)']";
		public static final String DOWNLOADTEMPLATE = "//*[@id='templateDownload']/div[2]/a";
		public static final String TEMPLATENAME = "//*[@id='templateDownload']/div[2]/div";
		public static final String UPLOADFILE = "//div[contains(@id,'versionUploadBlock')]/div/label[contains(@class,'small actionableIcon')]";
		public static final String VERFY_UPLOADING = "//div[@class=' sopheon-userdisplay inlineMiddle fillUp']";
		//id
		public static final String VERSIONAPPLY = "VersionsApply";
	}

	public static class findScreenPage {
		// Find Screen		
		//id
		public static final String TXT_PROJECT_ID = "xprojectcode";
		//object reference to switch to frame
		public static final String CHANGEFRAME = "LegacyIframe";
		// id
		public static final String BTN_SUBMIT = "btnApply";
		//xpath
		public static final String TABLE_SEARCHDATA = "//div[@id='oResultsinnerDiv']//table/tbody/tr[2]/td[2]";		
		public static final String LNK_PROJECT_ID = "//*[@id='oResultsinnerDiv']/table/tbody/tr[2]/td[1]//a";
		public static final String TEAMS_TAB = "//*[text()='Team']";
		public static final String STAGES_TAB = "//div[@class='projectNavPoleLabel'][text()='Stages']";
		public static final String PROJECTCHARTER = "//div[@class='linkWithImageText'][text()='QuEST: Project charter']";
		//cssSelector
		public static final String CONTINUE = "button[type='submit']";
		public static final String FINISH = "button[name='btnMigrate']";
	}

	public static class teamsPage {
		// Displays the Project
		//id
		public static final String BTN_ADDUSER = "usersAddButton";
		public static final String CLK_USERNAME = "username";
		public static final String BTN_APPLY = "Apply";
		//xpath
		public static final String SELECT_USER = "//*[@id='selectorresult']/option";
		public static final String BTN_ADDSELECTEDUSER = "//input[@id='addSelected']";
		public static final String BTN_DONE = "//*[@id='selectButton']/span";
		public static final String TABLE_VERIFYDATA = "//*[@id='oResultsinnerDiv']//table/tbody/tr[2]/td[2]";		
	}
	public static class stageScreen {
		// 5 th Scenario Objects
		// xpath
		public static final String TEXTCRITICALSUCCESSFACTOR = "//div[text()='CSF 1']/ancestor::td/following-sibling::td[1]/div/input";
		public static final String DRP_DWN_CSFSTATUS = "//div[text()='CSF 1']/ancestor::td/following-sibling::td[2]/div/select";
		public static final String TARGETTEXT = "//div[text()='Target 1']/ancestor::td/following-sibling::td[1]/div/input";
		public static final String DRP_DWN_TARGETSTATUS = "//div[text()='Target 1']/ancestor::td/following-sibling::td[2]/div/select";
		public static final String BTN_APPLY = "//*[@class='apply buttonPrimary cursorPointer']";
		public static final String CLK_ONOWNER = "//div[text()='QuEST: Project charter']/ancestor::td//following-sibling::td[@data-columnid='Owner']/div/div/div[1]";		
		public static final String BTN_CLOSE = "(//*[@class='dialogcornercontainer'])[2]//child::button[@title='Close']";
		public static final String Done = "//span[text()='Done']";
		// 6 th scenario objetcs
		// id
		public static final String MANAGEPROJECT = "projectActionMenuContainer";
		public static final String BTN_APPLYFORSTAGES = "Apply";
		//xpath
		public static final String MIGRATEORCOPY = "//li[contains(@class,'migrate')]";
		// 8 th scenario objects
		// xpath
		public static final String IDEAGENERATIONDB = "//div[@class='projectfieldvalue projectHeaderLabelValue cursorPointer text-ellipsed'][text()='Idea Generation (DB)']";
		public static final String PROJECTDESCRIPTIONTRACKER = "//div[text()='READINESS: Project description & tracker']";
		public static final String PROJECTDESCRIPTION_OWNER = "//div[text()='READINESS: Project description & tracker']/ancestor::td//following-sibling::td[@data-columnid='Owner']/div/div/div[1]";
		public static final String DRP_DWN = "//select[ @data-user-dropdown='1']";
		// 10 th Scenario
		// xpath
		public static final String BUSSINESSSIZEPRIZETOPRIZEOWNER = "//div[text()='BUSINESS MODEL: Size of prize']/ancestor::td//following-sibling::td[@data-columnid='Owner']/div/div/div[1]";
		public static final String EXPANDER = "//div[text()='BUSINESS MODEL: Size of prize']/ancestor::td/preceding-sibling::td/div";
		public static final String SELECT_OWNER_FOR_CHINA = "//div[text()='China']/ancestor::td//following-sibling::td[@data-columnid='Owner']/div/div/div[1]";
		public static final String CLK_CHINA = "(//div[text()='China'])[1]";
	}

	public static class migrateTheProject {
		// id
		public static final String MIGRATEMAP = "MigrationMap";
		//xpath
		public static final String MIGRATEMAPVALUE = "//*[@id='MigrationMap']/option[3]";
	}
	public static class modificationAfterDevServerUpdate{
		//id
		public static final String CLK_CONTINUE = "continue";
		public static final String CLK_CONTINUE1 = "Continue";
		public static final String SELECT_MIGRATEMAP = "migrationMapSelectionDropdown";
		public static final String CLK_APPLY = "apply";
		//xpath
		public static final String CLK_NPR_TO_QEST = "//select[@id='migrationMapSelectionDropdown']/option[3]";		
		public static final String LAUNCH_PERIOD ="//label[text()='Launch Period']/ancestor::div[@data-seleniumid='projectDataItem']//following-sibling::div";
		public static final String NPR_APPROVAL = "//label[text()='NPR Approval']/ancestor::div[@data-seleniumid='projectDataItem']//following-sibling::div";
		public static final String LAUNCH_YEAR = "//label[text()='Launch Year']/ancestor::div[@data-seleniumid='projectDataItem']//following-sibling::div";
		public static final String BUSSINESS_UNIT = "//label[text()='CONF Category Business Unit']/ancestor::div[@data-seleniumid='projectDataItem']//following-sibling::div";
		public static final String VOLUME_UNIT = "//label[text()='CONF Volume Units']/ancestor::div[@data-seleniumid='projectDataItem']//following-sibling::div";
		public static final String CLK_TEAM = "//div[text()='Team']/ancestor::div[@class='projectNavPoleItem cursorPointer']";
		public static final String SELECT_USER = "//div[@class='multiSelectOptionPrimary']";
        public static final String CLK_DONE = "//button[text()='Done']";
        public static final String CLK_STAGES = "//div[text()='Stages']/ancestor::div[@class='projectNavPoleItem cursorPointer']";
        public static final String CLK_GATES = "//div[text()='Gates']/ancestor::div[@class='projectNavPoleItem cursorPointer']";
        public static final String VERFIY_UPLOAD = "//a[contains(text(),'Version')]/ancestor::td";
        public static final String MIGRATEORCOPY = "(//li[contains(@class,'ui-menu-item') and text()='Migrate or Copy'])[2]";
	}
}