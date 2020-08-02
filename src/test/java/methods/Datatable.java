package methods;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import driver.DriverScript;

public class Datatable extends DriverScript{
	/*******************************************************
	 * Method Name			: getExcelTestData
	 * Purpose				: It is used to read the data from excel file using logical name
	 * Return Type			: String
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  Map<String, String> objData = getExcelTestData("SheetName", "LogicalName")
	 ******************************************************
	 */
	public Map<String, String> getExcelTestData(String sheetName, String logicalName)
	{
		FileInputStream fin = null;
		Workbook wb = null;
		Sheet sh = null;
		Row row1 = null;
		Row row2 = null;
		Cell cell1 = null;
		Cell cell2 = null;
		Map<String, String> objData = null;
		int rowNum = 0;
		String strKey = null;
		String strValue = null;
		String sDay = null;
		String sMonth = null;
		String sYear = null;
		try {
			objData = new HashMap<String, String>();
			fin = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\"+moduleName+".xlsx");
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(sheetName);
			
			if(sh==null) {
				reports.WriteResult(null, "Fail", "Failed to find the sheet '"+sheetName+"'. Exiting from the method", test, false);
				return null;
			}else {
				reports.WriteResult(null, "Pass", "The sheet '"+sheetName+"' was found successful", test, false);
			}
			
			//Find the given logical name is present & its row number
			int rows = sh.getPhysicalNumberOfRows();
			for(int r=0; r<rows; r++)
			{
				row1 = sh.getRow(r);
				cell1 = row1.getCell(0);
				if(cell1.getStringCellValue().equals(logicalName)) {
					rowNum = r;
					break;
				}
			}
			
			if(rowNum>0) {
				reports.WriteResult(null, "Pass", "Reading the TestData from '"+sheetName+"' & storing in Map", test, false);
				//Header row
				row1 = sh.getRow(0);
				
				//logicalName  row
				row2 = sh.getRow(rowNum);
				
				for(int c=0; c<row1.getPhysicalNumberOfCells(); c++)
				{
					cell1 = row1.getCell(c);
					cell2 = row2.getCell(c);
					strKey = cell1.getStringCellValue();
					
					//Format the cell values
					if(cell2==null || cell2.getCellType()==CellType.BLANK) {
						strValue = "";
					}
					else if(cell2.getCellType()==CellType.BOOLEAN)
					{
						strValue = String.valueOf(cell2.getBooleanCellValue());
					}
					else if(cell2.getCellType()==CellType.STRING)
					{
						strValue = cell2.getStringCellValue();
					}
					else if(cell2.getCellType()==CellType.NUMERIC)
					{
						if(HSSFDateUtil.isCellDateFormatted(cell2)) {
							double dt = cell2.getNumericCellValue();
							Calendar cal = Calendar.getInstance();
							cal.setTime(HSSFDateUtil.getJavaDate(dt));
							
							//If day is <10 then prefix with zero
							if(cal.get(Calendar.DAY_OF_MONTH)<10) {
								sDay = "0"+cal.get(Calendar.DAY_OF_MONTH);
							}else {
								sDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
							}
							
							
							//If month is <10 then prefix with zero
							if((cal.get(Calendar.MONTH)+1)<10) {
								sMonth = "0"+(cal.get(Calendar.MONTH)+1);
							}else {
								sMonth = String.valueOf((cal.get(Calendar.MONTH)+1));
							}
							
							sYear = String.valueOf(cal.get(Calendar.YEAR));
							
							strValue = sDay+"/"+sMonth+"/"+sYear;
						}else {
							strValue = String.valueOf(cell2.getNumericCellValue());
						}
					}
					
					
					objData.put(strKey, strValue);
				}
			}else {
				reports.WriteResult(null, "Fail", "Fail to fnd the given logical name '"+logicalName+"'", test, false);
				return null;
			}
			
			return objData;
		}catch(Exception e)
		{
			reports.WriteResult(null, "Exception", "Exception in 'getExcelTestData()' method."+e.getMessage(), test, false);
			return null;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				cell1 = null;
				cell2 = null;
				row1 = null;
				row2 = null;
				sh = null;
				wb.close();
				wb = null;
			}catch(Exception e)
			{
				reports.WriteResult(null, "Exception", "Exception in 'getExcelTestData()' method."+e.getMessage(), test, false);
				return null;
			}
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: getRowCount
	 * Purpose				: It is used to get the rowCount from the given excel file
	 * Return Type			: int
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  int rowCount = getRowCount(excelFile, SheetName);
	 ******************************************************
	 */
	public int getRowCount(String filePath, String sheetName) {
		FileInputStream fin = null;
		Workbook wb = null;
		Sheet sh = null;
		try {
			fin = new FileInputStream(filePath);
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(sheetName);
			
			if(sh==null) {
				reports.WriteResult(null, "Fail", "Failed to find the sheetname '"+sheetName+"'", test, false);
				return -1;
			}
			
			return sh.getPhysicalNumberOfRows();
		}catch(Exception e)
		{
			reports.WriteResult(null, "Exception", "Exception in getRowCount() method. "+e.getMessage(), test, false);
			return -1;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				sh = null;
				wb.close();
				wb = null;
			}catch(Exception e)
			{
				reports.WriteResult(null, "Exception", "Exception in getRowCount() method. "+e.getMessage(), test, false);
			}
		}
	}
	
	
	
	
	/*******************************************************
	 * Method Name			: getCellData
	 * Purpose				: It is used to get the cell value
	 * Return Type			: String
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  String strValue = getCellData(excelFile, SheetName, ColumnName, RowNum);
	 ******************************************************
	 */
	public String getCellData(String filePath, String sheetName, String colName, int rowNum) {
		FileInputStream fin = null;
		Workbook wb = null;
		Sheet sh = null;
		Row row = null;
		Cell cell = null;
		int colNum = 0;
		String strCellData = null;
		String sDay = null;
		String sMonth = null;
		String sYear = null;
		try {
			fin = new FileInputStream(filePath);
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(sheetName);
			
			if(sh==null) {
				reports.WriteResult(null, "Fail", "Failed to find the sheetname '"+sheetName+"'", test, false);
				return null;
			}
			
			//Find the column Number based on the column name
			row = sh.getRow(0);
			for(int col=0; col<row.getPhysicalNumberOfCells(); col++)
			{
				cell = row.getCell(col);
				if(cell.getStringCellValue().equals(colName)) {
					colNum = col;
					break;
				}
			}
			
			
			//Format & Read the cell data
			row = sh.getRow(rowNum);
			cell = row.getCell(colNum);
			
			if(cell==null || cell.getCellType()==CellType.BLANK) {
				strCellData = "";
			}
			else if(cell.getCellType()==CellType.BOOLEAN) {
				strCellData = String.valueOf(cell.getBooleanCellValue());
			}
			else if(cell.getCellType()==CellType.STRING) {
				strCellData = cell.getStringCellValue();
			}
			else if(cell.getCellType()==CellType.NUMERIC) {
				if(HSSFDateUtil.isCellDateFormatted(cell)) {
					double dt = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(dt));
					
					//If day is <10 then prefix zero
					if(cal.get(Calendar.DAY_OF_MONTH)<10) {
						sDay = "0" + cal.get(Calendar.DAY_OF_MONTH);
					}else {
						sDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
					}
					
					
					//If month is <10 then prefix zero
					if((cal.get(Calendar.MONTH)+1)<10) {
						sDay = "0" + (cal.get(Calendar.MONTH)+1);
					}else {
						sDay = String.valueOf((cal.get(Calendar.MONTH)+1));
					}
					
					
					sYear = String.valueOf(cal.get(Calendar.YEAR));
					
					strCellData = sDay +"/"+ sMonth +"/"+ sYear;
				}else {
					strCellData = String.valueOf(cell.getNumericCellValue());
				}
			}
			
			return strCellData;
		}catch(Exception e)
		{
			reports.WriteResult(null, "Exception", "Exception in getCellData() method. "+e.getMessage(), test, false);
			return null;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				cell = null;
				row = null;
				sh = null;
				wb.close();
				wb = null;
				strCellData = null;
				sDay = null;
				sMonth = null;
				sYear = null;
			}catch(Exception e)
			{
				reports.WriteResult(null, "Exception", "Exception in getCellData() method. "+e.getMessage(), test, false);
				return null;
			}
		}
	}
	
	
	
	/*******************************************************
	 * Method Name			: setCellData
	 * Purpose				: It is used to set the cell value
	 * Return Type			: String
	 * Date Created			: 
	 * Date modified		:
	 * Reviewed By			:
	 * Example				:  setCellData(excelFile, SheetName, ColumnName, RowNum);
	 ******************************************************/
	public void setCellData(String strFilePath, String sheetName, String columnName, int rowNum, String dataToSet)
	{
		FileInputStream fin = null;
		FileOutputStream fout = null;
		Workbook wb = null;
		Sheet sh = null;
		Row row = null;
		Cell cell = null;
		int colNum = 0;
		try {
			fin = new FileInputStream(strFilePath);
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(sheetName);
			
			if(sh==null) {
				reports.WriteResult(null, "Fail", "Failed to find the sheetname '"+sheetName+"'", test, false);
			}
			
			//Find the column Number based on the column name
			row = sh.getRow(0);
			for(int col=0; col<row.getPhysicalNumberOfCells(); col++)
			{
				cell = row.getCell(col);
				if(cell.getStringCellValue().equals(columnName)) {
					colNum = col;
					break;
				}
			}
			
			//write to the cell data
			row = sh.getRow(rowNum);
			cell = row.getCell(colNum);
			
			if(row.getCell(colNum)==null) {
				cell = row.createCell(colNum);
			}
			
			cell.setCellValue(dataToSet);
			
			fout = new FileOutputStream(strFilePath);
			wb.write(fout);
		}catch(Exception e)
		{
			reports.WriteResult(null, "Exception", "Exception in getCellData() method. "+e.getMessage(), test, false);
		}
		finally
		{
			try {
				fout.flush();
				fout.close();
				fout = null;
				fin.close();
				fin = null;
				cell = null;
				row = null;
				sh = null;
				wb.close();
				wb = null;
			}catch(Exception e)
			{
				reports.WriteResult(null, "Exception", "Exception in getCellData() method. "+e.getMessage(), test, false);
			}
		}
	}
}
