package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojos.User;

public class ExcelReaderUtility {

	public static Iterator<User> readExcelFile(String filename) {
		File xlsxFile=new File(System.getProperty("user.dir")+"//testData//"+filename);
		XSSFWorkbook xssfWorkbook=null;
		List<User> userList = null;
		Row row;
		Cell emailAddress;
		Cell password;
		User user ;
		Iterator<Row>rowIterator;
		XSSFSheet xssfSheet;
		try {
			xssfWorkbook=new XSSFWorkbook(xlsxFile);
			userList=new ArrayList<User>();
			xssfSheet=xssfWorkbook.getSheet("LoginTestData");
			
			rowIterator=xssfSheet.iterator();
			rowIterator.next();//to skip column name
			
			while(rowIterator.hasNext()) {
				row=rowIterator.next();
				emailAddress=row.getCell(0);
				password=row.getCell(1);
				user =new User(emailAddress.toString(),password.toString());
				userList.add(user);
				xssfWorkbook.close();
			}
		} catch (InvalidFormatException  e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList.iterator();
	}
	
}
