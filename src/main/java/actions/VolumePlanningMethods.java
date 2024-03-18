package actions;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;

public class VolumePlanningMethods extends BaseExecutor {
	
	private ExtentTest testlog;
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	public VolumePlanningMethods(WebDriver protectedDriver) {
		driver = protectedDriver;
		action = new Actions(driver);
	}
	
	
	
	//Verify Entry Created or Not
	
	public Status verifyEntry(String expectedText) {
		

		List<WebElement> rowCount = driver.findElements(By.xpath("//div[@class='react-gallery-items-window']"));
		
		for(WebElement e:rowCount)
		{
			System.out.println(e.getText());
		}

		for (WebElement e:rowCount) {
			boolean text = e.getText().contains(expectedText);
			if (text)
			{ 
				System.out.println("File Uploaded Successfully");
				softAssert().assertEquals(text, true);
				Assert.assertEquals(text, true);
				return Status.PASS;

			} else {
			System.out.println("File not uploaded"); 
			softAssert().assertEquals(text, true);
				return Status.FAIL;
			}
		}

		return Status.FAIL;
	}
	
	
	public int countRowsBeforeTest() {
		
		List<WebElement> rowCount = driver.findElements(By.xpath("//div[@class='react-gallery-items-window']"));
		int Beforenum = rowCount.size();	
		System.out.println(Beforenum);
		return Beforenum;
		
	}
	
	public Status countRowsAfterTest() {
		
		List<WebElement> rowCount = driver.findElements(By.xpath("//div[@class='react-gallery-items-window']"));
		int Afternum = rowCount.size();
		System.out.println(Afternum);
		if (Afternum > countRowsBeforeTest()) {
			System.out.println("File Uploaded Successfully");
			softAssert().assertEquals(true, true);
			Assert.assertEquals(true, true);
			return Status.PASS;

		} else {
		System.out.println("File not uploaded"); 
		softAssert().assertEquals(false, true);
			return Status.FAIL;
		}
			
		}

	
	
	
	//Click the Element from ListBox
	public Status clickListboxElement(String Text) {
		try {
		List<WebElement> listOfElements = driver.findElements(By.xpath("//*[@role='listbox']/li"));
		
		for(WebElement webElement:listOfElements) {
			
			if(webElement.getText().trim().equals(Text)) {
				Thread.sleep(1000);				
				webElement.click();
				System.out.println("Clicked on "+ webElement.getText());
				return Status.PASS;
				
			}}
			return Status.PASS;
			}
		
		catch (Exception e) {
				log.error(e + "Element not present in dropdown");
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
	}
	
	