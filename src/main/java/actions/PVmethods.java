package actions;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.BaseExecutor;

public class PVmethods extends BaseExecutor {
	
	
	private ExtentTest testlog;
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	public PVmethods(WebDriver protectedDriver) {
		driver = protectedDriver;
		action = new Actions(driver);
	}
	
	
	
	//Click the Element from ListBox
	public Status clickListboxElement(String Text) {
		try {
		List<WebElement> listOfElements = driver.findElements(By.xpath("//*[@role='listbox']/li"));
		for(WebElement webElement:listOfElements)
		{
			System.out.println(webElement.getText());
		}

		
		for(WebElement webElement:listOfElements) {
			
			if(webElement.getText().trim().equals(Text)) {
				Thread.sleep(1000);
				webElement.click();
				System.out.println("Clicked on "+ webElement.getText());
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
