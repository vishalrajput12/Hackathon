package BaseClasses;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import PageClasses.LandingPage;
import Utilities.DateUtil;
import Utilities.ExtentReportManager;

public class PageBaseClass {

	public static WebDriver driver;
	public static ExtentReports report = ExtentReportManager.getReportInstance();
	public static ExtentTest logger;

	public void invokeBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver","D:\\Download Folder\\Cognizant Doc's\\Hackathon\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
               reportPass("Browser Opened Succeessfully..");
          
			}
			else if(browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\geckodriver.exe");
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vishal Rajput\\Downloads\\Hackathon\\src\\test\\resources\\drivers\\geckodriver.exe");

				driver = new FirefoxDriver();
               reportPass(" firefox Browser Opened Succeessfully..");
			}
		} catch (Exception e) {
			 reportFail(e.getMessage());
			System.out.println(e.getMessage());
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	// OpenWebsite
	public LandingPage OpenApplication() {
		try{
		 logger.log(Status.INFO, "opening website");
		driver.get("https://emicalculator.net/");
		reportPass("Website opened successfully");
		}catch(Exception e){
			reportFail("Website invalid");
		}
		
		return PageFactory.initElements(driver, LandingPage.class);
	}
	
	public void sleep() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// TestFAil
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenshot();
		Assert.fail(reportString);
		
	}

	// TestPass
	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
		takeScreenshot();

	}

	// Take Screenshot
	public void takeScreenshot() {
		TakesScreenshot takess = (TakesScreenshot) driver;
		File srcfile = takess.getScreenshotAs(OutputType.FILE);
		File destfile = new File(System.getProperty("user.dir") + "//screenshots//" + DateUtil.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(srcfile, destfile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "//screenshots//" + DateUtil.getTimeStamp() + ".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	@AfterTest
	public void flush() {
		report.flush();
	}

	@AfterMethod
	public void tearDown(){
		driver.close();
	}

	@AfterSuite
	public void Quit() {
		driver.quit();
	}


}
