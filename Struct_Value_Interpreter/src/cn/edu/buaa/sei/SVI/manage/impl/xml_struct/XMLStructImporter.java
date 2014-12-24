package cn.edu.buaa.sei.SVI.manage.impl.xml_struct;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.edu.buaa.sei.SVI.manage.IStructImporter;
import cn.edu.buaa.sei.SVI.manage.SVIResource;
import cn.edu.buaa.sei.SVI.manage.StructManager;
import cn.edu.buaa.sei.SVI.manage.XMLStructTranslator;
import cn.edu.buaa.sei.SVI.manage.impl.SVIStream;
import cn.edu.buaa.sei.SVI.manage.impl.StructManagerImpl;
import cn.edu.buaa.sei.SVI.struct.core.Struct;

public class XMLStructImporter implements IStructImporter{
	
	SVIStream in;
	Element root;
	XMLStructTranslator translator;
	
	@Override
	public void setInput(SVIResource in) throws Exception {
		if(in==null)throw new Exception("Null input stream is invalid");
		if(!(in instanceof SVIStream))throw new Exception("SVIFile required");
		this.in=(SVIStream) in;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(this.in.getInputStream());
		
		root = (Element) doc.getElementsByTagName(XMLStructTags.ROOT).item(0);
		this.translator = new XMLStructTranslatorImpl(doc);
	}

	@Override
	public StructManager read() throws Exception {
		if(this.root==null)throw new Exception("File has not been read");
		NodeList list = this.root.getChildNodes();
		
		StructManager manager = new StructManagerImpl();
		this.translator.restart();
		
		for(int i=0;i<list.getLength();i++)
			if(list.item(i) instanceof Element){
				Element top = (Element) list.item(i);
				Struct result = this.translator.retranslate(top);
				if(result==null)throw new Exception("Translation failed: <"+top.getTagName()+">");
				manager.putTopStruct(result);
			}
		
		for(int i=0;i<list.getLength();i++)
			if(list.item(i) instanceof Element){
				this.translator.reupdate((Element) list.item(i));
			}
		
		return manager;
	}

}
