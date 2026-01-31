package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class LoginPage extends BrowserUtility {

	private final static By EMAIL_TEXT_BOX_LOCATOR=By.id("email");
	private final static By PASSOWRD_TEXT_BOX_LOCATOR=By.id("passwd");
	private final static By SUBMIT_LOGIN_BUTTON_LOCATOR=By.id("SubmitLogin");
	private final static By ERROR_MESSAGE_LOCATOR=By.xpath("//div[@class='alert alert-danger']/ol/li");
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public MyAccountPage doLoginWith(String emailId,String pass) {
		enterText(EMAIL_TEXT_BOX_LOCATOR,emailId);
		enterText(PASSOWRD_TEXT_BOX_LOCATOR,pass);
		clickOn(SUBMIT_LOGIN_BUTTON_LOCATOR);
		MyAccountPage myAccountPage=new MyAccountPage(getDriver());
		return myAccountPage;
	}

	
	public LoginPage doLoginWithInvalidCredentials(String emailId,String pass) {
		enterText(EMAIL_TEXT_BOX_LOCATOR,emailId);
		enterText(PASSOWRD_TEXT_BOX_LOCATOR,pass);
		clickOn(SUBMIT_LOGIN_BUTTON_LOCATOR);
		LoginPage loginPage=new LoginPage(getDriver());
		return loginPage;
	}
	
	public String getErrorMessage() {
		return getVisibleText(ERROR_MESSAGE_LOCATOR);
	}
}
