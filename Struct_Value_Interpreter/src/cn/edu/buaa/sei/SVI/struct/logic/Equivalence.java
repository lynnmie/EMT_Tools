package cn.edu.buaa.sei.SVI.struct.logic;

import cn.edu.buaa.sei.SVI.struct.core.expression.BinaryOperator;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;

/**
 * Equivalence is both <i>LogicOperator</i> and <i>BinaryOperator</i>.<br>
 * Equivalence only receives LogicStruct as its operands and children.
 * */
public interface Equivalence extends LogicOperator,BinaryOperator{
	/**
	 * Return the left operand.
	 * */
	public LogicStruct getLeftOperand();
	/**
	 * Return the right operand.
	 * */
	public LogicStruct getRightOperand();
	/**
	 * Set the both operands in BinaryOperator
	 * @exception Exception left==null||right==null
	 * */
	public void setOperands(LogicStruct left,LogicStruct right) throws Exception;
}
