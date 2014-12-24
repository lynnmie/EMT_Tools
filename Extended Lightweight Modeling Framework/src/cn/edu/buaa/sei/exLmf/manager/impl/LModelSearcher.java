package cn.edu.buaa.sei.exLmf.manager.impl;

import cn.edu.buaa.sei.exLmf.manager.ILModelSearcher;
import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;
import cn.edu.buaa.sei.exLmf.metamodel.impl.LMFException;

public abstract class LModelSearcher implements ILModelSearcher{
	
	LModelElement element;
	
	/*	Tool Functions   */
	Exception getException(String func,String arg,String reason){
		return LMFException.create("Model_Searcher", "LMFCreator", func, arg, reason);
	}
	String[] divide(String code){
		if(code==null)return null;
		int s1 = code.indexOf(LEFT);
		int s2 = code.indexOf(RIGHT);
		
		String[] ans = new String[2];
		ans[1]=null;
		
		if(s1<0||s2<0||s1>=s2)ans[0]=code;
		else{
			ans[0] = code.substring(0, s1);
			ans[1] = code.substring(s1+1, s2);
		}
		return ans;
	}
	void nullVerify(String code) throws Exception{
		if(this.element==null||code==null)
			throw this.getException("nullVerify(code)", "element|code", "Null");
	}
	
	@Override
	public LModelElement getElement() {return this.element;}
	@Override
	public void setElement(LModelElement elm) {this.element=elm;}
	
	@Override
	public Object next(String name) throws Exception {
		try{
			return this.nextElement(name);
		}catch(Exception ex){
			return this.nextElements(name);
		}
	}
	
	
	
	
}
