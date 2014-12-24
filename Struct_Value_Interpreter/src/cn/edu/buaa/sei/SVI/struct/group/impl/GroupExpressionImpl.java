package cn.edu.buaa.sei.SVI.struct.group.impl;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;
import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.group.GroupExpression;
import cn.edu.buaa.sei.SVI.struct.group.GroupOperator;

public class GroupExpressionImpl implements GroupExpression{
	
	CompositeStruct container;
	GroupOperator op;
	
	/**
	 * Children: [op]
	 * */
	GroupExpressionImpl(GroupOperator op,CompositeStruct container)throws Exception{
		if(op==null||container==null)
			throw new Exception("Operator|Container should not be Null");
		
		this.op=op;this.container=container;
		this.container.addChildStruct(this.op);
		this.op.setExpression(this);
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
	public GroupOperator getOperator() {return this.op;}

	@Override
	public String toString(){return "("+this.op.toString()+")";}
}
