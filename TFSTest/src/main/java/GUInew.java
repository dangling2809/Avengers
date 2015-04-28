import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import javax.swing.*;

public class GUInew {

	private JFrame f;
	private JPanel p;
	private JButton b;
	private JLabel l1;
	private JLabel l2;
	private JLabel Plabel;
	private JTextField t1;
	private JTextField t2;
	private JTextField path;

	public GUInew() {
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

		try {

			b.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					String Path = path.getText();
					String sprint = "Sprint " + t1.getText();
					Long Value;

					String pbino = "";

					String defno = "";

					String report = "";

					ArrayList<Long> list = new ArrayList<Long>();

					File SprintFolder = new File(Path + "\\" + sprint);
					if (!(SprintFolder.exists())) {
						SprintFolder.mkdir();
						File fintr = new File(Path + "\\" + sprint + "\\"
								+ "Integration_Test_Report_Sprint"
								+ t1.getText() + "_Upgrade XX.docx");
						try {
							fintr.createNewFile();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						System.out.println(sprint + "Directory is created");

						String numbers = t2.getText();

						for (String s : numbers.split(";"))
							list.add(new Long(s));
						System.out.println(list);

						for (int i = 0; i < list.size(); i++) {

							Value = list.get(i);

							pbino = "PBI_" + Value + "_TestCases.xls";
							defno = "PBI_" + Value + "_Defect.xls";
							report = "PBI_" + Value + "_Report.docx";

							File PbiFolder = new File(Path + "\\" + sprint + "\\"
									+ Value);
							if (!(PbiFolder.exists())) {
								PbiFolder.mkdir();
								File TestcaseFile = new File(Path + "\\" + sprint
										+ "\\" + Value.toString() + "\\"
										+ pbino);
								File DefectsFile = new File(Path + "\\" + sprint
										+ "\\" + Value.toString() + "\\"
										+ defno);
								File ReportFile = new File(Path + "\\" + sprint + "\\"
										+ Value.toString() + "\\" + report);

								if (SprintFolder.exists() && PbiFolder.exists()) {
									if (!(TestcaseFile.exists() && !(DefectsFile
											.exists())) && !(ReportFile.exists())) {

										File source1 = new File(
												"C:\\New folder\\Testcases_Standard.xls");
										File destinationFile1 = new File(Path
												+ "\\" + sprint + "\\"
												+ Value.toString() + "\\"
												+ pbino);

										File source2 = new File(
												"C:\\New folder\\Defect_Standard.xls");
										File destinationFile2 = new File(Path
												+ "\\" + sprint + "\\"
												+ Value.toString() + "\\"
												+ defno);
										File source3 = new File(
												"C:\\New folder\\Testreport_Standard.docx");
										File destinationFile3 = new File(Path
												+ "\\" + sprint + "\\"
												+ Value.toString() + "\\"
												+ report);

										File source4 = new File(
												"C:\\New folder\\Integration_Test_Report_SprintXX_Upgrade XX.docx");
										File destinationFile4 = new File(
												Path
														+ "\\"
														+ sprint
														+ "\\"
														+ "Integration_Test_Report_Sprint"
														+ sprint
														+ "_Upgrade XX");

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
										} catch (IOException e1) {
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
			c.gridy = 4;
			p.add(t1, c);

			c.gridx = 0;
			c.gridy = 6;
			p.add(t2, c);

			c.gridx = 0;
			c.gridy = 7;
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
		new GUInew();

	}

}
