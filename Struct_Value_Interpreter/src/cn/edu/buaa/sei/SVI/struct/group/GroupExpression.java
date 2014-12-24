package cn.edu.buaa.sei.SVI.struct.group;

import cn.edu.buaa.sei.SVI.struct.core.expression.Expression;

/**
 * GroupExpression is an Expression used in Group Computation.<br>
 * 1. GroupExpression could return Group: <b>Intersection</b>, <b>Union</b>, 
 * <b>Difference</b>, <b>Complement</b>, <b>CartesianProduct</b><br>
 * 2. GroupExpression could return Number: <b>Cardinality</b><br>
 * 3. GroupExpression could return Boolean: <b>GroupEqual</b>, <b>Contain</b>, <b>Include</b>
 * */
public interface GroupExpression extends CompositeGroupStruct,Expression{
	/**
	 * Return the operator of the expression.
	 * */
	public GroupOperator getOperator();
}
