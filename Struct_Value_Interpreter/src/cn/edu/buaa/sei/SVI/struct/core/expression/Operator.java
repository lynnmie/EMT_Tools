package cn.edu.buaa.sei.SVI.struct.core.expression;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;

/**
 * <i>Operator</i> is the component in <i>Expression</i>. The children of Operator is its operands.<br>
 * 1. Number Operator includes: +,-,*,/,% {return Number}<br>
 * 2. Logic Operator includes: &&,||,!,-->,<-->,all,any {return Boolean}<br>
 * 3. Group Operator includes: insert,union,difference,complement,CartesianProduct {return Set}; Cardility {return number}; 
 * */
public interface Operator extends CompositeStruct{
	/**
	 * Return the Expression in which the operator is owned.<br>
	 * The Expression is not children of the operator as a Struct.
	 * */
	public Expression getExpression();
	/**
	 * Set the expression as its parent container.
	 * @exception Exception expr==null
	 * */
	public void setExpression(Expression expr) throws Exception;
}
