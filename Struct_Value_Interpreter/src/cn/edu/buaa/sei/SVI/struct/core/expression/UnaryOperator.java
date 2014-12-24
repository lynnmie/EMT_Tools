package cn.edu.buaa.sei.SVI.struct.core.expression;

import cn.edu.buaa.sei.SVI.struct.core.Struct;

/**
 * UnaryOperator is an <i>FixedOperator</i> with only 1 operand as its child.
 * */
public interface UnaryOperator extends FixedOperator{
	/**
	 * Return the operand of the UnaryOperator.
	 * */
	public Struct getOperand();
	/**
	 * Set the operand of the UnaryOperator.
	 * @exception Exception operand==null
	 * */
	public void setOperand(Struct operand) throws Exception;
}
