package cn.edu.buaa.sei.exLmf.metamodel.impl;

import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LTypedElement;

public abstract class LTypedElementImpl extends LNamedElementImpl implements LTypedElement{
	
	LClassifier type;
	Boolean ordered=true;
	Boolean unique=false;
	int lowerBound = 0;
	int upperBound = 1;
	
	public static final int UNBOUNDED = -1;
	
	LTypedElementImpl(String name) throws Exception{super(name);}

	@Override
	public LClassifier getType() {return this.type;}
	// Would be rewritten in sub class
	@Override
	public void setType(LClassifier type) throws Exception {
		if(type==null){
			throw this.getException("setType(type)", "type", "Null");
		}
		this.type=type;
	}

	@Override
	public Boolean isOrdered() {return this.ordered;}
	@Override
	public Boolean isUnique() {return this.unique;}

	@Override
	public int getUpperBound() {return this.upperBound;}
	@Override
	public int getLowerBound() {return this.lowerBound;}

	@Override
	public void setOrdered(boolean ordered) {this.ordered=ordered;}
	@Override
	public void setUnique(boolean unique) {this.unique=unique;}

	@Override
	public void setUpperBound(int upperBound) throws Exception {
		if(upperBound<0&&upperBound!=UNBOUNDED){
			throw this.getException("setUpperBound(upperBound)", "upperBound", 
						"Invalid integer: "+upperBound);
		}
		/*if(upperBound<this.lowerBound){
			try {
				throw this.getException("setUpperBound(upperBound)", "upperBound", 
						"Out of range: upper = "+upperBound+", lower = "+this.lowerBound);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}*/
		this.upperBound=upperBound;
	}
	@Override
	public void setLowerBound(int lowerBound) throws Exception {
		if(lowerBound<0){
			throw this.getException("setLowerBound(lowerBound)", "lowerBound", 
						"Invalid integer: "+lowerBound);
		}
		/*if(lowerBound>this.upperBound&&this.upperBound>=0){
			
		}*/
		this.lowerBound=lowerBound;
	}

}
