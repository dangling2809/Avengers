import java.io.*;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

public class XlsCreation {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			System.out.println("Enter Sprint No.");
			Scanner s = new Scanner(System.in);
			String Name = "Sprint " + s.next();
			System.out.println(Name);

			File file = new File("C:\\" + Name);
			if (!(file.exists())) {
				file.mkdir();
				System.out.println("Directory is created");

				System.out.println("Enter PBI No.");
				Scanner pbi = new Scanner(System.in);
				
				String PBI = "PBI_" + pbi.next() + "_TestCases.xls";
				
				String DEFECT = "PBI_" + pbi.next() + "_DEFECT.xls";
				
				System.out.println(PBI + DEFECT);

				File filename = new File("C:\\" + Name + "\\" + PBI);
				
				File filename1 = new File("C:\\" + Name + "\\" + DEFECT);

				if (file.exists()) {
					if (!(filename.exists()) && !(filename1.exists())) {
						HSSFWorkbook workbook = new HSSFWorkbook();
						HSSFSheet sheet = workbook.createSheet("TestCases");

						
						 
						HSSFRow rowhead = sheet.createRow((short)2);
						rowhead.createCell(0).setCellValue("TestCase_Id");
						rowhead.createCell(1).setCellValue("Prerequisite");
						rowhead.createCell(2).setCellValue("Description");
						rowhead.createCell(3).setCellValue("Steps to Test");
						rowhead.createCell(4).setCellValue("Expected Result");
						rowhead.createCell(5).setCellValue("Actual Result");
						rowhead.createCell(5).setCellValue("Status");
						rowhead.createCell(5).setCellValue("Comments/ Test data");
						FileOutputStream fileOut = new FileOutputStream(filename);
				
							HSSFWorkbook workbook1 = new HSSFWorkbook();
							HSSFSheet sheet1 = workbook1.createSheet("Defects");
							
							HSSFRow rowhead1 = sheet1.createRow((short)0);
							rowhead1.createCell(0).setCellValue("Module");		
							rowhead1.createCell(1).setCellValue("PBI");
							rowhead1.createCell(2).setCellValue("Title");
							rowhead1.createCell(3).setCellValue("Test Steps");
							rowhead1.createCell(4).setCellValue("Severity");
							rowhead1.createCell(5).setCellValue("Status");
							rowhead1.createCell(5).setCellValue("QA Comments");
							rowhead1.createCell(5).setCellValue("Detected By");
							rowhead1.createCell(6).setCellValue("Detected Date");
							rowhead1.createCell(7).setCellValue("Detected Stage");
							rowhead1.createCell(8).setCellValue("Assigned To");
							rowhead1.createCell(8).setCellValue("Injected Stage");
							rowhead1.createCell(8).setCellValue("Closed Date");	
							rowhead1.createCell(8).setCellValue("Comments");
												
							FileOutputStream fileOut1 = new FileOutputStream(filename1);
							
							workbook.write(fileOut);
							
							
							workbook1.write(fileOut1);
							fileOut.close();
							fileOut1.close();
						
					
					
						System.out
								.println("Your excel files has been generated!");
					}
				}

			}

			else {
				System.out.println("Directory already exists");
			}

		} catch (Exception ex) {
			System.out.println(ex);

		}
	}

}
