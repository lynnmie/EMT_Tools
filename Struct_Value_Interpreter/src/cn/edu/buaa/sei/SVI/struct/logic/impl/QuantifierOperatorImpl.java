package cn.edu.buaa.sei.SVI.struct.logic.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;
import cn.edu.buaa.sei.SVI.struct.logic.DiscourseDomain;
import cn.edu.buaa.sei.SVI.struct.logic.QuantifierOperator;

public abstract class QuantifierOperatorImpl extends LogicOperatorImpl implements QuantifierOperator{
	
	DiscourseDomain domain;
	LogicStruct scope;

	/**
	 * Children: [domain, scope]
	 * */
	protected QuantifierOperatorImpl(DiscourseDomain domain,
			LogicStruct scope,CompositeStruct container) throws Exception {
		super(container);
		
		if(domain==null||scope==null)
			throw new Exception("Null Domain|Scope is invalid");
		
		this.domain=domain;
		this.scope=scope;
		
		this.container.addChildStruct(domain);
		this.container.addChildStruct(scope);
	}

	@Override
	public DiscourseDomain getDomain() {return this.domain;}

	@Override
	public LogicStruct getScope() {return this.scope;}
	
	@Override
	public DiscourseDomain getLeftOperand(){return this.getDomain();}
	@Override
	public LogicStruct getRightOperand(){return this.getScope();}
	@Override
	public void setOperands(DiscourseDomain domain,LogicStruct scope) throws Exception{
		if(domain==null||scope==null)
			throw new Exception("Null operands are invalid");
		
		this.container.removeChildStruct(this.scope);
		this.container.removeChildStruct(this.domain);
		
		this.domain=domain; this.scope=scope;
		
		this.container.addChildStruct(this.domain);
		this.container.addChildStruct(this.scope);
	}
	@Override
	public void setOperands(Struct left,Struct right) throws Exception{
		if(left==null||right==null)
			throw new Exception("Null operands are invalid");
		if(!(left instanceof DiscourseDomain))
			throw new Exception("Left must be DiscourseDomain");
		if(!(right instanceof LogicStruct))
			throw new Exception("Right must be LogicStruct");
		
		DiscourseDomain domain = (DiscourseDomain) left;
		LogicStruct scope = (LogicStruct) right;
		
		this.setOperands(domain, scope);
	}
	@Override
	public int getDimension() {return 2;}

}
