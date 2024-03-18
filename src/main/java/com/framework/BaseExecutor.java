package com.framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ReportStatusStats;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentEmailReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class BaseExecutor implements IHookable {

	public static WebDriver driver;
	protected Actions action;
	public static ExtentHtmlReporter reporter;
	public static ExtentEmailReporter emailReporter;
	public static ExtentReports extent;
	public String runEnviroment;
	public static HashMap<String, String> testResults;
	private static final String REPORTLINK = "test-output//HTMLReport.html";
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	private static final String KEY = "soAssert";
	public HashMap<String,Object> prefs = new HashMap<String,Object>();

	@Override
	public void run(IHookCallBack callBack, ITestResult testResult) {
		SoftAssert soAssert = new SoftAssert();
		testResult.setAttribute(KEY, soAssert);
		callBack.runTestMethod(testResult);
		try {
			soAssert.assertAll();
		} catch (AssertionError e) {
			throw new AssertionError(e.getMessage(), testResult.getThrowable());
		}
	}

	@BeforeSuite(alwaysRun = true)
	public void setupExtentReport() {
		// create test-output directory if doesn't exists
		File directory = new File("test-output");
		if (!directory.exists()) {
			directory.mkdir();
		}
		// initiate report generation
		reporter = new ExtentHtmlReporter("./test-output/ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		// printing execution environment
		try {
			runEnviroment = TestData.getConfigData("Environment");
		} catch (Exception e) {
			runEnviroment = "UNKNOWN";
		}
		switch (runEnviroment) {
		case "local":
			log.info("Running Tests on local System");
			// Below parameter has to be parameterized
			extent.setSystemInfo("System", "Local");
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			System.out.println("Running Tests on local System");
			break;
		case "remote":
			log.info("Running Tests on Distributed Selenium GRID System");
			// Below parameter has to be parameterized
			extent.setSystemInfo("System", "Remote");
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			System.out.println("Running Tests on Distributed Selenium GRID System");
			break;
		default:
			log.info("Unknown Execution Environment");
		}
		testResults = new HashMap<String, String>();
	}

	/**
	 * @Method - initiateTest
	 * @Description - To Start Driver based on configuration
	 * @param ?
	 * @author aggarkan
	 * @DateCreated - 15-09-2020
	 * @DateModified - 22-10-2021
	 */
	public WebDriver initiateTest() {
		// Setting up remote driver
		try {
			runEnviroment = TestData.getConfigData("Environment");
     			String browserName = getParameter("browser");
			String hubURL;
			DesiredCapabilities capabilities;
			switch (runEnviroment) {
			case "local":
				switch (browserName) {
				case "chrome":
					System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
					extent.setSystemInfo("Browser", browserName);
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("prefs", prefs);
					driver = new ChromeDriver(options);
					break;
				case "firefox":
					System.setProperty("webdriver.gecko.driver", "drivers//geckodriver.exe");
					extent.setSystemInfo("Browser", browserName);
					driver = new FirefoxDriver();
					break;
				case "internetexplorer":
					System.setProperty("webdriver.ie.driver", "drivers//IEDriverServer.exe");
					extent.setSystemInfo("Browser", browserName);
					driver = new InternetExplorerDriver();
					break;
				case "edge":
					System.setProperty("webdriver.edge.driver", "drivers//msedgedriver.exe");
					extent.setSystemInfo("Browser", browserName);
					driver = new EdgeDriver();
					break;
				default:
					log.info("Unknown Browser on remote");
				}
				break;
			case "remote":
				hubURL = TestData.getConfigData("GRID_HUB_URL");
				switch (browserName) {
				case "chrome":
					capabilities = DesiredCapabilities.chrome();
					capabilities.setBrowserName(capabilities.getBrowserName());
					capabilities.setPlatform(Platform.ANY);
					extent.setSystemInfo("Browser", capabilities.getBrowserName());
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("prefs", prefs);
					options.merge(capabilities);
					driver = new RemoteWebDriver(new URL(hubURL), options);
					break;
				case "firefox":
					capabilities = DesiredCapabilities.firefox();
					capabilities.setBrowserName(capabilities.getBrowserName());
					capabilities.setPlatform(Platform.ANY);
					extent.setSystemInfo("Browser", capabilities.getBrowserName());
					driver = new RemoteWebDriver(new URL(hubURL), capabilities);
					break;
				case "internetexplorer":
					capabilities = DesiredCapabilities.internetExplorer();
					capabilities.setBrowserName(capabilities.getBrowserName());
					capabilities.setPlatform(Platform.ANY);
					extent.setSystemInfo("Browser", capabilities.getBrowserName());
					driver = new RemoteWebDriver(new URL(hubURL), capabilities);
					break;
				case "edge":
					capabilities = DesiredCapabilities.edge();
					capabilities.setBrowserName(capabilities.getBrowserName());
					capabilities.setPlatform(Platform.ANY);
					extent.setSystemInfo("Browser", capabilities.getBrowserName());
					driver = new RemoteWebDriver(new URL(hubURL), capabilities);
					break;
				default:
					log.warn("Unknown Browser on remote");
				}
				break;
			default:
				log.warn("Unknown Execution Environment");
			}
			driver.manage().window().maximize();
			action = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e1) {
			log.warn(e1 + " - Fail");
		}
		return driver;
	}

	@AfterSuite()
	public void afterSuite() {
		log.info("All tests have finished, flushing ExtentReports");
		sendEmailReport(extent.getStats());
		extent.flush();
		/*below method will undo changes done to css and js endpoints by extent.flush() */		 
		updateExtentCssJsEndPoints();
	}
	
	/**
	 * @Method - getParameter
	 * @Description - To get value from command line
	 * @author aggarkan
	 * @DateCreated - 22-10-2021
	 * @DateModified - ?
	 */
	 private String getParameter(String name) {
		  String value = System.getProperty(name);
		  if (value == null)
			  throw new RuntimeException(name + " is not a parameter!");

		  else if (value.isEmpty())
			  throw new RuntimeException(name + " is empty!");
		  else
			  return value;
		 }

	/* generic methods to perform certain actions */

	/**
	 * @Method - openUrl
	 * @Description - To open URLS
	 * @return - Extent Report Step Status
	 * @author - aggarkan
	 * @DateCreated - 15-09-2020
	 * @DateModified - 04-01-2021
	 */
	public Status openUrl(String url) {
		try {
			driver.get(url);
			return Status.PASS;
		} catch (Exception e) {
			log.error(e + "Fail to open URL");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - waitForElement
	 * @Description - To wait for an element
	 * @return - Extent Report Step Status
	 * @author - aggarkan
	 * @DateCreated - 15-09-2020
	 * @DateModified - 04-01-2021
	 */
	public String waitForElement(By element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			return "PASS";
		} catch (Exception e) {
			log.error(e + "Fail to search element");
			return "FAIL";
		}
	}

	/**
	 * @Method - syncBrowser
	 * @Description - To sync browser if AJAX
	 * @author - aggarkan
	 * @DateCreated - 15-09-2020
	 * @DateModified - 17-12-2020
	 */
	public void syncBrowser() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {
			softAssert().fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	/**
	 * @Method - clickOnElement
	 * @Description - Click on Element
	 * @return - Extent Report Step Status
	 * @author - aggarkan
	 * @DateCreated - 15-09-2020
	 * @DateModified - 04-01-2021
	 */
	public Status clickOnElement(String selectBy, String objectReference) {
		try {
		softAssert().assertEquals(waitForElement(getSelector(selectBy, objectReference)), "PASS");
		action.moveToElement(driver.findElement(getSelector(selectBy, objectReference))).click().build().perform();
			
			return Status.PASS;
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - clickOnElementByJavascript
	 * @Description - Click on Element By Javascript
	 * @return - Extent Report Step Status
	 * @author - singhje1
	 * @DateCreated - 13-10-2020
	 * @DateModified - 04-01-2021
	 */
	public Status clickOnElementByJavascript(String selectBy, String objectReference) {
		try {
			softAssert().assertEquals(waitForElement(getSelector(selectBy, objectReference)), "PASS");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",
					driver.findElement(getSelector(selectBy, objectReference)));
			return Status.PASS;
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - getText
	 * @Description - To get text of an element
	 * @author - aggarkan
	 * @DateCreated - 15-09-2020
	 * @DateModified - 04-01-2021
	 */
	public String getText(String selectBy, String objectReference) {
		try {
			softAssert().assertEquals(waitForElement(getSelector(selectBy, objectReference)), "PASS");
			return driver.findElement(getSelector(selectBy, objectReference)).getText();
		} catch (Exception e) {
			log.error(e + "Fail");
			return "";
		}
	}

	/**
	 * @Method - getValue
	 * @Description - To get value of an element
	 * @author - aggarkan
	 * @DateCreated - 15-09-2020
	 * @DateModified - 04-01-2021
	 */
	public String getValue(String selectBy, String objectReference) {
		try {
			softAssert().assertEquals(waitForElement(getSelector(selectBy, objectReference)), "PASS");
			return driver.findElement(getSelector(selectBy, objectReference)).getAttribute("value");
		} catch (Exception e) {
			log.error(e + "Fail");
			return "";
		}
	}

	/**
	 * @Method - enterValues
	 * @Description - To enter data values into field
	 * @author aggarkan
	 * @DateCreated - 15-09-2020
	 * @DateMadified - 04-01-2021
	 */
	public Status enterValues(String selectBy, String objectReference, String inputValue) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			softAssert().assertEquals(waitForElement(getSelector(selectBy, objectReference)), "PASS");
			WebElement element = wait
					.until(ExpectedConditions.presenceOfElementLocated(getSelector(selectBy, objectReference)));
			element = (WebElement) ((JavascriptExecutor) driver)
					.executeScript("arguments[0].style.visibility = 'visible'; return arguments[0];", element);
			softAssert().assertNotNull(element);
			if (element != null) {
				element.sendKeys(inputValue);
				return Status.PASS;
			}
			return Status.FAIL;
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - enterValuesAndPressEnter
	 * @Description - To enter data values into field and send enter key
	 * @author singhje1
	 * @DateCreated - 08-04-2021
	 * @DateMadified - ?
	 */
	public Status enterValuesAndPressEnter(String selectBy, String objectReference, String inputValue) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			softAssert().assertEquals(waitForElement(getSelector(selectBy, objectReference)), "PASS");
			WebElement element = wait
					.until(ExpectedConditions.presenceOfElementLocated(getSelector(selectBy, objectReference)));
			element = (WebElement) ((JavascriptExecutor) driver)
					.executeScript("arguments[0].style.visibility = 'visible'; return arguments[0];", element);
			softAssert().assertNotNull(element);
			if (element != null) {
				element.sendKeys(inputValue);
				element.sendKeys(Keys.ENTER);
				return Status.PASS;
			}
			return Status.FAIL;
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - getSelector
	 * @Description - To select element using different selector properties
	 * @author - aggarkan
	 * @DateCreated - 15-09-2020
	 */
	public By getSelector(String selectBy, String objectReference) {
		By selector = null;
		switch (selectBy) {
		case "xpath":
			selector = By.xpath(objectReference);
			break;
		case "cssSelector":
			selector = By.cssSelector(objectReference);
			break;
		case "id":
			selector = By.id(objectReference);
			break;
		case "className":
			selector = By.className(objectReference);
			break;
		case "name":
			selector = By.name(objectReference);
			break;
		default:
			Assert.assertFalse(false, "Unable to Find Element");
		}
		return selector;
	}

	/**
	 * @Method - verifyElementText
	 * @Description - To verify element text
	 * @return - Extent Report Step Status
	 * @author - aggarkan
	 * @DateCreated - 15-09-2020
	 * @DateModified - 27-01-2021
	 */
	public Status verifyElementText(String selectBy, String objectReference, String textToVerify) {
		try {
			softAssert().assertEquals(waitForElement(getSelector(selectBy, objectReference)), "PASS");
			String actualText = getText(selectBy, objectReference);
			softAssert().assertTrue(actualText.equalsIgnoreCase(textToVerify), "Text not Verified");
			if (textToVerify.compareToIgnoreCase(actualText) == 0)
				return Status.PASS;
			else
				return Status.FAIL;
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - hoverOverElement
	 * @Description - To hover over element
	 * @return - status
	 * @author - singhje1
	 * @DateCreated - 23-09-2020
	 * @DateModified - 08-01-2021
	 */
	public Status hoverOverElement(String selectBy, String objectReference) {
		try {
			softAssert().assertEquals(waitForElement(getSelector(selectBy, objectReference)), "PASS");
			WebElement element = driver.findElement(getSelector(selectBy, objectReference));
			softAssert().assertNotNull(element, "Element not Found");
			if (element != null) {
				action.moveToElement(element).build().perform();
				return Status.PASS;
			}
			return Status.FAIL;
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}

	}

	/**
	 * @Method - changeFrame
	 * @Description - To Change frame
	 * @return - status
	 * @param - objectReference
	 * @author - aggarkan
	 * @DateCreated - 24-09-2020
	 * @DateModified - 08-12-2020
	 */
	public Status changeFrame(String objectReference) {
		try {
			driver.switchTo().frame(objectReference);
			return Status.PASS;
		} catch (Exception e) {
			log.error(e + "Fail");
			softAssert().fail("Not able to Switch Frame");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - releaseFromFrame
	 * @Description - To release from frame
	 * @return - status
	 * @author - aggarkan
	 * @DateCreated - 07-12-2020
	 * @DateModified - 04-01-2021
	 */
	public Status releaseFromFrame() {
		try {
			driver.switchTo().defaultContent();
			return Status.PASS;
		} catch (Exception e) {
			log.error(e + "Fail");
			softAssert().fail("Not able to Switch Frame");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - scrollToElement
	 * @Description - Scroll Page to element
	 * @return - status
	 * @author - aggarkan
	 * @DateCreated - 13-11-2020
	 * @DateModified - 04-01-2021
	 */

	public Status scrollToElement(String selectBy, String objectReference) {
		WebElement element;
		try {
			softAssert().assertEquals(waitForElement(getSelector(selectBy, objectReference)), "PASS");
			element = driver.findElement(getSelector(selectBy, objectReference));
			softAssert().assertNotNull(element);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			return Status.PASS;
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - iFrameByIndex
	 * @Description -To Switch Frame by index
	 * @return - status
	 * @author - aggarkan
	 * @DateCreated - 27-09-2020
	 * @DateModified - 04-01-2021
	 */
	public Status iFrameByIndex(int objectReference) {
		try {
			driver.switchTo().frame(objectReference);
			return Status.PASS;
		} catch (Exception e) {
			log.error(e + "Fail");
			softAssert().fail("Not able to Switch Frame");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - waitForElementClickable
	 * @Description - Wait for element to be clickable
	 * @return - status
	 * @author - aggarkan
	 * @DateCreated - 28-09-2020
	 * @DateModified - 04-01-2021
	 */

	public Status waitForElementClickable(String selectBy, String objectReference) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(getSelector(selectBy, objectReference)));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(getSelector(selectBy, objectReference)));
			softAssert().assertEquals(waitForElement(getSelector(selectBy, objectReference)), "PASS");
			return Status.PASS;
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - sendEmailReport
	 * @Description - to send email report
	 * @return - boolean
	 * @author - singhje1
	 * @DateCreated - 29-09-2020
	 * @DateModified - 04-01-2021
	 */
	public boolean sendEmailReport(ReportStatusStats report) {
		updateExtentCssJsEndPoints();
		String to;
		String defaultTo;
		String mailServer;
		String from = "noreply@effem.com";
		Session session;
		Message message;
		int totalTests;
		int passedTests;
		Properties props;
		try {
			totalTests = report.getParentCount();
			passedTests = report.getParentCountPass();
			to = TestData.getConfigData("SendEmailTo");
			defaultTo = TestData.getConfigData("DEFAULT_EMAIL_GRP_TEST");
			mailServer = TestData.getConfigData("MAIL_SERVER");

			props = new Properties();
			props.put("mail.smtp.auth", "false");
			props.put("mail.smtp.host", mailServer);
			props.put("mail.smtp.port", "25");

			session = Session.getInstance(props);

			message = new MimeMessage(session);
			Multipart multipart = new MimeMultipart();
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(defaultTo));
			message.setSubject(
					"Selenium Automation Test Results - " + passedTests + " out of " + totalTests + " passed");
			String resultHtml = "";
			int sn = 1;
			/* Generate Results HTML Table for email body */
			for (String testName : testResults.keySet()) {
				resultHtml += "<tr><td>" + sn++ + "</td><td>" + testName + "</td>"
						+ ((testResults.get(testName).equalsIgnoreCase("fail"))
								? ("<td style='color:rgb(200, 38, 19);'><b><a style='color: inherit;'>"
										+ testResults.get(testName) + "</a></b></td>")
								: ("<td span style='color:rgb(111, 192, 64);'>" + testResults.get(testName) + "</td>"));
				resultHtml += "</tr>";
			}

			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setContent(
					"<html class='translated-ltr'><head><link type='text/css' rel='stylesheet' charset='UTF-8' href='https://translate.googleapis.com/translate_static/css/translateelement.css'></head><body lang='EN-IN' style='tab-interval:36.0pt'>\r\n"
							+ "<div>\r\n" + "\r\n"
							+ "<table border='1' cellspacing='0' cellpadding='0' width='100%' style='width:100.0%;mso-cellspacing:0cm;background:white;border:solid #E9E9E9 1.0pt;\r\n"
							+ " mso-yfti-tbllook:1184;mso-padding-alt:0cm 0cm 0cm 0cm'>\r\n"
							+ " <tbody><tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes'>\r\n"
							+ "  <td valign='top' style='border:none;background:#1E90FF;padding:15.0pt 15.0pt 15.0pt 15.0pt'>\r\n"
							+ "  <p '='' align='center' style='text-align:center'><span style='font-size:12.0pt;font-family:&quot;Helvetica&quot;,sans-serif;color:white'>Test Report - DevOps Tools and CI/CD Automation"
							+ "<o:p></o:p></span></p>\r\n" + "  </td>\r\n" + " </tr>\r\n"
							+ "<tr style=\"mso-yfti-irow:0;mso-yfti-lastrow:yes\">\r\n"
							+ "  <td valign=\"top\" style=\"border:none;padding:7.5pt 7.5pt 7.5pt 7.5pt\">\r\n"
							+ "  <table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-cellspacing:0cm;background:white;border:solid #E9E9E9 1.0pt;\r\n"
							+ " mso-yfti-tbllook:1184;mso-padding-alt:0cm 0cm 0cm 0cm\">\r\n" + "  <tbody><tr>\r\n"
							+ "    <th>SN</th>\r\n" + "    <th>Test Case Name</th>\r\n" + "    <th>Result</th>\r\n"
							+ "  </tr>\r\n" + resultHtml + "</tbody></table>\r\n" + "  </td>\r\n" + " </tr>"
							+ "</tbody></table>\r\n" + "<p class='MsoNormal'><o:p>&nbsp;</o:p></p>\r\n"
							+ "</div><div id='goog-gt-tt' class='skiptranslate' dir='ltr'><div style='padding: 8px;'><div><div class='logo'><img src='https://www.gstatic.com/images/branding/product/1x/translate_24dp.png' width='20' height='20' alt='Google Translate'></div></div></div><div class='top' style='padding: 8px; float: left; width: 100%;'><h1 class='title gray'>Original text</h1></div><div class='middle' style='padding: 8px;'><div class='original-text'></div></div><div class='bottom' style='padding: 8px;'><div class='activity-links'><span class='activity-link'>Contribute a better translation</span><span class='activity-link'></span></div><div class='started-activity-container'><hr style='color: #CCC; background-color: #CCC; height: 1px; border: none;'><div class='activity-root'></div></div></div><div class='status-message' style='display: none;'></div></div>\r\n"
							+ "\r\n"
							+ "<div class='goog-te-spinner-pos'><div class='goog-te-spinner-animation'><svg xmlns='http://www.w3.org/2000/svg' class='goog-te-spinner' width='96px' height='96px' viewBox='0 0 66 66'><circle class='goog-te-spinner-path' fill='none' stroke-width='6' stroke-linecap='round' cx='33' cy='33' r='30'></circle></svg></div></div></body></html>",
					"text/html; charset=utf-8");

			multipart.addBodyPart(messageBodyPart1);
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			DataSource source = new FileDataSource(new File("test-output//HTMLReport.html"));
			// FileDataSource("test-output//HTMLReport.html");
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName("testReport.html");

			multipart.addBodyPart(messageBodyPart2);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Email report sent Successfully");
			log.info("Email report sent Successfully");
		} catch (Exception e1) {
			log.info("Issues in reading config.properties for email server/to details or sending report email");
		}
		return true;
	}

	/**
	 * @Method - clearValue
	 * @Description - To clear value in an element
	 * @author - aggarkan
	 * @DateCreated - 11-11-2020
	 * @DateModified - 04-01-2021
	 */
	public Status clearValue(String selectBy, String objectReference) {
		try {
			softAssert().assertEquals(waitForElement(getSelector(selectBy, objectReference)), "PASS");
			driver.findElement(getSelector(selectBy, objectReference)).clear();
			return Status.PASS;
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - softAssert
	 * @Description - To collect error
	 * @author - aggarkan
	 * @DateCreated - 28-01-2021
	 */
	protected final SoftAssert softAssert() {
		Object object = Reporter.getCurrentTestResult().getAttribute(KEY);
		if (object == null) {
			throw new RuntimeException("no soft assert object found");
		}
		if (!(object instanceof SoftAssert)) {
			throw new IllegalStateException("Not a soft assertion object");
		}
		return (SoftAssert) object;
	}

	/**
	 * @Method - setTestResults
	 * @Description - To set test results to be used by email function
	 * @author - singhje1
	 * @DateCreated - 16-03-2021
	 */
	public boolean setTestResults(ExtentTest testlog) {
		try {
			testResults.put(testlog.getModel().getName(), testlog.getModel().getStatus().toString());
		} catch (Exception e) {
			log.error("Unable to set test result");
		}
		return true;
	}

	/**
	 * @Method - waitForElement
	 * @Description - waitForElement
	 * @return - Extent Report Step Status
	 * @author - singhje1
	 * @DateCreated - 22-03-2021
	 * @DateModified - ?
	 */
	public Status waitForElement(String selectBy, String objectReference) {
		try {
			String result = waitForElement(getSelector(selectBy, objectReference));
			softAssert().assertEquals(result, "PASS");
			if (result.equalsIgnoreCase("PASS")) {
				return Status.PASS;
			} else {
				return Status.FAIL;
			}
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - handleBrowserAlert
	 * @Description - handle browser alerts based on messages
	 * @param - Expected message and action to be taken, possible action values
	 *          accept and cancel
	 * @return - Extent Report Step Status
	 * @author - singhje1
	 * @DateCreated - 22-03-2021
	 * @DateModified - ?
	 */
	public Status handleBrowserAlert(String expectedMessage, String action) {
		try {
			String actualMessage = driver.switchTo().alert().getText();
			if (actualMessage.equalsIgnoreCase(expectedMessage)) {
				if (action.equalsIgnoreCase("accept")) {
					driver.switchTo().alert().accept();
					return Status.PASS;
				} else if (action.equalsIgnoreCase("cancel")) {
					driver.switchTo().alert().dismiss();
					return Status.PASS;
				}
			}
			return Status.FAIL;
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - rightClickOnElement
	 * @Description - To right click on element
	 * @return - status
	 * @author - aggarkan
	 * @DateCreated - 24-05-2021
	 * @DateModified -
	 */
	public Status rightClickOnElement(String selectBy, String objectReference) {
		try {
			softAssert().assertEquals(waitForElement(getSelector(selectBy, objectReference)), "PASS");
			WebElement element = driver.findElement(getSelector(selectBy, objectReference));
			softAssert().assertNotNull(element, "Element not Found");
			if (element != null) {

				action.moveToElement(element).contextClick().build().perform();
				return Status.PASS;
			}
			return Status.FAIL;
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - ctrlClickOnElement
	 * @Description - To Ctrl click on element
	 * @return - status
	 * @author - aggarkan
	 * @DateCreated - 28-06-2021
	 * @DateModified - ?
	 */
	public Status ctrlClickOnElement(String selectBy, String objectReference) {
		try {
			softAssert().assertEquals(waitForElement(getSelector(selectBy, objectReference)), "PASS");
			WebElement element = driver.findElement(getSelector(selectBy, objectReference));
			softAssert().assertNotNull(element, "Element not Found");
			if (element != null) {
				action.keyDown(Keys.LEFT_CONTROL).click(element).keyUp(Keys.LEFT_CONTROL).build().perform();
				return Status.PASS;
			} else
				return Status.FAIL;
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - updateExcelValues
	 * @Description - to update values in excel
	 * @return - status
	 * @author - singhje1
	 * @DateCreated - 16-08-2021
	 * @DateModified - 13-10-2021
	 */
	public Status updateExcelValues(String fullFilePath, String sheetName, HashMap<Integer, ExcelCell> values) {
		try {
			FileInputStream file = new FileInputStream(new File(fullFilePath));

			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheet(sheetName);
			int totalValues = values.size();
			for (int i = 0; i < totalValues; i++) {
				Cell cell = sheet.getRow(values.get(i).getRow()).getCell(values.get(i).getColumn());
				cell.setBlank();
				cell.setCellValue(values.get(i).getValue());
			}
			file.close();
			FileOutputStream outFile = new FileOutputStream(new File(fullFilePath));
			workbook.write(outFile);
			workbook.close();
			outFile.close();
			return Status.PASS;
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - uploadFileViaWindowsModal
	 * @Description - to enter filename and submit if using windows modal, this method can only run in windows based selenium grid
	 * @return - status
	 * @author - aggarkan
	 * @DateCreated - 25-08-2021
	 * @DateModified - ?
	 */
	public Status uploadFileViaWindowsModal(String fullFilePath) {
		try {
			StringSelection selectedFilePath = new StringSelection(fullFilePath);
			// copy filename to windows clipboard
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selectedFilePath, null);

			Robot r = new Robot();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			r.delay(10000);
			// pressing enter
			r.keyPress(KeyEvent.VK_ENTER);
			// releasing enter
			r.keyRelease(KeyEvent.VK_ENTER);
			// pressing ctrl+v
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			// releasing ctrl+v
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_V);
			// pressing enter
			r.keyPress(KeyEvent.VK_ENTER);
			// releasing enter
			r.keyRelease(KeyEvent.VK_ENTER);
			return Status.PASS;
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
	}

	/**
	 * @Method - takeScreenShot
	 * @Description - to capture screenshot
	 * @return - String
	 * @author - aggarkan
	 * @DateCreated - 25-08-2021
	 * @DateModified - 02-11-2021
	 */
	public String takeScreenShot(String projectPath, String screenShotName) {
		String path = null;
		try {
			// create a string variable which will be unique always
			String df = new SimpleDateFormat("yyyyMMddhhss").format(new Date());

			// create object variable of TakeScreenshot class
			TakesScreenshot ts = (TakesScreenshot) driver;

			// create File object variable which holds the screen shot reference
			File source = ts.getScreenshotAs(OutputType.FILE);
			
			// store the screen shot path in path variable. Here we are storing the screenshots under screenshot path
			path = TestData.getConfigData("SCREENSHOT_PATH") + projectPath + "\\" + screenShotName + df + ".png";

			// create another File object variable which points(refer) to the above stored path variable
			File destination = new File(path);

			// use FileUtils class method to save the screen shot at desired path
			FileUtils.copyFile(source, destination);
			
			//remove sourcefile
			source.delete();

			// return the path where the screen shot is saved
			return path;
		} catch (Exception e) {
			log.error(e + "Fail");
		}
		return path;
	}

	/**
	 * @Method - updateExtentCssJsEndPoints
	 * @Description - to update css and js in MARS Local
	 * @return - boolean
	 * @author - singhje1
	 * @DateCreated - 01-09-2021
	 * @DateModified -
	 */
	public Status updateExtentCssJsEndPoints() {

		try {
			Path input = Path.of("test-output//HTMLReport.html");
			Document document = Jsoup.parse(Files.readString(input), "UTF-8");

			// Below is to get css file link and update to local, its 3rd element in DOM with link tag
			Element cssLink = document.select("link").get(2);
			cssLink.attr("href", "\\\\vmww7144\\Script Shared Files\\Automation Files\\ExtentFiles\\extent.css");

			// Below is to get js file link and update to local, its 4rd element in DOM with script tag
			Element jsLink = document.select("script").get(3);
			jsLink.attr("src", "\\\\vmww7144\\Script Shared Files\\Automation Files\\ExtentFiles\\extent.js");

			Path output = Path.of("test-output//HTMLReport.html");
			Files.writeString(output, document.outerHtml());
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
		return Status.PASS;
	}	

	/**
	 * @Method - selectFromDrpDwnByText
	 * @Description - selects the option from drop down by using visible text
	 * @return - Extent Report Step Status
	 * @author - aggarkan
	 * @DateCreated - 21-08-2021
	 * @DateModified - ?
	 */
	public Status selectFromDrpDwnByText(String selectBy, String objectReference, String text) {
		try {
			softAssert().assertEquals(waitForElement(getSelector(selectBy, objectReference)), "PASS");
			WebElement element = driver.findElement(getSelector(selectBy, objectReference));
			softAssert().assertNotNull(element, "Element not Found");
			if (element != null) {
				Select option = new Select(element);
				option.selectByVisibleText(text);
				return Status.PASS;
			}
			return Status.FAIL;
		} catch (Exception e) {
			log.error(e + "Fail");
			return Status.FAIL;
		}
	}
	
	/**
	 * @Method - waitForInvisiblityOfElement
	 * @Description - to handle load spinner
	 * @return - Status
	 * @author - aggarkan
	 * @DateCreated - 27-09-2021
	 * @DateModified - ?
	 */
	public Status waitForInvisiblityOfElement(String selectBy, String objectReference) {  
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
			 WebElement element = driver.findElement(getSelector(selectBy, objectReference));
			 softAssert().assertNotNull(element, "Element not Found");
				if (element != null) {
					 wait.until(ExpectedConditions.invisibilityOf(element));
					return Status.PASS;
				} else
					return Status.FAIL;
		} catch (Exception e) {
			log.error(e + "Fail to search element");
			return Status.FAIL;
		}
	  
	}
	
	/**
	 * @Method - takeFullPageScreenShot
	 * @Description - to capture screenshot
	 * @return - String
	 * @author - singhje1
	 * @DateCreated - 02-11-2021
	 * @DateModified - 
	 */
	public String takeFullPageScreenShot(String projectPath, String screenShotName) {
		String path = null;
		try {
			// create a string variable which will be unique always
			String df = new SimpleDateFormat("yyyyMMddhhss").format(new Date());

			Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver); 
			ImageIO.write(screenshot.getImage(),"PNG",new File(df+".png"));

			// create File object variable which holds the screen shot reference
			File source = new File(df+".png");
			
			// store the screen shot path in path variable. Here we are storing the screenshots under screenshot path
			path = TestData.getConfigData("SCREENSHOT_PATH") + projectPath + "\\" + screenShotName + df + ".png";


			// create another File object variable which points(refer) to the above stored path variable
			File destination = new File(path);

			// use FileUtils class method to save the screen shot at desired path
			FileUtils.copyFile(source, destination);
			
			/*remove source image*/
			source.delete();
			/*remove image block end*/

			// return the path where the screen shot is saved
			return path;
		} catch (Exception e) {
			log.error(e + "Fail");
		}
		return path;
	}
	
	/**
	 * @Method - compareImages
	 * @Description - To compareImages
	 * @return - status
	 * @author - singhje1
	 * @DateCreated - 02-11-2021
	 * @DateModified - ?
	 */
	public Status compareImages(String filePathFirstImage, String filePathSecondImage) {
		ImageDiffer imgDiff = new ImageDiffer(); 
		ImageDiff diff;
		try {
			BufferedImage firstImage = ImageIO.read(new File(filePathFirstImage)); 
			BufferedImage secondImage = ImageIO.read(new File(filePathSecondImage));
			diff = imgDiff.makeDiff(firstImage,secondImage);
			if(diff.hasDiff()) {
				return Status.FAIL;
			} else {
				return Status.PASS;
			}
		} catch (IOException e) {
			return Status.FAIL;
		}
	}
}