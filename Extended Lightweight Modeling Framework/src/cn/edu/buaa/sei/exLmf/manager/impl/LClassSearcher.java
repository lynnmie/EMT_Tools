package cn.edu.buaa.sei.exLmf.manager.impl;

import java.util.List;

import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;
import cn.edu.buaa.sei.exLmf.metamodel.LStructuralFeature;

public class LClassSearcher extends LModelSearcher{
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
	public LModelElement nextElement(String name) throws Exception {
		this.nullVerify(name);
		this.verifyElement();
		
		LClass elm = (LClass) this.element;
		String[] ans = this.divide(name);
		
		//name = ans[0];
		
		if(ans[0].equals(CONTAINER)&&ans[1]==null)return elm.getContainer();
		else if(ans[0].equals(IDATTR)&&ans[1]==null)return elm.getIDAttribute();
		else if(ans[1]!=null){
			if(ans[0].equals(ATTRIBUTE)){
				LStructuralFeature feature = this.getFeatures(elm, ans[1]);
				if(feature!=null&&feature instanceof LAttribute)return feature;
				throw this.getException("nextElement(name)", "name", "Undefined Attribute \""+ans[1]+"\"");
			}
			else if(ans[0].equals(REFERENCE)){
				LStructuralFeature feature = this.getFeatures(elm, ans[1]);
				if(feature!=null&&feature instanceof LReference)return feature;
				throw this.getException("nextElement(name)", "name", "Undefined Reference \""+ans[1]+"\"");
			}
			else if(ans[0].equals(SUPER)){
				List<LClass> supers = elm.getSuperTypes();
				try{
					Integer id = Integer.parseInt(ans[1]);
					for(int i=0;i<supers.size();i++)
						if(id==supers.get(i).getClassifierID())
							return supers.get(i);
					throw this.getException("nextElement(name)", "name", "Undefined Super Class [ID] \""+ans[1]+"\"");
				}
				catch(Exception ex){
					for(int i=0;i<supers.size();i++)
						if(supers.get(i).getName().equals(ans[1]))
							return supers.get(i);
					throw this.getException("nextElement(name)", "name", "Undefined Super Class \""+ans[1]+"\"");
				}
			}
			else if(ans[0].equals(FEATURE)){
				return this.getFeatures(elm, ans[1]);
			}
		}
		
		throw this.getException("nextElement(name)", "name", "Invalid Argument \""+ans[1]+"\"");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List nextElements(String name) throws Exception {
		this.nullVerify(name);
		this.verifyElement();
		
		LClass elm = (LClass) this.element;
		if(name.equals(ATTRIBUTE))return elm.getAttributes();
		if(name.equals(REFERENCE))return elm.getReferences();
		if(name.equals(SUPER))return elm.getSuperTypes();
		if(name.equals(FEATURE))return elm.getFeatures();
		throw this.getException("nextElement(name)", "name", "Invalid Argument: "+name);
	}

	@Override
	public void verifyElement() throws Exception {
		if(this.element==null||!(this.element instanceof LClass))
			throw this.getException("verifyElement()","element", "Not LClass");
	}

}
