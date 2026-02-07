package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ui.pages.MyAccountPage;
import com.ui.pojos.AddressPOJO;
import com.utility.FakeAddressUtility;

public class AddNewFirstAddressTest extends TestBase{
	private MyAccountPage myAccountPage;
	private AddressPOJO address;
	@BeforeMethod(description="Valid first time user logs into the appliaction")
	public void setup() {
		myAccountPage=homePage.goToLoginPage().doLoginWith("test@gmail.com", "Test1");
		address=FakeAddressUtility.getFakeAddress();
	}
	
	@Test
	public void addNewAddress() {
		String newAddress=myAccountPage.goToAddNewAddressPage().saveAddress(address);
		Assert.assertEquals(newAddress, address.getAddresTitle().toUpperCase());
	}
	
}
