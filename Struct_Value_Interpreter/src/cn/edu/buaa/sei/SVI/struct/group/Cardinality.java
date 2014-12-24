package cn.edu.buaa.sei.SVI.struct.group;

import cn.edu.buaa.sei.SVI.struct.core.expression.UnaryOperator;
import cn.edu.buaa.sei.SVI.struct.core.extend.GroupStruct;
import cn.edu.buaa.sei.SVI.struct.numeric.NumericOperator;

/**
 * Cardinality is a <i>NumericOperator</i> which returns the number of a given Group.
 * */
public interface Cardinality extends UnaryOperator,NumericOperator{
	/**
	 * Return the operand of the UnaryOperator.
	 * */
	public GroupStruct getOperand();
	/**
	 * Set the operand of the UnaryOperator.
	 * @exception Exception operand==null
	 * */
	public void setOperand(GroupStruct operand) throws Exception;
}
