package cn.edu.buaa.sei.SVI.struct.core.expression;

/**
 * <i>FixedOperator</i> is an Operator with fixed number of operands.
 * */
public interface FixedOperator extends Operator{
	/**
	 * Return the number of children structs in operator.
	 * */
	public int getDimension();
}
