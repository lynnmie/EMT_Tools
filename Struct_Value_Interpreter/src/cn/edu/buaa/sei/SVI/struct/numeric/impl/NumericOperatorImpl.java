package cn.edu.buaa.sei.SVI.struct.numeric.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.expression.Expression;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericExpression;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericOperator;

public abstract class NumericOperatorImpl implements NumericOperator{
	
	protected NumericExpression expr;
	protected CompositeStruct container;
	
	protected NumericOperatorImpl(CompositeStruct container) throws Exception {
		if(container==null)throw new Exception("Null Container is invalid");
		this.container=container;
	}

	@Override
	public Struct[] getChildrenStructs() {
		return this.container.getChildrenStructs();
	}

	@Override
	public void addChildStruct(Struct child) throws Exception {
		this.container.addChildStruct(child);
	}

	@Override
	public void removeChildStruct(Struct child) throws Exception {
		this.container.removeChildStruct(child);
	}

	@Override
	public boolean containChildStruct(Struct child) {
		return this.container.containChildStruct(child);
	}

	@Override
	public int getChildrenStructSize() {
		return this.container.getChildrenStructSize();
	}

	@Override
	public void setExpression(Expression expr) throws Exception {
		if(expr==null)this.expr=null;
		this.expr=(NumericExpression) expr;
	}

	@Override
	public NumericExpression getExpression() {
		return this.expr;
	}

	@Override
	public void setExpression(NumericExpression expr) throws Exception {
		this.expr=expr;
	}

}
