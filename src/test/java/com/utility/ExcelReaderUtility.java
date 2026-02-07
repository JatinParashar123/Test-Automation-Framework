package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtility {

    /**
     * Reads any Excel sheet and returns data as
     * List<Map<ColumnHeader, CellValue>>
     */
    public static List<Map<String, String>> readSheetData(
            String fileName,
            String sheetName
    ) {

        List<Map<String, String>> sheetData = new ArrayList<>();

        File file = new File(
                System.getProperty("user.dir") + "/testData/" + fileName
        );

        DataFormatter formatter = new DataFormatter();

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            Iterator<Row> rowIterator = sheet.iterator();

            // Read header row
            Row headerRow = rowIterator.next();
            List<String> headers = new ArrayList<>();

            for (Cell cell : headerRow) {
                headers.add(formatter.formatCellValue(cell));
            }

            // Read data rows
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Map<String, String> rowData = new LinkedHashMap<>();

                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = row.getCell(
                            i,
                            Row.MissingCellPolicy.CREATE_NULL_AS_BLANK
                    );

                    rowData.put(
                            headers.get(i),
                            formatter.formatCellValue(cell)
                    );
                }

                sheetData.add(rowData);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel file", e);
        }

        return sheetData;
    }
}

//package com.utility;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import com.ui.pojos.User;
//
//public class ExcelReaderUtility {
//
//	public static Iterator<User> readExcelFile(String filename) {
//		File xlsxFile=new File(System.getProperty("user.dir")+"//testData//"+filename);
//		XSSFWorkbook xssfWorkbook=null;
//		List<User> userList = null;
//		Row row;
//		Cell emailAddress;
//		Cell password;
//		User user ;
//		Iterator<Row>rowIterator;
//		XSSFSheet xssfSheet;
//		try {
//			xssfWorkbook=new XSSFWorkbook(xlsxFile);
//			userList=new ArrayList<User>();
//			xssfSheet=xssfWorkbook.getSheet("LoginTestData");
//			
//			rowIterator=xssfSheet.iterator();
//			rowIterator.next();//to skip column name
//			
//			while(rowIterator.hasNext()) {
//				row=rowIterator.next();
//				emailAddress=row.getCell(0);
//				password=row.getCell(1);
//				user =new User(emailAddress.toString(),password.toString());
//				userList.add(user);
//				xssfWorkbook.close();
//			}
//		} catch (InvalidFormatException  e) {
//			e.printStackTrace();
//		}
//		 catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return userList.iterator();
//	}
//	
//}
