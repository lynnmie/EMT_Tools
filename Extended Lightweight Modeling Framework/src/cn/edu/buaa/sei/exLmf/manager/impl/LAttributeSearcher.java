package cn.edu.buaa.sei.exLmf.manager.impl;

import java.util.List;

import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;

public class LAttributeSearcher extends LModelSearcher{

	@Override
	public void verifyElement() throws Exception {
		if(this.element==null||!(this.element instanceof LAttribute))
			throw this.getException("verifyElement()", "element", "Not Attribute");
	}

	@Override
	public LModelElement nextElement(String name) throws Exception {
		this.nullVerify(name);
		this.verifyElement();
		
		LAttribute a = (LAttribute) this.element;
		String[] ans = this.divide(name);
		
		if(ans[0].equals(CONTAINER)&&ans[1]==null)return a.getContainer();
		if(ans[0].equals(DEFAULT)&&ans[1]==null)return a.getDefaultValue();
		if(ans[0].equals(DATATYPE)&&ans[1]==null)return a.getDataType();
		if(ans[0].equals(TYPE)&&ans[1]==null)return a.getType();
		
		throw this.getException("nextElement(name)", "name", "Invalid Argument: "+name);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List nextElements(String name) throws Exception {
		throw this.getException("nextElement(name)", "name", "Invalid Argument: "+name);
	}
	
}
