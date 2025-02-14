package com.qa.opencart.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	private static final String TEST_DATA_SHEET_PATH=".src/test/resourcesTestdata/OpenCart.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	private static Object data[][];	
	public static Object[][] getTestData(String sheetName)
	{    
		FileInputStream ip;
		try {
			ip=new FileInputStream(TEST_DATA_SHEET_PATH);
			book =WorkbookFactory.create(ip);
			sheet=book.getSheet(sheetName);
			data =new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int r=0;r<sheet.getLastRowNum();r++)
			{
				for(int c=0;c<sheet.getRow(0).getLastCellNum();c++)
				{
					data[r][c]=sheet.getRow(r+1).getCell(c).toString();
				}
				
			}
			
		}catch(FileNotFoundException e) {
		e.printStackTrace();	
		}
		catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
