package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constants.Browser;

public abstract class BrowserUtility {
	WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
	Logger logger=LoggerUtility.getLogger(this.getClass());
	
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();
	
	public BrowserUtility(WebDriver driver) { 
		super();
		this.driver.set(driver);
	}
	
	public BrowserUtility(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
		}
		else {
			System.err.println("Invalid browser name !! Please select chrome and edge only");
		}
	}
	
	
	
	public BrowserUtility(String browserName, boolean isHeadless) {
		if(browserName.equalsIgnoreCase("chrome")) {
			if(isHeadless) {
				ChromeOptions options=new ChromeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
			}
			else {
				driver.set(new ChromeDriver());
			}
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			if(isHeadless) {
				EdgeOptions options=new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
			}
			else {
				driver.set(new EdgeDriver());
			}
		}
		else {
			System.err.println("Invalid browser name !! Please select chrome and edge only");
		}
	}
	
	public BrowserUtility(Browser browserName) {
		if(browserName==Browser.CHROME) {
			driver.set(new ChromeDriver());
		}
		else if(browserName==Browser.EDGE) {
			driver.set(new EdgeDriver());
		}
		else if(browserName==Browser.FIREFOX){
			driver.set(new FirefoxDriver());
		}
		else {
			System.err.println("Invalid browser name !! Please select chrome and edge only");
		}
	}
	
	
	public BrowserUtility(Browser browserName, boolean isHeadless) {
		if(browserName==Browser.CHROME) {
			if(isHeadless) {
				ChromeOptions options=new ChromeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--window-size=1920,1080");

				driver.set(new ChromeDriver(options));
			}
			else {
				driver.set(new ChromeDriver());
			}
		}
		else if(browserName==Browser.EDGE) {
			if(isHeadless) {
				EdgeOptions options=new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
			}
			else {
				driver.set(new EdgeDriver());
			}
		}
		else if(browserName==Browser.FIREFOX){
			if(isHeadless) {
				FirefoxOptions options=new FirefoxOptions();
				options.addArguments("--headless=old");
				driver.set(new FirefoxDriver(options));
			}
			else {
				driver.set(new FirefoxDriver());
			}
		}
		else {
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
		logger.info("Maximising thebrowser window");
		((WebDriver) driver).manage().window().maximize();
	}
	
	public void clickOn(By locator) {
		logger.info("Finding the element with the locator "+ locator);
		WebElement element=driver.get().findElement(locator);
		logger.info("Element found and clicker");
		element.click();
	}
	
	public void enterText(By locator, String data) {
		logger.info("Finding the element with the locator "+ locator);
		WebElement element=driver.get().findElement(locator);
		logger.info("Entering the text "+ data);
		element.sendKeys(data);
	}
	
	public String getVisibleText(By locator) {
		logger.info("Finding the element with the locator "+ locator);
		
		WebElement element= wait.until(
		    ExpectedConditions.visibilityOfElementLocated(locator)
		);
//		WebElement element=driver.get().findElement(locator);
		logger.info("Text is found "+ element.getText());
		return element.getText();
	}
	
	public String takeScreenShot(String name) {
		TakesScreenshot screenshot=(TakesScreenshot)driver.get();
		File screenshotData=screenshot.getScreenshotAs(OutputType.FILE);
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("HH-mm-ss");
		String timeStamp=format.format(date);
		String path="./screenshots/"+ name +" - "+timeStamp+".png";
		File screenshotFile=new File(path);
		
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
 