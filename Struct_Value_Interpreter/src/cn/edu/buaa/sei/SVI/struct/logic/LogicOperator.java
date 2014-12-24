package cn.edu.buaa.sei.SVI.struct.logic;

import cn.edu.buaa.sei.SVI.struct.core.expression.Operator;

/**
 * <i>LogicOperator</i> is an <i>Operator</i> used in Logic Expression.<br>
 * It could be: Conjunction, Disjunction, Negation, Implication, Equivalence, <i>QuantifierOperator</i><br><br>
 * LogicOperator only receives LogicExpression as its owner expression.
 * */
public interface LogicOperator extends CompositeLogicStruct,Operator{
	/**
	 * Return the Expression in which the operator is owned.<br>
	 * The Expression is not children of the operator as a Struct.
	 * */
	public LogicExpression getExpression();
	/**
	 * Set the expression as its parent container.
	 * @exception Exception expr==null
	 * */
	public void setExpression(LogicExpression expr) throws Exception;
}
