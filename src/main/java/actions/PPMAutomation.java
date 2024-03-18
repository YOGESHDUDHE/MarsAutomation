package actions;
import org.openqa.selenium.*;
import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.*;
import com.object_repository.*;

public class PPMAutomation extends BaseExecutor {
	private ExtentTest testlog;
	public PPMAutomation(WebDriver protectedDriver) {
		driver = protectedDriver;
		action = new Actions(driver);
	}
	public PPMAutomation() {
	}
	/**
	 * @Method - findTheProject
	 * @Description - Finding the already created project ID
	 * @return - status
	 * @author - harikbha
	 * @DateCreated - 04-08-2021
	 */
	public Status findTheProject(String project_id) {
		try {
			Status result = clickOnElement("xpath", PPM.ppmHomePage.LNK_PROJECT);
			if (result == Status.PASS) {
				clickOnElement("xpath", PPM.ppmHomePage.LNK_FIND_PROJECT);
				syncBrowser();
				// Enter Project ID
				changeFrame(PPM.findScreenPage.CHANGEFRAME);
				clickOnElementByJavascript("id", PPM.findScreenPage.TXT_PROJECT_ID);
				enterValues("id", PPM.findScreenPage.TXT_PROJECT_ID, project_id);
				// Click on Submit
				scrollToElement("id", PPM.findScreenPage.BTN_SUBMIT);
				clickOnElementByJavascript("id", PPM.findScreenPage.BTN_SUBMIT);
				releaseFromFrame();
				// Verify the value from Table
				changeFrame(PPM.findScreenPage.CHANGEFRAME);
				clickOnElement("xpath", PPM.findScreenPage.TABLE_SEARCHDATA);
				if (clickOnElement("xpath", PPM.findScreenPage.LNK_PROJECT_ID) == Status.PASS) {
					releaseFromFrame();
					syncBrowser();
					return Status.PASS;
				} else {
					return Status.FAIL;
				}
			}
			return Status.FAIL;
		} catch (Exception e) {
			return Status.FAIL;
		}
	}
	/**
	 * @Method - loginToPPM
	 * @Description - openurl and login in test/prod environment
	 * @return - status
	 * @author -gorlelak
	 */
	public Status loginToPPM(String environment, String username, String password) {
		try {
			if (environment.contentEquals("test")) {
				openUrl("https://" + username + ":" + password + "@ppmtest2.mars-ad.net/");
				clickOnElement("id", PPM.ppmHomePage.USER_MENU);
				Status result = waitForElement("xpath", PPM.ppmHomePage.LOGOUT);
				if (result == Status.PASS) {
					return Status.PASS;
				} else
					return Status.FAIL;
			} else {
				openUrl("http://" + username + ":" + password + "@ppmdev.mars/");
				clickOnElement("id", PPM.ppmHomePage.USER_MENU);
				Status result = waitForElement("xpath", PPM.ppmHomePage.LOGOUT);
				if (result == Status.PASS) {
					return Status.PASS;
				} else
					return Status.FAIL;
			}
		} catch (Exception e) {
			return Status.FAIL;
		}
	}

	/**
	 * @Method - waitForDownload
	 * @Description -waits for the downloaded file to appear in the specified location
	 * @return - status
	 * @author -gorlelak
	 */
	public Status waitForDownload(String filepath) {
		try {
			File file = new File(filepath);
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(40))
					.pollingEvery(Duration.ofMillis(100));
			wait.until(x -> file.exists());
			return Status.PASS;
		} catch (Exception e) {
			return Status.FAIL;
		}
	}
	/**
	 * @Method - uploadFile
	 * @Description -uploads the file using send keys
	 * @return - status
	 * @author -gorlelak
	 *  @DateCreated - 09-12-2021
	 */
	public Status uploadFile(String filepath) {
		try {
			syncBrowser();
			WebElement element = driver.findElement(By.xpath(PPM.saveToPPM.UPLOADFILE));
			element.sendKeys(filepath);
			return Status.PASS;
		} catch (Exception e) {
			return Status.FAIL;
		}
	}
	/**
	 * @Method - getPPMTestData
	 * @Description -gets the data from TDS
	 * @return - status
	 * @author -gorlelak
	 *  @DateCreated - 08-1-2022
	 */
	public String getPPMTestData(String tableName, String keyName) {
		String keyValue=null;
		try {
			TestData objTestData = new TestData();
			HashMap<String, String> testData = objTestData.getDataFromAPI("PPM",tableName);
			if (testData.toString().length() > 2) {
				keyValue =	testData.get(keyName);
				testlog.log(Status.PASS, "Read Data from TDS");
			} else {
				testlog.log(Status.FAIL, "Failed to Read Data from TDS");
			}
			return keyValue;
		} catch (Exception e) {
			return keyValue;
		}
	}
	/**
	 * @Method - updateExcelValuesInTemplate
	 * @Description -update the template
	 * @return - status
	 * @author -gorlelak
	 *  @DateCreated - 22-1-2022
	 */
	public Status updateExcelValuesInTemplate(String location) {
		try {
			int i = 0;
			int Volumeabsolute = Integer.parseInt(getPPMTestData("PPM_Conf", "volume absolute"));
			int Nsvabsolute = Integer.parseInt(getPPMTestData("PPM_Conf", "NSV absolute"));
			int Volumeincremental = Integer.parseInt(getPPMTestData("PPM_Conf", "volume incremental"));
			int Nsvincremental = Integer.parseInt(getPPMTestData("PPM_Conf", "NSV incremental"));
			HashMap<Integer, ExcelCell> values = new HashMap<Integer, ExcelCell>();
			// input volume absolute value
			ExcelCell currentCell = new ExcelCell(24, 12, Volumeabsolute);
			values.put(i++, currentCell);
			// input NSV absolute value
			currentCell = new ExcelCell(25, 12, Nsvabsolute);
			values.put(i++, currentCell);
			// input Volumeincremental value
			currentCell = new ExcelCell(28, 12, Volumeincremental);
			values.put(i++, currentCell);
			// input NSV incremental
			currentCell = new ExcelCell(29, 12, Nsvincremental);
			values.put(i++, currentCell);
			Status result = updateExcelValues(location, getPPMTestData("PPM_Conf", "template_sheetname"), values);
			if (result == Status.PASS) {
				return Status.PASS;
			}
			return Status.FAIL;
		} catch (Exception e) {
			return Status.FAIL;
		}
	}	
	/**
	 * @Method -  getLocationOfFile
	 * @Description -get the location of downloaded file
	 * @return - String
	 * @author -gorlelak
	 *  @DateCreated - 22-1-2022
	 */
	public String getLocationOfFile() {
		String location = null;
		try {
			location = System.getProperty("user.home") + "\\Downloads\\"
					+ getText("xpath", PPM.projectDescriptionTracker.TEMPLATENAME);
			File file = new File(location);
			if (file.exists()) {
				file.delete();
			}
			return location;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}
}