package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ui.pojos.AddressPOJO;
import com.utility.BrowserUtility;

public class AddressPage extends BrowserUtility {
	private static final By COMPANY_TEXTBOX_LOCATOR=By.id("company");
	private static final By ADDRESS1_TEXTBOX_LOCATOR=By.id("address1");
	private static final By ADDRESS2_TEXTBOX_LOCATOR=By.id("address2");
	private static final By POSTCODE_TEXTBOX_LOCATOR=By.id("postcode");
	private static final By CITY_TEXTBOX_LOCATOR=By.id("city");
	private static final By COUNTRY_DROPDOWN_LOCATOR=By.id("uniform-id_country");
	private static final By VAT_NUMBER_TEXTBOX_LOCATOR=By.id("vat-number");
	private static final By HOME_PHONE_TEXTBOX_LOCATOR=By.id("phone");
	private static final By MOBILE_PHONE_TEXTBOX_LOCATOR=By.id("phone_mobile");
	private static final By STATE_DROPDOWN_LOCATOR=By.xpath("//select[contains(@id,'state')]");
	private static final By ADDITIONAL_INFORMATION_PHONE_TEXTAREA_LOCATOR=By.id("other");
	private static final By ADDRESS_TITLE_TEXTBOX_LOCATOR=By.id("alias");
	private static final By SAVE_BUTTON_LOCATOR=By.xpath("//span[contains(text(),'Save')]");
	private static final By ADDRESS_HEADIING=By.tagName("h3");
	

	public AddressPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	public String saveAddress(AddressPOJO addressPOJO) {
		enterText(COMPANY_TEXTBOX_LOCATOR,addressPOJO.getCompany());
		enterText(ADDRESS1_TEXTBOX_LOCATOR,addressPOJO.getAddressLine1());
		enterText(ADDRESS2_TEXTBOX_LOCATOR,addressPOJO.getAddressLine2());
		enterText(POSTCODE_TEXTBOX_LOCATOR,addressPOJO.getPostCode());
		enterText(CITY_TEXTBOX_LOCATOR,addressPOJO.getCity());
		enterText(HOME_PHONE_TEXTBOX_LOCATOR,addressPOJO.getHomePhoneNumber());
		enterText(MOBILE_PHONE_TEXTBOX_LOCATOR,addressPOJO.getMobileNumber());
		enterText(ADDITIONAL_INFORMATION_PHONE_TEXTAREA_LOCATOR,addressPOJO.getOtherinformation());
		clearText(ADDRESS_TITLE_TEXTBOX_LOCATOR);
		enterText(ADDRESS_TITLE_TEXTBOX_LOCATOR,addressPOJO.getAddresTitle());
		enterText(VAT_NUMBER_TEXTBOX_LOCATOR,addressPOJO.getVatNumber());
//		selectFromDropdown(STATE_DROPDOWN_LOCATOR,addressPOJO.getState());
		selectFromDropdownByIndex(STATE_DROPDOWN_LOCATOR,addressPOJO.getState());
		clickOn(SAVE_BUTTON_LOCATOR);
		String newAddress=getVisibleText(ADDRESS_HEADIING);
		return newAddress;
		
	}
	

}
