package com.object_repository;

public class DigitalLogbook {
 
	public static String URL = "https://apps.powerapps.com/play/27992ed4-4ba2-4e80-9ca4-0a391798b9dc?tenantId=2fc13e34-f03f-498b-982a-7cb446e25bc6";
	public static final String location = "//div[@data-control-name='ComboLocation']";
	public static final String store = "//div[@data-control-name='ComboStore']";
	public static final String proceed = "//div[@data-control-name='BtnProceed']";
	public static final String candyOperationBtn ="//div[@data-control-name='Btn-CandyOpn']";
	
	
	
public static class tempnHumidity{
	
	public static final String tempnHumidityBttn = "//div[.='Temperature And Humidity']";
	public static final String shift= "//div[@data-control-name='DropDwShift']";
	public static final String temperature= "//div[@data-control-name='TxtTemp']//input";
	public static final String humidity = "//div[@data-control-name='TxtHumidity']//input";
	public static final String comments = "//div[@data-control-name='TxtComment']//textarea";
	public static final String save = "//div[@data-control-name='BtnSave']//button";
	public static final String savenSubmit = "//div[@data-control-name='Btn-SaveSubmit']//button";
	
}

public static class prouctAccountability{
	
	public static final String PABttn = "//div[.='Product Accountability']";
	public static final String shift = "//div[@data-control-name='DropDateShift']";
	public static final String addDesc = "//div[@data-control-name='Button3_1']//button";
	public static final String descText = "//div[@data-control-name='TextDescription']//input";
	public static final String saveDesc = "//div[@data-control-name='BtnSave1_2']//button";
	public static final String description = "//div[@data-control-name='ComboDescription']";
	public static final String codeDate = "//div[@data-control-name='TxtCodeDate']//input";
	public static final String quantityOfCases = "//div[@data-control-name='TxtQuantity']//input";
	public static final String locationUsed = "//div[@data-control-name='DropLocation']";
	public static final String binNumberField = "//div[@data-control-name='TxtBinnumber']//input";
	public static final String comments = "//div[@data-control-name='TxtComment_3']//textarea";
	public static final String save = "//div[@data-control-name='BtnSave_3']//button";
	public static final String savenSubmit = "//div[@data-control-name='Btn-SaveSubmit_1']//button";
			
}	

public static class binRotation{
	
	public static final String binRotationBttn = "//div[.='Bin Rotation Form']";
	public static final String c = "//div[@data-control-name='TxtCW']//input";
	public static final String personalised = "//div[@data-control-name='TxtPersonalised']//input";
	public static final String reason = "//div[@data-control-name='TxtReason']//textarea";
	public static final String associateRotating = "//div[@data-control-name='ComboAssoRotating']";
	public static final String searchAR = "//input[@placeholder='Associate Rotating']";
	public static final String associateWashing = "//div[@data-control-name='ComboAssowashing']";
	public static final String searchAW = "//input[@placeholder='Associate Washing']";
	public static final String verifiedLnS = "//div[@data-control-name='TogVerifiedlabel']//div[@class='appmagic-toggleSwitch-handle']";
	public static final String hardwareorBinGoodCondition = "//div[@data-control-name='TogBin']//div[@class='appmagic-toggleSwitch-handle']";
	public static final String comments = "//div[@data-control-name='TxtComment_6']//textarea";
	public static final String save = "//div[@data-control-name='BtnSave']//button";
	public static final String savenSubmit = "//div[@data-control-name='Btn-SaveSubmit']//button";
	
}


public static class colorworksDaily{
	
	public static final String colorworksDailyBttn = "//div[.='Colorworks Daily Waste Log']";
	public static final String scaleCalibration = "//div[@data-control-name='TogScaleCalibration']//div[@class='appmagic-toggleSwitch-handle']";
	public static final String wasteDefaced = "//div[@data-control-name='TogWaste']//div[@class='appmagic-toggleSwitch-handle']";
	public static final String addBttn = "//div[@data-control-name='Button3']//button";
	public static final String wasteType = "//div[@data-control-name='ComboBox1']";
	public static final String qtyOfWaste = "//div[@data-control-name='TextDescription_1']//input";
	public static final String addWasteBttn = "//div[@data-control-name='BtnSave1_5']//button";
	
	public static final String comments = "//div[@data-control-name='TxtComment_8']//textarea";
	public static final String save = "//div[@data-control-name='BtnSave_7']//button";
	public static final String savenSubmit = "//div[@data-control-name='Btn-SaveSubmit_3']//button";
}
}
