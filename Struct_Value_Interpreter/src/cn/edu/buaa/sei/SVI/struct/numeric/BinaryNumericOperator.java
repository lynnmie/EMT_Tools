package cn.edu.buaa.sei.SVI.struct.numeric;

import cn.edu.buaa.sei.SVI.struct.core.expression.BinaryOperator;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;

/**
 * <i>BinaryNumericOperator</i> is Binary Numeric Operator.
 * */
public interface BinaryNumericOperator extends NumericOperator,BinaryOperator{
	/**
	 * Return the left operand.
	 * */
	public NumericStruct getLeftOperand();
	/**
	 * Return the right operand.
	 * */
	public NumericStruct getRightOperand();
	/**
	 * Set the both operands in BinaryOperator
	 * @exception Exception left==null||right==null
	 * */
	public void setOperands(NumericStruct left,NumericStruct right) throws Exception;
}
