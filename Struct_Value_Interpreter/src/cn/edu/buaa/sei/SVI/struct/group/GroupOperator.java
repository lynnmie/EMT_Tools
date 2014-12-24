package cn.edu.buaa.sei.SVI.struct.group;

import cn.edu.buaa.sei.SVI.struct.core.expression.Operator;

/**
 * <i>GroupOperator</i> is the <i>Operator</i> used in GroupExpression.
 * */
public interface GroupOperator extends CompositeGroupStruct,Operator{
	/**
	 * Return the Expression in which the operator is owned.<br>
	 * The Expression is not children of the operator as a Struct.
	 * */
	public GroupExpression getExpression();
	/**
	 * Set the expression as its parent container.
	 * @exception Exception expr==null
	 * */
	public void setExpression(GroupExpression expr) throws Exception;
}
