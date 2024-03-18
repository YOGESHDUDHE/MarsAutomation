package actions;

import java.io.Console;
import java.lang.invoke.MethodHandles;
import java.util.List;

import org.openqa.selenium.support.ui.Select;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.framework.*;

public class MWSupplierMatrixMethods extends BaseExecutor{
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	
	public MWSupplierMatrixMethods(WebDriver protectedDriver) {
		driver = protectedDriver;
		action = new Actions(driver);
	}	
	
	
	
	//Verify Entry Created or Not
	
	public Status verifyEntry(String expectedText) {
		

		List<WebElement> rowCount = driver.findElements(By.xpath("(//div[@data-control-name='SupplierMasterGal']//div[@role='listitem'])[1]"));
		

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
	public Status clickListboxElement(String ObjectRef, String value) {
		try {
			
			driver.findElement(By.xpath(ObjectRef)).click();
			Thread.sleep(2000);
			WebElement object =driver.findElement(By.xpath("//*[@role='listbox']/li/div[.='"+value+"']"));
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].scrollIntoView(true);", object);

		    object.click();
			return Status.PASS;
			}
		
		catch (Exception e) {
				log.error(e + "Element not present in dropdown");
				return Status.FAIL;
			}
	}
	
	
	
	public Status clickListboxvalue(String Value, String path) {
		try {
			
			driver.findElement(By.xpath(path)).click();
			Thread.sleep(2000);
			WebElement object =driver.findElement(By.xpath("//*[@role='listbox']/div[.='"+Value+"']"));
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].scrollIntoView(true);", object);

		    object.click();
				return Status.PASS;
				
			}
	
		catch (Exception e) {
				log.error(e + "Fail");
				return Status.FAIL;
			}
	}
	
	
	public Status calender(String Date, String Month) throws InterruptedException {
	
		//Month
		WebElement element = driver.findElement(By.xpath("//div/select[@class='pika-select pika-select-month']"));
		softAssert().assertNotNull(element, "Element not Found");
		if (element != null) {
			Select option = new Select(element);
			if(Month!=null) {
				option.selectByVisibleText(Month);	
			//	Console.log("High");
			}
		
		}
		Thread.sleep(2000);
		//Date
		List<WebElement> listOfElements = driver.findElements(By.xpath("//table[@class='pika-table']//td//button"));
		
		for(WebElement webElement:listOfElements) {
			if(webElement.getText().trim().equals(Date)) {
				
				webElement.click();
				WebElement okBttn= driver.findElement(By.xpath("//div[@class='appmagic-datepicker-footer']//button[contains(text(),'Ok')]"));
						okBttn.click();
						return Status.PASS;
}
		}
		return Status.FAIL;
	}

	
	public Status calender2(String Date, String Month)
	{
		WebElement element =driver.findElement(By.xpath("(//select[@class='pika-select pika-select-month' ])[2]"));
		element.click();
		
		Select option = new Select(element);
		option.selectByVisibleText(Month);
		
        List<WebElement> listOfElements = driver.findElements(By.xpath("//table[@class='pika-table']//td//button"));
		
		for(WebElement webElement:listOfElements) {
			if(webElement.getText().trim().equals(Date)) {
				
				webElement.click();
				WebElement okBttn= driver.findElement(By.xpath("(//div[@class='appmagic-datepicker-footer']//button[contains(text(),'Ok')])[2]"));
						okBttn.click();
						return Status.PASS;
	}
	
	}
		return Status.FAIL;
	}

	
	
	
	//
	public void scrollToElementAndClick(String element, String value) throws InterruptedException {
		
		driver.findElement(By.xpath(element)).click();
		Thread.sleep(2000);
		WebElement object =driver.findElement(By.xpath("//*[@role='listbox']/li/div[.='"+value+"']"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", object);

	    object.click();
	}

}