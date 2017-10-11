package com.selenium.reusableMethods;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author HarshA
 * 
 * this class contains all excel methods 
 */
public class XlsReader {

	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row =null;
	private XSSFCell cell = null;
	private FileInputStream fis;
	private FileOutputStream fos;

	/**
	 * Constructor to set values for excel used further in the scripts 
	 * 
	 * @param path
	 */
	public XlsReader(String path) {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// returns the row count in a sheet
	public int getRowCount(String sheetName){
		sheet = workbook.getSheet(sheetName); 
		int number=sheet.getLastRowNum();
		return number;
	}

	// returns the data from a cell
	public String getCellData(String sheetName,String colName,int rowNum){
		int col_Num = 0;
		String cellText = "";
		try{
			sheet = workbook.getSheet(sheetName);
			row=sheet.getRow(0);
			for(int i=0;i<row.getLastCellNum();i++){
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				{	col_Num=i;
				break;
				}
			}

			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			cell = row.getCell(col_Num);

			if(cell==null)
				cellText = "";
			else
				if(cell.getCellType()==Cell.CELL_TYPE_STRING)
					cellText = cell.getStringCellValue();
				else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
					cellText = String.valueOf((long)cell.getNumericCellValue());
				}
			return cellText;
		}
		catch(Exception e){

			e.printStackTrace();
			return "row "+rowNum+" or column "+colName +" does not exist in xls";
		}
	}

	//method to create excel 
	public void createExcel(String pathDataFile, String pathResultFile) {
		try{

			fis = new FileInputStream(pathDataFile);
			workbook = new XSSFWorkbook(fis);
			fos = new FileOutputStream(pathResultFile);
			workbook.write(fos);
			fos.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	//method to read cell with value Yes
	public List<String> testsToRun(String sheetName)
	{
		ArrayList<String> testsToRun = new ArrayList<>();
		sheet = workbook.getSheet(sheetName);
		for (Row row : sheet) {
			Cell cell = row.getCell(3);
			if(cell.getStringCellValue().trim().equalsIgnoreCase("Yes"))
			{	
				testsToRun.add(row.getCell(2).getStringCellValue().trim());
			}
		}
		return testsToRun;
	}

}