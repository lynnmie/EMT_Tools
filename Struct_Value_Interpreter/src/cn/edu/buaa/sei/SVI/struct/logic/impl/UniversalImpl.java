package cn.edu.buaa.sei.SVI.struct.logic.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;
import cn.edu.buaa.sei.SVI.struct.logic.Universal;

public class UniversalImpl extends QuantifierOperatorImpl implements Universal{

	protected UniversalImpl(DiscourseDomain domain, LogicStruct scope,
			CompositeStruct container) throws Exception {
		super(domain, scope, container);
	}

	@Override
	public String toString(){
		return "all("+this.domain.toString()+","+this.scope.toString()+")";
	}
}
