package cn.edu.buaa.sei.SVI.struct.logic;

import cn.edu.buaa.sei.SVI.struct.core.expression.FlexibleOperator;
import cn.edu.buaa.sei.SVI.struct.core.extend.LogicStruct;

/**
 * Conjunction is <i>LogicOperator</i> and FlexibleOperator which could receive any number of operands (LogicStruct)<br>
 * Conjunction only receives LogicStruct as its operand {not all the Struct, such as NumericStruct}.
 * */
public interface Conjunction extends LogicOperator,FlexibleOperator{
	/**
	 * Return all the operands in operator. Each operand is a Struct so to be interpreted further.
	 * */
	public LogicStruct[] getOperands();
	/**
	 * Set the operands of the operator with any length.
	 * @exception Exception operands==null
	 * @exception Exception operands.contain(null)
	 * */
	public void setOperands(LogicStruct[] operands) throws Exception;
}
