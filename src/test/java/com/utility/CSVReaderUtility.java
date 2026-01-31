package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojos.User;

public class CSVReaderUtility {

	public static Iterator<User> readCSVFile(String fileName) {
		File csvFile=new File(System.getProperty("user.dir")+"//testData//"+fileName);
		FileReader fileReader=null;
		CSVReader csvReader;
		String [] dataRow;
		List<User> userList=null;
		
		try {
			fileReader=new FileReader(csvFile);
			csvReader=new CSVReader(fileReader);
			csvReader.readNext();// in first it reads the header or column name
//			data=csvReader.readNext();//row 1 removed as it reads only row
			
			userList=new ArrayList<User>();// so we can return iterator
			User user;
			while((dataRow=csvReader.readNext())!=null) {
				user =new User(dataRow[0],dataRow[1]);
				userList.add(user);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 catch (CsvValidationException | IOException e) {
			
			e.printStackTrace();
		}
		return userList.iterator();
	}

}
