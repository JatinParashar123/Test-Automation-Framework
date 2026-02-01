package com.ui.listener;

import org.apache.logging.log4j.util.PropertiesUtil;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtility;

public class MyRetryAnalyser implements IRetryAnalyzer{

//	private static final int MAX_NUMBER_OF_ATTEMPS=Integer.parseInt(PropertiesUtil.readProperty(Env.DEV,"MAX_NUMBER_OF_ATTEMPS"));
	
	// if you want total retries for all tests  
	private static final int MAX_NUMBER_OF_ATTEMPS=JSONUtility.readJson(Env.QA).getMAX_NUMBER_OF_ATTEMPS();
	private static int currentAttempt=1;
	
	
	// if you want max number of atempts per test static is removed inder to create new instance per test
//	private  int currentAttempt=1;  
//	private final int MAX_NUMBER_OF_ATTEMPS=JSONUtility.readJson(Env.QA).getMAX_NUMBER_OF_ATTEMPS();
	
	
	
	@Override
	public boolean retry(ITestResult result) {
		if(currentAttempt<=MAX_NUMBER_OF_ATTEMPS) {
			currentAttempt++;
			return true;//means ready for re execution
		}
		return false;
	}

}