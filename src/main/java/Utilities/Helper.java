package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
public class Helper {

	public void copyFile(String source,String destination) throws IOException {
		System.out.println("Entered copyFile");
		try {
		File src=new File(source);
		File dest=new File(destination);
		FileUtils.copyToDirectory(src, dest);
		System.out.println("Copied file from "+source+" to "+destination);
		}catch(Exception e) {
			System.out.println("failed to copy file:"+e.getMessage());
		}
	}

	public List<Map<String,String>> readDataFromExcel(String sheetname,int rowNo,String excelPath) throws IOException {

		File file=new File("./src/test/resources/DataFiles/SampleTest_1.xlsx");
		System.out.println("File initialized");
		try {
			if(file.exists()) {
				//file.delete();
				System.out.println("File deleted");
			}else
			{
				System.out.println("File is not available");
			}
		}catch(Exception e) {
			System.out.println("Failed in deleting excel file-Exception is:"+e.getMessage());
		}
		//copyFile("D://AttraData//Projects//Data Files//SampleTest_1.xlsx","./src/test/resources/DataFiles");
		copyFile(excelPath,"./src/test/resources/DataFiles");

		System.out.println("File copied");
		FileInputStream fis=new FileInputStream("./src/test/resources/DataFiles/SampleTest_1.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet =workbook.getSheet(sheetname);
		Row header=sheet.getRow(0);
		Row row=sheet.getRow(rowNo);
		int rowCount=sheet.getLastRowNum();
		System.out.println("Rows count:"+rowCount);
		List<Map<String,String>> excelRows=new ArrayList<Map<String,String>>();
		HashMap<String,String> data=new HashMap<String,String>();
		try {
			for(int j=0;j<rowCount;j++) {
				for(int i=0;i<row.getLastCellNum();i++) {
					System.out.println("i value:"+i);
					String key=header.getCell(i).toString();
					String value=row.getCell(i).toString();
					System.out.println("Key:"+key+"--Value:"+value);
					data.put(key, value);
				}
				excelRows.add(data);
			}
		}catch(Exception e) {
			System.out.println("Exception ---->"+e.getMessage());
		}
		fis.close();
		return excelRows;

	}

	
	public List<Map<String,String>> readDataFromExcel_Test(String sheetname,int rowNo) throws IOException {

		
		//copyFile("D://AttraData//Projects//Data Files//SampleTest_1.xlsx","./src/test/resources/DataFiles");
		//copyFile(excelPath,"./src/test/resources/DataFiles");

		System.out.println("Entered readDataFromExcel_Test");
		FileInputStream fis=new FileInputStream("./src/test/resources/DataFiles/SampleTest_2.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet =workbook.getSheet(sheetname);
		Row header=sheet.getRow(0);
		Row row=sheet.getRow(rowNo);
		int rowCount=sheet.getLastRowNum();
		System.out.println("Rows count:"+rowCount);
		List<Map<String,String>> excelRows=new ArrayList<Map<String,String>>();
		HashMap<String,String> data=new HashMap<String,String>();
		try {
			for(int j=0;j<rowCount;j++) {
				for(int i=0;i<row.getLastCellNum();i++) {
					System.out.println("i value:"+i);
					String key=header.getCell(i).toString();
					String value=row.getCell(i).toString();
					System.out.println("Key:"+key+"--Value:"+value);
					data.put(key, value);
				}
				excelRows.add(data);
			}
		}catch(Exception e) {
			System.out.println("Exception ---->"+e.getMessage());
		}
		fis.close();
		return excelRows;

	}
	
	
	
	public Connection connection;
	public Recordset readExcelFillo(String sheetname,int rowNum) throws FilloException, IOException {
		File file=new File("src/test/resources/DataFiles/SampleTest.xls");
		if(file.delete()) {
			System.out.println("File deleted");
		}else
		{
			System.out.println("Failed to delete file");
		}

		copyFile("D://AttraData//Projects//Data Files//SampleTest.xls","src/test/resources/DataFiles");
		System.out.println("File copied");

		Fillo fillo=new Fillo();
		String query="SELECT * FROM "+sheetname+" where Sno="+rowNum;
		//connection=fillo.getConnection("D://AttraData//Projects//Data Files//SampleTest.xlsx");
		connection=fillo.getConnection("src/test/resources/DataFiles/SampleTest.xls");
		Recordset recordset = null;
		try {
			recordset=connection.executeQuery("Select * from Sheet1 where Sno=1");
		}catch(Exception e) {
			System.out.println("Exception in recordset:"+e.getMessage());
		}
		System.out.println("Recordset size:"+recordset.getCount());
		while(recordset.next()) {
			System.out.println(recordset.getField("Sno")+" "+recordset.getField("Name"));
		}
		return recordset;

	}

	public Object[] readExcelDatausingFillo() throws FilloException {
		Fillo fillo=new Fillo();
		Object[] object=null;
		String query="SELECT * FROM Sheet1";
		try {
			connection=fillo.getConnection("D://AttraData//Projects//Data Files//SampleTest.xlsx");
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Recordset recordset = null;
		try {
			recordset = connection.executeQuery(query);
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int numberOfRows=recordset.getCount();
		System.out.println("Size:"+numberOfRows);
		int i=0;
		object=new Object[numberOfRows];
		while(recordset.next()) {
			object[i]= recordset.getField("Name");
			System.out.println(object[i]);
			i++;
		}
		recordset.close();
		connection.close();
		return object;
	}


	public static HashMap<String,HashMap<String,String>> readExcelDataToHashMap(String filepath) throws FileNotFoundException
	{
		FileInputStream file=new FileInputStream(filepath);
		//Workbook wb=new WorkbookFactory.create(file);



		return null;

	}


}
