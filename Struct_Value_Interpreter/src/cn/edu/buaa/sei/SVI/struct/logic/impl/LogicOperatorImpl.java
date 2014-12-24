package cn.edu.buaa.sei.SVI.struct.logic.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.expression.Expression;
import cn.edu.buaa.sei.SVI.struct.logic.LogicExpression;
import cn.edu.buaa.sei.SVI.struct.logic.LogicOperator;

public abstract class LogicOperatorImpl implements LogicOperator{
	protected LogicExpression expr;
	protected CompositeStruct container;
	
	protected LogicOperatorImpl(CompositeStruct container) throws Exception{
		if(container==null)throw new Exception("Null Container is invalid to constructing LogicOperator");
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
		this.expr=(LogicExpression) expr;
	}
	@Override
	public LogicExpression getExpression() {
		return this.expr;
	}
	@Override
	public void setExpression(LogicExpression expr) throws Exception {
		this.expr=expr;
	}

}
