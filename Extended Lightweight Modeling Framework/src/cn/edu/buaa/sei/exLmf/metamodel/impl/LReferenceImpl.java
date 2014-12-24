package cn.edu.buaa.sei.exLmf.metamodel.impl;

import cn.edu.buaa.sei.exLmf.metamodel.LClass;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LReference;

public class LReferenceImpl extends LStructuralFeatureImpl implements LReference{
	LReference opposite;
	Boolean containment = false;
	
	public LReferenceImpl(int fid,String name,LClassifier container) throws Exception{
		super(fid,name,container);
	}

	@Override
	public LClass getLClass() {
		if(this.type==null)return null;
		return (LClass) this.type;
	}
	@Override
	public void setLClass(LClass type) throws Exception {
		if(type==null)throw new Exception("Null class type is invalid");
		this.type=type;
	}
	public void setType(LClassifier type) throws Exception{
		if((type==null)||!(type instanceof LClass)){
			throw this.getException("setType(type)", "type", "Reference's type must be LClass");
		}
		this.type=(LClass) type;
	}
	
	@Override
	public LReference getOpposite() {
		return this.opposite;
	}
	// Only to call setOpposite(), don't use release_opposite or link_opposite !!!!
	@Override
	public void setOpposite(LReference opposite) {
		if(this.opposite!=null){
			this.opposite.release_opposite();
		}
		this.release_opposite();
		
		this.link_opposite(opposite);
		if(opposite!=null){
			opposite.link_opposite(this);
		}
	}
	@Override
	public void release_opposite() {this.opposite=null;}
	@Override
	public void link_opposite(LReference opposite) {this.opposite=opposite;}
	
	@Override
	public Boolean isContainment() {return this.containment;}
	@Override
	public void setContainment(Boolean containment) {this.containment=containment;}

}
