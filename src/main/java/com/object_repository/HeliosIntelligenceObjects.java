package com.object_repository;

public class HeliosIntelligenceObjects {

	public static class HomePage {
		// xpath - relative
		public static final String LNK_STANDARD = "(//table//img)[3]";
	}

	public static class Login {
		//xpath-relative
		public static final String LBL_USERNAME = "//span[contains(text(),'Username')]";
		public static final String TXT_USERNAME = "(//div[@class='arcField focusableWithGoto']/span)[2]";
		public static final String TXT_PASSWORD = "//input[@type='password']";
		
		//id
		public static final String BTN_LOGIN = "(//button/span)[3]";
	}
	
	public static class StandardPage {
		public static final String LBL_BY_PERIOD = "//div[contains(text(),'By Period')]";
		public static final String SPAN_SEARCH_BOX = "(//span[@class='arcFieldCell'])[3]";
	}
	
	public static class ReportPage {
		public static final String DIV_PERIOD_SELECTOR = "(//div[@class=\"arcHierPopup\"])[12]";
		public static final String DIV_YEAR_SELECTOR = "(//div[@class=\"arcHierPopup\"])[11]";
		public static final String DIV_BACK = "//div[@title='Back']";
	}
}
