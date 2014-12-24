package cn.edu.buaa.sei.SVI.struct.logic.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;
import cn.edu.buaa.sei.SVI.struct.logic.Existential;

public class ExistentialImpl extends QuantifierOperatorImpl implements Existential{

	protected ExistentialImpl(DiscourseDomain domain, LogicStruct scope,
			CompositeStruct container) throws Exception {
		super(domain, scope, container);
	}
	
	@Override
	public String toString(){
		return "exist("+domain.toString()+","+scope.toString()+")";
	}
}
