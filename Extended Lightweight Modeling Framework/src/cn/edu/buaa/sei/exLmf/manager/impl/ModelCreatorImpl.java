package cn.edu.buaa.sei.exLmf.manager.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.edu.buaa.sei.exLmf.manager.IModelCreator;
import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LDataType;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LEnumLiteral;
import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LAttributeImpl;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LClassImpl;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LEnumImpl;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LEnumLiteralImpl;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LMFException;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LPackageImpl;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LReferenceImpl;

public class ModelCreatorImpl implements IModelCreator{
	String name;
	LPackage root;
	
	Map<Integer,LModelElement> map = new HashMap<Integer,LModelElement>();
	Map<LModelElement,Integer> rmap = new HashMap<LModelElement,Integer>();
	Integer id=0;
	
	/*
	 *	Tool Functions 
	 */
	Exception getException(String func,String arg,String reason){
		return LMFException.create("Model_Manager "+this.name, "LMFCreator", func, arg, reason);
	}
	void nextID(){id++;}
	int generateID() throws Exception{
		Integer init = id;
		while(this.map.containsKey(id)){
			nextID();
			if(init==id)
				throw this.getException("generateID()", "id", "ID Space used out");
		}
		return this.id;
	}
	Boolean inputSpace(LModelElement element) throws Exception{
		if(element==null||this.containElement(element)){return false;}
		Integer id = this.generateID();
		this.map.put(id, element);
		this.rmap.put(element, id);
		return true;
	}
	Boolean inputSpace(Integer id,LModelElement element)throws Exception{
		if(element==null||this.map.containsKey(id)||this.rmap.containsKey(element))return false;
		this.map.put(id, element);
		this.rmap.put(element, id);
		return true;
	}
	void setMultiple(LStructuralFeature feature,int multipleType) throws Exception{
		if(feature==null)
			throw this.getException("setMultiple(feature,multipleType)", "feature", "Null");
		
		switch(multipleType){
		case UNIQUE_ORDER:feature.setOrdered(true);feature.setUnique(true);break;
		case INUNIQUE_ORDER:feature.setOrdered(true);feature.setUnique(false);break;
		case UNIQUE_INORDER:feature.setOrdered(false);feature.setUnique(true);break;
		case INUNIQUE_INORDER:feature.setOrdered(false);feature.setUnique(false);break;
		default: throw this.getException("setMultiple(feature,multipleType)", 
				"multipleType", "Unknown Value: "+multipleType);
		}
	}
	
	public ModelCreatorImpl(String name) throws Exception{
		this.name=name;
		this.root=new LPackageImpl(_ROOT,nsURI,prefix);
	}
	public ModelCreatorImpl(String name,String root_name,String root_uri,String root_prefix) throws Exception{
		this.name=name;
		this.root=new LPackageImpl(root_name,root_uri,root_prefix);
	} 

	@Override
	public LPackage getRoot() {return this.root;}

	@Override
	public LPackage createPackage(LPackage container, String name) throws Exception {
		if(container==null||name==null||name.trim().length()==0){
			throw getException("createPackage(container,name)","container|name","Null");
		}
		LPackage p = new LPackageImpl(name.trim(),nsURI,prefix);
		p.setContainer(container);
		container.addSubPackage(p);
		
		this.inputSpace(p);
		return p;
	}
	@Override
	public LPackage createPackage(LPackage container, String name,
			String nsURI, String prefix)  throws Exception{
		if(container==null||name==null||name.trim().length()==0||nsURI==null||prefix==null){
			throw getException("createPackage(container,name,nsURI,prefix)","container|name|nsURI|prefix","Null");
		}
		LPackage p = new LPackageImpl(name.trim(),nsURI,prefix);
		p.setContainer(container);
		container.addSubPackage(p);
		
		this.inputSpace(p);
		return p;
	}
	
	@Override
	public LClass createClass(LPackage container, String name)  throws Exception{
		if(container==null||name==null||name.trim().length()==0)
			throw this.getException("createClass(container,name)", "container|name", "Null");
		LClass type = new LClassImpl(name.trim(),container);
		
		type.setAbstract(false);
		type.setFinal(false);
		type.setContainer(container);
		type.setDefaultValue(null);
		type.setInstanceName(name.trim());
		
		this.inputSpace(type);
		Integer id = this.rmap.get(type);
		type.setClassifierID(id);
		
		container.addType(type);
		return type;
	}
	@Override
	public LClass createClass(LPackage container, String name, String ins) throws Exception {
		if(container==null||name==null||name.trim().length()==0||ins==null)
			throw this.getException("createClass(container,name,ins)", "container|name|ins", "Null");
		LClass type = new LClassImpl(name.trim(),container);
		
		type.setAbstract(false);
		type.setFinal(false);
		type.setContainer(container);
		type.setDefaultValue(null);
		type.setInstanceName(ins.trim());
		
		this.inputSpace(type);
		Integer id = this.rmap.get(type);
		type.setClassifierID(id);
		
		container.addType(type);
		return type;
	}
	
	@Override
	public LClass createAbstractClass(LPackage container, String name) throws Exception {
		LClass type = this.createClass(container, name);
		type.setAbstract(true);
		return type;
	}
	@Override
	public LClass createAbstractClass(LPackage container, String name,
			String ins) throws Exception {
		LClass type = this.createClass(container, name, ins);
		type.setAbstract(true);
		return type;
	}
	
	@Override
	public LClass createFinalClass(LPackage container, String name) throws Exception {
		LClass type = this.createClass(container, name);
		type.setFinal(true);
		return type;
	}
	@Override
	public LClass createFinalClass(LPackage container, String name, String ins) throws Exception {
		LClass type = this.createClass(container, name, ins);
		type.setFinal(true);
		return type;
	}
	
	@Override
	public LEnum createEnum(LPackage container, String name) throws Exception {
		if(container==null||name==null||name.trim().length()==0)
			throw this.getException("createEnum(container,name)", "container|name", "Null");
		
		LEnum e = new LEnumImpl(name.trim(),container);
		e.setContainer(container);
		e.setDefaultValue(null);
		e.setInstanceName(name.trim());
		
		this.inputSpace(e);
		Integer id = this.rmap.get(e);
		e.setClassifierID(id);
		
		container.addType(e);
		return e;
	}
	@Override
	public LEnum createEnum(LPackage container, String name, String ins) throws Exception {
		if(container==null||name==null||name.trim().length()==0||ins==null)
			throw this.getException("createEnum(container,name,ins)", "container|name|ins", "Null");
		
		LEnum e = new LEnumImpl(name.trim(),container);
		e.setContainer(container);
		e.setDefaultValue(null);
		e.setInstanceName(ins.trim());
		
		this.inputSpace(e);
		Integer id = this.rmap.get(e);
		e.setClassifierID(id);
		
		container.addType(e);
		return e;
	}
	
	@Override
	public LAttribute createAttribute(LClass container, String name,
			LDataType type) throws Exception {
		if(container==null||name==null||name.trim().length()==0||type==null){
			throw this.getException("createAttribute(container,name,type)", "container|name|type", "Null");
		}
		
		LAttribute attribute = new LAttributeImpl(this.generateID(),name.trim(),container);
		attribute.setChangable(true);
		attribute.setContainer(container);
		attribute.setDataType(type);
		attribute.setDefaultValue(type.getDefaultValue());
		attribute.setLowerBound(0);
		attribute.setUpperBound(1);
		attribute.setOrdered(true);
		attribute.setUnique(false);
		container.addAttribute(attribute);
		
		this.inputSpace(attribute.getFeatureID(), attribute);
		
		return attribute;
	}
	@Override
	public LAttribute createConstantAttribute(LClass container, String name,
			LDataType type) throws Exception {
		LAttribute attribute = this.createAttribute(container, name, type);
		attribute.setChangable(false);
		return attribute;
	}
	@Override
	public LAttribute createMultipleAttribute(LClass container, String name,
			LDataType type, int lower, int upper, int multipleType)  throws Exception{
		LAttribute attribute = this.createConstantAttribute(container, name, type);
		attribute.setLowerBound(lower);
		attribute.setUpperBound(upper);
		
		this.setMultiple(attribute, multipleType);
		
		return attribute;
	}
	
	@Override
	public LReference createReference(LClass container, String name, LClass type) throws Exception {
		if(container==null||name==null||name.trim().length()==0||type==null){
			throw this.getException("createReference(container,name,type)", "container|name|type", "Null");
		}
		
		LReference r = new LReferenceImpl(this.generateID(),name.trim(),container);
		r.setChangable(true);
		r.setContainer(container);
		r.setContainment(false);
		r.setDefaultValue(type.getDefaultValue());
		r.setLClass(type);
		r.setType(type);
		r.setLowerBound(0);
		r.setUpperBound(1);
		r.setOpposite(null);
		r.setOrdered(true);
		r.setUnique(false);
		container.addReference(r);
		
		this.inputSpace(r.getFeatureID(),r);
		return r;
	}
	@Override
	public LReference createConstantReference(LClass container, String name,
			LClass type) throws Exception {
		LReference r = this.createReference(container, name, type);
		r.setChangable(false);
		return r;
	}
	@Override
	public LReference createMultipleReference(LClass container, String name,
			LClass type, int lower, int upper, int multipleType) throws Exception {
		LReference r = this.createConstantReference(container, name, type);
		r.setLowerBound(lower);
		r.setUpperBound(upper);
		this.setMultiple(r, multipleType);
		return r;
	}
	
	@Override
	public LEnumLiteral createLiteral(LEnum container, String name, int value) throws Exception {
		if(container==null||name==null||name.trim().length()==0)
			throw this.getException("createLiteral(container,name,value)", "container|name", "Null");
		
		LEnumLiteral l = new LEnumLiteralImpl(this.generateID(),name.trim(),container);
		//l.setChangable(false);
		l.setContainer(container);
		//l.setDefaultValue(null);
		l.setLiteral(name.trim());
		//l.setType(null);
		//l.setUnique(true);l.setOrdered(false);
		//l.setLowerBound(1);l.setUpperBound(1);
		l.setValue(value);
		container.addLiteral(l);
		
		this.inputSpace(l.getFeatureID(), l);
		return l;
	}
	@Override
	public LEnumLiteral createLiteral(LEnum container, String name, int value,
			String literal) throws Exception {
		if(literal==null)
			throw this.getException("createLiteral(container,name,value,literal)","literal", "Null");
		LEnumLiteral l = this.createLiteral(container, name, value);
		l.setLiteral(literal.trim());
		return l;
	}
	
	@Override
	public Set<Integer> getIDs() {return this.map.keySet();}
	@Override
	public LModelElement getElement(Integer id) {
		if(id==null||!this.map.containsKey(id)){
			try {
				throw this.getException("getElement(id)", "id", "Access Undefined Element in Space ["+id+"]");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		return this.map.get(id);
	}
	@Override
	public Integer getIDOf(LModelElement elm) {
		if(elm==null||!this.rmap.containsKey(elm)){
			try {
				throw this.getException("getIDOf(elm)", "elm", "Undefined Element with NO Identificatiom");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		return this.rmap.get(elm);
	}
	@Override
	public Boolean containElement(LModelElement elm) {
		return (elm!=null)&&(this.rmap.containsKey(elm));
	}
	@Override
	public Boolean removeElement(LModelElement elm) {
		if(!this.containElement(elm)){
			try {
				throw this.getException("removeElement(elm)", "elm", "Undefined Element");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		Integer id = this.rmap.get(elm);
		this.map.remove(id);
		this.rmap.remove(elm);
		return true;
	}
	
	Set<LModelElement> flags = new HashSet<LModelElement>();
	Set<LModelElement> rems = new HashSet<LModelElement>();
	@Override
	public Boolean updateSpace() throws Exception {
		this.flags.clear();
		this.rems.clear();
		
		this.updateElement(this.root);
		
		for(LModelElement elm:this.rems)
			this.releaseLink(elm);
		
		return true;
	}
	Boolean updateElement(LModelElement elm) throws Exception{
		if(elm==null)
			throw this.getException("updateElement(elm)", "elm", "Null");
		if(this.flags.contains(elm))return true;
		
		if(elm instanceof LClass){this.updateClass((LClass) elm);}
		else if(elm instanceof LEnum){this.updateEnum((LEnum) elm);}
		else if(elm instanceof LPackage){this.updatePackage((LPackage) elm);}
		else if(elm instanceof LAttribute){this.updateAttribute((LAttribute) elm);}
		else if(elm instanceof LReference){this.updateReference((LReference) elm);}
		else if(elm instanceof LEnumLiteral){this.updateLiteral((LEnumLiteral) elm);}
		else{throw this.getException("updateElement(elm)", "elm", "Unknown LModelElement: "+elm.getClass().getName());}
		return true;
	}
	Boolean updatePackage(LPackage p) throws Exception{
		if(p==null)return false;
		
		if(!this.containElement(p)&&p!=this.root){
			List<LClassifier> types = p.getTypes();
			for(int i=0;i<types.size();i++)
				if(this.containElement(types.get(i)))
					this.removeElement(types.get(i));
			List<LPackage> subs = p.getSubPackages();
			for(int i=0;i<subs.size();i++)
				if(this.containElement(subs.get(i)))
					this.removeElement(subs.get(i));
		}
		
		this.flags.add(p);
		
		List<LClassifier> types = p.getTypes();
		for(int i=0;i<types.size();i++){
			if(!this.containElement(types.get(i)))
				this.rems.add(types.get(i));
			this.updateElement(types.get(i));
		}
		
		List<LPackage> subs = p.getSubPackages();
		for(int i=0;i<subs.size();i++){
			if(!this.containElement(subs.get(i)))
				this.rems.add(subs.get(i));
			this.updateElement(subs.get(i));
		}
		return true;
		
	}
	Boolean updateClass(LClass type) throws Exception{
		if(type==null)return false;
		
		List<LClass> supers = type.getSuperTypes();
		Boolean isRemovable = false;
		for(int i=0;i<supers.size();i++)
			if(!this.containElement(supers.get(i))){
				isRemovable = true;
				break;
			}
		if(!isRemovable)isRemovable = !this.containElement(type);
		
		if(isRemovable){
			this.rems.add(type);
			
			List<LAttribute> attributes = type.getAttributes();
			List<LReference> references = type.getReferences();
				
			for(int i=0;i<attributes.size();i++)
				if(this.containElement(attributes.get(i)))
					this.removeElement(attributes.get(i));
			for(int i=0;i<references.size();i++)
				if(this.containElement(references.get(i)))
					this.removeElement(references.get(i));
		}
		
		this.flags.add(type);
		
		List<LAttribute> attributes = type.getAttributes();
		List<LReference> references = type.getReferences();
		rems.clear();
		
		for(int i=0;i<attributes.size();i++){
			if(!this.containElement(attributes.get(i)))
				rems.add(attributes.get(i));
			this.updateElement(attributes.get(i));
		}
		for(int i=0;i<references.size();i++){
			if(!this.containElement(references.get(i)))
				rems.add(references.get(i));
			this.updateElement(references.get(i));
		}
		return true;
	}
	Boolean updateEnum(LEnum e) throws Exception{
		if(e==null)return false;
		
		if(!this.containElement(e)){
			rems.add(e);
			List<LEnumLiteral> literals = e.getLiterals();
			for(int i=0;i<literals.size();i++)
				if(this.containElement(literals.get(i))){
					this.removeElement(literals.get(i));
				}
		}
		
		this.flags.add(e);
		List<LEnumLiteral> literals = e.getLiterals();
		
		for(int i=0;i<literals.size();i++){
			if(!this.containElement(literals.get(i)))
				rems.add(literals.get(i));
			this.updateElement(literals.get(i));
		}
		
		return true;
	}
	Boolean updateAttribute(LAttribute attr){
		if(attr==null)return false;
		this.flags.add(attr);
		return true;
	}
	Boolean updateReference(LReference r) throws Exception{
		if(r==null)return false;
		this.flags.add(r);
		
		if(!this.containElement(r)){
			if(this.containElement(r.getOpposite())){
				r.getOpposite().release_opposite();
			}
		}
		
		this.updateElement(r.getOpposite());
		return true;
	}
	Boolean updateLiteral(LEnumLiteral l){
		if(l==null)return false;
		this.flags.add(l);
		return true;
	}
	
	void releaseLink(LModelElement elm) throws Exception{
		if(elm==null)return;
		
		if(elm instanceof LClass){this.releaseClass((LClass) elm);}
		else if(elm instanceof LEnum){this.releaseEnum((LEnum) elm);}
		else if(elm instanceof LPackage){this.releasePackage((LPackage) elm);}
		else if(elm instanceof LAttribute){this.releaseAttribute((LAttribute) elm);}
		else if(elm instanceof LReference){this.releaseReference((LReference) elm);}
		else if(elm instanceof LEnumLiteral){this.releaseLiteral((LEnumLiteral) elm);}
		else{throw this.getException("updateElement(elm)", "elm", "Unknown LModelElement: "+elm.getClass().getName());}
	}
	void releasePackage(LPackage p) throws Exception{
		if(p==null||p==this.root)return;
		p.getContainer().removeSubPackage(p);
	}
	void releaseClass(LClass type) throws Exception{
		if(type==null)return;
		type.getContainer().removeType(type);
	}
	void releaseEnum(LEnum e) throws Exception{
		if(e==null)return;
		e.getContainer().removeType(e);
	}
	void releaseAttribute(LAttribute a) throws Exception{
		if(a==null)return;
		LClass type = (LClass) a.getContainer();
		type.removeAttribute(a);
	}
	void releaseReference(LReference r) throws Exception{
		if(r==null)return;
		LClass type = (LClass) r.getContainer();
		type.removeReference(r);
	}
	void releaseLiteral(LEnumLiteral l) throws Exception{
		if(l==null)return;
		LEnum e = (LEnum) l.getContainer();
		e.removeLiteral(l);
	}
	
}
