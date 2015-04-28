import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.JComboBox; 
import javax.swing.JButton; 
import javax.swing.JLabel; 
import javax.swing.JList; 
import javax.swing.JTextField;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.awt.BorderLayout; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Form extends JFrame {

	public static void main(String args[])
	{
		new Form();
	}
	public Form() 
	{
		JFrame guiFrame = new JFrame();
		
		
		//make sure the program exits when the frame closes 
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		guiFrame.setTitle("SPRINT"); 
		guiFrame.setSize(600,500);
		
		
		final JPanel Panel = new JPanel();
		JLabel label = new JLabel("Sprint Number");
		final JTextField text = new JTextField(30);
		text.setBounds(5,5,50,50);
		Panel.add(label);
		Panel.add(text);
		
	
		JLabel label1 = new JLabel("PBI Number");
		final JTextField text1 = new JTextField(30);
		text1.setBounds(5,5,50,50);
		Panel.add(label1);
		Panel.add(text1);
		
		
		JButton Go = new JButton( "Go");

		//The ActionListener class is used to handle the 
		//event that happens when the user clicks the button. 
		//As there is not a lot that needs to happen we can  
		//define an anonymous inner class to make the code simpler. 
		
		
		
		Go.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sprint = "Sprint "+ text.getText();
				try{
				File file = new File("C:\\" + sprint);
				if (!(file.exists())) {
					file.mkdir();
					System.out.println(sprint + "Directory is created");

					System.out.println("Enter PBI No.");
					Scanner pbi = new Scanner(System.in);
					String PBI = "PBI_" + pbi.next() + "_TestCases.xls";
					System.out.println(PBI);

					File filename = new File("C:\\" + sprint + "\\" + PBI);

					if (file.exists()) {
						if (!(filename.exists())) {
							HSSFWorkbook workbook = new HSSFWorkbook();
							HSSFSheet sheet = workbook.createSheet("TestCases");

							HSSFRow rowhead = sheet.createRow((short) 2);
							rowhead.createCell(0).setCellValue("TestCase_Id");
							rowhead.createCell(1).setCellValue("Prerequisite");
							rowhead.createCell(2).setCellValue("Description");
							rowhead.createCell(3).setCellValue("Steps to Test");
							rowhead.createCell(4).setCellValue("Expected Result");
							rowhead.createCell(5).setCellValue("Actual Result");
							rowhead.createCell(5).setCellValue("Status");
							rowhead.createCell(5).setCellValue(
									"Comments/ Test data");

							FileOutputStream fileOut = new FileOutputStream(
									filename);
							workbook.write(fileOut);
							fileOut.close();
							System.out
									.println("Your excel file has been generated!");
						}

					}

				}

				else {
					System.out.println("Directory already exists");
				}
		} catch (Exception ex) {
			System.out.println(ex);

		}
		Panel.setVisible(!Panel.isVisible());
			}
		});
		
		//The JFrame uses the BorderLayout layout manager. 
		
		//Put the two JPanels and JButton in different areas. 
		guiFrame.add(Panel, BorderLayout.NORTH);
	    //guiFrame.add(text,BorderLayout.NORTH);
		guiFrame.add(Go,BorderLayout.SOUTH);  
		
		//make sure the JFrame is visible 
		guiFrame.setVisible(true); 
		
		
		
	
			}  
}

	
