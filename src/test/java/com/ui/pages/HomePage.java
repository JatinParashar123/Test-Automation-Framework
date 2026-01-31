package com.ui.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;


public final  class HomePage extends BrowserUtility{ //to use the browser utility methods without creating object
	private static final By SIGN_IN_LINK_LOCATOR=By.xpath("//a[contains(text(),'Sign in')]");
	
	public HomePage(Browser browser,boolean isHeadless) {
		super(browser,isHeadless);//call the parent constructor
		goToWebsite(JSONUtility.readJson(QA).getUrl());	
	}
	
	
	
	public HomePage(WebDriver driver) {
		super(driver);//call the parent constructor
		goToWebsite(JSONUtility.readJson(QA).getUrl());	
	}
	 
	
	public LoginPage goToLoginPage() {
		 clickOn(SIGN_IN_LINK_LOCATOR);
		 LoginPage loginPage=new LoginPage(getDriver());
		 return loginPage;
	}
}
