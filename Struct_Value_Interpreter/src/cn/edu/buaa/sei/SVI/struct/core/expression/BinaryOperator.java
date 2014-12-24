package cn.edu.buaa.sei.SVI.struct.core.expression;

import cn.edu.buaa.sei.SVI.struct.core.Struct;

/**
 * BinaryOperator is a FixedOperator with exactly 2 operands {left + right}
 * */
public interface BinaryOperator extends FixedOperator{
	/**
	 * Return the left operand.
	 * */
	public Struct getLeftOperand();
	/**
	 * Return the right operand.
	 * */
	public Struct getRightOperand();
	/**
	 * Set the both operands in BinaryOperator
	 * @exception Exception left==null||right==null
	 * */
	public void setOperands(Struct left,Struct right) throws Exception;
}
