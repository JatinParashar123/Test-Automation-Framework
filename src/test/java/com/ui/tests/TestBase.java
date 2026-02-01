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

    //	Logger logger=LoggerUtility.getLogger(this.getClass());   //this does not work for static class so we added stackwalker class so it will work with static classes as well
	private static final Logger logger = LoggerUtility.getLogger();

    @Parameters({"browser", "isLambdaTest", "isHeadless"})
    @BeforeMethod(description = "Loads the Home page of the application")
    public void setUp(
            @Optional("chrome") String browser,
            ITestResult result,
            @Optional("false") String isLambdaTest,
            @Optional("false") String isHeadless) {

        // ✅ System property override (CI-friendly)
        String finalBrowser = System.getProperty("browser", browser);
        boolean finalIsLambdaTest = Boolean.parseBoolean(
                System.getProperty("isLambdaTest", isLambdaTest)
        );
        boolean finalIsHeadless = Boolean.parseBoolean(
                System.getProperty("isHeadless", isHeadless)
        );

        this.isLambdaTest = finalIsLambdaTest;

        WebDriver driver;

        if (finalIsLambdaTest) {
            driver = LambdaTestUtility.initializeLambdaTestSession(
                    finalBrowser,
                    result.getMethod().getMethodName()
            );
            homePage = new HomePage(driver);
        } else {
            logger.info("Loads the home page of the application");

            Browser browserType;
            try {
                browserType = Browser.valueOf(finalBrowser.trim().toUpperCase());
            } catch (Exception e) {
                browserType = CHROME; // safe fallback
            }

            homePage = new HomePage(browserType, finalIsHeadless);
        }
    }

    public BrowserUtility getInstance() {
        return homePage;
    }

    @AfterMethod(description = "Tear down the browser")
    public void tearDown() {
        if (isLambdaTest) {
            LambdaTestUtility.quitSession();
        }
    }
}