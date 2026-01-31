package com.ui.tests;

import static com.constants.Browser.CHROME;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {
	protected HomePage homePage;
	private boolean isLambdaTest;
	
	Logger logger=LoggerUtility.getLogger(this.getClass());
	
	@Parameters({"browser", "isLambdaTest", "isHeadless"})
	@BeforeMethod(description="Loads the Home page of the application")
	public void setUp(@Optional("chrome") String browser, ITestResult result, @Optional("false") boolean isLambdaTest, @Optional("false") boolean isHeadless) {

		WebDriver lambdaDriver;
		
		this.isLambdaTest=isLambdaTest; // as islambdaTest instance variable is also present in the teardown method also
		if(isLambdaTest) {
			lambdaDriver=LambdaTestUtility.initializeLambdaTestSession(browser,result.getMethod().getMethodName());
			homePage=new HomePage(lambdaDriver);
		}
		else {
			//Running test in local machine
			logger.info("Loads the home page of the application");
			homePage=new HomePage(Browser.valueOf(browser.toUpperCase()),isHeadless);
		}
	}
	
	public BrowserUtility getInstance() {
		return homePage;
	}
	
	@AfterMethod(description="Tear down the browser")
	public void tearDown() {
		if(isLambdaTest) {
			LambdaTestUtility.quitSession();
		}
	}
}
