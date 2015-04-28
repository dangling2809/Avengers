
	import java.awt.FlowLayout;
	import javax.swing.JFrame;
	import javax.swing.JTextField;
	public class TextField extends JFrame {
    private static final long serialVersionUID = 1L;
    public TextField() {
	        // set flow layout for the frame
    this.getContentPane().setLayout(new FlowLayout(200));
	        // create empty JTextField
	        JTextField field1 = new JTextField(10);
	        field1.setText("Sprint");
	        // create JTextField with default text
	        JTextField field2 = new JTextField("PBI",10);
	        // create JTextField with specified number of columns
	        JTextField field3 = new JTextField("Defect Count",10);
	        // create JTextField with default text and columns
	        JTextField field4 = new JTextField("TestCase Count", 10);
	        // add textfields to frame
	        add(field1);

	        add(field2);

	        add(field3);

	        add(field4);
	    }

	    private static void createAndShowGUI() {
	  //Create and set up the window.
	  JFrame frame = new TextField();
	  //Display the window.

	  frame.pack();

	  frame.setVisible(true);

	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	    }
	
	    public static void main(String[] args) {
	
	  //Schedule a job for the event-dispatching thread:
	
	 
	 //creating and showing this application's GUI.
	
	 

	  javax.swing.SwingUtilities.invokeLater(new Runnable() {

	public void run() {

	    createAndShowGUI();

	}

	  });
	
	    }
	
	 
	
	}



