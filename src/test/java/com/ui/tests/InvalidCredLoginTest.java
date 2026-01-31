package com.ui.tests;
import static com.constants.Browser.*;  

import static org.testng.Assert.*;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojos.User;
import com.utility.LoggerUtility;

@Listeners({com.ui.listener.TestListener.class})
public class InvalidCredLoginTest extends TestBase {
	private static final String INVALID_EMAIL_ADDRESS="test1@gmail.com";
	private static final String INVALID_PASSWORD="Kuch bhi";

	@Test(description="Verifies if proper error message is shown for the user when they enter invalid credential", groups= {"e2e","Sanity"},
			retryAnalyzer=com.ui.listener.MyRetryAnalyser.class)
	public void loginTest(){
		assertEquals(homePage.goToLoginPage().doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS, INVALID_PASSWORD).getErrorMessage(),"Authentication failed.");
	}
}
