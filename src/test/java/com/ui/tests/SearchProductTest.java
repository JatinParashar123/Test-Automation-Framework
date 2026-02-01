package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ui.pages.MyAccountPage;

public class SearchProductTest extends TestBase{
	private static final String SEARCH_TERM="Printed Summer Dress";
	private MyAccountPage myAccountPage;
	
	@BeforeMethod(description="Valid user logs into  the application")
	public void setup() {
		myAccountPage=homePage.goToLoginPage().doLoginWith("test@gmail.com", "Test1");
	}
	
	@Test(description="Verify if the logged in user is able to search a product and correct searched products are displayed" , groups= {"e2e","smoke","sanity"})
	public void verifyProductsSearch() {
		boolean actualResult=myAccountPage.searchForAProduct(SEARCH_TERM).isSearchedtermPresentInProductList(SEARCH_TERM);
		Assert.assertEquals(actualResult, true);
	}

}
