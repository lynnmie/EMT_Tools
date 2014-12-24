package cn.edu.buaa.sei.exLmf.metamodel.impl;

import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;

public class LClassifierImpl extends LNamedElementImpl implements LClassifier{
	
	String ins_name;
	LObject default_val;
	int id=DEFAULT_ID;
	public static final int DEFAULT_ID = -1;
	LPackage container;
	
	LClassifierImpl(String name,LPackage container) throws Exception{super(name);this.container=container;}
	/*
	 *	Tool Functions for all model elements. 
	 */
	Exception getException(String func,String arg,String reason){
		return LMFException.create("LMFException", this.getClass().getName(), func, arg, reason);
	}
	
	
	@Override
	public int getClassifierID() {return this.id;}
	@Override
	public void setClassifierID(int id) {this.id=id;}

	@Override
	public String getInstanceName() {
		return this.ins_name;
	}
	@Override
	public LObject getDefaultValue() {
		return this.default_val;
	}
	@Override
	public void setInstanceName(String ins) {
		this.ins_name=ins;
	}
	// Need to be modified in sub class
	@Override
	public LObject setDefaultValue(LObject val) throws Exception {
		if(val!=null){
			if(this!=val.type())
				throw this.getException("setDefaultValue(val)", "val", "val.type do not match this classifier");
		}
		return this.default_val=val;
	}

	@Override
	public LPackage getContainer() {return this.container;}
	@Override
	public void setContainer(LPackage container) {this.container=container;}
	@Override
	public String getAbsolutePath() {
		if(this.container==null)return this.name;
		else return this.container.getAbsolutePath()+SPLIT+this.name;
	}
	
}
