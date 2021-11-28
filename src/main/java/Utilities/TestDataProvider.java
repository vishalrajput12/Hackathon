package Utilities;

import java.util.Hashtable;

//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;

public class TestDataProvider {

	
	public static Object[][] getTestData(String DataFileName, String SheetName, String TestName) {

		ReadExcelDataFile readdata = new ReadExcelDataFile(
				System.getProperty("user.dir") + "\\TestData\\" + DataFileName);
		String sheetName = SheetName;
		String testName = TestName;

		int startRowNum = 0;
		while (!readdata.getCellData(sheetName, 0, startRowNum).equalsIgnoreCase(testName)) {
			startRowNum++;

		}

		int StartTestColumn = startRowNum + 1;
		int StartTestRow = startRowNum + 2;
		int rows = 0;
		while (!readdata.getCellData(sheetName, 0, StartTestRow + rows).equals("")) {
			rows++;
		}

		int columns = 0;
		while (!readdata.getCellData(sheetName, columns, StartTestColumn).equals("")) {
			columns++;

		}
		// define 2d arrays
		Object[][] dataSet = new Object[rows][1];

		Hashtable<String, String> dataTable = null;
		int dataRowNumber = 0;
		for (int rowNumber = StartTestRow; rowNumber <= StartTestColumn + rows; rowNumber++) {
			dataTable = new Hashtable<String, String>();
			for (int colNumber = 0; colNumber < columns; colNumber++) {
				String key = readdata.getCellData(sheetName, colNumber, StartTestColumn);
				String value = readdata.getCellData(sheetName, colNumber, rowNumber);
				dataTable.put(key, value);
				

			}
			dataSet[dataRowNumber][0] = dataTable;
			dataRowNumber++;
		}

		return dataSet;

	}

}
