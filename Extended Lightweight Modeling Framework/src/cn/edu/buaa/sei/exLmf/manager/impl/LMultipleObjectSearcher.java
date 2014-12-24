package cn.edu.buaa.sei.exLmf.manager.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;
import cn.edu.buaa.sei.exLmf.metamodel.LMultipleObject;
import cn.edu.buaa.sei.exLmf.metamodel.LObject;

public class LMultipleObjectSearcher extends LModelSearcher{

	@Override
	public void verifyElement() throws Exception {
		if(this.element==null||!(this.element instanceof LMultipleObject))
			throw this.getException("verifyElement()", "element", "Not MultipleObject");
	}

	@Override
	public LModelElement nextElement(String name) throws Exception {
		this.nullVerify(name);
		this.verifyElement();
		
		LMultipleObject o = (LMultipleObject) this.element;
		if(name.equals(TYPE))return o.type();
		if(name.equals(PARAMETER_TYPE))return o.getParameterType();
		else{
			String[] ans = this.divide(name);
			if(ans[1]==null)
				throw this.getException("nextElement(name)","name", "Invalid Argument: "+name);
			if(ans[0].equals(UNIQUE_ORDER)||ans[0].equals(INUNIQUE_ORDER)){
				
				if(ans[0].equals(UNIQUE_ORDER)&&(!o.isOrdered()||!o.isUnique()))
					throw this.getException("nextElement(name)","permit", "Permition Failed: "+ans[0]);
				if(ans[0].equals(INUNIQUE_ORDER)&&(!o.isOrdered()||o.isUnique()))
					throw this.getException("nextElement(name)","permit", "Permition Failed: "+ans[0]);
				
				try{
					Integer i = Integer.parseInt(ans[1]);
					return o.getByOrder(i);
				}catch(Exception ex){
					throw this.getException("nextElement(name)","name.index", "Invalid Index: "+ans[1]);
				}
			}
			if(ans[0].equals(UNIQUE_INORDER)||ans[0].equals(INUNIQUE_INORDER)){
				
				if(ans[0].equals(UNIQUE_INORDER)&&(!o.isUnique()||o.isOrdered()))
					throw this.getException("nextElement(name)","permit", "Permition Failed: "+ans[0]);
				if(ans[0].equals(INUNIQUE_INORDER)&&(o.isOrdered()||o.isUnique()))
					throw this.getException("nextElement(name)","permit", "Permition Failed: "+ans[0]);
				
				try{
					Integer i = Integer.parseInt(ans[1]);
					if(i<0||i>=o.getAllObjects().size())
						throw this.getException("nextElement(name)","name.index", 
								"Out of range[0-"+o.getAllObjects().size()+"]: "+ans[1]);
					
					Iterator<LObject> itor = o.getByUnordered();
					while(i-->0)itor.next();
					return itor.next();
				}catch(Exception ex){
					throw this.getException("nextElement(name)","name.index", "Invalid Index: "+ans[1]);
				}
			}
		}
		
		throw this.getException("nextElement(name)","name", "Invalid Argument: "+name);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List nextElements(String name) throws Exception {
		this.nullVerify(name);
		this.verifyElement();
		
		LMultipleObject o =(LMultipleObject) this.element;
		if(name.equals(UNIQUE_ORDER)&&o.isUnique()&&o.isOrdered())
			return this.getList(o);
		if(name.equals(INUNIQUE_ORDER)&&!o.isUnique()&&o.isOrdered())
			return this.getList(o);
		if(name.equals(UNIQUE_INORDER)&&!o.isOrdered()&&o.isUnique())
			return this.getList(o);
		if(name.equals(INUNIQUE_INORDER)&&!o.isOrdered()&&!o.isUnique())
			return this.getList(o);
		
		throw this.getException("nextElements(name)","permit", "Permition Failed: "+name);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	List getList(LMultipleObject o){
		if(o==null)return null;
		List ans = new ArrayList();
		
		Collection<LObject> list=null;
		try {
			list = o.getAllObjects();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<LObject> itor = list.iterator();
		
		while(itor.hasNext())
			ans.add(itor.next());
		
		return ans;
	}

}
