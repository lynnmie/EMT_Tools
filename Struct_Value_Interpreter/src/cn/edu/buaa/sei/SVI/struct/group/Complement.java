package cn.edu.buaa.sei.SVI.struct.group;

import cn.edu.buaa.sei.SVI.struct.core.expression.UnaryOperator;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;

public interface Complement extends GroupOperator,UnaryOperator{
	/**
	 * Return the operand of the UnaryOperator.
	 * */
	public GroupStruct getOperand();
	/**
	 * Return the universal domain of the complement.
	 * */
	public GroupStruct getDomain();
	/**
	 * Set the operand of the UnaryOperator.
	 * @exception Exception operand==null
	 * */
	public void setOperand(GroupStruct operand) throws Exception;
}
