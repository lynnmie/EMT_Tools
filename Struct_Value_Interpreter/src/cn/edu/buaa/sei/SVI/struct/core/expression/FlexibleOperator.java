package cn.edu.buaa.sei.SVI.struct.core.expression;

import cn.edu.buaa.sei.SVI.struct.core.Struct;

/**
 * FlexibleOperator is Operator with flexible number of operands as its children of CompositeStruct.
 * */
public interface FlexibleOperator extends Operator{
	/**
	 * Return all the operands in operator. Each operand is a Struct so to be interpreted further.
	 * */
	public Struct[] getOperands();
	/**
	 * Set the operands of the operator with any length.
	 * @exception Exception operands==null
	 * @exception Exception operands.contain(null)
	 * */
	public void setOperands(Struct[] operands) throws Exception;
}
