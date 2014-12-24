package cn.edu.buaa.sei.SVI.struct.group;

import cn.edu.buaa.sei.SVI.struct.core.Struct;
import cn.edu.buaa.sei.SVI.struct.core.expression.BinaryOperator;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.logic.LogicOperator;

/**
 * Include is a <i>LogicOperator</i> {used in LogicExpression} to present whether a given
 * element {Struct} is included in a group {GroupStruct}, like "x in A" in which x is
 * element and A is Group.
 * */
public interface Include extends LogicOperator,BinaryOperator{
	/**
	 * Return the left operand.
	 * */
	public Struct getLeftOperand();
	/**
	 * Return the right operand.
	 * */
	public GroupStruct getRightOperand();
	/**
	 * Set the both operands in BinaryOperator
	 * @exception Exception left==null||right==null
	 * */
	public void setOperands(Struct left,GroupStruct right) throws Exception;
}
