package cn.edu.buaa.sei.SVI.struct.logic;

import cn.edu.buaa.sei.SVI.struct.core.expression.UnaryOperator;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;

/**
 * Negation is a <i>LogicOperator</i> and also an <i>UnaryOperator</i>.<br>
 * Negation only receives <i>LogicStruct</i> as its operand and child.
 * */
public interface Negation extends LogicOperator,UnaryOperator{
	/**
	 * Return the operand of the UnaryOperator.
	 * */
	public LogicStruct getOperand();
	/**
	 * Set the operand of the UnaryOperator.
	 * @exception Exception operand==null
	 * */
	public void setOperand(LogicStruct operand) throws Exception;
}
