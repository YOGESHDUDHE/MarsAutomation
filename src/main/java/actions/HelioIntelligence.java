package actions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.framework.*;
import com.object_repository.*;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class HelioIntelligence extends BaseExecutor{
	
	public HelioIntelligence(WebDriver protectedDriver) {
		driver = protectedDriver;
		action = new Actions(driver);
	}	
	public HelioIntelligence() {
	}
	//Write methods specific to your application here
	/**
	 * @Method - loginToHelios
	 * @Description - To login into  application
	 * @return - status
	 * @author - singhje1
	 * @DateCreated - 01-11-2021
	 * @DateModified - ?
	 */
	public Status loginToHelios() {
		waitForElement("xpath", HeliosIntelligenceObjects.Login.LBL_USERNAME);
		syncBrowser(); 
		enterValues("xpath", HeliosIntelligenceObjects.Login.TXT_USERNAME,"singhje1");
		syncBrowser(); 
		enterValues("xpath", HeliosIntelligenceObjects.Login.TXT_PASSWORD, "maskedusedyours");
		syncBrowser(); 
		Status clickOnLogin = clickOnElementByJavascript("xpath", HeliosIntelligenceObjects.Login.BTN_LOGIN);
		syncBrowser(); 
		if(clickOnLogin==Status.PASS)
			return Status.PASS;
		else
			return Status.FAIL;
	}
	
	/**
	 * @Method - takeReportScreenshot
	 * @Description - To take screenshot of current report, this will also crop the headers of report out
	 * @return - String
	 * @author - singhje1
	 * @DateCreated - 02-11-2021
	 * @DateModified - ?
	 */
	public String takeReportScreenshot(String reportName) {
		  Point ptB =  driver.findElement(By.xpath(HeliosIntelligenceObjects.StandardPage.LBL_BY_PERIOD)).getLocation();
		  try {
			  String fullPageScreenshotPath = takeFullPageScreenShot("HeliosIntelligence",reportName);
			  BufferedImage bufferedImage =  ImageIO.read(new File(fullPageScreenshotPath)); 
			  int eleWidth = bufferedImage.getWidth(); 
			  int eleHeight = bufferedImage.getHeight() - ptB.getY();
			  BufferedImage croppedImage = bufferedImage.getSubimage(0, ptB.getY(), eleWidth, eleHeight); 
			  String df = new SimpleDateFormat("yyyyMMddhhss").format(new Date());
			  String path = TestData.getConfigData("SCREENSHOT_PATH") + "HeliosIntelligence" + "\\" + reportName + "_final" + df + ".png";
			  File croppedImagePath = new File(path);
			  ImageIO.write(croppedImage,"PNG",croppedImagePath);	
			  
			  /*remove main image*/
			  File fullPageScreenshot = new File(fullPageScreenshotPath);
			  fullPageScreenshot.delete();
			  /*remove image block end*/
			  
			  return croppedImagePath.toString();

		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * @Method - compareWithProdReport
	 * @Description - To compare current report with saved prod report
	 * @return - status
	 * @author - singhje1
	 * @DateCreated - 02-11-2021
	 * @DateModified - ?
	 */
	public Status compareWithProdReport(String prodReportScreenshot, String testReportScreenshot) {
		//StrinprodReportScreenshot = "\\\\vmww7144\\Script Shared Files\\SeleniumScreenShots\\HeliosIntelligence\\ProdReportImages\\final.png";
		/*take Current Snapshot*/
		//takeReportScreenshot(reportName);
		return compareImages(prodReportScreenshot, testReportScreenshot);
	}
}