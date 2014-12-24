package cn.edu.buaa.sei.exLmf.ogm.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LDataObject;
import cn.edu.buaa.sei.exLmf.metamodel.LEnumLiteral;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;
import cn.edu.buaa.sei.exLmf.ogm.IObjectGroup;
import cn.edu.buaa.sei.exLmf.ogm.IObjectWorld;
import cn.edu.buaa.sei.exLmf.ogm.OGResource;
import cn.edu.buaa.sei.exLmf.ogm.OGResourceWriter;
import cn.edu.buaa.sei.exLmf.ogm.OG_Directory;

public class XMLFileWriter implements OGResourceWriter{
	
	OG_Directory resource;
	IObjectWorld cache;
	
	public XMLFileWriter(IObjectWorld cache) throws Exception{
		if(cache==null)throw new Exception("Null cache is invalid");
		this.cache = cache;
	}

	@Override
	public void setResource(OGResource resource) throws Exception {
		if(resource==null)throw new Exception("Null resource is invalid");
		if(!(resource instanceof OG_Directory))throw new Exception("Directory is required");
		this.resource = (OG_Directory) resource;
	}
	@Override
	public OGResource getResource() {return this.resource;}
	@Override
	public IObjectWorld getCache() {return this.cache;}

	@Override
	public void write() throws Exception {
		if(resource==null)throw new Exception("Null resource cannot be written...");
		System.out.println("Try to write the data into directory: "+this.resource.getDirectory().getAbsolutePath());
		
		Map<LClass,IObjectGroup> groups = this.cache.getGroups();
		Set<LClass> ids = groups.keySet();
		for(LClass id:ids){
			IObjectGroup group = groups.get(id);
			File file = new File(resource.getDirectory().getAbsoluteFile()+"\\"+this.getFileName(id)+".xml");
			System.out.println("Start to interpret \""+id.getAbsolutePath()+"\" into File: "+file.getAbsolutePath());
			
			this.writeOG(group, file);
			System.out.println("File: "+file.getAbsolutePath()+" written completed...");
		}
	}
	protected void writeOG(IObjectGroup group,File file) throws Exception{
		if(group==null||file==null)throw new Exception("Null group|file is invalid");
		Set<LClassObject> uset = group.getObjects();
		
		/**
		 * Creating JDOM Tree
		 * */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();
		Element root = document.createElement(OG_XMLFile.ROOT);
		
		/**
		 * Translate the LClassObject into JDOM Tree
		 * */
		for(LClassObject val:uset){
			Element elm = this.writeLObject(val, group, document);
			root.appendChild(elm);
		}
		
		/**
		 * Writting the JDOM Data into file
		 * */
		document.appendChild(root);
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new FileOutputStream(file));
		transformer.transform(source, result);
	}
	
	protected String getContent(LObject val,IObjectWorld context) throws Exception{
		if(val==null||context==null)return null;
		if(val instanceof LClassObject){
			LClass type = ((LClassObject) val).getType();
			if(!context.containModelClass(type)){
				System.err.println("Reference Type \""+type.getAbsolutePath()+"\" is not loaded in current template.");
				return null;
			}
			
			IObjectGroup r_group = context.getObjectGroup(type);
			if(!r_group.isTagged((LClassObject) val)){
				System.err.println("Reference \""+type.getAbsolutePath()+"@"
						+val.hashCode()+"\" has not been added into Name-Space...");
				return null;
			}
			String rid = r_group.getTag((LClassObject) val);
			return rid;
		}
		else if(val instanceof LDataObject){
			
			if(((LDataObject) val).getValue()==null)return null;
			else if(((LDataObject) val).getValue() instanceof LEnumLiteral){
				LEnumLiteral literal = (LEnumLiteral) ((LDataObject) val).getValue();
				return literal.getLiteral();
			}
			else
				return ((LDataObject) val).getValue().toString();
		}
		else{
			return null;
		}
	}
	protected Element writeLObject(LClassObject val,IObjectGroup group,Document document) throws Exception{
		if(val==null||document==null||group==null)return null;
		
		LClass type = val.getType();
		String type_name = this.getTypeName(type);
		Element elm = document.createElement(type_name);
		if(group.isTagged(val))elm.setAttribute(OG_XMLFile.ID, group.getTag(val));
		
		List<LStructuralFeature> fs = type.getAllFeatures();
		for(int i=0;i<fs.size();i++){
			LStructuralFeature f = fs.get(i);
			if(f.getUpperBound()>1||f.getUpperBound()==LMultipleObject.UNBOUNDED){
				LMultipleObject list = (LMultipleObject) val.get(f);
				Collection<LObject> items = list.getAllObjects();
				
				Element item_elm = document.createElement(f.getName());
				for(LObject item:items){
					String content = this.getContent(item, group.getContainer());
					//System.out.println("###: "+content);
					if(content==null)continue;
					
					Element ei = document.createElement(OG_XMLFile.ITEM);
					ei.setTextContent(content);
					item_elm.appendChild(ei);
				}
				elm.appendChild(item_elm);
			}
			else{
				LObject item = val.get(f);
				String content = this.getContent(item, group.getContainer());
				//System.out.println("###: "+content);
				if(content==null)continue;
				
				Element item_elm = document.createElement(f.getName());
				item_elm.setTextContent(content);
				elm.appendChild(item_elm);
			}
		}
		
		return elm;
	}
	protected String getTypeName(LClass type){
		String type_name = type.getAbsolutePath();
		int k = type_name.indexOf(".");
		if(k>=0)type_name = type_name.substring(k+1).trim();
		else type_name = type_name.trim();
		
		return type_name;
	}
	protected String getFileName(LClass type){
		String type_name = this.getTypeName(type);
		if(type_name==null)return null;
		return type_name.replaceAll("\\.", "_");
	}

}
