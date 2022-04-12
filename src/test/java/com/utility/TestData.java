package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData {
	public static XSSFSheet ExcelWSheet;
	public static XSSFWorkbook ExcelWBook;
	public static XSSFCell Cell;
	public static XSSFRow Row;
		
	public static String[] renameFile(String origin, String  newFileName)
	{
		String fileType="";
		String originf="";
	
		File f1 = new File("UploadData/"+origin);
		
		if(origin.contains(".xlsx")) {
			fileType=".xlsx";
		originf=origin.split(".x")[0];
		}
		else if(origin.contains(".csv")) {
			fileType=".csv";
		originf=origin.split(".c")[0];
		}
		else if(origin.contains(".txt")) {
			fileType=".txt";
		originf=origin.split(".t")[0];
		}
		
		File f2 = new File("UploadData/"+originf+"_"+newFileName+fileType);
		f1.renameTo(f2);
		String [] str={f2.getAbsolutePath(),originf+"_"+newFileName+fileType};
		return str;
	}
	public static Object[][] getDataForDataprovider(String FilePath, String SheetName, int startRow, int startCol)
			throws Exception {
		String[][] tabArray = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int ci, cj;
			int totalRows = ExcelWSheet.getLastRowNum();
			Row = ExcelWSheet.getRow(startRow);

			int totalCols = Row.getPhysicalNumberOfCells();
			tabArray = new String[totalRows - 1][totalCols];
			ci = 0;
			System.out.println("total rows" + totalRows + " start: " + startRow + " total col:" + totalCols);
			for (int i = startRow; i < totalRows; i++, ci++) {
				cj = 0;
				for (int j = startCol; j < totalCols; j++, cj++) {
					tabArray[ci][cj] = getCellData(i, j);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return (tabArray);
	}

	@SuppressWarnings("unchecked")
	public static <UnicodeString> UnicodeString getCellData(int RowNum, int ColNum) throws Exception {
		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			int dataType = 3;
			try {
				dataType = Cell.getCellType();
			} catch (NullPointerException e) {
				dataType = 3;
			}
			if (dataType == 3) {
				return (UnicodeString) "";
			} else {
				DataFormatter formatter = new DataFormatter();
				UnicodeString Data = (UnicodeString) formatter.formatCellValue(Cell);
				return Data;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}
	}

	public static String uploadFilePath(String filename) {
		String dataFilePath = "UploadData/" + filename ;
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		return fullpath;
	}
	public static String dowanloadFilePath(String filename) {
		String dataFilePath = "DownloadData/" + filename;
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		return fullpath;
	}
	public static String filePath(String folder,String filename) {
		String dataFilePath = folder+"/" + filename;
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		return fullpath;
	}
	public static String findFile(String directory,String fileNm)
	{
		File dir = new File(directory);
	      String[] children = dir.list();
	      String name = null;
	      
	      if (children == null) {
	      } else {
	         for (int i = 0; i < children.length; i++) {
	            if(children[i].contains(fileNm))
	            {
	            	name=children[i];
	            	break;
	            }
	           
	         }
	        
	      } return name;
	}
	   


	// Data provider
	public static void  clearProperties(String Datafile) throws IOException {
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(new File(Datafile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		FileWriter fw= new FileWriter(new File(Datafile));
		Properties prop = new Properties();
		try {
				prop.load(fileInput);
				prop.store(fw, null);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
		}
	}
	
	public static String getValueFromConfig(String Datafile, String value) throws IOException {
		String result="";
		File file = new File(Datafile);
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
				prop.load(fileInput);
				result = prop.getProperty(value,"No Value");
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
		}
		return result;
	}
	public static void setValueConfig(String Datafile, String Key, String value) throws IOException {
		File file = new File(Datafile);
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
				prop.load(fileInput);
				prop.setProperty(Key, value);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
		}
		
		prop.store(new FileOutputStream(file), null);
	}
	public static void createZipfileForOutPut( String FolderName)
	{String home = System.getProperty("user.home");
	File directory = new File(home+"/Documents/" +"AutomationExecutionReports");
    if (! directory.exists()){
        directory.mkdir();
    }
	try {
		FileOutputStream fos = new FileOutputStream(home+"/Documents/AutomationExecutionReports/"+FolderName+".zip");
		ZipOutputStream zos = new ZipOutputStream(fos);
			File folder = new File("test-output/HHAeXchangeAutomation.html");
			ArrayList<File> filelist = new ArrayList<File>();
			listf(folder.getPath(), filelist);
	
		    for (int i = 0; i < filelist.size(); i++) {
		      if (filelist.get(i).isFile()) {
		    	  addCopyFile(filelist.get(i).getPath(), zos);
		      } else if (filelist.get(i).isDirectory()) {
		      }
		    }	
			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void listf(String directoryName, ArrayList<File> files) {
	    File directory = new File(directoryName);
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile()) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	            listf(file.getPath(), files);
	        }
	    }
	}
	
	public static void addCopyFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zos.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}
		zos.closeEntry();
		fis.close();
	}
	
	public static void deletePastScreenshots(String path)
	{
		File index = new File(path);
		
		if(index.exists() && index.isDirectory())
		{
			String[]entries = index.list();
			
			for(String s: entries)
			{
			    File currentFile = new File(index.getPath(),s);
			    currentFile.delete();
			}
			
			index.delete();
		}
	}
	
}
