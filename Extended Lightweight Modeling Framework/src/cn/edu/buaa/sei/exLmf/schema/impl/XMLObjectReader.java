package cn.edu.buaa.sei.exLmf.schema.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.edu.buaa.sei.exLmf.manager.IObjectSpace;
import cn.edu.buaa.sei.exLmf.manager.impl.ObjectSpace;
import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LDataObject;
import cn.edu.buaa.sei.exLmf.metamodel.LDataType;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LMFException;
import cn.edu.buaa.sei.exLmf.schema.IObjectReader;

public class XMLObjectReader implements IObjectReader{
	
	LPackage p;
	InputStream in;
	String name;
	
	IObjectSpace os;
	
	Document doc;
	Element root;
	
	Map<String,Integer> tmap = new HashMap<String,Integer>(); 
	
	public XMLObjectReader(String name){this.name=name;}
	/*Tool Functions*/
	Exception getException(String func,String arg,String reason){
		return LMFException.create("XML-Object-Importer "+this.name, "LMFCreator", func, arg, reason);
	}

	@Override
	public void setTemplate(LPackage template) {this.p=template;}
	@Override
	public void setInputStream(InputStream in) {this.in=in;}
	@Override
	public boolean validate() {
		return this.p!=null&&this.in!=null;
	}

	@Override
	public IObjectSpace translate() throws Exception {
		// TODO Auto-generated method stub
		if(!this.validate())
			throw this.getException("IObjectSpace", "", "Validation failed");
		
		this.init();
		this.generateObjectSet();
		this.decodeObjectSet();
		
		return this.os;
	}
	
	void init() throws Exception{
		if(this.os!=null)this.os.clearSpace();
		this.os=new ObjectSpace("",this.p);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
		
        this.doc = db.parse(in);
		this.root = (Element) doc.getElementsByTagName(XMLFormatDefinition.ROOT).item(0);
	}
	void generateObjectSet() throws Exception{
		NodeList list = this.root.getChildNodes();
		for(int i=0;i<list.getLength();i++)
			if(list.item(i) instanceof Element){
				this.generateObject((Element) list.item(i));
			}
	}
	void decodeObjectSet() throws Exception{
		NodeList list = this.root.getChildNodes();
		for(int i=0;i<list.getLength();i++)
			if(list.item(i) instanceof Element)
				this.decodeObject((Element) list.item(i));
	}
	
	void generateObject(Element elm) throws Exception{
		if(elm==null)return;
		
		String name = elm.getTagName();
		String id = elm.getAttribute(XMLFormatDefinition.ID_ATTR);
		
		if(this.tmap.containsKey(id))
			throw this.getException("generateObject(elm)", "elm.id", "Conflict ID: \""+id+"\"");
		
		LClassifier type = this.p.getClassifierByName(name);
		if(type==null||!(type instanceof LClass))
			throw this.getException("generateObject(elm)", "elm.tagName", "Undefined Class Name: \""+name+"\"");
		
		LClass ctype = (LClass) type;
		
		LClassObject obj = this.os.createClassObject(ctype);
		Integer tid = this.os.getIDof(obj);
		this.tmap.put(id, tid);
		/*NodeList children = elm.getChildNodes();
		for(int i=0;i<children.getLength();i++){
			if(!(children.item(i) instanceof Element))continue;
			Element ei = (Element) children.item(i);
			
			String cname = ei.getTagName();
			LStructuralFeature f = ctype.getFeatureByName(cname);
			
			if(f instanceof LAttribute){
				if(f.getUpperBound()>1||f.getUpperBound()==LMultipleObject.UNBOUNDED){}
				else{}
			}
			else if(f instanceof LReference){
				if(f.getUpperBound()>1||f.getUpperBound()==LMultipleObject.UNBOUNDED){}
				else{}
			}
			else throw this.getException("generateObject(elm)", "elm.features["+i+"]", 
					"Unknown Feature \""+cname+"\" in class \""+type.getName()+"\"");
		}*/
	}
	LDataObject decodeAttribute(LAttribute attr,String value) throws Exception{
		if(attr==null||value==null)return null;
		LDataType type = attr.getDataType();
		return this.os.createDataObject(type, value);
	}
	LClassObject decodeReference(LReference ref,String id) throws Exception{
		if(ref==null||id==null)return null;
		if(!this.tmap.containsKey(id))
			throw this.getException("decodeReference(ref,id)", "id", "Undefined ID: \""+id+"\"");
		
		Integer tid = this.tmap.get(id);
		if(!this.os.containObject(tid))
			throw this.getException("decodeReference(ref,id)", "tid", "Undefined Translated ID: \""+tid+"\"");
		
		return (LClassObject) this.os.getObject(tid);
	}
	void decodeObject(Element elm) throws Exception{
		if(elm==null)return;
		
		String id = elm.getAttribute(XMLFormatDefinition.ID_ATTR);
		if(!this.tmap.containsKey(id))
			throw this.getException("decodeObject(elm)", "elm", "Un-generated Map ID: \""+id+"\"");
		
		Integer tid = this.tmap.get(id);
		if(!this.os.containObject(tid))
			throw this.getException("decodeObject(elm)", "elm", "Un-generated Translated ID: \""+tid+"\"");
		
		LClassObject obj = (LClassObject) this.os.getObject(tid);
		
		NodeList children = elm.getChildNodes();
		for(int i=0;i<children.getLength();i++)
			if(children.item(i) instanceof Element){
				Element ei = (Element) children.item(i);
				String cname = ei.getTagName();
				
				LStructuralFeature f = obj.getType().getFeatureByName(cname);
				LObject val = null;
				if(f instanceof LAttribute){
					if(f.getUpperBound()>1||f.getUpperBound()==LMultipleObject.UNBOUNDED){
						LMultipleObject list = (LMultipleObject) obj.get(f);
						NodeList ei_child = ei.getElementsByTagName(XMLFormatDefinition.ITEM);
						for(int j=0;j<ei_child.getLength();j++)
							if(ei_child.item(j) instanceof Element){
								LDataObject vi = this.decodeAttribute((LAttribute) f, ei_child.item(j).getTextContent());
								list.addObject(vi);
							}
					}
					else{
						val = this.decodeAttribute((LAttribute) f, ei.getTextContent());
						obj.set(f, val);
					}
				}
				else if(f instanceof LReference){
					if(f.getUpperBound()>1||f.getUpperBound()==LMultipleObject.UNBOUNDED){
						LMultipleObject list = (LMultipleObject) obj.get(f);
						NodeList ei_child = ei.getElementsByTagName(XMLFormatDefinition.ITEM);
						for(int j=0;j<ei_child.getLength();j++)
							if(ei_child.item(j) instanceof Element){
								LClassObject vi = this.decodeReference((LReference) f, ei_child.item(j).getTextContent());
								list.addObject(vi);
							}
					}
					else{
						val = this.decodeReference((LReference) f, ei.getTextContent());
						obj.set(f, val);
					}
				}
				else if(f==null)throw this.getException("decodeObject(elm)", "feature["+cname+"]", "Unknown Feature by Name: "+cname);
				else throw this.getException("decodeObject(elm)", "feature["+f.getName()+"]", "Unknown Feature Type: "+f.getClass().getName());
			}
	}
	

}
