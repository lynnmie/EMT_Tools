package cn.edu.buaa.sei.exLmf.schema.impl;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import cn.edu.buaa.sei.exLmf.manager.IObjectSpace;
import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LDataObject;
import cn.edu.buaa.sei.exLmf.metamodel.LDataType;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LMFException;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LPrimitiveTypeImpl;
import cn.edu.buaa.sei.exLmf.schema.IObjectWriter;

public class XMLObjectWriter implements IObjectWriter{
	OutputStream out;
	IObjectSpace os;
	String name;
	
	Document doc;
	Element root;
	
	public XMLObjectWriter(String name){this.name=name;}
	/*Tool Functions*/
	Exception getException(String func,String arg,String reason){
		return LMFException.create("XML-Object-Importer "+this.name, "LMFCreator", func, arg, reason);
	}

	@Override
	public void setOutputStream(OutputStream out) {this.out=out;}
	@Override
	public void setObjectSpace(IObjectSpace os) {this.os=os;}
	@Override
	public boolean validate() {return (this.os!=null)&&(this.out!=null);}

	@Override
	public void translate() throws Exception {
		if(!this.validate()){
			throw this.getException("translate()", "this.out||this.os", "Validation failed");
		}
		
		/*Process*/
		this.initRoot();
		Collection<LClassObject> values = this.os.getAllObjects();
		for(LClassObject obj:values){
			Element elm = this.generateObject(obj);
			if(elm!=null)this.root.getChildren().add(elm);
		}
		
		/*Producing document to output stream*/
		XMLOutputter output = new XMLOutputter();
		Writer writer = new PrintWriter(this.out);
		output.output(this.doc, writer);
		writer.flush();
		writer.close();
	}
	void initRoot(){
		this.doc=new Document();
		this.root=new Element(XMLFormatDefinition.ROOT);
		doc.setRootElement(root);
	}
	Element generateAttribute(LAttribute attribute,LDataObject obj) throws Exception{
		if(attribute==null||obj==null)return null;
		LDataType type = obj.getType();
		Element elm = new Element(attribute.getName());
		if(obj.getValue()==null)return elm;
		
		String code = this.getCode(type, obj);
		elm.setText(code);
		return elm;
	}
	Element generateAttributes(LAttribute attribute,LMultipleObject list) throws Exception{
		if(attribute==null||list==null)return null;
		LDataType type = attribute.getDataType();
		Element elm = new Element(attribute.getName());
		
		Collection<LObject> values = list.getAllObjects();
		for(LObject vi:values){
			if(vi==null)continue;
			if(!(vi instanceof LDataObject))
				throw this.getException("generateAttributes(attribute,list)", 
						"list", "list contains class object");
			
			LDataObject obj = (LDataObject) vi;
			if(obj.getValue()!=null){
				Element ei = new Element(XMLFormatDefinition.ITEM);
				String code = this.getCode(type, obj);
				ei.setText(code);
				elm.getChildren().add(ei);
			}
		}
		return elm;
	}
	String getCode(LDataType type,LDataObject obj) throws Exception{
		if(type==null||obj==null)return null;
		String code = null;
		if(type==LPrimitiveTypeImpl.BOOL){code = obj.boolVal().toString();}
		else if(type==LPrimitiveTypeImpl.INT){code = obj.integerVal().toString();}
		else if(type==LPrimitiveTypeImpl.LONG){code = obj.longVal().toString();}
		else if(type==LPrimitiveTypeImpl.FLOAT){code = obj.floatVal().toString();}
		else if(type==LPrimitiveTypeImpl.DOUBLE){code = obj.doubleVal().toString();}
		else if(type==LPrimitiveTypeImpl.STRING){code = obj.stringVal();}
		else if(type instanceof LEnum){code = obj.literalVal().getName();}
		else throw this.getException("getCode(type,obj)", "obj.type", 
				"Cannot generate element from type: \""+type.getClass().getName()+"\"");
		return code;
	}
	Element generateReference(LReference reference,LClassObject obj) throws Exception{
		if(reference==null||obj==null)return null;
		if(!this.os.containObject(obj))
			throw this.getException("generateReference(reference,obj)", "obj", "Not contained in object space");
		
		Element elm = new Element(reference.getName());
		elm.setText(XMLFormatDefinition.ID_PREFIX+this.os.getIDof(obj).toString());
		return elm;
	}
	Element generateReferences(LReference reference,LMultipleObject list) throws Exception{
		if(reference==null||list==null)return null;
		Element elm = new Element(reference.getName());
		
		Collection<LObject> values = list.getAllObjects();
		for(LObject vi:values){
			if(vi==null)continue;
			if(!(vi instanceof LClassObject))
				throw this.getException("generateReferences(reference,list)","list", "contains not-class object");
			
			LClassObject obj = (LClassObject) vi;
			if(!this.os.containObject(obj))
				throw this.getException("generateReferences(reference,list)", "list[i]", "Not contained in object space");
			Element item = new Element(XMLFormatDefinition.ITEM);
			item.setText(XMLFormatDefinition.ID_PREFIX+this.os.getIDof(obj).toString());
			elm.getChildren().add(item);
		}
		
		return elm;
	}
	
	Element generateObject(LClassObject obj) throws Exception{
		if(obj==null)return null;
		if(!this.os.containObject(obj))
			throw this.getException("generateObject(obj)", "obj", "Undefinde in Object Space");
		LClass type = obj.getType();
		
		Element elm = new Element(type.getName());
		elm.setAttribute(XMLFormatDefinition.ID_ATTR, XMLFormatDefinition.ID_PREFIX+this.os.getIDof(obj).toString());
		
		List<LStructuralFeature> features = type.getAllFeatures();
		for(int i=0;i<features.size();i++){
			LStructuralFeature fi = features.get(i);
			if(fi instanceof LAttribute){
				Element item = null;
				if(fi.getUpperBound()>1||fi.getUpperBound()==LMultipleObject.UNBOUNDED)
					item = this.generateAttributes((LAttribute)fi, (LMultipleObject)obj.get(fi));
				else
					item = this.generateAttribute((LAttribute)fi, (LDataObject)obj.get(fi));
				
				if(item!=null)elm.getChildren().add(item);
			}
			else if(fi instanceof LReference){
				Element item = null;
				if(fi.getUpperBound()>1||fi.getUpperBound()==LMultipleObject.UNBOUNDED)
					item = this.generateReferences((LReference)fi, (LMultipleObject)obj.get(fi));
				else
					item = this.generateReference((LReference)fi, (LClassObject)obj.get(fi));
				
				if(item!=null)elm.getChildren().add(item);
			}
			else throw this.getException("generateObject(obj)", "obj.type.features["+i+"]", 
					"Feature \""+fi.getName()+"\" is invalid type ["+fi.getClass().getName()+"] for generating element");
		}
		
		return elm;
	}
	
	

}
