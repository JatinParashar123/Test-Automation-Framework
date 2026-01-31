package com.ui.listener;

import org.apache.logging.log4j.util.PropertiesUtil;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtility;

public class MyRetryAnalyser implements IRetryAnalyzer{

//	private static final int MAX_NUMBER_OF_ATTEMPS=Integer.parseInt(PropertiesUtil.readProperty(Env.DEV,"MAX_NUMBER_OF_ATTEMPS"));
	private static final int MAX_NUMBER_OF_ATTEMPS=JSONUtility.readJson(Env.QA).getMAX_NUMBER_OF_ATTEMPS();

	private static int currentAttempt=1;
	@Override
	public boolean retry(ITestResult result) {
		if(currentAttempt<=MAX_NUMBER_OF_ATTEMPS) {
			currentAttempt++;
			return true;//means ready for re execution
		}
		return false;
	}

}