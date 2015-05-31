import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.docx4j.XmlUtils;
import org.docx4j.model.structure.HeaderFooterPolicy;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.FooterPart;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.relationships.Namespaces;
import org.docx4j.openpackaging.parts.relationships.RelationshipsPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Text;
import org.w3c.dom.Document;


public class PlaceHolderReplacer 
{
	
	public static void main(String[] args) throws FileNotFoundException, InvalidFormatException, Docx4JException, IOException, JAXBException {
		process(new File("/home/dangling/shi/result/Sprint 15/1/PBI_1_Report.docx"), "/home/dangling/shi/result/Sprint 15/result.docx", "PBI_Number", "123");
	}
	
    public static void process( File documentToProcess,String destinationPath,String placeHolder,String value ) throws Docx4JException, FileNotFoundException, IOException, InvalidFormatException, JAXBException
    {
       // WordprocessingMLPackage template=getTemplate("/home/piyush/Testreport_Standard.docx");
        WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(documentToProcess));
        
        org.docx4j.wml.Document document=template.getMainDocumentPart().getJaxbElement();
       /* List list=document.getContent();
        for(Object obj:list)
    	{		
        	System.out.println("========================");
    	
    	
    		System.out.println(obj);
    	}*/
        printAllContent(document);
        
      //  replacePlaceholderHeaderFooter(template, placeHolder, value);
       // replacePlaceholder(template, value, placeHolder);
      // replaceHF(template,placeHolder,value);
       // replaceParagraph(placeholder, name, template, template.getMainDocumentPart());
        
      /// writeDocxToStream(template, destinationPath);
        
       
    }
    
    
    public static void printAllContent(org.docx4j.wml.Document document)
    {
    	List list=document.getContent();
    	for(Object obj:list)
    	{		System.out.println("========================");
    	
    	if(obj instanceof javax.xml.bind.JAXBElement)
    	//	printAllContent(((javax.xml.bind.JAXBElement)obj));
    		
    	System.out.println(obj);
    	}
    }
    
    
    
    private static String replacePh(String base, String placeHolder, String value) {

    	if(value==null) value="";

    	base=StringUtils.replace(base, placeHolder, value);
    	
    	return base;

    	}

    	private static void replaceHeader(HeaderPart headerPart, String placeholder ,String newValue ) throws JAXBException {

    	if(headerPart!=null){

    	String xml = XmlUtils.marshaltoString(headerPart.getJaxbElement(), true);

    	xml=replacePh(xml, placeholder, newValue);

    	Object obj = XmlUtils.unmarshallFromTemplate(xml, null);

    	//change JaxbElement

    	headerPart.setJaxbElement((org.docx4j.wml.Hdr) obj);

    	}

    	}

    	private static void replaceFooter(FooterPart footerPart, String placeholder ,String newValue ) throws JAXBException {

    	if(footerPart!=null){

    	String xml = XmlUtils.marshaltoString(footerPart.getJaxbElement(), true);

    	xml=replacePh(xml, placeholder, newValue);

    	Object obj = XmlUtils.unmarshallFromTemplate(xml, null);

    	//change JaxbElement

    	footerPart.setJaxbElement((org.docx4j.wml.Ftr) obj);

    	}

    	}



    	public static void replacePlaceholderHeaderFooter(WordprocessingMLPackage template, String placeholder ,String newValue ) throws JAXBException {

    	if(newValue==null) newValue="";

    	List<SectionWrapper> sectionWrappers = template.getDocumentModel().getSections();

    	
    	for (SectionWrapper sw : sectionWrappers) {

    	HeaderFooterPolicy hfp = sw.getHeaderFooterPolicy();

    	HeaderPart firstHeader= hfp.getFirstHeader();

    	HeaderPart defaultHeader= hfp.getDefaultHeader();

    	HeaderPart evenHeader= hfp.getEvenHeader();

    	FooterPart firstFooter= hfp.getFirstFooter();
    	
    	

    	FooterPart defaultFooter= hfp.getDefaultFooter();

    	FooterPart evenFooter= hfp.getEvenFooter();

    	replaceHeader(firstHeader,placeholder,newValue);

    	replaceHeader(defaultHeader,placeholder,newValue);

    	replaceHeader(evenHeader,placeholder,newValue);
    	
    	replaceHeader(hfp.getHeader(3), placeholder, newValue);

    	replaceFooter(firstFooter,placeholder,newValue);

    	replaceFooter(defaultFooter,placeholder,newValue);

    	replaceFooter(evenFooter,placeholder,newValue);

    	}

    	}

    	private static WordprocessingMLPackage getTemplate(String name) throws Docx4JException, FileNotFoundException {
    			WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new File(name)));
    			return template;
    		}

    	private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
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

    	private static void replacePlaceholder(WordprocessingMLPackage template, String name, String placeholder ) {
    			List<Object> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);
    	 
    			for (Object text : texts) {
    				Text textElement = (Text) text;
    				if (textElement.getValue().equals(placeholder)) {
    					textElement.setValue(name);
    				}
    			}
    		}

    	private static void writeDocxToStream(WordprocessingMLPackage template, String target) throws IOException, Docx4JException {
    			File f = new File(target);
    			template.save(f);
    		}
   
}
