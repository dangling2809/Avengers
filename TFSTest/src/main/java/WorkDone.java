import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Text;

import java.util.List;

/**
 * Hello world!
 *
 */
public class WorkDone
{
	
    public static void main( String[] args )
    {
    	
    	try
    	{
    		
    		WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new File("D:\\Parth.docx")));
    		VariablePrepare.prepare(template);
    		replacePlaceholder(template,"12345","PBI_Number");
    		System.out.println("mila");
    		replacePlaceholder(template,"77777","73168");
    		System.out.println("fir mila");
    		writeDocxToStream(template,"D:\\Done.docx");
    		System.out.println("Printed");
    	}
    	catch(Exception e)
    	{
    		
    	}
    	

    }
    
   /* private static WordprocessingMLPackage getTemplate(String name) throws Docx4JException, FileNotFoundException {  
    	try
    	{
    		WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new File(name)));  
            return template;	
    	}
    	catch(Exception e){
    	return null;	
    	}
    }*/
    	
    	
    
    private static void writeDocxToStream(WordprocessingMLPackage template,
			String string) {
		// TODO Auto-generated method stub
		
	}

	private static void replacePlaceholder(WordprocessingMLPackage template, String name, String placeholder ) {  
	    List<Object> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);  
	  
	    for (Object text : texts) {  
	        Text textElement = (Text) text;  
	        if (textElement.getValue().equals(placeholder)) {  
	            textElement.setValue(name);  
	        }  
	    }  
	} 
	
    
    
    
     public static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
    		
    			List<Object> result = new ArrayList<Object>();  
        	    if (obj instanceof JAXBElement) obj = ((JAXBElement<?>) obj).getValue();  
        	  
        	    if (obj.getClass().equals(toSearch))  
        	        result.add(obj);  
        	    else if (obj instanceof ContentAccessor) {  
        	        List<?> children = ((ContentAccessor) obj).getContent();  
        	        for (Object child : children) {  
        	            result.addAll(getAllElementFromObject(child, toSearch));  
        	        }  
        	  
        	    }  
        	    return result;	
    		  
    	}
    	
}