import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewGUI {

	private JFrame f;
	private JPanel p;
	private JButton b;
	private JLabel l1;
	private JLabel l2;
	private JLabel lblPBIName;
	private JLabel Plabel;
	private JTextField t1;
	private JTextField t2;
	private JTextField path;
	private JTextField txtPBIName;
	public NewGUI() {
		gui();
	}

	public void gui() {
		f = new JFrame("Test Tool");
		f.setVisible(true);
		f.setSize(500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);

		p = new JPanel(new GridBagLayout());    // GridBagLayout s used to align
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
		lblPBIName=new JLabel("PBI Name");
		txtPBIName=new JTextField(30);
		

		try {

			b.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					String Path = path.getText();
					String sprint = "Sprint " + t1.getText();
					
					
					

					String pbino = "";

					String defno = "";

					String report = "";

					Map<String,String> mapPBINumberToName=new HashMap<String,String>();
					List<String> pbiNumberList = new ArrayList<String>();
					List<String> pbiNameList = new ArrayList<String>();

					File SprintFolder = new File(Path + File.separator + sprint);
					if (!(SprintFolder.exists())) {
						SprintFolder.mkdir();
						File fintr = new File(Path + File.separator + sprint + File.separator
								+ "Integration_Test_Report_Sprint"
								+ t1.getText() + "_Upgrade XX.docx");
						/*try {
							fintr.createNewFile();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}*/

						System.out.println(sprint + "Directory is created");

						String numbers = t2.getText();
						String names=txtPBIName.getText();
						for (String pnum : numbers.split("|"))
							pbiNumberList.add(pnum);
						for(String pname:names.split("|"))
							pbiNameList.add(pname);
						
						Iterator<String> it1=pbiNumberList.iterator();
						Iterator<String> it2=pbiNameList.iterator();

						while(it1.hasNext() && it2.hasNext()) {
							
							String pbiNumber=it1.next();
							String pbiName=it2.next();
							
							pbino = "PBI_" + pbiNumber + "_TestCases.xls";
							defno = "PBI_" + pbiNumber + "_Defect.xls";
							report = "PBI_" + pbiNumber + "_Report.docx";

							File PbiFolder = new File(Path + File.separator + sprint + File.separator
									+ pbiNumber);
							if (!(PbiFolder.exists())) {
								PbiFolder.mkdir();
								File TestcaseFile = new File(Path + File.separator + sprint
										+ File.separator + pbiNumber.toString() + File.separator
										+ pbino);
								File DefectsFile = new File(Path + File.separator + sprint
										+ File.separator + pbiNumber.toString() + File.separator
										+ defno);
								File ReportFile = new File(Path + File.separator + sprint + File.separator
										+ pbiNumber.toString() + File.separator + report);

								if (SprintFolder.exists() && PbiFolder.exists()) {
									if (!(TestcaseFile.exists() && !(DefectsFile
											.exists())) && !(ReportFile.exists())) {

										File source1 = new File(
												"/home/dangling/shi/docs/Testcases_Standard.xlsx");
										File destinationFile1 = new File(Path
												+ File.separator + sprint + File.separator
												+ pbiNumber.toString() + File.separator
												+ pbino);

										File source2 = new File(
												"/home/dangling/shi/docs/Defect_Standard.xlsx");
										File destinationFile2 = new File(Path
												+ File.separator + sprint + File.separator
												+ pbiNumber.toString() + File.separator
												+ defno);
										File source3 = new File(
												"/home/dangling/shi/docs/Testreport_Standard.docx");
										File destinationFile3 = new File(Path
												+ File.separator + sprint + File.separator
												+ pbiNumber.toString() + File.separator
												+ report);

										File source4 = new File(
												"/home/dangling/shi/docs/Integration_Test_Report_SprintXX_Upgrade XX.docx");
										File destinationFile4 = new File(
												Path
														+ File.separator
														+ sprint
														+ File.separator
														+ "Integration_Test_Report_"
														+ sprint
														+ "_Upgrade XX.docx");
										
										
								
										
										System.out.println(pbino
												+ "PBI is created");
										System.out.println(defno
												+ "Defect file is created");
										System.out.println(defno
												+ "Report file is created");

										try {
											copyxls(source1, destinationFile1);
											
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										try {
											copyxls(source2, destinationFile2);
											
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										try {
											copyxls(source3, destinationFile3);
										
										    }
										
										 catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										
										try {
											copyxls(source4, destinationFile4);
										
										    }
										
										 catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									

									}

								}
							}

						}
						
					}

					else {
						System.out.println("Directory already exists");
					}
					
					//Here be dragons
					
					

					JOptionPane.showMessageDialog(null, "Done");

				}
			

			});

			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(10, 10, 10, 10);

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
			c.gridy = 7;
			p.add(lblPBIName, c);

			c.gridx = 0;
			c.gridy = 4;
			p.add(t1, c);

			c.gridx = 0;
			c.gridy = 6;
			p.add(t2, c);
			
			c.gridx = 0;
			c.gridy = 8;
			p.add(txtPBIName, c);

			c.gridx = 0;
			c.gridy = 10;
			p.add(b, c);

			f.add(p);

		} catch (Exception e) {

		}
	}

	private static void copyxls(File source1, File target1) throws IOException {
		FileChannel sourceChanne2 = null;
		FileChannel targetChanne2 = null;
		try {
			sourceChanne2 = new FileInputStream(source1).getChannel();
			targetChanne2 = new FileOutputStream(target1).getChannel();
			targetChanne2.transferFrom(sourceChanne2, 0, sourceChanne2.size());
		} finally {
			targetChanne2.close();
			sourceChanne2.close();
		}
		System.out.println("Success1");
	}
	
	

	public static void main(String args[]) {
		new NewGUI();
		

	}
}



