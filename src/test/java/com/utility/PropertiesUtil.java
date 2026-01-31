package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constants.Env;

public class PropertiesUtil {

	public static String readProperty(Env env, String propertyName){
		
		File propFile=new File(System.getProperty("user.dir")+"\\config\\"+env+".properties");
		FileReader fileReader = null;
		Properties properties=new Properties();
		try {
			fileReader = new FileReader(propFile);
			properties.load(fileReader);
		} catch (FileNotFoundException e) {
	
			System.err.println("Property File not found");
		}
		 catch (IOException e) {
			
			 System.err.println("Property not found in the property file");
		}
		return properties.getProperty(propertyName.toUpperCase());	
		
	}

}
