package testcase;

import java.util.Hashtable;
//import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import BaseClasses.PageBaseClass;

import PageClasses.LandingPage;
import Utilities.TestDataProvider;

public class Tests extends PageBaseClass {
	LandingPage landingpage;

	@Test(dataProvider = "getTestData", priority = 1,description="Test to check all fields are filled or not")
	public void emiCalculator(Hashtable<String, String> testData) {
		logger = report.createTest("Emi Calculator " + testData.get("Comments"));
		PageBaseClass pageBase = new PageBaseClass();
		logger.log(Status.INFO, "Opening Browser");
		invokeBrowser("Chrome");
		landingpage = pageBase.OpenApplication();
		landingpage.clickCarloan();
		sleep();
		landingpage.enterDetails(testData.get("Loanamount"), testData.get("Loaninterest"), testData.get("Tenure"));
		if (testData.get("Loanamount") == "" || testData.get("Loaninterest") == "" || testData.get("Tenure") == "") {
			reportFail("Incomplete Data");
			
		} else {
			landingpage.result();
			reportPass("All fields are filled");
			
		}

	}

	@Test(dataProvider = "getTestData2", priority = 2, description="Test to check the field amounts as per the problem statement")
	public void DataCheck(Hashtable<String, String> testData) {
		logger = report.createTest("DataCheck " + testData.get("Comments"));
		PageBaseClass pageBase = new PageBaseClass();
		logger.log(Status.INFO, "Opening Browser");
		invokeBrowser("chrome");
		landingpage = pageBase.OpenApplication();
		sleep();
		landingpage.clickCarloan();
		landingpage.enterDetails(testData.get("Loanamount"), testData.get("Loaninterest"), testData.get("Tenure"));

		if (!testData.get("Loanamount").equalsIgnoreCase("1500000.0")) {
			reportFail("Wrong Amount Entered!!!");
		
		} else if (!testData.get("Loaninterest").equalsIgnoreCase("9.5")) {
			reportFail("Wrong Interest Entered!!!");
		
		} else if (!testData.get("Tenure").equalsIgnoreCase("1.0")) {
			reportFail("Wrong Tanure Entered!!!");
		
		} else {
			landingpage.result();
			reportPass("Correct Data Entered");
		}

	}

	@DataProvider
	public Object[][] getTestData() {
		return TestDataProvider.getTestData("hackathon.xlsx", "Data", "emiCalculator");

	}

	@DataProvider
	public Object[][] getTestData2() {
		return TestDataProvider.getTestData("hackathon.xlsx", "Data", "DataCheck");

	}

}