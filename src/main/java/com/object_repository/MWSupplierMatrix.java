package com.object_repository;

public class MWSupplierMatrix {
	
	public static class create {
		
		//Supplier Information
		
		public static final String addSupplierBttn = "//div[.='CREATE NEW SUPPLIER']";
		public static final String GSLN= "//div[@data-control-name='CGSLDDL']";
		public static final String vendorCode = "//div[@data-control-name='VendorCode_2']";
		public static final String stateCountry = "//div[@data-control-name='CCountryDDL']";
		public static final String city = "//div[@data-control-name='CCityDDL']";
		public static final String supplierName = "//div[@data-control-name='CSupNameDDL']";
		public static final String materialRiskRating = "//div[@data-control-name='CMaterialRiskRatingDDL']";
		public static final String veritasCategory = "//div[@data-control-name='CCatVeritas']";
		public static final String commercialcategory = "//div[@data-control-name='CCatgCom']";
		public static final String crossSegment = "//div[@data-control-name='CXSegDDL_1']";
		public static final String crossRegion = "//div[@data-control-name='CXregDDL_1']";
		public static final String DMT = "//div[@data-control-name='CDistDDL_1']";
		public static final String supplierStatus= "//div[@data-control-name='CSupplierStatusDDl_2']";
		public static final String SQAregion = "//div[@data-control-name='CSQAReg']";
		public static final String SQAresponsible = "//div[@data-control-name='SQAemailDDL_1']";
		
		//Audit Information
		
		public static final String priorAuditDate = "//div[@data-control-name='PriorAuditDateC']";
//		public static final String month = "//div/select[@class='pika-select pika-select-month']";
		
		public static final String nextAuditDate = "//div[@data-control-name='CCalNxtAuditDate']";
		public static final String scheduledAuditDate = "//div[@data-control-name='CScheduledAditDate']";
		public static final String auditCompletionDate = "//div[@data-control-name='CAuditCompletionDate']";
		public static final String activeCriticalIncident= "//div[@data-control-name='DDLACI_1']";
		public static final String auditPerformance = "//div[@data-control-name='DDLAuditPerformance_1']";
		public static final String auditType = "//div[@data-control-name='CAuditType']";
		public static final String nexusAuditNumber = "//div[@data-control-name='AuditNumberC']";
		public static final String auditStatus = "//div[@data-control-name='CAuditStatus']";
		public static final String comments = "//div[@data-control-name='StatusCommentTxt_1']//textarea";
		
		
		public static final String saveBttn = "//div[@data-control-name='Button1_2']";
		
		
		public static class dashboard{
			
		
		public static final String supplierName = "//div[@data-control-name='SupplierNameDDL']";
		public static final String status = "//div[@data-control-name='CurrentStatusDDL']";
		
		}
		
		public static class edit{
		public static final String editBttn = "(//div[@data-control-name='SupplierMasterGal']//div[@role='listitem'])[1]//div[@class='appmagic-image-pseudo-button']";
		public static final String commCate = "//div[@data-control-name='CommercialCatgDDL']";
		public static final String changeComment = "//textarea[@appmagic-control='COMCategoryChangedCommenttextarea']";
		public static final String saveClose= "//div[@data-control-name='Cancel_7']//div[@class='appmagic-image-pseudo-button']";
		
	}
		
		public static class vesion{
			
			public static final String VersionBttn = "//div[.='View Version History']";
			public static final String versionCount = "//div[@data-control-name='VersionGal']//div[@role='listitem']";
			
			public static final String goBtn="(//div[@data-control-name='Image2_1'])[last()]";
			public static final String BACKBttn = "//div[.='BACK']";
			public static final String Save ="//div[.='Save']";
		}

}
}
