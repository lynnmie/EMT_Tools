package cn.edu.buaa.sei.SVI.struct.numeric;

import cn.edu.buaa.sei.SVI.struct.core.expression.Operator;

/**
 * <i>NumericOperator</i> is a Numeric Struct so to be computed by computer.<br>
 * <i>NumericOperator</i> is an Operator whose expression could only be NumericExpression.
 * */
public interface NumericOperator extends CompositeNumericStruct,Operator{
	/**
	 * Return the Expression in which the operator is owned.<br>
	 * The Expression is not children of the operator as a Struct.
	 * */
	public NumericExpression getExpression();
	/**
	 * Set the expression as its parent container.
	 * @exception Exception expr==null
	 * */
	public void setExpression(NumericExpression expr) throws Exception;
}
