package cn.edu.buaa.sei.exLmf.manager.impl;

import java.util.ArrayList;
import java.util.List;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;

public class LPackageSearcher extends LModelSearcher{

	@Override
	public void verifyElement() throws Exception {
		if(this.element==null||!(this.element instanceof LPackage))
			throw this.getException("verifyElement()", "element", "Not LPackage");
	}

	@Override
	public LModelElement nextElement(String name) throws Exception {
		// TODO Auto-generated method stub
		this.nullVerify(name);
		this.verifyElement();
		
		String[] ans = this.divide(name);
		LPackage p = (LPackage) this.element;
		
		if(ans[0].equals(PACKAGE)&&ans[1]!=null){
			return p.getSubPackageByName(ans[1]);
		}
		else if(ans[0].equals(CLASS)&&ans[1]!=null){
			LClassifier type = null;
			try{
				Integer id = Integer.parseInt(ans[1]);
				type = p.getClassifierByID(id);
			}catch(Exception ex){
				type = p.getClassifierByName(ans[1]);
			}
			
			if(type!=null&&type instanceof LClass)return type;
			throw this.getException("nextElement(name)", "name", "No Class \""+ans[1]+"\" was defined");
		}
		else if(ans[0].equals(ENUM)&&ans[1]!=null){
			LClassifier type = null;
			try{
				Integer id = Integer.parseInt(ans[1]);
				type = p.getClassifierByID(id);
			}catch(Exception ex){
				type = p.getClassifierByName(ans[1]);
			}
			
			if(type!=null&&type instanceof LEnum)return type;
			throw this.getException("nextElement(name)", "name", "No Enum \""+ans[1]+"\" was defined");
		}
		else if(ans[0].equals(CONTAINER)&&ans[1]==null)return p.getContainer();
		
		throw this.getException("nextElement(name)", "name", "Invalid Argument: "+name);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List nextElements(String name) throws Exception {
		this.nullVerify(name);
		this.verifyElement();
		
		LPackage p = (LPackage) this.element;
		if(name.equals(PACKAGE))return p.getSubPackages();
		else if(name.equals(CLASS)){
			List<LClass> types = new ArrayList<LClass>();
			List<LClassifier> list = p.getTypes();
			for(int i=0;i<list.size();i++)
				if(list.get(i) instanceof LClass)
					types.add((LClass) list.get(i));
			return types;
		}
		else if(name.equals(ENUM)){
			List<LEnum> types = new ArrayList<LEnum>();
			List<LClassifier> list = p.getTypes();
			for(int i=0;i<list.size();i++)
				if(list.get(i) instanceof LEnum)
					types.add((LEnum) list.get(i));
			return types;
		}
		
		throw this.getException("nextElements(name)", "name", "Invalid Argument: "+name);
	}
}
