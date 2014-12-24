package cn.edu.buaa.sei.SVI.struct.logic;

import cn.edu.buaa.sei.SVI.struct.core.expression.Expression;

/**
 * LogicExpression is an <i>Expression</i> whose operator is <i>LogicOperator</i>.<br>
 * LogicExpression is also a <i>LogicStruct</i> so that could be computed for its Boolean result.
 * */
public interface LogicExpression extends CompositeLogicStruct,Expression{
	/**
	 * Return the operator of the expression.
	 * */
	public LogicOperator getOperator();
}
