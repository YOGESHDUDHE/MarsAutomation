package com.object_repository;

public class VolumePlanningATG {
	
	public static final String Activity ="selenium activity";
	public static final String SKU ="selenium SKU";
	public static final String LaunchPeriod ="P5";
	public static final String Year ="2022";
	
	
	
	public static class createNewProject {
		
		public static final String dataImport = "//div[contains(text(),'Data Import')]";
		public static final String activityName = "//input[@appmagic-control='txtActivitytextbox']";
		public static final String skuName = "//input[@appmagic-control='txtSKUtextbox']";
		public static final String launchPeriod = "//div[@id='react-combobox-view-2']";
		public static final String year = "//input[@appmagic-control='txtYeartextbox']";
		public static final String proceed = "(//div[contains(text(),'Proceed')])[2]";
		
		
		
		public static final String ActNameText="//div[@data-control-name='txtActivityHeader']/div/div/div/div/div";
		public static final String SKUNameText="//div[@data-control-name='txtSKUHeader']/div/div/div/div/div";
		public static final String LPText="//div[@data-control-name='TxtlaunchPeriod']/div/div/div/div/div";
		public static final String YearText="//div[@data-control-name='txtYearHeader']/div/div/div/div/div";
		
		public static final String fileType = "//div[@id='react-combobox-view-3']";
		public static final String attachFile = "//input[@class='addFileInput_1xqaqtd-o_O-addFileInput_3hmsj']";
		public static final String confirm = "//div[contains(text(),'Confirm')]";
		public static final String perROSbttn = "//div[contains(text(),'Proceed to enter %ROS')]";
		public static final String ROSbenchmarkvalue1 = "//input[@appmagic-control='txtPercentage1textbox']";
		public static final String ROSbenchmarkcomment1="//textarea[@appmagic-control='txtCommROS-B1textarea']";
		public static final String ROSbenchmarkvalue2 = "//input[@appmagic-control='txtPercentage2textbox']";
		public static final String ROSbenchmarkcomment2="//textarea[@appmagic-control='txtCommROS-B2textarea']";
		public static final String ROSbenchmarkvalue3 = "//input[@appmagic-control='txtPercentage3textbox']";
		public static final String ROSbenchmarkcomment3="//textarea[@appmagic-control='txtCommROS-B3textarea']";
		public static final String uploadnSubmitBttn ="//div[contains(text(),'Upload and Submit Data')]";
		
	}
	
	public static class updateBenchmark{
		
		public static final String updateBenchmark = "//div[contains(text(),'Update Benchmark')]";
		public static final String activity = "//div[@id='react-combobox-view-0']";
		public static final String sku = "//div[@id='react-combobox-view-1']";
		public static final String proceed = "(//div[contains(text(),'Proceed')])[1]";
		public static final String b1="(//div[@aria-labelledby='pa-gallery-label-1']//div/label//input[@value='Benchmark 3'])[1]";
		
		
		public static final String updateBtn ="//button[@title='Click to update Benchmark and Distribution Used']";
		
	}
	
	public static class updatePipefill{
		
		public static final String  updatePipefill="//div[@data-control-name='btnGSPipefill']";
		public static final String activity="//div[@data-control-name='cmbActivity_1']";
		public static final String sku="//div[@data-control-name='cmbSKU_1']";
		public static final String proceed = "(//div[contains(text(),'Proceed')])[3]";
		public static final String view = "(//div[@data-control-name='Icon1'])[1]";
		
		public static final String level1F="//div[@data-control-name='cmbLevel1_2']";
		public static final String Level2F="//div[@data-control-name='cmbLevel2_2']";
		
		public static final String pipefillDrodwn ="//div[@data-control-name='drpPipeFill']";
		public static final String option="//*[@role='listbox']/div";
		public static final String close= "//div[@data-control-name='iconclose_4']";
		
		public static final String updateBtn ="//div[@data-control-name='btnUpdatebenchmark_2']";
	}
	
	
	public static class GSV{
		
		public static final String updateGSV="//div[@data-control-name='btnGSV']";
		public static final String activity ="//div[@data-control-name='cmbActivity_2']";
		public static final String proceed="//div[contains(text(),'Proceed')]";
		
		public static final String GPU1="(//input[@appmagic-control='txtGSVValtextbox'])[1]";
		public static final String NPU1="(//input[@appmagic-control='txtNSVValtextbox'])[1]";
		public static final String CC1="((//input[@appmagic-control='txtCaseCountValtextbox'])[1]";
		
		public static final String GPU2="(//div[@data-control-name='txtGSVVal'])[2]";
		public static final String NPU2="(//div[@data-control-name='txtNSVVal'])[2]";
		public static final String CC2="(//div[@data-control-name='txtCaseCountVal'])[2]";
		
		public static final String updateBtn="//div[.='Update Metrics']";

		
	}
	public static class salesTeamApp{
		
		public static final String activityName = "//div[@id='react-combobox-view-6']" ;
		public static final String level2 ="//div[@id='react-combobox-view-8']";
		public static final String account="//div[@id='react-combobox-view-9']";
		public static final String proceed="//div[contains(text(),'Proceed')]";
		
		
		public static final String editBttn ="//div[@data-control-name='btnHidden']";
		public static final String volumeText ="//div[@data-control-name='lblVolume']";
		public static final String Newvolume = "//div[@data-control-name='txtVolume']";
				public static final String distributionText ="//div[@data-control-name='lblDist']";
				public static final String newDistribution ="//div[@data-control-name='txt%Distribution']";
				public static final String launchPeriod = "//div[@data-control-name='cmbLaunchPeriod']";
				public static final String listingType = "//div[@data-control-name='cmbListingType']";
				public static final String comments = "//div[@data-control-name='txtCommSales']";
				public static final String growthStorm = "//div[@data-control-name='cmbGS']";
				
				
				public static final String newVolumeText = "//div[@data-control-name='lblNewVolume']";
				public static final String perofNewVolumeText = "//div[@data-control-name='lblR&ODist_2']";
				public static final String rnoUnitsText = "//div[@data-control-name='lblR&OUnits']";
				public static final String rnoDistributionText = "//div[@data-control-name='lblR&ODist']";
				public static final String updateDataBttn = "//div[@data-control-name='btnUpdateVolume']";
		
		
	}

}
