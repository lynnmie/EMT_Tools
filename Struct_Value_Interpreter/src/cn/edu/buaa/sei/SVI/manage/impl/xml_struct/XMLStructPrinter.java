package cn.edu.buaa.sei.SVI.manage.impl.xml_struct;

import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cn.edu.buaa.sei.SVI.manage.IStructPrinter;
import cn.edu.buaa.sei.SVI.manage.SVIResource;
import cn.edu.buaa.sei.SVI.manage.StructManager;
import cn.edu.buaa.sei.SVI.manage.XMLStructTranslator;
import cn.edu.buaa.sei.SVI.manage.impl.SVIStream;
import cn.edu.buaa.sei.SVI.struct.core.Struct;

public class XMLStructPrinter implements IStructPrinter{
	SVIStream out;
	XMLStructTranslator translator;
	Document doc;
	Element root;

	@Override
	public void setOutputStream(SVIResource out) throws Exception {
		if(out==null)throw new Exception("Null resource is invalid");
		if(!(out instanceof SVIStream))throw new Exception("SVIStream required");
		this.out=(SVIStream) out;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.newDocument();
		
		this.root = doc.createElement(XMLStructTags.ROOT);
		this.doc.appendChild(root);
		this.translator = new XMLStructTranslatorImpl(this.doc);
	}

	@Override
	public void write(StructManager manager) throws Exception {
		if(manager==null)throw new Exception("Null manager is invalid");
		
		this.translator.restart();
		Set<Struct> structs = manager.getTopStructs();
		Set<Element> elements = new HashSet<Element>();
		
		for(Struct struct:structs){
			Element element = this.translator.translate(struct);
			elements.add(element);
		}
		
		for(Struct struct:structs){
			this.translator.update(struct);
		}
		
		for(Element ei:elements)
			this.root.appendChild(ei);
		
		/**
		 * Write the XML File
		 * */
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(this.out.getOutputStream());
		transformer.transform(source, result);
	}
}
