package com.ui.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojos.AddressPOJO;
import com.utility.FakeAddressUtility;

public class AddNewAddressTest extends TestBase{
	private MyAccountPage myAccountPage;
	private AddressPage addressPage;
	private AddressPOJO address;
	@BeforeMethod(description="Valid user logs into the appliaction")
	public void setup() {
		myAccountPage=homePage.goToLoginPage().doLoginWith("test@gmail.com", "Test1");
		address=FakeAddressUtility.getFakeAddress();
	}
	
	@Test
	public void addNewAddress() {
		myAccountPage.goToAddNewAddressPage().saveAddress(address);;
	}
	
}
