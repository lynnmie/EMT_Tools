package cn.edu.buaa.sei.SVI.struct.group;

import cn.edu.buaa.sei.SVI.struct.core.expression.BinaryOperator;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;

public interface Difference extends GroupOperator,BinaryOperator{
	/**
	 * Return the left operand.
	 * */
	public GroupStruct getLeftOperand();
	/**
	 * Return the right operand.
	 * */
	public GroupStruct getRightOperand();
	/**
	 * Set the both operands in BinaryOperator
	 * @exception Exception left==null||right==null
	 * */
	public void setOperands(GroupStruct left,GroupStruct right) throws Exception;
}
