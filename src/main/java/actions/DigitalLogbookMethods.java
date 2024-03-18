package actions;

import java.lang.invoke.MethodHandles;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.Status;
import com.framework.*;
import com.object_repository.*;

public class DigitalLogbookMethods extends BaseExecutor{
	
	public DigitalLogbookMethods(WebDriver protectedDriver) {

		driver = protectedDriver;
		action = new Actions(driver);
	}
	
	
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	
	
	
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
	
	
	
	// Select from dropdown (div)
	
	public Status clickListboxvalue(String path, String Value) {
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
	
	
	//Search & Select the Element from ListBox
	public Status SearchnSelect(String ObjectRef, String SearchPath, String value) {
		try {
			
			driver.findElement(By.xpath(ObjectRef)).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(SearchPath)).sendKeys("MSFlow");
			Thread.sleep(2000);
			WebElement object =driver.findElement(By.xpath("//*[@role='listbox']/li/div[.='"+value+"']"));
	
		    object.click();
			return Status.PASS;
		}
		catch (Exception e) {
			log.error(e + "Element not present in dropdown");
			return Status.FAIL;
		}

}	
}