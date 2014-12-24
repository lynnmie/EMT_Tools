package cn.edu.buaa.sei.exLmf.ogm.impl;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LEnum;
import cn.edu.buaa.sei.exLmf.metamodel.LNamedElement;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;

public class ModelAccessor {
	public static LNamedElement access(LNamedElement top,String path) throws Exception{
		if(top==null||path==null)throw new Exception("Null top element | path is invalid");
		
		String[] cols = path.split("\\.");
		
		LNamedElement cur = top;
		for(int i=0;i<cols.length&&cur!=null;i++){
			String name = cols[i];
			
			if(cur instanceof LPackage){
				cur = next((LPackage)cur,name);
			}
			else if(cur instanceof LClass){
				cur = next((LClass)cur,name);
			}
			else if(cur instanceof LEnum){
				cur = next((LEnum)cur,name);
			}
			else 
				throw new Exception("Invalid Named Elmenet Type: "+cur.getClass().getCanonicalName()+" at \""+cur.getAbsolutePath()+"\"");
		}
		
		if(cur==null)throw new Exception("NamedElement accessed failed: "+cur);
		
		return cur;
	}
	
	protected static LNamedElement next(LPackage top,String name) throws Exception{
		if(top==null||name==null)throw new Exception("Null package|name is invalid");
		
		try{
			LPackage next = top.getSubPackageByName(name);
			if(next!=null)return next;
		}catch(Exception e1){
			try{
				LClassifier type = top.getClassifierByName(name);
				if(type!=null)return type;
			}
			catch(Exception e2){
				throw new Exception("No sub packages/classifiers with name: \""+name+"\"");
			}
		}
		return null;
	}
	protected static LNamedElement next(LClass type,String name) throws Exception{
		if(type==null||name==null)throw new Exception("Null type|name is invalid");
		return type.getFeatureByName(name);
	}
	protected static LNamedElement next(LEnum type,String name) throws Exception{
		if(type==null||name==null)throw new Exception("Null type|name is invalid");
		return type.getLiteralByName(name);
	}
}
