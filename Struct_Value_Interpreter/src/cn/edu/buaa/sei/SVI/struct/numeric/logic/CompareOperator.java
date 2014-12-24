package cn.edu.buaa.sei.SVI.struct.numeric.logic;

import cn.edu.buaa.sei.SVI.struct.core.expression.BinaryOperator;
import cn.edu.buaa.sei.SVI.struct.core.extend.NumericStruct;
import cn.edu.buaa.sei.SVI.struct.logic.LogicOperator;

/**
 * <i>CompareOperator</i> is a LogicOperator which returns whether two <b>NumbericStruct</b> compares with each other.<br>
 * <i>CompareOperator</i> includes: > (Bigger), < (Smaller), = (Equal), >= (EBigger), <= (ESmaller).<br>
 * Children: [left,right]
 * */
public interface CompareOperator extends LogicOperator,BinaryOperator{
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
