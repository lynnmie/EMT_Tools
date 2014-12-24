package cn.edu.buaa.sei.SVI.struct.numeric.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericExpression;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericOperator;

public class NumericExpressionImpl implements NumericExpression{
	
	CompositeStruct container;
	NumericOperator op;
	
	NumericExpressionImpl(NumericOperator op,CompositeStruct container) throws Exception {
		if(op==null||container==null)
			throw new Exception("Null Operator|Container is invalid");
		
		this.op=op;this.op.setExpression(this);
		this.container=container;
		
		this.container.addChildStruct(op);
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
	public NumericOperator getOperator() {
		return this.op;
	}

	@Override
	public String toString(){
		return "("+this.op.toString()+")";
	}
	
}
