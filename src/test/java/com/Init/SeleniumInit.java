package com.Init;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.fastcompany.HomeUiVerification;
import com.utility.Constants;
import com.utility.TestData;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumInit {
	protected static WebDriver driver;
	public String suiteName = "";
	public String testUrl;
	public String testName = "";
	public static String browserName = "";
	public static String osName = "";
	public String HomeDir = "";
	public static String browserVersion = "";
	public static String Url = "";
	public static String AuthorName;
	public static String ModuleName;
	public static String Version = "";

	
	
	protected HomeUiVerification homeUiVerification;
	

	@BeforeClass(alwaysRun = true, enabled = true)
	public void setUpforBrowsers(ITestContext testContext) throws IOException, InterruptedException {
		suiteName = testContext.getSuite().getName();
		System.out.println("ran");
		testUrl = TestData.getValueFromConfig("config.properties", "URL");
		browserName = testContext.getCurrentXmlTest().getParameter("browser");

		if (browserName.trim().equalsIgnoreCase(Constants.BROWSER_CHROME)) {
			//WebDriverManager.chromiumdriver().setup();
			DesiredCapabilities capability = DesiredCapabilities.chrome();
//			ChromeOptions chromeOptions = new ChromeOptions();
			
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("window-size=1400,800");
			chromeOptions.addArguments("headless");
			
			capability.setBrowserName("chrome");
			capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			capability.setCapability("disable-popup-blocking", true);
			osName = capability.getPlatform().name();
			capability.setJavascriptEnabled(true);
			osName = capability.getPlatform().name();
			browserVersion = capability.getVersion();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
			
			// threadDriver.set(new ChromeDriver(chromeOptions));

		} else if (browserName.trim().equalsIgnoreCase(Constants.BROWSER_FIREFOX)) {
			File driverpath = new File("Resource/geckodriver.exe");
			String path1 = driverpath.getAbsolutePath();
			System.setProperty("webdriver.gecko.driver", path1);
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			capability.setJavascriptEnabled(true);
			osName = System.getProperty("os.name");
			HomeDir = System.getProperty("user.home");
			capability.setCapability("marionette", true);
			driver = new FirefoxDriver(capability);
			
		} else if (browserName.trim().equalsIgnoreCase(Constants.BROWSER_IE)) {
			WebDriverManager.iedriver().setup();
			DesiredCapabilities capability = DesiredCapabilities.chrome();

			capability.setBrowserName("internet explorer");
			capability.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capability.setCapability("nativeEvents", false);
			capability.setJavascriptEnabled(true);
			osName = capability.getPlatform().name();

			// driver = new InternetExplorerDriver(capability);

		} else if (browserName.trim().equalsIgnoreCase(Constants.BROWSER_EDGE)) {
			WebDriverManager.edgedriver().setup();
			File driverpath = new File("Resource/msedge.exe");
			String path1 = driverpath.getAbsolutePath();
			// System.setProperty("webdriver.edge.driver", path1);
			driver = new EdgeDriver();

			EdgeOptions edgeOptions = new EdgeOptions();

			// threadDriver.set(new EdgeDriver(edgeOptions));

		}

		// driver = threadDriver.get();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(testUrl);

	}

	@BeforeSuite(alwaysRun = true, enabled = false)
	public void fetchSuite(ITestContext testContext) throws IOException {

		testUrl = TestData.getValueFromConfig("config.properties", "URL");
		browserName = TestData.getValueFromConfig("config.properties", "Browser");

		if (browserName.trim().equalsIgnoreCase(Constants.BROWSER_CHROME)) {
			WebDriverManager.chromiumdriver().setup();
			DesiredCapabilities capability = DesiredCapabilities.chrome();

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("window-size=1400,800");
			chromeOptions.addArguments("headless");
			
			capability.setBrowserName("chrome");
			capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			capability.setCapability("disable-popup-blocking", true);
			osName = capability.getPlatform().name();
			capability.setJavascriptEnabled(true);
			osName = capability.getPlatform().name();
			browserVersion = capability.getVersion();

			driver = new ChromeDriver();
		} else if (browserName.trim().equalsIgnoreCase(Constants.BROWSER_FIREFOX)) {
			File driverpath = new File("Resource/geckodriver.exe");
			String path1 = driverpath.getAbsolutePath();
			System.setProperty("webdriver.gecko.driver", path1);
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			capability = DesiredCapabilities.firefox();
			capability.setJavascriptEnabled(true);
			osName = System.getProperty("os.name");
			HomeDir = System.getProperty("user.home");
			capability.setCapability("marionette", true);
			driver = new FirefoxDriver(capability);
		} else if (browserName.trim().equalsIgnoreCase(Constants.BROWSER_IE)) {
			WebDriverManager.iedriver().setup();
			DesiredCapabilities capability = DesiredCapabilities.chrome();

			capability.setBrowserName("internet explorer");
			capability.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capability.setCapability("nativeEvents", false);
			capability.setJavascriptEnabled(true);
			osName = capability.getPlatform().name();

			driver = new InternetExplorerDriver(capability);
		} else if (browserName.trim().equalsIgnoreCase(Constants.BROWSER_EDGE)) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(testUrl);

	}

	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method, ITestContext testContext, ITestResult testResult)
			throws IOException, InterruptedException {

		suiteName = testContext.getSuite().getName();
		homeUiVerification = new HomeUiVerification(driver);
		

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult testResult, ITestContext testContext) {
		String screenshotName = "";
		testName = testResult.getName();

		try {
			screenshotName = Common.getCurrentTimeStampString(); // + testName;
			if (!testResult.isSuccess()) {
				System.out.println();
				System.out.println("TEST FAILED - " + testName);
				System.out.println();
				System.out.println("ERROR MESSAGE: " + testResult.getThrowable());
				System.out.println("\n");
				Reporter.setCurrentTestResult(testResult);
				if (testResult.getStatus() == ITestResult.FAILURE) {
					System.out.println("1 message from tear down");
					log("Please look to the screenshot :- " + Common.makeScreenshot(driver, screenshotName));
				}
			}
		} catch (Throwable throwable) {
			System.out.println(throwable);
		}
	}

	/**
	 * Log given message to Reporter output.
	 * 
	 * @param msg Message/Log to be reported.
	 */
	@AfterSuite(alwaysRun = true)
	public void postConfigue() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	public void log(String msg) {
		Reporter.log(msg + "</br>");
		System.out.println(msg);
	}

	public void logStep(int msg1, String msg2) {
		// Reporter.log("Step-"+msg1+" : "+msg2 + "</br>");
		Reporter.log(msg2 + "</br>");
		System.out.println("Step-" + msg1 + " : " + msg2);// for jenkins
	}

	public void logCase(String msg) {
		Reporter.log("Test Case : " + msg + "</br>");
		System.out.println("Test Case : " + msg);
	}

	public static void slog(String msg) {
		Reporter.log(msg + "</br>");
		System.out.println(msg);
	}

	public void logStatus(final int test_status, String msg) {
		switch (test_status) {
		case ITestStatus.PASSED:
			// test.log(Status.PASS,"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp "+msg+" ");
			log(msg + " <Strong><font color=#008000>Pass</font></strong>");
			break;
		case ITestStatus.FAILED:
			String screenshotName = Common.getCurrentTimeStampString();
			log(msg + " <Strong><font color=#FF0000>Fail</font></strong> -> Please look to the screenshot :- "
					+ Common.makeScreenshot(driver, screenshotName));
			// MakeScreenshots();
			break;
		case ITestStatus.SKIPPED:
			log(msg);
			break;
		default:
			break;
		}
	}

	public void MakeScreenshots() {
		String screenshotName = Common.getCurrentTimeStampString() + testName;
		Common.makeScreenshot2(driver, screenshotName);
	}
}
