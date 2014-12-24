package cn.edu.buaa.sei.SVI.struct.core.expression;

import cn.edu.buaa.sei.SVI.struct.core.CompositeStruct;

/**
 * <i>Expression</i> is a composited struct which could be interpreted to compute a value result.<br>
 * 1. Logic Expression: &&, ||, !, -->, <-->, all, any, atLeast, atMost, atLeast...and atMost... <i>{Return Boolean in Interpreter}</i><br>
 * 2. Numberic Expression: +,-,*,/,%
 * 3. Group Expression: Insertion, Union, Difference, Complement, CartesianProduct; Cardinality; Include, Contain, GroupEqual.
 * */
public interface Expression extends CompositeStruct{
	/**
	 * Return the operator of the expression.<br>
	 * Operator is one of the children structs in Expression.
	 * */
	public Operator getOperator();
}
