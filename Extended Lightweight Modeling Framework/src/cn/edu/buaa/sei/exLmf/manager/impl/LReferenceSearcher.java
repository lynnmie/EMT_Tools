package cn.edu.buaa.sei.exLmf.manager.impl;

import java.util.List;

import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;

public class LReferenceSearcher extends LModelSearcher{

	@Override
	public void verifyElement() throws Exception {
		if(this.element==null||!(this.element instanceof LReference))
			throw this.getException("verifyElement()", "element", "Not Reference");
	}

	@Override
	public LModelElement nextElement(String name) throws Exception {
		this.nullVerify(name);
		this.verifyElement();
		
		LReference r = (LReference) this.element;
		
		if(name.equals(CONTAINER))return r.getContainer();
		if(name.equals(DEFAULT))return r.getDefaultValue();
		if(name.equals(OPPOSITE))return r.getOpposite();
		if(name.equals(TYPE))return r.getType();
		if(name.equals(CLASS))return r.getLClass();
		
		throw this.getException("nextElement(name)", "name", "Invalid Argument: "+name);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List nextElements(String name) throws Exception {
		this.nullVerify(name);
		this.verifyElement();
		
		throw this.getException("nextElements(name)", "name", "Invalid Argument: "+name);
	}

}
