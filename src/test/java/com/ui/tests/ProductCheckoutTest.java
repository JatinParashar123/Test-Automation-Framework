package com.ui.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ui.pages.SearchResultPage;

public class ProductCheckoutTest extends TestBase{
	
	private static final String SEARCH_PRODUCT="Printed Summer dress";
	
	private SearchResultPage searchResultPage;
	
	@BeforeMethod(description="User logs into the applcation and searches for a product")
	public void setup() {
		searchResultPage=homePage.goToLoginPage().doLoginWith("test@gmail.com", "Test1").searchForAProduct(SEARCH_PRODUCT);
	}

	@Test(description="Verify if the logged in user is able to buy a dress", groups= {"e2e","smoke","sanity"})
	public void checkoutTest() {
		searchResultPage.clickOnTheProductAtIndex(0).changeSize().addToCart().proceedToCheckout().goToConfirmAddressPage().goToShipmentPage();
	}
}
