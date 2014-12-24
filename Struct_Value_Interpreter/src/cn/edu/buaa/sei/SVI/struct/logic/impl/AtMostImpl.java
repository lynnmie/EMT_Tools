package cn.edu.buaa.sei.SVI.struct.logic.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.logic.AtMost;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;

public class AtMostImpl extends QuantifierOperatorImpl implements AtMost{
	
	int upper=AtMost.UNBOUNDED;
	
	/**
	 * Default: upper = UNBOUNDED
	 * */
	protected AtMostImpl(DiscourseDomain domain, LogicStruct scope,
			CompositeStruct container) throws Exception {
		super(domain, scope, container);
	}

	@Override
	public int getUpperBound() {return this.upper;}

	@Override
	public void setUpperBound(int upper) throws Exception {
		if(upper<0&&upper!=AtMost.UNBOUNDED)
			throw new Exception("Invalid Upper Bound: "+upper);
		this.upper=upper;
	}
	
	@Override
	public String toString(){
		return "AtMost("+this.upper+","+domain.toString()+","+scope.toString()+")";
	}

}
