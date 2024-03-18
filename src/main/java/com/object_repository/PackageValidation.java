package com.object_repository;

public class PackageValidation {
	
	public static class LogInPage {
		
		public static final String EMAIL = "//input[@type='email']";
		public static final String SIGNINBTN = "//input[@id='idSIButton9']" ;
		public static final String Username = "//input[@id='okta-signin-username']";
		public static final String Password= "//input[@type='password']";
		public static final String LogInBTN= "//input[@type='submit']";
		public static final String StaySignedInBTN = "//input[@id='idSIButton9']";
	}	
		
	public static class createNewProject {
		
		public static final String createNewProject = "//div[contains(text(),'Create New Project')]";
		public static final String ppmID = "//input[contains(@appmagic-control,'txtPPMIDtextbox')]";
		public static final String brand = "//input[contains(@appmagic-control,'txtBrandtextbox')]";
		public static final String projectName = "//input[contains(@appmagic-control,'txtProjectNametextbox')]";
		public static final String rndEngineer = "//input[contains(@appmagic-control,'txtRandDEngineertextbox')]";
		public static final String region = "//div[@id='react-combobox-view-0']";
		public static final String platform = "//div[@id='react-combobox-view-1']";
		public static final String supplierSites = "//input[contains(@appmagic-control,'txtNoOfSupplierSitestextbox')]";
		public static final String marsSites = "//input[contains(@appmagic-control,'txtNoOfMarsSitestextbox')]";
		public static final String technology = "//div[@id='react-combobox-view-2']";
		public static final String change = "//div[@id='react-combobox-view-3']";
		public static final String savenProceed="//div[contains(text(),'Save and Proceed')]";
		
	}
	
	public static class uploadDoc{
		public static final String designBrief="//button[@data-control-part='button' and @title='Design Brief']";
		public static final String downloadTemplate="(//div[.='Download Template'])[1]";
		public static final String attachment="(//input[@class='addFileInput_1xqaqtd-o_O-addFileInput_3hmsj' ])[1]";
		public static final String upload="(//div[.='Upload'])[1]";
		public static final String confirm="(//div[.='Confirm'])[1]";
	}

}
