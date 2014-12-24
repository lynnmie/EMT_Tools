package cn.edu.buaa.sei.SVI.struct.logic;
import cn.edu.buaa.sei.SVI.struct.core.expression.BinaryOperator;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;

/**
 * <i>QuantifierOperator</i> is a <i>LogicOperator</i> used in Quantifier.<br>
 * Its expression could be seen as a Quantifier. {we do not have such a class}.<br>
 * A <i>QuantifierOperator</i> could be: Universal, Existential, AtLeast, AtMost, Range
 * */
public interface QuantifierOperator extends LogicOperator,BinaryOperator{
	/**
	 * Return the domain of the Quantifier.
	 * */
	public DiscourseDomain getDomain();
	/**
	 * Return the scope logic element of the Quantifier.
	 * */
	public LogicStruct getScope();
	/**
	 * Return the left operand.
	 * */
	public DiscourseDomain getLeftOperand();
	/**
	 * Return the right operand.
	 * */
	public LogicStruct getRightOperand();
	/**
	 * Set the both operands in BinaryOperator
	 * @exception Exception left==null||right==null
	 * */
	public void setOperands(DiscourseDomain left,LogicStruct right) throws Exception;
}
