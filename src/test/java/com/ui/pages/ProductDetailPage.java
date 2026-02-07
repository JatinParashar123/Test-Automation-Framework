package com.ui.pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.utility.BrowserUtility;

public class ProductDetailPage extends BrowserUtility {
	
	private static final By SIZE_DROPDOWN_LOCATOR=By.xpath("//select[contains(@id,'group_1')]");
	private static final By ADD_TO_CART_BUTTON_LOCATOR=By.xpath("//span[contains(text(),'Add to cart')]");
	private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR=By.xpath("//span[contains(text(),'Proceed to checkout')]");

	public ProductDetailPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	public ProductDetailPage changeSize() {
		selectFromDropdownByIndex(SIZE_DROPDOWN_LOCATOR,String.valueOf(
				new Random().nextInt(3)
				));
		return new ProductDetailPage(getDriver());
	}
	
	public ProductDetailPage addToCart() {
		clickOn(ADD_TO_CART_BUTTON_LOCATOR);
		return new ProductDetailPage(getDriver());
	}
	
	public ShoppingCartPage proceedToCheckout() {
		clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
		return new ShoppingCartPage(getDriver());
	}

}
