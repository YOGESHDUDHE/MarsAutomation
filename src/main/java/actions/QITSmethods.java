package actions;

import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;

public class QITSmethods extends BaseExecutor{
	private ExtentTest testlog;
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	public QITSmethods(WebDriver protectedDriver) {
		driver = protectedDriver;
		action = new Actions(driver);
	}
	
	
	
	//Verify Entry Created or Not
	
	public Status verifyEntry(String expectedText) {
		

		List<WebElement> rowCount = driver.findElements(By.xpath("(//div[@class=' appmagic-content-control-name appmagic-control-view canvasContentDiv'])[2]"));
		
		for(WebElement e:rowCount)
		{
			System.out.println(e.getText());
		}

		for (WebElement e:rowCount) {
			boolean text = e.getText().contains(expectedText);
			if (text)
			{ 
				System.out.println("New Entry is created");
				softAssert().assertEquals(text, true);
				Assert.assertEquals(text, true);
				return Status.PASS;

			} else {
			System.out.println("New Entry is Not created"); 
			softAssert().assertEquals(text, true);
				return Status.FAIL;
			}
		}

		return Status.FAIL;
	}


	
	
	//Click the Element from ListBox
	public Status clickListboxElement(String Text) {
		try {
		List<WebElement> listOfElements = driver.findElements(By.xpath("//*[@role='listbox']/li"));
		
		for(WebElement webElement:listOfElements) {
			System.out.println(webElement.getText());
			if(webElement.getText().trim().equals(Text)) {
				
				webElement.click();
				return Status.PASS;
				
			}}
			return Status.FAIL;
			}
		
		catch (Exception e) {
				log.error(e + "Fail");
				return Status.FAIL;
			}
		}

	
	
	public Status clickListboxvalue(String Value, String path) {
		try {
		List<WebElement> listOfElements = driver.findElements(By.xpath(path));
		
		for(WebElement webElement:listOfElements) {
			if(webElement.getText().trim().equals(Value)) {
				
				webElement.click();
				return Status.PASS;
				
			}}
			return Status.FAIL;
			}
	
		catch (Exception e) {
				log.error(e + "Fail");
				return Status.FAIL;
			}
		}




	// Select Date from DatePicker

public Status selectDate(String Date) {
	try {
	List<WebElement> listOfElements = driver.findElements(By.xpath("//table[@class='pika-table']//td//button"));
	
	for(WebElement webElement:listOfElements) {
//		System.out.println(webElement.getText());
		if(webElement.getText().trim().equals(Date)) {
			
			webElement.click();
			WebElement okBttn= driver.findElement(By.xpath("//div[@class='appmagic-datepicker-footer']//button[contains(text(),'Ok')]"));
					okBttn.click();
			return Status.PASS;
		}
		}
	return Status.FAIL;}
	
	catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}

}




}



