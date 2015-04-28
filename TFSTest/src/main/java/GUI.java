import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Scanner;

import javax.swing.*;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class GUI {

	private JFrame f;
	private JPanel p;
	private JButton b;
	private JLabel l1;
	private JLabel l2;
	private JTextField t1;
	private JTextField t2;
	private JLabel Plabel;
	private JTextField path;

	public GUI() {
		gui();
	}

	public void gui() {
		f = new JFrame("Test Tool");
		f.setVisible(true);
		f.setSize(500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);

		p = new JPanel(new GridBagLayout()); // GridBagLayout s used to align
												// text/button/drop down/combo
												// box
		p.setBackground(Color.white);
		
		Plabel = new JLabel("Path / Location");
		path = new JTextField(30);
		b = new JButton("GO");
		l1 = new JLabel("Sprint Number");
		t1 = new JTextField(30);
		l2 = new JLabel("PBI Number");
		t2 = new JTextField(30);

		b.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String sprint = "Sprint " + t1.getText();

				String pbino = "PBI_" + t2.getText() + "_TestCases.xls";
				String defno = "PBI_" + t2.getText() + "_Defect.xls";
				String report = "PBI_" + t2.getText() + "_Report.docx";
				
				
				  
				try {

					File file = new File("C:\\" + sprint);
					if (!(file.exists())) {
						file.mkdir();
						System.out.println(sprint + "Directory is created");
						System.out.println(pbino + "PBI is created");
						System.out.println(defno + "Defect file is created");

						File filename = new File("C:\\" + sprint + "\\" + pbino);
						File filename1 = new File("C:\\" + sprint + "\\"
								+ defno);
						

						File f = new File("C:\\" + sprint + "\\" + report);
						if (file.exists()) {
							if (!(filename.exists() && !(filename1.exists())) && !(f.exists())) {
								
						       
								HSSFWorkbook workbook = new HSSFWorkbook();
								HSSFSheet sheet = workbook
										.createSheet("TestCases");

								HSSFWorkbook workbook1 = new HSSFWorkbook();
								HSSFSheet sheet1 = workbook1
										.createSheet("Defect");

							        
								/*HSSFRow rowhead = sheet.createRow((short) 2);
								rowhead.createCell(0).setCellValue("TestCase_Id");
								
								rowhead.createCell(1).setCellValue(
										"Prerequisite");
								rowhead.createCell(2).setCellValue(
										"Description");
								rowhead.createCell(3).setCellValue(
										"Steps to Test");
								rowhead.createCell(4).setCellValue(
										"Expected Result");
								rowhead.createCell(5).setCellValue(
										"Actual Result");
								rowhead.createCell(5).setCellValue("Status");
								rowhead.createCell(5).setCellValue(
										"Comments/ Test data");

								HSSFRow rowhead1 = sheet1.createRow((short) 0);
								rowhead1.createCell(0).setCellValue("Module");
								rowhead1.createCell(1).setCellValue("PBI");
								rowhead1.createCell(2).setCellValue("Title");
								rowhead1.createCell(3).setCellValue(
										"Test Steps");
								rowhead1.createCell(4).setCellValue("Severity");
								rowhead1.createCell(5).setCellValue("Status");
								rowhead1.createCell(5).setCellValue(
										"QA Comments");
								rowhead1.createCell(5).setCellValue(
										"Detected By");
								rowhead1.createCell(6).setCellValue(
										"Detected Date");
								rowhead1.createCell(7).setCellValue(
										"Detected Stage");
								rowhead1.createCell(8).setCellValue(
										"Assigned To");
								rowhead1.createCell(8).setCellValue(
										"Injected Stage");
								rowhead1.createCell(8).setCellValue(
										"Closed Date");
								rowhead1.createCell(8).setCellValue("Comments");*/

								FileOutputStream fileOut1 = new FileOutputStream(
										filename1);
								workbook1.write(fileOut1);
								fileOut1.close();

								FileOutputStream fileOut = new FileOutputStream(
										filename);
								workbook.write(fileOut);
								fileOut.close();
								
								 f.createNewFile();
								System.out
										.println("Your excel file has been generated!");
								
								    File source1 = new File("C:\\New folder\\TestCases_Standard");  
							        File destinationFile1 = new File("C:\\"+ sprint + "\\" + pbino); 
							        
							        File source2 = new File("C:\\New folder\\Defects_Standard");  
							        File destinationFile2 = new File("C:\\"+ sprint + "\\" + defno);
							        
							        File source3 = new File("C:\\New folder\\Report_Standard");  
							        File destinationFile3 = new File("C:\\" + sprint + "\\" + report);
							        try {
										copyxls(source1, destinationFile1);
										copyxls(source2, destinationFile2);
										copyxls(source3, destinationFile3);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} 
							}

						}

					}

					else {
						System.out.println("Directory already exists");
					}
				} catch (Exception ex) {
					System.out.println(ex);

				}

				JOptionPane.showMessageDialog(null, "Done");

			}

		});

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 1;
		p.add(Plabel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		p.add(path, c);
		
		c.gridx = 0;
		c.gridy = 3;
		p.add(l1, c);

		c.gridx = 0;
		c.gridy = 5;
		p.add(l2, c);

		c.gridx = 0;
		c.gridy = 4;
		p.add(t1, c);

		c.gridx = 0;
		c.gridy = 6;
		p.add(t2, c);

		c.gridx = 0;
		c.gridy = 7;
		p.add(b, c);
		
		f.add(p);
		
	}
		
		private static void copyxls ( File source1,  File target1)  
	            throws IOException {  
	            FileChannel sourceChanne2 = null;  
	            FileChannel targetChanne2 =null;  
	            try {  
	                sourceChanne2 =new FileInputStream(source1).getChannel();  
	                targetChanne2=  new FileOutputStream(target1).getChannel();  
	            targetChanne2.transferFrom(sourceChanne2, 0,  
	            sourceChanne2.size());  
	            }  
	            finally {  
	            targetChanne2.close();  
	            sourceChanne2.close();  
	            }  
	            System.out.println("Success1");
	            }  
		

	public static void main(String args[]) {
		new GUI();
	        
	}


}
