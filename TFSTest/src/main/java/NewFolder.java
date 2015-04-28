import java.io.*;
public class NewFolder 
{
		 
	    public static void main(String args[])
	    {
	    String Value = " 56";
	    String Name = "Sprint";
	    Name = Name.concat(Value);
	    
	    System.out.println(Name);
	    File f = new File("C:\\Name");
	    try{
	        if(f.exists()==false){
	            f.mkdir();
	        System.out.println("Directory Created");
	        
	        }
	        else{
	        System.out.println("Directory is not created");
	        }
	    }catch(Exception e){
	        e.printStackTrace();
	       }
	    }
	}



