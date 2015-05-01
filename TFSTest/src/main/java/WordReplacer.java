import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


public class WordReplacer {
	
	public static void process(String sourceDocxFile,String targetDocxFile,Map<String,String> placeHolderToValueMap) throws IOException
	{
		ZipFile zipFile = new ZipFile(sourceDocxFile);//"/home/dangling/shi/result/Sprint 15/1/PBI_1_Report.docx");
		final ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(targetDocxFile));//"/home/dangling/shi/result/Sprint 15/1/PBI_1_Report_Result.docx"));
		for(Enumeration e = zipFile.entries(); e.hasMoreElements(); ) 
		{
		   ZipEntry entryIn = (ZipEntry) e.nextElement();
		   System.out.println(entryIn.getName());
		   /* if(!entryIn.getName().equalsIgnoreCase("word/document.xml") && !entryIn.getName().contains("word/header")){
		    	 ZipEntry destEntry = new ZipEntry (entryIn.getName());
		    			// zout.putNextEntry(destEntry);
		        zos.putNextEntry(destEntry);
		        InputStream is = zipFile.getInputStream(entryIn);
		        byte [] buf = new byte[1024];
		        int len;
		        while((len = (is.read(buf))) > 0) {            
		        	 zos.write(buf, 0, (len < buf.length) ? len : buf.length);
		        }
		    }
		    else{*/
		       
		    	 zos.putNextEntry(new ZipEntry(entryIn.getName()));
		                InputStream is = zipFile.getInputStream(entryIn);
		                byte[] buf = new byte[1024];
		                int len;
		                while ((len = (is.read(buf))) > 0) {
		                    String s = new String(buf);
		                    String result=null;
		                    for(Map.Entry<String, String> entry:placeHolderToValueMap.entrySet())
		                    {
		                    	Pattern p = Pattern.compile(entry.getKey(), Pattern.CASE_INSENSITIVE);
		                    	Matcher m = p.matcher(s);
		                    	result = m.replaceAll(entry.getValue());
		                    	s=result;
		                    	
			                   /* if (s.contains("(?i)PBI_Number")) {
			                        buf = s.replaceAll("(?i)PBI_Number", "1234").getBytes();
			                    }*/
		                    }
		                    buf=s.getBytes();
		                    zos.write(buf, 0, (len < buf.length) ? len : buf.length);
		                }
		               
		  // }
		    zos.closeEntry();
		}
		zos.close();
	}
	
	public static void main(String[] args) throws IOException {
		Map<String,String> placeHolderToValueMap=new HashMap<String, String>();
		placeHolderToValueMap.put("PBI_Number", "1234");
		placeHolderToValueMap.put("PBI_Title", "MyTitle");
		//placeHolderToValueMap.put("Names of test team members", "Piyush,Shivangi");
		placeHolderToValueMap.put("Author_name", "DanB");
		placeHolderToValueMap.put("Names of test team members", "Piyush,Shivangi");
		process("/home/dangling/shi/result/Sprint 15/1/PBI_1_Report.docx", "/home/dangling/shi/result/Sprint 15/1/PBI_1_Report_Result.docx", placeHolderToValueMap);
	}
}


