package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constants.Browser;

public abstract class BrowserUtility {
	WebDriverWait wait;
//	Logger logger=LoggerUtility.getLogger(this.getClass());   //this does not work for static class so we added stackwalker class so it will work with static classes as well
	private static final Logger logger = LoggerUtility.getLogger();

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
	}

	public BrowserUtility(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else {
			System.err.println("Invalid browser name !! Please select chrome and edge only");
		}
	}

	public BrowserUtility(String browserName, boolean isHeadless) {
		if (browserName.equalsIgnoreCase("chrome")) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new ChromeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}
		} else if (browserName.equalsIgnoreCase("edge")) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new EdgeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}
		} else {
			System.err.println("Invalid browser name !! Please select chrome and edge only");
		}
	}

	public BrowserUtility(Browser browserName) {
		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else {
			System.err.println("Invalid browser name !! Please select chrome and edge only");
		}
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--window-size=1920,1080");

				driver.set(new ChromeDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new ChromeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}
		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new EdgeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}
		} else if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");
				driver.set(new FirefoxDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new FirefoxDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}
		} else {
			System.err.println("Invalid browser name !! Please select chrome and edge only");
		}
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void goToWebsite(String url) {
		logger.info("Visiting the URL");
		driver.get().get(url);

	}

	public void maximiseWindow() {
		logger.info("Maximising the browser window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding the element with the locator " + locator);
//		WebElement element = driver.get().findElement(locator);
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locator));
		logger.info("Element found and clicked");
		element.click();
	}

	public void clickOn(WebElement element) {
		logger.info("Element found and clicked");
		element.click();
	}

	public void clickOnCheckbox(By locator) {
		logger.info("Finding the element with the locator " + locator);
//		WebElement element = driver.get().findElement(locator);
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("Element found and clicked");
		element.click();
	}
	
	
	public void enterText(By locator, String data) {
		logger.info("Finding the element with the locator " + locator);
//		WebElement element = driver.get().findElement(locator);
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		logger.info("Entering the text " + data);
		element.sendKeys(data);
	}

	public void clearText(By locator) {
		logger.info("Finding the element with the locator " + locator);
//		WebElement element = driver.get().findElement(locator);
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		logger.info("Clearing the data");
		element.clear();
	}

	public void enterSpecialKey(By locator, Keys keyToEnter) {
		logger.info("Finding the element with the locator " + locator);
//		WebElement element = driver.get().findElement(locator);
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		logger.info("Element found and now enter the special key:  " + keyToEnter);
		element.sendKeys(keyToEnter);
	}

	public String getVisibleText(By locator) {
		logger.info("Finding the element with the locator " + locator);

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//		WebElement element=driver.get().findElement(locator);
		logger.info("Text is found " + element.getText());
		return element.getText();
	}

	public void selectFromDropdown(By dropDownLocator, String optionToSelect) {

	    logger.info("Waiting for dropdown to be visible: " + dropDownLocator);

	    WebElement element = wait.until(
	    		ExpectedConditions.presenceOfElementLocated(dropDownLocator)
	    );

	    logger.info("Selecting option: " + optionToSelect);

	    Select select = new Select(element);
	    for (WebElement option : select.getOptions()) {
	        System.out.println("TEXT=[" + option.getText() + "] LENGTH=" + option.getText().length());
	    }
	    select.selectByVisibleText(optionToSelect);
	}
	

	public void selectFromDropdownByIndex(By dropDownLocator, String idx) {
		logger.info("Parsing string index value into int");
		int index;
		try {
			index = Integer.parseInt(idx);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid dropdown index: " + idx, e);
		}

		logger.info("Waiting for dropdown to be visible: " + dropDownLocator);

		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(dropDownLocator));

		logger.info("Selecting option: " + index);

		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public String getVisibleText(WebElement element) {
		logger.info("Returning the visible text " + element.getText());
		return element.getText();
	}

	public List<String> getAllVisibleTexts(By locator) {
		logger.info("Finding all the elements with the locator " + locator);

		List<WebElement> elementList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		List<String> visibleTextList = new ArrayList<String>();
//		WebElement element=driver.get().findElement(locator);
		logger.info("Element and now returning the list ");
		for (WebElement e : elementList) {
			visibleTextList.add(getVisibleText(e));
		}
		System.out.println(visibleTextList);
		return visibleTextList;
	}

	public List<WebElement> getAllElements(By locator) {
		logger.info("Finding all the elements with the locator " + locator);

		List<WebElement> elementList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

		return elementList;
	}

	public String takeScreenShot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		String path = "./screenshots/" + name + " - " + timeStamp + ".png";
		File screenshotFile = new File(path);

		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
