package com.ui.listener;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentreporterUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener{
	Logger logger=LoggerUtility.getLogger(this.getClass());
	ExtentReports extentReports;
	ExtentTest extentTest;
	ExtentSparkReporter extentSparkReporter;
	
	public void onTestStart(ITestResult result) {
	    logger.info(result.getMethod().getMethodName());
	    logger.info(result.getMethod().getDescription());
	    logger.info(Arrays.toString(result.getMethod().getGroups())); 
	    ExtentreporterUtility.createExtenttest(result.getMethod().getMethodName());
	  }
	
	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName()+" PASSED");
		ExtentreporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName()+" PASSED");
	  }
	
	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName()+" FAILED");
		logger.error(result.getThrowable().getMessage());
		ExtentreporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName()+" Failed");
		ExtentreporterUtility.getTest().log(Status.FAIL,result.getThrowable().getMessage());
		
		Object testClass=result.getInstance();
		
		BrowserUtility browserUtility=((TestBase)testClass).getInstance();
		logger.info("Capturing screenshots for the failed test");
		
		String screenshotPath=browserUtility.takeScreenShot(result.getMethod().getMethodName());
		logger.info("Attaching the screenshot to the HTML file");
		ExtentreporterUtility.getTest().addScreenCaptureFromPath(screenshotPath);
		
	  }
	
	public void onTestSkipped(ITestResult result) {
	    logger.warn(result.getMethod().getMethodName()+" SKIPPED");
	    ExtentreporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName()+" SKIPPED");
	  }
	
	public void onStart(ITestContext context) {
	    logger.info("Test suite started");
	    ExtentreporterUtility.setupSparkReporter("report.html");
	  }
	
	public void onFinish(ITestContext context) {
		logger.info("Test suite completed");
		ExtentreporterUtility.flushReport();
	  }
}
