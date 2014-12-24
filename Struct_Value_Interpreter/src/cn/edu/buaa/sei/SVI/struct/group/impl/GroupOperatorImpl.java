package cn.edu.buaa.sei.SVI.struct.group.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.expression.Expression;
import cn.edu.buaa.sei.SVI.struct.group.GroupExpression;
import cn.edu.buaa.sei.SVI.struct.group.GroupOperator;

public abstract class GroupOperatorImpl implements GroupOperator{
	
	protected CompositeStruct container;
	protected GroupExpression expr;
	
	protected GroupOperatorImpl(CompositeStruct container) throws Exception{
		if(container==null)
			throw new Exception("Container should not be Null");
		
		this.container=container;
	}

	@Override
	public Struct[] getChildrenStructs() {return this.container.getChildrenStructs();}
	@Override
	public void addChildStruct(Struct child) throws Exception {this.container.addChildStruct(child);}
	@Override
	public void removeChildStruct(Struct child) throws Exception {this.container.removeChildStruct(child);}
	@Override
	public boolean containChildStruct(Struct child) {return this.container.containChildStruct(child);}
	@Override
	public int getChildrenStructSize() {return this.container.getChildrenStructSize();}

	@Override
	public void setExpression(Expression expr) throws Exception {
		if(expr==null)this.expr=null;
		else this.expr=(GroupExpression) expr;
	}
	@Override
	public GroupExpression getExpression() {return this.expr;}
	@Override
	public void setExpression(GroupExpression expr) throws Exception {
		this.expr=expr;
	}

}
