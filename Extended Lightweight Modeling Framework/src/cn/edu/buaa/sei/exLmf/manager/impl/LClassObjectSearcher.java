package cn.edu.buaa.sei.exLmf.manager.impl;

import java.util.List;

import cn.edu.buaa.sei.exLmf.metamodel.LClassObject;
import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;

public class LClassObjectSearcher extends LModelSearcher{

	@Override
	public void verifyElement() throws Exception {
		if(this.element==null||!(this.element instanceof LClassObject))
			throw this.getException("verifyElement()", "element", "Not ClassObject");
	}

	@Override
	public LModelElement nextElement(String name) throws Exception {
		this.nullVerify(name);
		this.verifyElement();
		
		LClassObject o = (LClassObject) this.element;
		if(name.equals(TYPE))return o.type();
		if(name.equals(CLASSTYPE))return o.getType();
		
		throw this.getException("nextElement(name)", "name", "Invalid Arguement: "+name);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List nextElements(String name) throws Exception {
		this.nullVerify(name);
		this.verifyElement();
		
		throw this.getException("nextElements(name)", "name", "Invalid Arguement: "+name);
	}

}
