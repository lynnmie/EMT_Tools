package cn.edu.buaa.sei.exLmf.metamodel.impl;

import cn.edu.buaa.sei.exLmf.metamodel.LNamedElement;

public abstract class LNamedElementImpl extends LModelElementImpl implements LNamedElement{
	
	String name;
	
	LNamedElementImpl(String name) throws Exception{
		super();
		this.setName(name);
	}

	@Override
	public String getName() {return this.name;}
	@Override
	public void setName(String name) throws Exception {
		if(name==null){
			throw this.getException("setName(name)","name", "Null");
		}
		this.name=name;
	}

}
