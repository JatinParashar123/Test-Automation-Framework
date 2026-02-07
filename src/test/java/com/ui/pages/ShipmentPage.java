package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public class ShipmentPage extends BrowserUtility {
	
	private static final By ACCEPT_TERMS_CHECKBOX_LOCATOR=By.id("cgv");

	public ShipmentPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

}
