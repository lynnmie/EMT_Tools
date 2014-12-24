package cn.edu.buaa.sei.exLmf.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import cn.edu.buaa.sei.exLmf.metamodel.LAnnotation;
import cn.edu.buaa.sei.exLmf.metamodel.LModelElement;

public abstract class LModelElementImpl implements LModelElement{
	
	List<LAnnotation> annotations = new ArrayList<LAnnotation>();
	
	/*
	 *	Tool Functions for all model elements. 
	 */
	Exception getException(String func,String arg,String reason){
		return LMFException.create("LMFException", this.getClass().getName(), func, arg, reason);
	}

	/*
	 *	Core Functions 
	 */
	@Override
	public List<LAnnotation> getAnnotations() {
		// TODO Auto-generated method stub
		return this.annotations;
	}

	@Override
	public void addAnnotation(LAnnotation annotation) throws Exception {
		if(annotation==null){
			throw this.getException("addAnnotation(annotation)", "annotation", "Null");
		}
		if(annotation.getContainer()!=null)
			annotation.getContainer().removeAnnotation(annotation);
		
		annotation.setContainer(this);
		this.annotations.add(annotation);
	}
	@Override
	public LAnnotation getAnnotation(int i) throws Exception {
		if(i<0||i>=this.annotations.size()){
			throw this.getException("getAnnotation(i)", "i", i+" has been out of range");
		}
		return this.annotations.get(i);
	}
	@Override
	public void removeAnnotation(LAnnotation annotation) throws Exception {
		if(!this.annotations.contains(annotation)){
			throw this.getException("getAnnotation(annotation)", "annotation", "Undefined in model elements");
		}
		this.annotations.remove(annotation);
	}
	@Override
	public boolean containAnnotation(LAnnotation annotation){return this.annotations.contains(annotation);}

}
