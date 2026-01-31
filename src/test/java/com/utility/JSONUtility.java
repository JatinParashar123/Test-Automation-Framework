package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.constants.Env;
import com.google.gson.Gson;
import com.ui.pojos.Config;
import com.ui.pojos.Environment;

public class JSONUtility {
	
	public static Environment readJson(Env env) {
		Gson gson=new Gson();
		FileReader fileReader=null;
		File jsonFile=new File(System.getProperty("user.dir")+"\\config\\config.json");
		
		try {
			fileReader=new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Config config=gson.fromJson(fileReader, Config.class);
		System.out.println(config.getEnvironments().get(env.name()).getUrl());
		Environment environment=config.getEnvironments().get(env.name());
		return environment;
		
	}
	
}
