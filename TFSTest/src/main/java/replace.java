import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

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


public class replace 
{
    public static void main( String[] args ) throws Docx4JException, FileNotFoundException, IOException, InvalidFormatException, JAXBException
    {
        //WordprocessingMLPackage template=getTemplate("/home/piyush/Testreport_Standard.docx");
        WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new File("C://New folder/Testreport_Standard - Copy.docx")));
        String name="1234";
        String placeholder="PBI_Number";
        replacePlaceholder(template, name, placeholder);
       replaceHF(template,placeholder,name);
       // replaceParagraph(placeholder, name, template, template.getMainDocumentPart());
        
       writeDocxToStream(template, "D://netemplate.docx");
        
       
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
    
    private static void writeDocxToStream(WordprocessingMLPackage template, String target) throws IOException, Docx4JException {
		File f = new File(target);
		template.save(f);
	}
    private static String replacePh(String base, String placeHolder, String value) {

if(value==null) value="";

if(!base.contains(placeHolder)) {

return base;

}

return base.replaceAll(placeHolder, value);

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



public static  void replacePlaceholderHeaderFooter(WordprocessingMLPackage template, String placeholder ,String newValue ) throws JAXBException {

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

replaceFooter(firstFooter,placeholder,newValue);

replaceFooter(defaultFooter,placeholder,newValue);

replaceFooter(evenFooter,placeholder,newValue);

}



}
private static void replaceHF(WordprocessingMLPackage template, String placeholder ,String newValue) throws JAXBException
{
    
    RelationshipsPart rp = template.getMainDocumentPart()
					.getRelationshipsPart();
			for (Relationship r : rp.getRelationships().getRelationship()) {
	
				if (r.getType().equals(Namespaces.HEADER)) {
					replaceHeader((HeaderPart) rp.getPart(r),placeholder,newValue);
				} else if (r.getType().equals(Namespaces.FOOTER)) {
					replaceFooter((FooterPart) rp.getPart(r),placeholder,newValue);
				}
			}
}
   
}
