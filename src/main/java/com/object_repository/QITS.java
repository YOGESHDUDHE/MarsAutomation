package com.object_repository;

import org.openqa.selenium.By;

public class QITS {
	
	public static class Homepage {

		// Login Page
		public static final String EMAIL = "//input[@type='email']";
		public static final String SIGNINBTN = "//input[@id='idSIButton9']" ;
		public static final String Username = "//input[@id='okta-signin-username']";
		public static final String Password= "//input[@type='password']";
		public static final String LogInBTN= "//input[@type='submit']";
		public static final String StaySignedInBTN = "//input[@id='idSIButton9']";
		
		// Home Page
		public static class Application {
		
			public static final String AppName ="//div[.='QITS']";
			public static final String UserName ="//div[@data-control-name='lbl_UserName_2']";
			public static final String WelcomeUser="(//div[@class='appmagic-label-text'])[5]";
			public static final String LanguageDRPDWN= "//div[@data-control-name='drp_Language']";
		
		//public static final String ForFarmersBTN ="//div[@data-control-name='btn_ForFormers']/div/div/div/div/button";
		public static final String PetcomGermanyBTN="//div[.='Petcom Germany']";
		public static final String ForFarmersBTN = "//div[contains(text(),'ForFarmers')]";
		
		// ForFarmers Plant
		
		public static final String CreateBTN= "//div[.='CREATE']";
		public static final String MaterialCode= "(//input[@class='appmagic-text mousetrap block-undo-redo'])[2]";
		public static final String SupplierMaterialCode= "(//input[@class='appmagic-text mousetrap block-undo-redo'])[3]";
		public static final String Description= "(//input[@class='appmagic-text mousetrap block-undo-redo'])[4]";
		public static final String Status= "(//div[@class='appmagic-dropdownLabelText no-focus-outline'])[2]";
		public static final String Approved ="//div[.='Approved']";
		public static final String SaveBTN= "//div[.='SAVE']";
		public static final String searchbar="(//input[@class='appmagic-text mousetrap block-undo-redo'])[1]";
		
		//Supplier Tab
		public static final String Supplier ="//div[.='Supplier']";
		public static final String CreateBTNS= "//div[.='CREATE']";
		public static final String SupplierCode= "(//input[@class='appmagic-text mousetrap block-undo-redo'])[2]";
		public static final String DescriptionS= "(//input[@class='appmagic-text mousetrap block-undo-redo'])[3]";
		public static final String StatusS= "(//div[@class='appmagic-dropdownLabelText no-focus-outline'])[2]";
		public static final String ApprovedS ="//div[.='Approved']";
		public static final String SaveBTNS= "//div[.='SAVE']";
		
		//Plant Maaterial Supplier Connect Tab
		
		public static final String PMSC ="//div[.='Plant Supplier Material Connect']";
		public static final String createBtn ="//div[.='CREATE']";
		public static final String clickSelectMaterial="(//div[@class='label_kohvda-o_O-label_6dvev6'])[1]";
		public static final String SelectMaterial ="//input[@class='labelText_awd9vl-o_O-searchInput_10muldr-o_O-labelText_vuv5ov-o_O-label_6dvev6-o_O-inputTop_11b8lle mousetrap block-undo-redo']";
		public static final String testingMterial="//span[.='testing Material']";
		public static final String clickSelectSupplier="(//div[@class='label_kohvda-o_O-label_6dvev6'])[2]";
		public static final String SelectSupplier ="//input[@class='labelText_awd9vl-o_O-searchInput_10muldr-o_O-labelText_vuv5ov-o_O-label_6dvev6-o_O-inputTop_11b8lle mousetrap block-undo-redo']";
		public static final String testingSupplier="//span[.='testing Supplier']";
		public static final String SelectStatus ="(//div[@class='appmagic-dropdownLabelText no-focus-outline'])[2]";
		public static final String approved="//div[.='Approved']";
		public static final String SelectPR ="(//div[@class='appmagic-dropdownLabelText no-focus-outline'])[3]";
		public static final String no="//div[.='No']";
		public static final String Modifier ="(//input[@class='appmagic-text mousetrap block-undo-redo'])[2]";
		public static final String savebtn ="//div[.='SAVE']";
		
		// Material Specification
		
		public static final String materialSpec ="(//div[.='Material Specification'])[1]";
		public static final String description="//input[@appmagic-control='txt_SpecMaterialtextbox']";
		public static final String search="(//div[@class='appmagic-image-pseudo-button'])[2]";
		public static final String plus="(//div[@class='powerapps-icon no-focus-outline'])[4]";
		public static final String primary="(//input[@class='appmagic-text mousetrap block-undo-redo'])[2]";
		public static final String secondary="(//input[@class='appmagic-text mousetrap block-undo-redo'])[3]";
		public static final String docNo="(//input[@class='appmagic-text mousetrap block-undo-redo'])[4]";
		public static final String owner="(//div[@class='appmagic-dropdown no-focus-outline'])[1]";
		public static final String yogesh="//div[contains(@class,'appmagic-dropdownListItem')][.='Dudhe, Yogesh Raju (Contractor)']";
		public static final String checkType="(//div[@class='appmagic-dropdown no-focus-outline'])[2]";
		public static final String saveSpec="(//div[.='SAVE'])[1]";
		public static final String addRequ="(//div[@class='appmagic-button-label no-focus-outline' and .='ADD'])[3]";
		public static final String methodDrp="//div[@id='dropdown_siena_123f442767d8bb09']";
		
		// Entry Manual Good
		
		public static final String other= "//div[.='Other']";
		public static final String entryManualGoodBttn= "(//div[.='Entry Manual Goods'])[1]";
		public static final String GRDCode="//div[@id='react-combobox-view-0']";
		public static final String searchGRDcode="//div[@class='inputContainer_1l626pd']/input";
		
		public static final String material="(//div[@class='label_kohvda-o_O-label_6dvev6'])[2]";
		public static final String arrivlDate="//div[@class='appmagic-datepicker-icon']";
		public static final String supplierCade="//div[@id='react-combobox-view-2']";
		public static final String searchSuppliercode= "//div[@class='inputContainer_1l626pd']/input";
		public static final String supplier="(//div[@class='label_kohvda-o_O-label_6dvev6'])[4]";
		public static final String quantity="//input[@appmagic-control='txt_Quantitytextbox']";
		public static final String poNo="//input[@appmagic-control='txt_PONotextbox']";
		
		
		//Goods Received
		
		public static final String goodReceivedTab="//div[.='Good Received']";
		public static final String materialSearch="//div[@id='react-combobox-view-4']";
		public static final String searchForMat ="//*[@id='powerapps-flyout-react-combobox-view-4']/div/div/div/div/input";
		public static final String supplierSearch="//div[@id='react-combobox-view-5']";
		public static final String searchForSupp="//*[@id='powerapps-flyout-react-combobox-view-5']/div/div/div/div/input";
		public static final String searchForDate="(//div[@class='appmagic-datepicker-icon'])[1]";
		public static final String nextGood="//div[contains(@class,'canvasContentDiv container_1vt1y2p')]//div[contains(@class,'powerapps-icon no-focus-outline')]";
		//Good Receipt
		public static final String lorry ="//div[@class='appmagic-textbox']//input[@appmagic-control='txt_Lorry_1textbox']";
		public static final String delNo ="//div[@class='appmagic-textbox']//input[@appmagic-control='txt_DelnoteGRtextbox']";
		public static final String sealingNo ="//div[@class='appmagic-textbox']//input[@appmagic-control='txt_sealingtextbox']";
		public static final String noGoodReceipt ="//div[@class='appmagic-textbox']//input[@appmagic-control='txt_MatNotextbox']";
		public static final String killos ="//div[@class='appmagic-textbox']//input[@appmagic-control='txt_killosGRtextbox']";
		public static final String plannedDateGR="(//div[@class='appmagic-datepicker-icon'])[2]";
		public static final String poNoGR="//div[@class='appmagic-textbox']//input[@appmagic-control='txt_PONoGR_2textbox']";
		public static final String line="//div[@class='appmagic-textbox']//input[@appmagic-control='txt_Line_2textbox']";
		public static final String slTo="//div[@class='appmagic-textbox']//input[@appmagic-control='txt_SLto_2textbox']";
		public static final String delNoteQty="//div[@class='appmagic-textbox']//input[@appmagic-control='txt_DeliveryNoteQty_2textbox']";
		public static final String weighedQty="//div[@class='appmagic-textbox']//input[@appmagic-control='txt_WeighedQty_2textbox']";
		public static final String suppBatch="//div[@class='appmagic-textbox']//input[@appmagic-control='txt_SuppBatch_2textbox']";
		public static final String warehouseBatch="//div[@class='appmagic-textbox']//input[@appmagic-control='txt_WarehouseBatch_2textbox']";
		public static final String bestBeforeDate="(//div[@class='appmagic-datepicker-icon'])[3]";
		public static final String sapBatch="//div[@class='appmagic-textbox']//input[@appmagic-control='txt_SAPBatch_2textbox']";
		public static final String prodDate="(//div[@class='appmagic-datepicker-icon'])[4]";
		public static final String prodSite="//div[@class='appmagic-textbox']//input[@appmagic-control='txt_ProdSite_2textbox']";
		
		//Disaster Check
		
		public static final String disasterCheckTab="//div[.='Disaster Check']";
		public static final String materialSearchDC="//div[@id='react-combobox-view-4']";
		public static final String searchForMatDC ="//*[@id='powerapps-flyout-react-combobox-view-4']/div/div/div/div/input";
		
		//Visual Inspections
		public static final String sendFileBttn="//div[.='Send File']";
		public static final String addProblemBttn="//div[@class='appmagic-button-label no-focus-outline'][text()='Add Problem']";
		public static final String selectExceptionDrpDwn="//*[@id=\"react-combobox-view-82\"]/div[2]";
		public static final String exceptionInput="//*[@id='powerapps-flyout-react-combobox-view-17']/div/div/div/div/input";
		public static final String qtyException="//div[@class='appmagic-textbox']//input[@appmagic-control='txt_exceptionQuantitytextbox']";
		public static final String descArea="//div[@class='appmagic-textbox']//textarea[@appmagic-control='txt_exceptionDesctextarea']";
		public static final String saveException ="//div[contains(@title,'Save Exception')]";
		
		
}
		
		public static class frequencyCheck {
			
			public static final String checkType ="//div[@class='dropdown_siena_7b4d2c665611341 appmagic-dropdownLabel drop-target drop-abutted drop-abutted-left drop-element-attached-top drop-element-attached-left drop-target-attached-bottom drop-target-attached-left']";
			public static final String retro ="//div[.='R Retrospective Lab Analysis']";
			public static final String addmethodbttn ="//div[@data-control-name='btn_AddMethod']";
			
			public static final String selectParID= "(//*[@role='listbox'])[2]/div";
			public static final String parID ="(//div[@class='appmagic-dropdown no-focus-outline'])[3]";
			public static final String methodName ="(//div[@class='appmagic-dropdown no-focus-outline'])[4]";
			
			public static final String selectIMParID= "(//*[@role='listbox'])[3]/div";
			public static final String IMparID ="//div[@data-control-name='drp_MIC_MethodID']";
			public static final String IMmethod ="//div[@data-control-name='drp_MethodName_MIC']";
			
			public static final String selectUOM= "(//*[@role=\"listbox\"])[4]/div";
			public static final String uom ="//div[@data-control-name='drp_UOM']";

			public static final String selectDecimal ="(//*[@role='listbox'])[3]/div";
			public static final String decimals="//div[@data-control-name='drp_Decimal']";
			
			public static final String min="//input[@appmagic-control='txt_Mintextbox']";
			public static final String target="//input[@appmagic-control='txt_Targettextbox']";
			public static final String max="//input[@appmagic-control='txt_Maxtextbox']";
			
			public static final String selectFrequency="(//*[@role='listbox'])[6]/div";
			public static final String frequency ="//div[@data-control-name='drp_Frequency']";
			public static final String eachDel="//div[.='Each Delivery']";
			
			
			
			
		}
}
}