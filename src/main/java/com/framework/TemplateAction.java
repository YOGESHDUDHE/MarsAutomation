package com.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.framework.*;
import com.object_repository.*;

public class TemplateAction extends BaseExecutor{
	
	public TemplateAction(WebDriver protectedDriver) {
		driver = protectedDriver;
		action = new Actions(driver);
	}	
	
	//Write methods specific to your application here
}