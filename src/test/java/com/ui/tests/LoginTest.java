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
public class LoginTest extends TestBase {
	
	
	

//	@Test(description="Verifies that the valid user is able to login into the application", groups= {"e2e","Sanity","Regression"},
//			dataProviderClass=com.ui.DataProviders.LoginDataProvider.class,dataProvider="LoginTestDataProvider")
//	public void loginTest(User user){
////		 WebDriver driver=new ChromeDriver();
//		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Jatin Parashar");
//	}
//	
//	@Test(description="Verifies that the valid user is able to login into the application", groups= {"e2e","Sanity","Regression"},
//			dataProviderClass=com.ui.DataProviders.LoginDataProvider.class,dataProvider="LoginTestCSVDataProvider")
//	public void loginCSVTest(User user){
////		 WebDriver driver=new ChromeDriver();
//		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Jatin Parashar");
//	}
	
	@Test(description="Verifies that the valid user is able to login into the application", groups= {"e2e","Sanity","Regression"},
			dataProviderClass=com.ui.DataProviders.LoginDataProvider.class,dataProvider="LoginTestExcelDataProvider",
			retryAnalyzer=com.ui.listener.MyRetryAnalyser.class)
	public void loginExcelTest(User user){
		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Jatin Parashar");
	}
}
