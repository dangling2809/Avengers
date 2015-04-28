import java.awt.Desktop;
import java.io.*;
import java.util.*;
import java.util.regex.*;

import javax.annotation.Resource;


public class CreatWord {

	public static void main(String[] args) throws Exception {	   

		String find = "";
		String replace = "";
		Replace(find , replace);
	 
	}
	
	public static void Replace (String source, String Dest) throws IOException
	{
		//java.io.File file = new File("C:\\MyFolder\\abc.docx");
		
		java.io.File file = new File("C:\\MyFolder\\abc.docx");
        java.io.File outFile = new File("C:\\MyFolder\\abcd.docx");
       FileInputStream fis = new FileInputStream(file);
        FileOutputStream fout = new FileOutputStream(outFile);

        byte bytes[] = new byte[1024];
        int con=0;
        con=fis.read(bytes);
        System.out.println("HI");
        int i = 0; 
        while(con!=-1){
        	
        	
        fout.write(bytes,0,con);
        con=fis.read(bytes);
        System.out.println(con);
        }
		//FileInputStream fis = new FileInputStream("C:\\MyFolder\\abc.docx");
		
		//fis.read();
		//FileInputStream Fin = new FileInputStream("C:\\MyFolder\\abc.docx");
		//BufferedReader br = new BufferedReader(new FileReader("C:\\MyFolder\\abc.docx"));
		//int j;
		//while((j=Fin.read())!=-1)
			//System.out.print((char)j);

		

	}
}