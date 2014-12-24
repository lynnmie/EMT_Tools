package cn.edu.buaa.sei.SVI.struct.numeric;

import cn.edu.buaa.sei.SVI.struct.core.expression.Expression;

/**
 * NumericExpression is the <i>Expression</i> in Numeric Computation and produce Numeric result.
 * */
public interface NumericExpression extends CompositeNumericStruct,Expression{
	/**
	 * Return the operator of the expression.<br>
	 * Operator is one of the children structs in Expression.
	 * */
	public NumericOperator getOperator();
}
