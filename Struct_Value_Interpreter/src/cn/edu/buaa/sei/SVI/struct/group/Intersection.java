package cn.edu.buaa.sei.SVI.struct.group;

import cn.edu.buaa.sei.SVI.struct.core.expression.FlexibleOperator;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;

public interface Intersection extends GroupOperator,FlexibleOperator{
	/**
	 * Return all the operands in operator. Each operand is a Struct so to be interpreted further.
	 * */
	public GroupStruct[] getOperands();
	/**
	 * Set the operands of the operator with any length.
	 * @exception Exception operands==null
	 * @exception Exception operands.contain(null)
	 * */
	public void setOperands(GroupStruct[] operands) throws Exception;
}
