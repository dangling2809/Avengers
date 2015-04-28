import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Tabbedpane extends JFrame {
    
    public Tabbedpane() {
        
    	
    	JFrame frame = new JFrame("Test Tool");
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
        setTitle("Test Tool");
        JTabbedPane jtp = new JTabbedPane();
        getContentPane().add(jtp);
        JPanel jpS = new JPanel();
        JPanel jpD = new JPanel();
        JLabel labelS = new JLabel();
        labelS.setText("Source Data");
        JLabel labelD = new JLabel();
        //labelD.setText("Destination Data");
        jpS.add(labelS);
        jpD.add(labelD);
        
        
        JLabel PlabelS = new JLabel("Path / Location");
		JTextField pathS = new JTextField(30);
		JButton bS = new JButton("Save");
		JLabel l1S = new JLabel("Standard File Names");
		//JTextField t1S = new JTextField(30);
		//JLabel l2S = new JLabel("PBI Number");
		JTextField t2S = new JTextField(30);
		
		jpD = new JPanel(new GridBagLayout());    
		GridBagConstraints d = new GridBagConstraints();
		d.insets = new Insets(10, 10, 10, 10);

		d.gridx = 0;
		d.gridy = 1;
		jpD.add(PlabelS, d);

		d.gridx = 0;
		d.gridy = 2;
		jpD.add(pathS, d);

		d.gridx = 0;
		d.gridy = 3;
		jpD.add(l1S, d);

		/*d.gridx = 0;
		d.gridy = 5;
		jpD.add(l2S, d);

		d.gridx = 0;
		d.gridy = 4;
		jpD.add(t1S, d);*/

		d.gridx = 0;
		d.gridy = 6;
		jpD.add(t2S, d);

		d.gridx = 0;
		d.gridy = 7;
		jpD.add(bS, d);
		
		jtp.addTab("Source", jpD);
		
	
        
		
		
        JLabel PlabelD = new JLabel("Path / Location");
		JTextField pathD = new JTextField(30);
		JButton bD = new JButton("GO");
		JLabel l1D = new JLabel("Sprint Number");
		JTextField t1D = new JTextField(30);
		JLabel l2D = new JLabel("PBI Number");
		JTextField t2D = new JTextField(30);
		
		jpD = new JPanel(new GridBagLayout());    
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);

		c.gridx = 0;
		c.gridy = 1;
		jpD.add(PlabelD, c);

		c.gridx = 0;
		c.gridy = 2;
		jpD.add(pathD, c);

		c.gridx = 0;
		c.gridy = 3;
		jpD.add(l1D, c);

		c.gridx = 0;
		c.gridy = 5;
		jpD.add(l2D, c);

		c.gridx = 0;
		c.gridy = 4;
		jpD.add(t1D, c);

		c.gridx = 0;
		c.gridy = 6;
		jpD.add(t2D, c);

		c.gridx = 0;
		c.gridy = 7;
		jpD.add(bD, c);
		
		
		jtp.addTab("Destination", jpD);
		
		 
			
			
        frame.add(jtp);
        
    }
    public static void main(String[] args) {
        
        Tabbedpane tp = new Tabbedpane();
        tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp.setVisible(true);
        
    }
   
}