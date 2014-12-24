package cn.edu.buaa.sei.SVI.struct.logic.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.logic.LogicExpression;
import cn.edu.buaa.sei.SVI.struct.logic.LogicOperator;

public class LogicExpressionImpl implements LogicExpression{
	
	CompositeStruct container;
	LogicOperator op;
	
	/**
	 * Children: [operator]
	 * */
	public LogicExpressionImpl(LogicOperator op,CompositeStruct container) throws Exception{
		if(op==null||container==null)
			throw new Exception("Null Operator|Container is invalid for Expression");
		this.op=op;
		this.op.setExpression(this);
		
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
	public LogicOperator getOperator() {
		return this.op;
	}
	
	@Override
	public String toString(){
		return "("+this.op.toString()+")";
	}
}
