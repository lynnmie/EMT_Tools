package cn.edu.buaa.sei.SVI.struct.core.expression;

import cn.edu.buaa.sei.SVI.struct.core.Struct;

/**
 * MultipleOperator is a FixedOperator with more than 2 operands which cannot be changed after it is constructed.<br>
 * 
 * */
public interface MultipleOperator extends FixedOperator{
	/**
	 * Return the operands.
	 * */
	public Struct[] getOperands();
	/**
	 * Set a new operands to the operator.
	 * @exception Exception operands==null
	 * @exception Exception operands.length<2
	 * @exception Exception operands.conatins(null)
	 * */
	public void setOperands(Struct[] operands) throws Exception;
}
