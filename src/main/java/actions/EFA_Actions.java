
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

	import com.aventstack.extentreports.Status;
	import com.framework.*;

	public class EFA_Actions extends BaseExecutor{
		
		public EFA_Actions(WebDriver protectedDriver) {
			driver = protectedDriver;
			action = new Actions(driver);
		}	
		
		private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
		
		public Status SearchnSelect(String ObjectRef, String SearchPath, String value) {
			

			try {
				
				driver.findElement(By.xpath(ObjectRef)).click();
				Thread.sleep(2000);
				//driver.findElement(By.xpath(SearchPath)).sendKeys(value);
				//Thread.sleep(2000);
				WebElement object =driver.findElement(By.xpath("//*[@role='listbox']/li/div[.='"+value+"']"));
		
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
				WebElement object =driver.findElement(By.xpath("//*[@role='listbox']/li/div[.='"+Value+"']"));
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
		
		
		
	public Status verifyActionEntry(String expectedText) {
			

			List<WebElement> rowCount = driver.findElements(By.xpath("(//div[@class='react-gallery-items-window'])[3]/div"));
			
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
		
	}
