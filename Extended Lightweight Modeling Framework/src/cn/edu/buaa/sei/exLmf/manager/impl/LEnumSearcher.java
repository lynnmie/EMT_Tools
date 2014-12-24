package cn.edu.buaa.sei.exLmf.manager.impl;

import java.util.List;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;

public class LEnumSearcher extends LModelSearcher{
	
	/*Tools*/
	LStructuralFeature getFeatures(LClass type,String name){
		LStructuralFeature feature = null;
		try{
			Integer id = Integer.parseInt(name);
			feature = type.getFeatureByID(id);
		}
		catch(Exception ex){
			feature = type.getFeatureByName(name);
		}
		return feature;
	}
	
	@Override
	public void verifyElement() throws Exception {
		if(this.element==null||!(this.element instanceof LEnum))
			throw this.getException("verifyElement()", "element", "Not LEnum");
	}

	@Override
	public LModelElement nextElement(String name) throws Exception {
		nullVerify(name);
		this.verifyElement();
		
		LEnum e = (LEnum) this.element;
		String[] ans = this.divide(name);
		
		if(ans[0].equals(CONTAINER)&&ans[1]==null)
			return e.getContainer();
		else if(ans[0].equals(LITERAL)&&ans[1]!=null){
			try{
				Integer id = Integer.parseInt(ans[1]);
				return e.getLiteralByValue(id);
			}
			catch(Exception ex){
				return e.getLiteralByName(ans[1]);
			}
		}
		throw this.getException("nextElement(name)", "name", "Invalid Argument: "+name);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List nextElements(String name) throws Exception {
		nullVerify(name);
		this.verifyElement();
		
		LEnum e = (LEnum) this.element;
		if(name.equals(LITERAL))return e.getLiterals();
		throw this.getException("nextElements(name)", "name", "Invalid Argument: "+name);
	}
	
}
