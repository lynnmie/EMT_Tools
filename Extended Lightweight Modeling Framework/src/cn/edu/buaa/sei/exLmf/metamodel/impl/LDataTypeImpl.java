package cn.edu.buaa.sei.exLmf.metamodel.impl;

import cn.edu.buaa.sei.exLmf.metamodel.LDataType;
import cn.edu.buaa.sei.exLmf.metamodel.LPackage;

public abstract class LDataTypeImpl extends LClassifierImpl implements LDataType{
	LDataTypeImpl(String name,LPackage container) throws Exception{
		super(name,container);
	}
}
