package cn.edu.buaa.exLmf.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SchemaVerifiers {

	public static void main(String[] args) {
		verify(new File("testobj.xml"),new File("schema.xsd"));
	}
	
	public static void verify(File xml,File xsd){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			Schema schema = schemaFactory.newSchema(new SAXSource(new InputSource(new FileInputStream(xsd))));
			factory.setSchema(schema);
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setErrorHandler(new ErrorHandler() {  
		          @Override  
		          public void warning(SAXParseException exception) throws SAXException {  
		              throw new RuntimeException(exception);  
		          }  
		          @Override  
		          public void fatalError(SAXParseException exception) throws SAXException {  
		              throw new RuntimeException(exception);  
		          }  
		          @Override  
		          public void error(SAXParseException exception) throws SAXException {  
		              throw new RuntimeException(exception);  
		          }  
		      });
			
			Document document = builder.parse(new FileInputStream(xml));
			System.out.println(document);
			Element root = (Element) document.getChildNodes().item(0);
			System.out.println(root.getTagName());
		} catch (FileNotFoundException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
