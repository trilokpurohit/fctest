package com.utility;

import org.testng.annotations.DataProvider;

public class Dataproviders {
	@DataProvider(name = "excelDataProvider")
	public static Object[][] getExcelDataProvider() throws Exception {
		String path = TestData.uploadFilePath("testdata.xlsx");
		return TestData.getDataForDataprovider(path, "Sheet1", 1, 0);
	}
	
	
}
