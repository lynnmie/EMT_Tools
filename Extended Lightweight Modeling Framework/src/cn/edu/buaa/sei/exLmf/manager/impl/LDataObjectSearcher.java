package cn.edu.buaa.sei.exLmf.manager.impl;

import java.util.List;

import cn.edu.buaa.sei.exLmf.metamodel.LDataObject;
import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;

public class LDataObjectSearcher extends LModelSearcher{

	@Override
	public void verifyElement() throws Exception {
		if(this.element==null||!(this.element instanceof LDataObject))
			throw this.getException("verifyElement()", "element", "Not DataObject");
	}

	@Override
	public LModelElement nextElement(String name) throws Exception {
		this.nullVerify(name);
		this.verifyElement();
		
		LDataObject o = (LDataObject) this.element;
		if(name.equals(TYPE))return o.type();
		if(name.equals(DATATYPE))return o.getType();
		
		throw this.getException("nextElement(name)", "name", "Invalid Argument: "+name);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List nextElements(String name) throws Exception {
		this.nullVerify(name);
		this.verifyElement();
		
		throw this.getException("nextElement(name)", "name", "Invalid Argument: "+name);
	}

}
