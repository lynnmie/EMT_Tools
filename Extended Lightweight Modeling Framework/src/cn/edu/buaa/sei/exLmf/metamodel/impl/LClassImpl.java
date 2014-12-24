package cn.edu.buaa.sei.exLmf.metamodel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;

public class LClassImpl extends LClassifierImpl implements LClass{
	
	List<LClass> supers = new ArrayList<LClass>();
	List<LAttribute> attributes = new ArrayList<LAttribute>();
	List<LReference> references = new ArrayList<LReference>();
	List<LStructuralFeature> features = new ArrayList<LStructuralFeature>();
	List<LClass> subs = new ArrayList<LClass>();
	
	// Record the names and identifications used in local variable space.
	Map<String,LStructuralFeature> name_feature = new HashMap<String,LStructuralFeature>();
	Map<Integer,LStructuralFeature> id_feature = new HashMap<Integer,LStructuralFeature>();
	
	LAttribute id_attr=null;

	public LClassImpl(String name,LPackage container) throws Exception{super(name,container);}
	
	@Override
	public List<LClass> getSuperTypes() {return this.supers;}
	@Override
	public void addSuperType(LClass type) throws Exception {
		if(type==null){
			throw this.getException("addSuperType(type)", "type", "Null");
		}
		if(type.isSuperOf(this))return;
		if(this.isSuperOf(type)){
			throw this.getException("addSuperType(type)", "type", 
						"Circle of generalization is invalid");
		}
		if(type.isFinal()){
			throw this.getException("addSuperType(type)", "type", 
						"Final type \""+type.getName()+"\" cannot be generalized to sub-class");
		}
		
		this.supers.add(type);
		
		/**
		 * Danger Code
		 * */
		if(type instanceof LClassImpl){
			((LClassImpl) type).subs.add(this);
		}
	}
	@Override
	public void removeSuperType(LClass type) throws Exception {
		if(!this.supers.contains(type)){
			throw this.getException("removeSuperType(type)", "type", "Undefined");
		}
		this.supers.remove(type);
		
		/**
		 * Danger Code
		 * */
		if(type instanceof LClassImpl){
			((LClassImpl) type).subs.remove(this);
		}
	}
	@Override
	public Boolean isSuperOf(LClass type) {
		if(type==null)return false;
		
		if(type.getSuperTypes().contains(this))return true;
		else{
			for(int i=0;i<type.getSuperTypes().size();i++)
				if(this.isSuperOf(type.getSuperTypes().get(i)))
					return true;
		}
		
		return false;
	}
	@Override
	public Boolean isSubOf(LClass type) {
		if(type==null)return false;
		return type.isSuperOf(this);
	}

	@Override
	public LAttribute getIDAttribute() {return this.id_attr;}
	// Only after the attribute has been successfully added into attribute list then it could be set as id_attribute
	@Override
	public void setIDAttribute(LAttribute attribute) throws Exception {
		if(attribute==null){
			throw this.getException("setIDAttribute(attribute)", "attribute", "Null");
		}
		this.addAttribute(attribute);
		this.id_attr=attribute;
	}

	/*The getAllXXXs would generate new list for each call and waste time and space, it is good idea not to call this function too much times.*/
	@Override
	public List<LAttribute> getAttributes() {return this.attributes;}
	@Override
	public List<LAttribute> getAllAttributes() {
		List<LAttribute> alls = new ArrayList<LAttribute>();
		
		alls.addAll(this.attributes);
		for(int i=0;i<this.supers.size();i++)
			alls.addAll(this.supers.get(i).getAllAttributes());
		
		return alls;
	}

	@Override
	public List<LReference> getReferences() {return this.references;}
	@Override
	public List<LReference> getAllReferences() {
		List<LReference> alls = new ArrayList<LReference>();
		
		alls.addAll(this.references);
		for(int i=0;i<this.supers.size();i++)
			alls.addAll(this.supers.get(i).getAllReferences());
		
		return alls;
	}

	@Override
	public List<LStructuralFeature> getFeatures() {return this.features;}
	@Override
	public List<LStructuralFeature> getAllFeatures() {
		List<LStructuralFeature> alls = new ArrayList<LStructuralFeature>();
		
		alls.addAll(this.features);
		for(int i=0;i<this.supers.size();i++){
			alls.addAll(this.supers.get(i).getAllFeatures());
		}
		
		return alls;
	}

	/*Searching by name would not cause exception. If no name is found in local features, it would trace to the super class to search.*/
	@Override
	public LStructuralFeature getFeatureByName(String name) {
		if(name==null)return null;
		else if(this.name_feature.containsKey(name)) 
			return this.name_feature.get(name);
		else
			for(int i=0;i<this.supers.size();i++){
				LStructuralFeature f = this.supers.get(i).getFeatureByName(name);
				if(f!=null)return f;
			}
		return null;
	}
	@Override
	public LStructuralFeature getFeatureByID(int id) {
		if(this.id_feature.containsKey(id))return this.id_feature.get(id);
		else{
			for(int i=0;i<this.supers.size();i++){
				LStructuralFeature f = this.supers.get(i).getFeatureByID(id);
				if(f!=null)return f;
			}
			return null;
		}
	}
	// Only return the local feature identification space.
	@Override
	public int[] getFeatureIDSet() {
		int n = this.id_feature.size();
		int[] ids = new int[n];
		int i = 0;
		for(Integer id:id_feature.keySet())
			ids[i++] = id;
		return ids;
	}
	
	/*
	 *	void addXXX(feature)
	 *	1) If the class or its super classes contain the feature, do nothing.
	 *	2) If the local name/id of feature space conflict with the feature, exception
	 *	3) Null and exception
	 *	4) If feature is from another existing class, then remove the links with former container
	 */
	void _addFeature(LStructuralFeature feature) throws Exception{
		if(feature==null){
			throw this.getException("_addFeature(feature)", "feature", "Null");
		}
		
		// including the super classes.
		if(this.containFeature(feature))return;
		
		int fid = feature.getFeatureID();
		String name = feature.getName();
		
		/*The name and id of feature should not conflict with local name/id space of features*/
		if(this.name_feature.containsKey(name)){
			throw this.getException("_addFeature(feature)", "feature.name", 
						name+ " has been defined in feature namespace");
		}
		if(this.id_feature.containsKey(fid)){
			throw this.getException("_addFeature(feature)", "feature.id", 
						fid+ " has been defined in feature id-space");
		}
		if(feature.getContainer()!=this&&feature.getContainer()!=null){
			LClass ftype = (LClass) feature.getContainer();
			if(ftype.containFeature(feature))
				ftype.removeFeature(feature);
		}
		
		this.features.add(feature);
		feature.setContainer(this);
		this.name_feature.put(name, feature);
		this.id_feature.put(fid, feature);
	}
	@Override
	public void addAttribute(LAttribute attribute) throws Exception {
		this._addFeature(attribute);
		if(!this.containAttribute(attribute)){
			this.attributes.add(attribute);
		}
	}
	@Override
	public void addReference(LReference reference) throws Exception {
		this._addFeature(reference);
		if(!this.containReference(reference))
			this.references.add(reference);
		
	}
	@Override
	public void addFeature(LStructuralFeature feature) throws Exception {
		if(feature instanceof LAttribute)
			this.addAttribute((LAttribute) feature);
		else if(feature instanceof LReference)
			this.addReference((LReference) feature);
		else{
			throw this.getException("addFeature(feature)", "feature", 
						feature.getClass().getName()+" behavior is not defined");
		}
	}

	/*containXXX would check whether the class or its super class contains this XXX*/
	@Override
	public Boolean containAttribute(LAttribute attribute) {
		if(this.attributes.contains(attribute))return true;
		else{
			for(int i=0;i<this.supers.size();i++)
				if(this.supers.get(i).containAttribute(attribute))
					return true;
			return false;
		}
	}
	@Override
	public Boolean containReference(LReference reference) {
		if(this.references.contains(reference))return true;
		else{
			for(int i=0;i<this.supers.size();i++)
				if(this.supers.get(i).containReference(reference))
					return true;
			return false;
		}
	}
	@Override
	public Boolean containFeature(LStructuralFeature feature) {
		if(this.features.contains(feature))return true;
		else{
			for(int i=0;i<this.supers.size();i++)
				if(this.supers.get(i).containFeature(feature))
					return true;
			return false;
		}
	}

	/*	removeXXX(): can only remove the local features, has no way to remove features in super class. */
	@Override
	public void removeAttribute(LAttribute attribute) throws Exception {
		if(attribute==null||!this.attributes.contains(attribute)){
			throw this.getException("removeAttribute(attribute)", "attribute \""+attribute.getName()+"\"", "Undefined");
		}
		
		String name = attribute.getName();
		int id = attribute.getFeatureID();
		
		this.name_feature.remove(name);
		this.id_feature.remove(id);
		this.features.remove(attribute);
		this.attributes.remove(attribute);
		attribute.setContainer(null);
	}
	@Override
	public void removeReference(LReference reference) throws Exception {
		if(reference==null||!this.references.contains(reference)){
			throw this.getException("removeReference(reference)","reference \""+reference.getName()+"\"", "Undefined");
		}
		
		String name = reference.getName();
		int id = reference.getFeatureID();
		
		this.name_feature.remove(name);
		this.id_feature.remove(id);
		this.features.remove(reference);
		this.references.remove(reference);
		reference.setContainer(null);
	}
	@Override
	public void removeFeature(LStructuralFeature feature) throws Exception {
		if(feature==null){
			throw this.getException("removeFeature(feature)", "feature", "Undefined");
		}
		
		if(feature instanceof LAttribute)
			this.removeAttribute((LAttribute) feature);
		else if(feature instanceof LReference)
			this.removeReference((LReference) feature);
		else{
			throw this.getException("removeFeature(feature)", "feature", 
						feature.getClass().getName()+" behavior is not defined");
		}
	}

	/*Only remove the local attribute (would no affect the super classes)*/
	@Override
	public LStructuralFeature removeFeatureByID(int id) throws Exception {
		if(!this.id_feature.containsKey(id)){
			throw this.getException("removeFeatureByID(id)","id", id + " Undefined");
		}
		
		LStructuralFeature f = this.id_feature.get(id);
		this.removeFeature(f);
		return f;
	}
	@Override
	public LStructuralFeature removeFeatureByName(String name) throws Exception {
		if(!this.name_feature.containsKey(name)){
			throw this.getException("removeFeatureByName(name)","name", name + " Undefined");
		}
		
		LStructuralFeature f = this.name_feature.get(name);
		this.removeFeature(f);
		return f;
	}

	Boolean isAbstract=false;
	Boolean isFinal=false;
	@Override
	public Boolean isAbstract() {return this.isAbstract;}
	@Override
	public Boolean isFinal() {return this.isFinal;}
	@Override
	public void setAbstract(Boolean isAbstract) {this.isAbstract=isAbstract;}
	@Override
	public void setFinal(Boolean isFinal) {this.isFinal=isFinal;}

	/*Modify the super functions*/
	@Override
	public LObject setDefaultValue(LObject val) throws Exception {
		if(val!=null){
			if(!(val instanceof LClassObject)){
				throw this.getException("setDefaultValue(val)", "val", "val should be class object: currently ["+val.getClass().getName()+"]");
			}
			if(this!=val.type()||!this.isSuperOf((LClass) val.type())){
				throw this.getException("setDefaultValue(val)", "val", "val.type do not match this classifier");
			}
		}
		return this.default_val=val;
	}

	@Override
	public Boolean isInstance(LClassObject val) {
		if(val==null)return true;
		
		LClass type = val.getType();
		if(type==null)return false;
		
		Queue<LClass> queue = new LinkedList<LClass>();
		queue.add(this);
		while(!queue.isEmpty()){
			LClass qtype = queue.poll();
			if(qtype==type)return true;
			
			List<LClass> supers = qtype.getSuperTypes();
			for(int i=0;i<supers.size();i++)
				queue.add(supers.get(i));
		}
		return false;
	}

	@Override
	public List<LClass> getSubClasses() {return this.subs;}
}
