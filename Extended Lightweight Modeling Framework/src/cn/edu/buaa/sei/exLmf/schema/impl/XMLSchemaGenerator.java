package cn.edu.buaa.sei.exLmf.schema.impl;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.XMLOutputter;

import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LDataType;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LEnumLiteral;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LMFException;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LPrimitiveTypeImpl;
import cn.edu.buaa.sei.exLmf.schema.ISchemaGenerator;

public class XMLSchemaGenerator implements ISchemaGenerator{
	LPackage p;
	OutputStream out;
	String name;
	
	Set<LClassifier> types = new HashSet<LClassifier>();
	
	Document document;
	Namespace ns;
	Element schema;
	
	
	public XMLSchemaGenerator(String name){this.name=name;}
	/*Tool Functions*/
	Exception getException(String func,String arg,String reason){
		return LMFException.create("XML-Object-Importer "+this.name, "LMFCreator", func, arg, reason);
	}
	
	@Override
	public void setModel(LPackage p) {this.p=p;}
	@Override
	public void setOPipe(OutputStream out) {this.out=out;}
	@Override
	public boolean validate() {
		if(this.p==null||this.out==null)return false;
		else return true;
	}

	@Override
	public void generateSchema() throws Exception {
		if(!this.validate()){
			throw this.getException("generateSchema()", "this.out||this.model", "Validation failed");
		}
		
		/*Creating Document*/
		this.document=new Document();
		this.ns = Namespace.getNamespace("xs",XMLFormatDefinition.SCHEMA_NSPACE);
		
		/*Process*/
		this.createSchemaRoot();
		this.updateTypes();
		this.generateRootElement();
		for(LClassifier type:this.types){
			if(type instanceof LClass)
				this.generateClass((LClass) type);
			else if(type instanceof LEnum)
				this.generateEnum((LEnum) type);
			else
				throw this.getException("generateSchema()", "type", 
						"Cannot generate element for type: \""+type.getClass().getName()+"\"");
		}
		
		/*Producing document to output stream*/
		XMLOutputter output = new XMLOutputter();
		Writer writer = new PrintWriter(this.out);
		output.output(document, writer);
		writer.flush();
		writer.close();
	}
	
	void createSchemaRoot(){
		this.schema=new Element(XMLFormatDefinition.SCHEMA);
		this.schema.setNamespace(ns);
		schema.setAttribute("elementFormDefault", "qualified");
		this.document.setRootElement(schema);
	}
	void updateTypes(){
		this.types.clear();
		List<LClassifier> ntypes = this.p.getTypes();
		for(int i=0;i<ntypes.size();i++){
			LClassifier ti = ntypes.get(i);
			if(ti instanceof LClass)
				if(((LClass) ti).isAbstract())
					continue;
			this.types.add(ti);
		}
	}
	void generateRootElement() throws Exception{
		Element root = new Element(XMLFormatDefinition.SCHEMA_ELEMENT);
		root.setNamespace(ns);
		root.setAttribute("name", XMLFormatDefinition.ROOT);
		root.setAttribute("type", XMLFormatDefinition.ROOT_TYPE);
		this.schema.getChildren().add(root);
		
		Element root_type = new Element(XMLFormatDefinition.SCHEMA_COMTYPE);
		root_type.setNamespace(ns);
		root_type.setAttribute("name", XMLFormatDefinition.ROOT_TYPE);
		this.schema.getChildren().add(root_type);
		
		Element seq = new Element(XMLFormatDefinition.SCHEMA_SEQUENCE);
		seq.setNamespace(ns);
		root_type.getChildren().add(seq);
		
		for(LClassifier type:this.types){
			if(type instanceof LClass){
				Element ei = new Element(XMLFormatDefinition.SCHEMA_ELEMENT);
				ei.setNamespace(ns);
				ei.setAttribute("name", type.getName());
				ei.setAttribute("type", type.getName());
				ei.setAttribute("minOccurs", "0");
				ei.setAttribute("maxOccurs", XMLFormatDefinition.UNBOUNDED);
				seq.getChildren().add(ei);
			}
			else if(type instanceof LEnum)continue;
			else
				throw this.getException("generateRootElement()", "types", 
						"Cannot generate element for type: \""+type.getClass().getName()+"\"");
		}
	}
	void generateClass(LClass type) throws Exception{
		Element elm = new Element(XMLFormatDefinition.SCHEMA_COMTYPE);
		elm.setNamespace(ns);
		elm.setAttribute("name", type.getName());
		this.schema.getChildren().add(elm);
		
		Element all = new Element(XMLFormatDefinition.SCHEMA_ALL);
		all.setNamespace(ns);
		elm.getChildren().add(all);
		
		Element id = new Element(XMLFormatDefinition.SCHEMA_ATTRIBUTE);
		id.setNamespace(ns);
		id.setAttribute("name", XMLFormatDefinition.ID_ATTR);
		id.setAttribute("type", XMLFormatDefinition.SCHEMA_ID_TYPE);
		id.setAttribute("use", "required");
		elm.getChildren().add(id);
		
		List<LStructuralFeature> features = type.getAllFeatures();
		
		for(int i=0;i<features.size();i++){
			LStructuralFeature fi = features.get(i);
			if(fi instanceof LAttribute){
				if(fi.getUpperBound()>1||fi.getUpperBound()==LMultipleObject.UNBOUNDED)
					all.getChildren().add(this.generateAttributes((LAttribute) fi));
				else all.getChildren().add(this.generateAttribute((LAttribute) fi));
			}
			else if(fi instanceof LReference){
				if(fi.getUpperBound()>1||fi.getUpperBound()==LMultipleObject.UNBOUNDED)
					all.getChildren().add(this.generateReferences((LReference) fi));
				else
					all.getChildren().add(this.generateReference((LReference) fi));
			}
			else throw this.getException("generateClass()", "type.features["+i+"]", 
					"Cannot generate element for type: \""+fi.getClass().getName()+"\"");
		}
		
	}
	void generateEnum(LEnum type){
		Element elm = new Element(XMLFormatDefinition.SCHEMA_SIMTYPE);
		elm.setNamespace(ns);
		elm.setAttribute("name", type.getName());
		this.schema.getChildren().add(elm);
		
		Element res = new Element(XMLFormatDefinition.SCHEMA_RESTRICT);
		res.setAttribute("base", XMLFormatDefinition.SCHEMA_STRING_TYPE);
		res.setNamespace(ns);
		elm.getChildren().add(res);
		
		for(LEnumLiteral l:type.getLiterals()){
			Element lm = new Element(XMLFormatDefinition.SCHEMA_ENUMERATION);
			lm.setNamespace(ns);
			lm.setAttribute("value", l.getName());
			res.getChildren().add(lm);
		}
	}
	Element generateAttribute(LAttribute attribute) throws Exception{
		if(attribute==null)return null;
		Element elm = new Element(XMLFormatDefinition.SCHEMA_ELEMENT);
		elm.setNamespace(ns);
		elm.setAttribute("name", attribute.getName());
		
		LDataType atype = attribute.getDataType();
		if(atype==LPrimitiveTypeImpl.BOOL){elm.setAttribute("type", XMLFormatDefinition.SCHEMA_BOOL_TYPE);}
		else if(atype==LPrimitiveTypeImpl.INT){elm.setAttribute("type", XMLFormatDefinition.SCHEMA_INT_TYPE);}
		else if(atype==LPrimitiveTypeImpl.LONG){elm.setAttribute("type", XMLFormatDefinition.SCHEMA_LONG_TYPE);}
		else if(atype==LPrimitiveTypeImpl.FLOAT){elm.setAttribute("type", XMLFormatDefinition.SCHEMA_FLOAT_TYPE);}
		else if(atype==LPrimitiveTypeImpl.DOUBLE){elm.setAttribute("type", XMLFormatDefinition.SCHEMA_DOUBLE_TYPE);}
		else if(atype==LPrimitiveTypeImpl.STRING){elm.setAttribute("type", XMLFormatDefinition.SCHEMA_STRING_TYPE);}
		else if(atype instanceof LEnum){elm.setAttribute("type", atype.getName());}
		else
			throw this.getException("generateAttribute()","Attribute("+attribute.getName()+")", 
					"Cannot generate attribute element for type: \""+atype.getClass().getName()+"\"");
		
		elm.setAttribute("minOccurs", "0");
		elm.setAttribute("maxOccurs", "1");
		
		if(attribute.isRequired())elm.setAttribute("use", "required");
		
		return elm;
	}
	Element generateAttributes(LAttribute attribute) throws Exception{
		if(attribute==null)return null;
		Element elm = new Element(XMLFormatDefinition.SCHEMA_ELEMENT);
		elm.setNamespace(ns);
		elm.setAttribute("name", attribute.getName());
		
		Element ct = new Element(XMLFormatDefinition.SCHEMA_COMTYPE);
		ct.setNamespace(ns);
		elm.getChildren().add(ct);
		
		Element seq = new Element(XMLFormatDefinition.SCHEMA_SEQUENCE);
		seq.setNamespace(ns);
		ct.getChildren().add(seq);
		
		String atype = this.getType(attribute.getDataType());
		if(atype==null)
			throw this.getException("generateAttributes()","Attribute("+attribute.getName()+")", 
					"Cannot generate attribute element for type: \""+attribute.getDataType().getClass().getName()+"\"");
		
		Element item = new Element(XMLFormatDefinition.SCHEMA_ELEMENT);
		item.setNamespace(ns);
		item.setAttribute("name",XMLFormatDefinition.ITEM);
		item.setAttribute("type", atype);
		seq.getChildren().add(item);
		
		Integer low = attribute.getLowerBound();
		Integer up = attribute.getUpperBound();
		
		if(up==LMultipleObject.UNBOUNDED)item.setAttribute("maxOccurs", XMLFormatDefinition.UNBOUNDED);
		else item.setAttribute("maxOccurs", up.toString());
		item.setAttribute("minOccurs", low.toString());
		
		elm.setAttribute("minOccurs", "0");
		elm.setAttribute("maxOccurs", "1");

		if(attribute.isRequired())elm.setAttribute("use", "required");
		
		return elm;
	}
	Element generateReference(LReference reference){
		if(reference==null)return null;
		Element elm = new Element(XMLFormatDefinition.SCHEMA_ELEMENT);
		elm.setNamespace(ns);
		elm.setAttribute("name", reference.getName());
		elm.setAttribute("type", XMLFormatDefinition.SCHEMA_IDREF_TYPE);
		
		elm.setAttribute("minOccurs", "0");
		elm.setAttribute("maxOccurs", "1");

		if(reference.isRequired())elm.setAttribute("use", "required");
		
		return elm;
	}
	Element generateReferences(LReference reference){
		if(reference==null)return null;
		Element elm = new Element(XMLFormatDefinition.SCHEMA_ELEMENT);
		elm.setNamespace(ns);
		elm.setAttribute("name", reference.getName());
		
		Element ct = new Element(XMLFormatDefinition.SCHEMA_COMTYPE);
		ct.setNamespace(ns);
		elm.getChildren().add(ct);
		
		Element seq = new Element(XMLFormatDefinition.SCHEMA_SEQUENCE);
		seq.setNamespace(ns);
		ct.getChildren().add(seq);
		
		String atype = XMLFormatDefinition.SCHEMA_IDREF_TYPE;
		
		Element item = new Element(XMLFormatDefinition.SCHEMA_ELEMENT);
		item.setNamespace(ns);
		item.setAttribute("name",XMLFormatDefinition.ITEM);
		item.setAttribute("type", atype);
		seq.getChildren().add(item);
		
		Integer low = reference.getLowerBound();
		Integer up = reference.getUpperBound();
		
		if(up==LMultipleObject.UNBOUNDED)item.setAttribute("maxOccurs", XMLFormatDefinition.UNBOUNDED);
		else item.setAttribute("maxOccurs", up.toString());
		item.setAttribute("minOccurs", low.toString());
		
		elm.setAttribute("minOccurs", "0");
		elm.setAttribute("maxOccurs", "1");

		if(reference.isRequired())elm.setAttribute("use", "required");
		
		return elm;
	}
	
	String getType(LDataType atype){
		if(atype==LPrimitiveTypeImpl.BOOL){return XMLFormatDefinition.SCHEMA_BOOL_TYPE;}
		else if(atype==LPrimitiveTypeImpl.INT){return XMLFormatDefinition.SCHEMA_INT_TYPE;}
		else if(atype==LPrimitiveTypeImpl.LONG){return XMLFormatDefinition.SCHEMA_LONG_TYPE;}
		else if(atype==LPrimitiveTypeImpl.FLOAT){return XMLFormatDefinition.SCHEMA_FLOAT_TYPE;}
		else if(atype==LPrimitiveTypeImpl.DOUBLE){return XMLFormatDefinition.SCHEMA_DOUBLE_TYPE;}
		else if(atype==LPrimitiveTypeImpl.STRING){return XMLFormatDefinition.SCHEMA_STRING_TYPE;}
		else if(atype instanceof LEnum){return atype.getName();}
		else return null;
	}
	
	

}
