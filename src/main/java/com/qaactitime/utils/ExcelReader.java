package com.qaactitime.utils;
import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	XSSFWorkbook wb;
	
	public ExcelReader(String excelPath) {
	try {
		File src = new File(excelPath);
		FileInputStream fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
	} catch (Exception e) {
		System.out.println("Excel sheet not found in the path " + e.getMessage());
	}
}

public int getRowCount(int sheetnum) {
	return wb.getSheetAt(sheetnum).getLastRowNum() + 1;
}

public String getCellData(int sheetnum, int row, int col) {
	return wb.getSheetAt(sheetnum).getRow(row).getCell(col).toString();
}

}
