package cn.edu.buaa.sei.exLmf.metamodel.impl;

import cn.edu.buaa.sei.exLmf.metamodel.LAttribute;
import cn.edu.buaa.sei.exLmf.metamodel.LClassifier;
import cn.edu.buaa.sei.exLmf.metamodel.LDataType;

public class LAttributeImpl extends LStructuralFeatureImpl implements LAttribute{
	
	public LAttributeImpl(int fid,String name,LClassifier container) throws Exception{
		super(fid,name,container);
	}
	@Override
	public LDataType getDataType() {return (LDataType) this.type;}
	@Override
	public void setDataType(LDataType type) throws Exception {
		if(type==null)throw new Exception("Null data type is invalid");
		this.type=type;
	}
	@Override
	public void setType(LClassifier type) throws Exception{
		if((type==null)||!(type instanceof LDataType)){
			throw this.getException("setType(type)", "type", "Attribute's type must be LDataType");
		}
		this.type=(LDataType) type;
	}
}
